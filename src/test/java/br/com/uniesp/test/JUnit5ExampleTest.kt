package br.com.uniesp.test

import io.restassured.RestAssured
import org.apache.http.HttpStatus
import io.restassured.module.jsv.JsonSchemaValidator
import br.com.uniesp.entidate.PessoaRequest
import br.com.uniesp.entidate.PessoaResponse
import io.restassured.http.ContentType
import org.junit.jupiter.api.*

internal class JUnit5ExampleTest {
    @BeforeEach
    fun configuraApi() {
        RestAssured.baseURI = "https://reqres.in/"
    }

    @Test
    fun justAnExample() {
    }

    @Test
    @DisplayName("esse teste faz isso")
    @Tag("E2E")
    fun methodGet() {
        RestAssured.given().log().all()
            .`when`()["api/users/2"]
            .then().contentType("application/json")
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
            .log()
    }

    @Test
    fun methodPost() {

//        basePath = "/api/users";
        RestAssured.given()
            .`when`()["api/users/2"]
            .then().contentType("application/json")
            .statusCode(HttpStatus.SC_OK)
            .log().all()
    }

    @Test
    fun methodPostFull() {
        val pessoaRequest = PessoaRequest("thiago", "QA")
        RestAssured.basePath = "/api/users"
        val `as` = RestAssured.given().log().all()
            .contentType(ContentType.JSON)
            .body(pessoaRequest)
            .`when`()
            .post("/")
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .and()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/methodPost.json"))
            .log().all().extract().response().`as`(PessoaResponse::class.java)
        Assertions.assertNotNull(`as`)
        Assertions.assertNotNull(`as`.id)
        Assertions.assertEquals(pessoaRequest.nome, `as`.nome)
        Assertions.assertEquals(pessoaRequest.job, `as`.job)
    }
}