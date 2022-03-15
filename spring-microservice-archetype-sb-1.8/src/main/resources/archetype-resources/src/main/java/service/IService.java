package ${package}.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import ${package}.dto.UserDTO;
import ${package}.request.UserRequest;
import ${package}.response.GenericResponse;

public interface IService {
	
	ResponseEntity<List<UserDTO>> serviceSearchByName(String name);
	ResponseEntity<GenericResponse> serviceSendAmqp(UserRequest request);
	ResponseEntity<GenericResponse> serviceUpdateByRut(UserRequest request);
	ResponseEntity<GenericResponse> serviceDeleteByRut(Integer rut);
	ResponseEntity<GenericResponse> serviceSendEmail(String email);
}