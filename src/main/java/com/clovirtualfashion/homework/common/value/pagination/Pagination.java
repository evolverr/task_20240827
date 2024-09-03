package com.clovirtualfashion.homework.common.value.pagination;

import lombok.Builder;

@Builder
public record Pagination(
  int page,
  int pageSize,
  Long totalPages,
  Long totalCount
) {

  public static Pagination from(int page, int pageSize, Long totalCount) {
    return Pagination.builder()
        .page(page)
        .pageSize(pageSize)
        .totalPages(totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1)
        .totalCount(totalCount)
        .build();
  }

}
