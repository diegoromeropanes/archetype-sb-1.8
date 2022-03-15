package ${package}.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ${package}.amqp.sender.ISenderAmqp;
import ${package}.dto.UserDTO;
import ${package}.entity.ArchetypeEntity;
import ${package}.repository.IRepository;
import ${package}.request.AttachmentRequest;
import ${package}.request.CorreoRequest;
import ${package}.request.UserRequest;
import ${package}.response.GenericResponse;
import ${package}.response.TokenResponse;
import ${package}.rest.CorreoClient;
import ${package}.rest.TokenClient;
import ${package}.service.IService;
import ${package}.util.Utils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceImpl implements IService {
	
	@Autowired
	IRepository repository;
	
	@Autowired
	ISenderAmqp senderAmqp;
	
	@Autowired
	CorreoClient correoClient;
	
	@Autowired
	TokenClient tokenClient;
	
	@Value("${rabbitmq.in.queue}")
	private String queue;
		
	@Value("${token.service.clientid}")
	private String clientId;
	
	@Value("${token.service.clientsecret}")
	private String clientSecret;
	
	private static final String FROM = "no-responder@sb.cl";
	private static final String SUBJECT = "Prueba Consumo API Correo";
	private static final String MESSAGE = "<h1>Esto es una prueba de la API</h1>";
	private static final String CLIENT_ID_KEY = "client_id";
	private static final String CLIENTE_SECRET_KEY = "client_secret";
	private static final String GRANT_TYPE_KEY = "grant_type";
	private static final String GRANT_TYPE_VALUE = "client_credentials";
	
	public ResponseEntity<List<UserDTO>> serviceSearchByName(String name){
		log.info("Service::Impl::serviceSearchByName");
		
		List<UserDTO> response = new ArrayList<>();
		
		try {
			List<ArchetypeEntity> listAe = repository.repositorySearchByName(name);
						
			if(Objects.nonNull(listAe) && !listAe.isEmpty()) {
				for(ArchetypeEntity ae : listAe) {
					UserDTO user = new UserDTO();
					
					user.setCreationDate(ae.getCreationDate());
					user.setDv(ae.getDv());
					user.setMessage(ae.getMessage());
					user.setName(ae.getName());
					user.setRut(ae.getRut());
					
					response.add(user);
				}
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else if(Objects.isNull(listAe)) {
				log.info("Lista Null");
				
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}else{
				log.info("Lista vacía");
				
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GenericResponse> serviceSendAmqp(UserRequest request) {
		log.info("Service::Impl::serviceSendAmqp");
		
		GenericResponse response = new GenericResponse();
		
		try {
			senderAmqp.sendMessage(queue, Utils.sampleObjectToJSONString(request));
			
			response.setMessage(HttpStatus.OK.toString());
			response.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GenericResponse> serviceUpdateByRut(UserRequest request) {
		log.info("Service::Impl::serviceUpdateByRut");
		
		GenericResponse response = new GenericResponse();
		
		try {
			int count = repository.repositoryUpdateByRut(request.getName(), request.getMessage(), request.getRut(), request.getDv());
			
			if(count>0) {
				response.setMessage(HttpStatus.OK.toString());
				response.setStatus(HttpStatus.OK.value());
				
				return new ResponseEntity<>(response, HttpStatus.OK);
			}else {
				response.setMessage(HttpStatus.NO_CONTENT.toString());
				response.setStatus(HttpStatus.NO_CONTENT.value());
				
				return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
			}			
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GenericResponse> serviceDeleteByRut(Integer rut) {
		log.info("Service::Impl::serviceUpdateByRut");
		
		GenericResponse response = new GenericResponse();
		
		try {
			repository.repositoryDeleteByRut(rut);
			
			response.setMessage(HttpStatus.OK.toString());
			response.setStatus(HttpStatus.OK.value());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<GenericResponse> serviceSendEmail(String email) {
		log.info("Service::Impl::serviceSendEmail");
		
		GenericResponse response = new GenericResponse();
		
		try {
			TokenResponse tokenResponse = generaToken();
						
			if(Objects.nonNull(tokenResponse)) {
			
				CorreoRequest request = new CorreoRequest();
				
				request.setFrom(FROM);
				request.setTo(email);
				request.setCc("");
				request.setCco("");
				request.setSubject(SUBJECT);
				request.setMessage(MESSAGE);
				
				List<AttachmentRequest> listAttachment = new ArrayList<>();
				
				request.setAttachments(listAttachment);
				String token = tokenResponse.getTokenType() + " " + tokenResponse.getAccessToken();
				
				ResponseEntity<GenericResponse> sendEmail = correoClient.postEnvioRelay(token, request);
				
				if(Objects.nonNull(sendEmail)) {
					response = sendEmail.getBody();
					
					return new ResponseEntity<>(response, sendEmail.getStatusCode());
				}else{
					return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else {				
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private TokenResponse generaToken() {
		log.info("Service::Impl::generaToken");
				
		try {
			Map<String, String> map = new HashMap<>();
			
			map.put(GRANT_TYPE_KEY, GRANT_TYPE_VALUE);
			map.put(CLIENT_ID_KEY, clientId);
			map.put(CLIENTE_SECRET_KEY, clientSecret);

			ResponseEntity<TokenResponse> tokenResponse = tokenClient.postToken(map);
			
			if(Objects.nonNull(tokenResponse) && tokenResponse.getStatusCode().value() == 200) {
				return tokenResponse.getBody();
			}else {
				return null;
			}
		}catch(Exception e) {
			log.error("Exception: {}", e.getMessage());
			
			return null;
		}
	}
}
