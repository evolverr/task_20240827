package com.clovirtualfashion.homework.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
        .version("v1.0") //버전
        .title("Employee Contact API") //이름
        .description("직원 연락처 정보 서비스 API"); //설명

    return new OpenAPI()
        .components(new Components())
        .info(info);
  }

}
