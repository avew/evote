package com.kyora.studio.vote.config;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/**
 * Created by avew on 12/26/17.
 */
@SuppressWarnings("SpellCheckingInspection")
@Configuration
@ConditionalOnClass({ApiInfo.class, BeanValidatorPluginsConfiguration.class})
@EnableSwagger2
@EnableWebMvc
@Import(BeanValidatorPluginsConfiguration.class)
@Profile({ApplicationConstant.SPRING_PROFILE_SWAGGER, ApplicationConstant.SPRING_PROFILE_DEVELOPMENT})
@Slf4j
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ResourceHttpMessageConverter());
    }

    @Bean
    public Docket apiUser() {
        return createDocket("user", "com.kyora.studio.vote.web.rest.user");
    }

    @Bean
    public Docket apiUpload() {
        return createDocket("upload", "com.kyora.studio.vote.web.rest.upload");
    }

    @Bean
    public Docket apiCandidate() {
        return createDocket("candidate", "com.kyora.studio.vote.web.rest.candidate");
    }

//    @Bean
//    public Docket apiOauth() throws IOException {
//        String host = "localhost:8092";
//        String[] protocols = new String[]{"http", "https"};
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("oauth")
//                .host(host)
//                .protocols(new HashSet<>(Arrays.asList(protocols)))
//                .forCodeGeneration(true)
//                .directModelSubstitute(ByteBuffer.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .select()
//                .paths(regex("/oauth" + ".*"))
//                .build()
//                .securitySchemes(Collections.singletonList(securitySchema()))
//                .securityContexts(Collections.singletonList(securityContext()));
//    }


    private Docket createDocket(String s, String s2) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(s)
                .select()
                .apis(RequestHandlerSelectors.basePackage(s2))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .directModelSubstitute(ByteBuffer.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

//    @Bean
//    public Docket apiManagement(@Value("${spring.application.name}") String appName,
//                                @Value("${server.servlet.context-path}") String managementContextPath,
//                                @Value("${evote.application.version}") String appVersion) {
//
//        String host = "localhost:8092";
//        String[] protocols = new String[]{"http", "https"};
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfo(appName + " management API", "Management endpoints documentation",
//                        appVersion, "", ApiInfo.DEFAULT_CONTACT, "", "", new ArrayList<>()))
//                .groupName("management")
//                .host(host)
//                .protocols(new HashSet<>(Arrays.asList(protocols)))
//                .forCodeGeneration(true)
//                .directModelSubstitute(ByteBuffer.class, String.class)
//                .genericModelSubstitutes(ResponseEntity.class)
//                .select()
//                .paths(regex(managementContextPath + ".*"))
//                .build()
//                .securitySchemes(Collections.singletonList(securitySchema()))
//                .securityContexts(Collections.singletonList(securityContext()));
//    }
//
//
//    @Bean
//    public springfox.documentation.swagger.web.SecurityConfiguration security() {
//        return new springfox.documentation.swagger.web.SecurityConfiguration(
//                "test-app-client-id",
//                "test-app-client-secret",
//                "test-app-realm",
//                "test-app",
//                "apiKey",
//                ApiKeyVehicle.HEADER,
//                "api_key",
//                "," /*scope separator*/);
//    }

    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(
                "validatorUrl",// url
                "none",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => writer milliseconds, defaults to null (uses jquery xh timeout)
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("eVote")
                .description("Rest API eVote")
                .termsOfServiceUrl("https://www.app.vote.com/tos")
                .contact(new Contact("Asep Rojali", "http://www.aseprojali.id", "aseprojali@gmail.com"))
                .license("license")
                .licenseUrl("https://www.aseprojali.id")
                .version("v2").build();
    }

    private static OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopeList = CollectionHelper.newArrayList();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));
        List<GrantType> grantTypes = CollectionHelper.newArrayList();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8092/oauth/token");
        grantTypes.add(creGrant);
        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
    }

    private static SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
    }

    private static List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{new AuthorizationScope("read", "read all"), new AuthorizationScope("write", "write all")};
        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }


}
