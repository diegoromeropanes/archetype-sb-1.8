package ${package}.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachmentRequest implements Serializable{
	
	private static final long serialVersionUID = -2970191135741408811L;
	
	@JsonPropertyDescription("Archivo Adjunto codificado en Base64")
	@JsonProperty(value = "base64", required = true)
	private String base64;
	
	@JsonPropertyDescription("Nombre del Archivo Adjunto")
	@JsonProperty(value = "name", required = true)
	private String name;
	
	@JsonPropertyDescription("Extension del Archivo Adjunto")
	@JsonProperty(value = "extension", required = true)
	private String extension;
}
