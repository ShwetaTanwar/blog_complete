package initializ.blog.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	public static final String AUTHORIZATION_HEADER="Authorization";
	
	private ApiKey apiKeys()
	{
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
		
	}
	private List<SecurityContext> securityContexts()
	{
		return Arrays.asList(SecurityContext.builder().securityReferences(security_Ref()).build());
	}
	private List<SecurityReference> security_Ref() 
	{   AuthorizationScope scope =new AuthorizationScope("global","accessEverything");
		return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	}
	
	//all config needs docket class
	@Bean
	public Docket api() {
		
	
		return new Docket(DocumentationType.SWAGGER_2)//create obj
				.apiInfo(getInfo())//call api
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();//docket obj build
	}

	private ApiInfo getInfo() {
		// TODO Auto-generated method stub
		return new ApiInfo("Blogging Application", "This project developed by Shweta Tanwar" ,"version 1.0","Terms of Service",
				new Contact("Shweta","https://github.com/ShwetaTanwar/Blogg_app","shwetatanwar.9800@gmail.com"), "License of API", "API URL",Collections.emptyList() );
	};

}
