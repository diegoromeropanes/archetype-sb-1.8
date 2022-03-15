package ${package}.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("rut")
	private Integer rut;
	
	@JsonProperty("dv")
	private String dv;
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty("creation_date")
	private Date creationDate;
}
