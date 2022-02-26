package br.com.uniesp.test

import br.com.uniesp.entidate.PessoaRequest
import br.com.uniesp.entidate.PessoaResponse
import br.com.uniesp.servicos.Servicos
import io.restassured.RestAssured
import io.restassured.RestAssured.basePath
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.module.jsv.JsonSchemaValidator
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ReqResExampleTest {
    @BeforeEach
    fun configuraApi() {
        RestAssured.baseURI = "https://reqres.in/"
    }

    @Test
    fun methodGet() {
        given()
            .`when`()[Servicos.getUsers_ID.valor, 2]
            .then().contentType(ContentType.JSON)
            .statusCode(HttpStatus.SC_OK)
            .and()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
            .log().all()
            .and().extract().response()
    }

    @Test
    fun methodPost() {
        val pessoaRequest = PessoaRequest("thiago", "QA")
        basePath = "/api/users"
        val `as`: PessoaResponse = given()
            .contentType("application/json")
            .body(pessoaRequest)
            .`when`()
            .post("/")
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .extract().response().`as`(PessoaResponse::class.java)
        assertNotNull(`as`)
        assertNotNull(`as`.id)
        assertEquals(pessoaRequest.nome, `as`.nome)
        assertEquals(pessoaRequest.job, `as`.job)
    }

    @Test
    fun methodPut() {
        val pessoaRequest = PessoaRequest("thiago", "QA")
        basePath = "api/users/2"
        val request = given()
            .contentType("application/json")
            .body(pessoaRequest)
            .`when`()
            .put(basePath)
            .then()
            .statusCode(HttpStatus.SC_OK)
        println(request)
        assertNotNull(request)
    }

    @Test
    fun methodDelete() {
        basePath = "api/users/2"
        val request = given()
            .`when`()
            .delete(basePath)
            .then()
            .statusCode(HttpStatus.SC_NO_CONTENT)
        println(request)
        assertNotNull(request)
    }
}