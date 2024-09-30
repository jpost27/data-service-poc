package com.jp.dataservice.config;

import com.jp.dataservice.framework.DataRestSwaggerV3OpenAPIAppender;
import com.jp.dataservice.framework.PersistenceMapping;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DocumentationConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sports Data API")
                        .description("Data service for Sports Data API")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    @Bean
    public DataRestSwaggerV3OpenAPIAppender dataRestSwaggerAPIAppender(
            PersistenceMapping persistenceMapping, OpenAPI openAPI) {
        return new DataRestSwaggerV3OpenAPIAppender(persistenceMapping, openAPI);
    }
}
