package ${package}.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class ReceiveDTO {
	
	private String name;
	private Integer rut;
	private String dv;
	private String message;
}