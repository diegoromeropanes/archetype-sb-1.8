package ${package}.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GenericResponse{
	
	@JsonProperty("status")
	private int status;
	
	@JsonProperty("message")
	private String message;
}
