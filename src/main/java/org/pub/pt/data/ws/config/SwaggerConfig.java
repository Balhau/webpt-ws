package org.pub.pt.data.ws.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    @Value("${swagger.title}")
    private String swaggerTitle;
    @Value("${swagger.description}")
    private String swaggerDescription;
    @Value("${swagger.contact.name}")
    private String swaggerContactName;
    @Value("${swagger.contact.url}")
    private String swaggerContactURL;
    @Value("${swagger.contact.email}")
    private String swaggerContactEmail;
    @Value("${swagger.license}")
    private String swaggerLicense;
    @Value("${swagger.license.url}")
    private String swaggerLicenseURL;
    @Value("${app.version}")
    private String appVersion;


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo());

    }

    private ApiInfo apiEndPointsInfo() {

        return new ApiInfoBuilder().title(swaggerTitle)
                .description(swaggerDescription)
                .contact(new Contact(swaggerContactName, swaggerContactURL, swaggerContactEmail))
                .license(swaggerLicense)
                .licenseUrl(swaggerLicenseURL)
                .version(appVersion)
                .build();

    }

}
