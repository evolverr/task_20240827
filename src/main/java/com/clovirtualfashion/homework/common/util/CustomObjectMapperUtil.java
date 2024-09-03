package com.clovirtualfashion.homework.common.util;

import com.clovirtualfashion.homework.common.exception.FailConvertWithObjectMapperException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomObjectMapperUtil {

  private final ObjectMapper objectMapper;

  public String toString(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new FailConvertWithObjectMapperException(e);
    }
  }

  public <T> T toType(String value, Class<T> clazz) {
    try {
      return objectMapper.readValue(value, clazz);
    } catch (JsonProcessingException e) {
      throw new FailConvertWithObjectMapperException(e);
    }
  }

  public <T> List<T> toList(String value, Class<T> clazz) {
    try {
      return objectMapper.readValue(value, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    } catch (JsonProcessingException e) {
      throw new FailConvertWithObjectMapperException(e);
    }
  }

}
