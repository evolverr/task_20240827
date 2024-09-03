package com.clovirtualfashion.homework.common.value.pagination;

import com.clovirtualfashion.homework.common.value.pagination.PageList.PageListBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

@JsonDeserialize(builder = PageListBuilder.class)
public record PageList<T>(List<T> list, Pagination pagination) {

  public static <T> PageListBuilder<T> builder() {
    return new PageListBuilder<>();
  }

  @JsonPOJOBuilder(withPrefix = "")
  public static final class PageListBuilder<T> {

    private List<T> list;
    private Pagination pagination;

    public PageListBuilder<T> list(List<T> list) {
      this.list = list;
      return this;
    }

    public PageListBuilder<T> pagination(Pagination pagination) {
      this.pagination = pagination;
      return this;
    }

    public PageList<T> build() {
      return new PageList<>(this.list, this.pagination);
    }
  }
}
