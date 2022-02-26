package br.com.uniesp.test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;

import br.com.uniesp.servicos.Servicos;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReqResExampleTest {

	@BeforeEach
	public void configuraApi() {
		baseURI = 	"https://reqres.in/";
	}
	@Test
	void methodGet() {
//		PessoaResponse as =
				given()
		.when()
			.get(Servicos.getUsers_ID.getValor(), 2)
		.then().contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
			.log().all();
//			.and().extract().response().as(PessoaResponse.class);
		
		
	}
	/*@Test
	public void methodPost() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		PessoaResponse as = given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED)
			.extract().response().as(PessoaResponse.class);

		Assert.assertNotNull(as);
		Assert.assertNotNull(as.getId());
		Assert.assertEquals(pessoaRequest.getNome(), as.getNome());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
	}
	@Test
	public void methodPost2() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		String as =
				given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED).log().all()
			.and().extract().response().path("nome");
		
		System.out.println(as);
		Assert.assertNotNull(as);
	}*/
}
