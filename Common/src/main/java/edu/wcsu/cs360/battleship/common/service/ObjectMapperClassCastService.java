package edu.wcsu.cs360.battleship.common.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ObjectMapperClassCastService implements IClassCastService {
	
	private Log log = LogFactory.getLog(this.getClass());
	private ObjectMapper objectMapper;
	
	public ObjectMapperClassCastService() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	public ObjectMapperClassCastService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	/**
	 * Uses an {@link ObjectMapper} to cast a JSON string to a {@link Class} instance
	 *
	 * @param object Object to cast
	 * @param clazz  Class to cast object to
	 * @param <T>    Casted Class
	 * @return Casted Class instance
	 */
	@Override
	public <T> T cast(Object object, Class<T> clazz) {
		if (object == null)
			return null;
		return objectMapper.convertValue(object, clazz);
	}
}
