package com.clovirtualfashion.homework.common.value;

import io.swagger.v3.oas.annotations.media.Schema;

public record PageVo(

    @Schema(description = "page 번호", example = "1", defaultValue = "1")
    int page,

    @Schema(description = "pageSize", example = "10", defaultValue = "10")
    int pageSize
) {
}
