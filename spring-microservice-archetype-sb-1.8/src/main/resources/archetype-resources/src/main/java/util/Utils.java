package ${package}.util;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils implements Serializable {
	
	private static final long serialVersionUID = 375209610708786798L;
	
	private static ObjectMapper objectMapper;
	
	public static String sacaCaractereRut(String rut) {

		rut = rut.toUpperCase();
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");
		
		return rut.substring(0, rut.length() - 1);
		
	}
	
	public static final String sampleObjectToJSONString(Object input) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(input);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		
		return jsonString;
	}
	
	public static final Object jsonStringToObject(Class<?> type, String value) {
		try {
			Reader reader = new StringReader(value);	
			return objectMapper.readValue(reader, type);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertJsonToHashMap(String json) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> hash = null;
		try {
			hash = mapper.readValue(json, HashMap.class);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return hash;
	}
	
}