package br.com.cadmus.solicitacaoServico.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final ResponseMessage m200 = simpleMessage(200, "SUCESSO");
	private final ResponseMessage m201 = simpleMessage(201, "CRIADO");
	private final ResponseMessage m204 = simpleMessage(204, "");
	private final ResponseMessage m304 = simpleMessage(304, "NÃO ALTERADO");
	private final ResponseMessage m400 = simpleMessage(400, "BAD REQUEST");
	private final ResponseMessage m404 = simpleMessage(404, "NOT FOUND");

	private final ResponseMessage m500 = simpleMessage(500, "ERRO NO SERVIDOR");

	private String title = "Projeto para Entrevista CADMUS";
	private String description = "Objetivo: Realizar Solicitação de Serviços usando JMS";
	private String version = "1";
	private String termsOfServiceUrl ="";
	private String contactName = "Rodrigo Cordeiro";
	private String contactUrl = "";
	private String contactEmail = "rodrigoyuri2@hotmail.com";
	private String license = "";
	private String licenseUrl= "";

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false) // quais respostas dos metodos
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m200, m400, m404, m500))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m400, m404, m500))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m200, m204, m400, m404, m500))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204, m400, m404, m500, m304)).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cadmus.solicitacaoServico.controller"))
				.paths(PathSelectors.any()).build()
				// .host("10.207.58.30:5432/produto_db")
				.apiInfo(apiInfo());
	}

	private ResponseMessage simpleMessage(final int code, final String msg) {
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(title, description, version, termsOfServiceUrl,
				new Contact(contactName, contactUrl, contactEmail), license, licenseUrl, Collections.emptyList());
	}

}
