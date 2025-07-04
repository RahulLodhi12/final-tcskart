//package com.tcs.product.config;
//
//import io.swagger.*;
//
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
////@Configuration
////@SecurityScheme(
////    name = "bearerAuth",
////    type = SecuritySchemeType.HTTP,
////    bearerFormat = "JWT",
////    scheme = "bearer"
////)
////public class OpenApiConfig {
////
////    @Bean
////    public OpenAPI customOpenAPI() {
////        return new OpenAPI()
////            .info(new Info().title("Your API Title").version("1.0"))
////            .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("bearerAuth"));
////    }
////}
//
//
//@Configuration
//public class OpenApiConfig {
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//            .components(new Components()
//                .addSecuritySchemes("bearerAuth", 
//                    new SecurityScheme()
//                        .name("Authorization")
//                        .type(SecurityScheme.Type.HTTP)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")))
//            .info(new Info().title("Product Service API").version("1.0"));
//    }
//}

package com.tcs.cart.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        final String schemeName = "bearerAuth";
        return new OpenAPI()
            .info(new Info().title("Cart Service API").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList(schemeName))
            .components(new Components().addSecuritySchemes(schemeName,
                new SecurityScheme()
                    .name(schemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
    }
}







