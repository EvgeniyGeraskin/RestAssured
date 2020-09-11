// Created by Evgeniy Geraskin
// GeraskinEvgeniy@mail.ru

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class Tests {

	@Test
	public void test_01_post() { //Point 1. New user with name "TestUser" and job "QA Automation Engineer"

		System.out.println("-----------------------TEST_1_CREATE----------------------------");
		
		JSONObject request = new JSONObject(); //create JSONObject

		request.put("name", "TestUser"); //put name
		request.put("job", "QA Automation Engineer"); //put job

		given(). //define what will be sent in the request
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(request.toJSONString()).
			body(request.toJSONString()).
		when(). //determine with which method we send the request
			post("https://reqres.in/api/users"). //call the method post to create new user
		then(). //define how the received answer is checked
			statusCode(201). //expecting the 201 code
			log().all(); //logging to make sure that the user has been created
		System.out.println("---------------------------------------------------");
		
	}

	@Test
	public void test_02_patch() { //Point 3. Change the job of the created user
		
		System.out.println("-----------------------TEST_2_UPDATE----------------------------");

		JSONObject request = new JSONObject();

		request.put("name", "TestUser"); //put name
		request.put("job", "Senior QA Automation Engineer"); //put job

		given(). //define what will be sent in the request
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			accept(request.toJSONString()).
			body(request.toJSONString()).
		when(). //determine with which method we send the request
			patch("https://reqres.in/api/users/2"). //call the method patch to update job of our user
		then(). //define how the received answer is checked
			statusCode(200). //expecting the 200 code
			log().all(); //logging to make sure that the user has been updated
		
		System.out.println("---------------------------------------------------");

	}
	
	@Test
	public void test_03_delete() { //Point 4. Delete the our user
		
		System.out.println("-----------------------TEST_3_DELETE----------------------------");

		when(). //determine with which method we send the request
			delete("https://reqres.in/api/users/2").
		then(). //define how the received answer is checked
			statusCode(204). //expecting the 204 code
			log().all(); //logging to make sure that the user has been deleted
		
		System.out.println("---------------------------------------------------");

	}
	
}
