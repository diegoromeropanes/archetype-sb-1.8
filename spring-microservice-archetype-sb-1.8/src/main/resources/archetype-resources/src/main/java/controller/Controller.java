package ${package}.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package}.dto.UserDTO;
import ${package}.request.UserRequest;
import ${package}.response.GenericResponse;
import ${package}.service.IService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/archetype")
public class Controller {
	
	@Autowired
	IService service;
	
	@GetMapping(value = "/search-by-name/{name}")
	public ResponseEntity<List<UserDTO>> controllerSearchByName(@PathVariable(required = true) String name) {
		log.info("Controller::controllerSearchByName");
		return service.serviceSearchByName(name);
	}
	
	@PostMapping(value = "/send-amqp")
	public ResponseEntity<GenericResponse> controllerSendAmqp(@RequestBody(required = true) UserRequest request){
		log.info("Controller::controllerSendAmqp");
		return service.serviceSendAmqp(request);
	}
	
	@PutMapping(value = "/update-by-rut")
	public ResponseEntity<GenericResponse> controllerUpdateByRut(@RequestBody(required = true) UserRequest request){
		log.info("Controller::controllerUpdateByRut");
		return service.serviceUpdateByRut(request);
	}
	
	@DeleteMapping(value = "/delete-by-rut/{rut}")
	public ResponseEntity<GenericResponse> controllerDeleteByRut(@PathVariable(required = true) Integer rut){
		log.info("Controller::controllerDeleteByRut");
		return service.serviceDeleteByRut(rut);
	}
	
	@GetMapping(value = "/send-email/{email}")
	public ResponseEntity<GenericResponse> controllerSendEmail(@PathVariable(required = true) String email){
		log.info("Controller::controllerSendEmail");
		return service.serviceSendEmail(email);
	}
}
