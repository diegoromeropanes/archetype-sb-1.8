package ${package}.rest;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ${package}.annotation.FeignFormEncodecConfig;
import ${package}.annotation.TimeDetector;
import ${package}.response.TokenResponse;

@FeignClient(name = "tokenClient", value = "tokenClient", url = "${token.service.host}", configuration = FeignFormEncodecConfig.class)
public interface TokenClient {
	
	@TimeDetector
	@PostMapping(value = "/integracionti/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<TokenResponse> postToken(@RequestBody Map<String, ?> body);
}
