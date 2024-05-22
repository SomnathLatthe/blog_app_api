package com.somnath.blogappapis.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

   /* public static final String AUTHORIZATION_HEADER="Authorization";

    private ApiKey apiKey()
    {
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    private List<SecurityContext> securityContexts()
    {
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }

    private List<SecurityReference> securityReferences()
    {
        AuthorizationScope scopes=new AuthorizationScope("global","AccessEverything");
        return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[]{scopes}));
    }*/

    /*@Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getInfo());
    }*/
    @Bean
    public GroupedOpenApi controllerApi() {
        return GroupedOpenApi.builder()
                .group("controller-api")
                .packagesToScan("com.somnath.blogappapis") // Specify the package to scan
                .build();
    }


    @Bean
    public OpenAPI customOpenAPI() {
        /*return new OpenAPI().components(new Components()).info(new Info().title("Spring MVC REST API")
                .contact(new Contact("somanth","soma.coo.in","soma@gmail.com").name("Rasika Kaluwalgoda")).version("1.0.0"));*/

        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("titleI")
                        .version("1.0.0"));


    }



    /*private ApiInfo getInfo() {

        //return new ApiInfo("Blogging Application: Backend Course","This project is developed by Durgesh !","1.0","Terms of services","Somnath","License of APIs","API license url");
        return new ApiInfoBuilder().title("NAME OF SERVICE")
                .description("API Endpoint Decoration")
                .contact(new Contact("Dev-Team", "https://www.dev-team.com/", "dev-team@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }*/


}
