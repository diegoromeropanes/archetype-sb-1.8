package ${package}.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="ARCHETYPE_SB",schema = "RRHH")
public class ArchetypeEntity {
	
	@Column(name="name")
	private String name;
	
	@Id
	@Column(name="rut")
	private Integer rut;
	
	@Column(name="dv")
	private String dv;
	
	@Column(name="message")
	private String message;
	
	@Column(name="creation_date")
	private Date creationDate;
}
