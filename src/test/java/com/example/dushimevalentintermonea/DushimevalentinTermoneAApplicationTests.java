package com.example.dushimevalentintermonea;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DushimevalentinTermoneAApplicationTests {
	@LocalServerPort
	private int port;

	@Test
	public void testDoMathOperation_E2E() {
		// Set the base URI for RestAssured
		RestAssured.baseURI = "http://localhost:" + port;

		// Given
		double operand1 = 2;
		double operand2 = 5;
		String operation = "+";

		// When
		given()
				.contentType(ContentType.JSON)
				.body("{\"operand1\": " + operand1 + ", \"operand2\": " + operand2 + ", \"operation\": \"" + operation + "\"}")
				.when()
				.post("/api/v1/do_math")
				.then()
				.statusCode(200)
				.body("calcResponse", equalTo(7.0f));
	}
}
