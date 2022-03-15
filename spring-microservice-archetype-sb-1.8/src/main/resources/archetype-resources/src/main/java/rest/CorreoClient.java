package ${package}.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import ${package}.annotation.FeignConfig;
import ${package}.annotation.TimeDetector;
import ${package}.request.CorreoRequest;
import ${package}.response.GenericResponse;

@FeignClient(name = "correoClient", value = "correoClient", url = "${correo.service.host}", configuration = FeignConfig.class)
public interface CorreoClient {
	
	@TimeDetector
	@PostMapping(value = "/envio-relay")
	ResponseEntity<GenericResponse> postEnvioRelay(@RequestHeader(value = "Authorization", required = true) String token, @RequestBody(required = true) CorreoRequest request);
}
