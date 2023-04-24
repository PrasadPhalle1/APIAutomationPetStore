package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 {
	
	// Following method is created for getting URLs from properties file
	static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load properties file using it's name only
		return routes;
	}
	
	public static Response createUser(User payload){
		
		String post_url = getURL().getString("post_url");	// Fetch the value of URL from properties file using key
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(post_url);	// Pass the value URL fetched from properties file
		
		return response;
	}
	
	public static Response readUser(String userName){
		
		String get_url = getURL().getString("get_url");	// Fetch the value of URL from properties file using key
		Response response = given()
			.pathParam("username", userName)
		.when()
			.get(get_url);	// Pass the value URL fetched from properties file
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload){
		
		String update_url = getURL().getString("update_url");	// Fetch the value of URL from properties file using key
		Response response = given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("username", userName)
			.body(payload)
		.when()
			.put(update_url);	// Pass the value URL fetched from properties file
		
		return response;
	}
	
	public static Response deleteUser(String userName){
		
		String delete_url = getURL().getString("delete_url");	// Fetch the value of URL from properties file using key
		Response response = given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url);	// Pass the value URL fetched from properties file
		
		return response;
	}

}
