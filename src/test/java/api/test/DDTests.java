package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;

public class DDTests {
	
	public org.apache.logging.log4j.Logger logger;
	
	@BeforeClass
	public void setup(){
		
		//logs
			logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userID, String userName, String fName, String lName, String useremail, String pwd, String ph) {
		
		logger.info("********** Creating User **********");
		
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.createUser(userPayload);
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("********** User is created **********");
	}
	
	@Test(priority = 2, dataProvider="UserNames", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName) {
		logger.info("********** Deleting User **********");
		
		Response response = UserEndPoints.deleteUser(userName);
//		response.then().log().body();
//		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("********** User Deleted **********");
	}

}
