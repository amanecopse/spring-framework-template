package org.example.global.config.mvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.example.global.util.Constant;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springdoc.webmvc.core.configuration.SpringDocWebMvcConfiguration;
import org.springdoc.webmvc.ui.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API 명세서", description = "API 명세서", version = "v1"))
@EnableWebMvc
@ComponentScan(basePackages = {"org.example"})
@Import({SpringDocConfiguration.class,
        SpringDocWebMvcConfiguration.class,
        SwaggerConfig.class,
        SwaggerUiConfigProperties.class,
        SwaggerUiOAuthProperties.class,
        JacksonAutoConfiguration.class,
        SpringDocConfigProperties.class})
public class OpenApiConfig {
    @Autowired
    Environment env;
    @Bean
    OpenAPI openAPI(){
        String schemeName = "Authorization";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(schemeName);
        Components components = new Components();
        components.addSecuritySchemes(
                schemeName, new SecurityScheme()
                        .name(schemeName)
                        .type(Type.HTTP)
                        .scheme(Constant.BEARER_TOKEN_PREFIX)
                        .bearerFormat("JWT")
        );
        return new OpenAPI()
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}