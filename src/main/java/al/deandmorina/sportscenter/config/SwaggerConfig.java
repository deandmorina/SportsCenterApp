package al.deandmorina.sportscenter.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "APIs",
                "Documentation",
                "1.0.0",
                "Terms Of Service",
                new Contact("Deand Morina", "https://github.com/deandmorina", "morinadeand1@gmail.com"),
                "Licence",
                "URL",
                Collections.emptyList()
        );
    }
}
