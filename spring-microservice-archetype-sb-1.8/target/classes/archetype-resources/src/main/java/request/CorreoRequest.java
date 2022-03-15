package ${package}.request;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CorreoRequest implements Serializable{
	
	private static final long serialVersionUID = -2970191135741408811L;
	
	@NonNull
	@JsonPropertyDescription("Remitente")
	@JsonProperty(value="from", required = true)
	private String from;
	
	@NonNull
	@JsonPropertyDescription("Lista Destinatarios, separadas por ','")
	@JsonProperty(value="to", required = true)
	private String to;
	
	@JsonPropertyDescription("Lista Con Copia, separadas por ','")
	@JsonProperty(value="cc", required = true)
	private String cc;
	
	@JsonPropertyDescription("Lista Con Copia Oculta, separadas por ','")
	@JsonProperty(value="cco", required = true)
	private String cco;
	
	@NonNull
	@JsonPropertyDescription("Asunto")
	@JsonProperty(value="subject", required = true)
	private String subject;
	
	@NonNull
	@JsonPropertyDescription("Mensaje, en texto plano o HTML")
	@JsonProperty(value="message", required = true)
	private String message;
	
	@JsonPropertyDescription("Lista de Archivos Adjuntos (Opcional)")
	@JsonProperty(value="attachments", required = false)
	private List<AttachmentRequest> attachments;
}
