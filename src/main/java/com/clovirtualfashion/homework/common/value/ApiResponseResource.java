package com.clovirtualfashion.homework.common.value;

import com.clovirtualfashion.homework.common.value.ApiResponseResource.ApiResponseResourceBuilder;
import com.clovirtualfashion.homework.common.value.pagination.Pagination;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@JsonDeserialize(builder = ApiResponseResourceBuilder.class)
public class ApiResponseResource<T> {

  private final T data;
  private final Map<String, Object> meta;
  private final StatusResult status;

  public static ApiResponseResource<?> error(StatusResult status) {
    return new ApiResponseResource<>(null, null, status);
  }

  private ApiResponseResource(T data, Map<String, Object> meta, StatusResult status) {
    this.data = data;
    this.meta = meta;
    this.status = status;
  }

  public static <T> ApiResponseResourceBuilder<T> builder() {
    return new ApiResponseResourceBuilder<>();
  }

  @JsonPOJOBuilder(withPrefix = "")
  @ToString
  public static final class ApiResponseResourceBuilder<T> {
    private T data;
    private Map<String, Object> meta;
    private StatusResult result;

    ApiResponseResourceBuilder() {
      this.meta = new HashMap<>();
      this.result = StatusResult.successResult();
    }

    public ApiResponseResourceBuilder<T> data(T data) {
      this.data = data;
      return this;
    }

    public ApiResponseResourceBuilder<T> meta(Map<String, Object> meta) {
      if (meta != null) {
        this.meta.putAll(meta);
      }
      return this;
    }

    public ApiResponseResourceBuilder<T> pagination(Pagination pagination) {
      this.meta.put("pagination", pagination);
      return this;
    }

    public ApiResponseResourceBuilder<T> addMeta(String key, Object value) {
      this.meta.put(key, value);
      return this;
    }

    public ApiResponseResourceBuilder<T> result(StatusResult result) {
      this.result = result;
      return this;
    }

    public ApiResponseResource<T> build() {
      return new ApiResponseResource<>(this.data, this.meta, this.result);
    }

  }
}