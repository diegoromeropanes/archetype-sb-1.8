package ${package}.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserRequest {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("rut")
	private Integer rut;
	
	@JsonProperty("dv")
	private String dv;
	
	@JsonProperty("message")
	private String message;
}