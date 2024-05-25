package gitHubRepositoryAPITestCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import base.TestBase;

public class TC004_Delete_The_Created_Repository extends TestBase{
	
	
	@Test(dependsOnMethods = "gitHubRepositoryAPITestCase.TC001_Create_GitHub_Repository.CraeteTheRepository")
	public void DeleteTheCraetedRepository() {
		logger.info("************** Started TC004 Delete The Created GitHub Repository **************");
		// Pre Conduction first creating the repository then geting the repository.
		logger.info("************** Successfully Created GitHub Repository **************");
		
		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		String deleteRepositoryEndPoint = prop.getProperty("deleteRepository");
	    String ownerNameEndPoint = prop.getProperty("ownerName");
	    String contentType = prop.getProperty("contentType");
	    String bearerToken = prop.getProperty("bearerToken");
	    String authorizationName = prop.getProperty("authorizationName");
	    String authorizationType = prop.getProperty("authorizationType");
	    String responseStatusCode_204 = prop.getProperty("responseStatusCode_204");
	    int responseCode_204=Integer.parseInt(responseStatusCode_204);
	    String responseTimeInString = prop.getProperty("responseTime");
	    long responseTimeInInteger=Long.parseLong(responseTimeInString);
	    String statusLineOfNoContent = prop.getProperty("statusLineOfNoContent");

	    logger.info("************** Executing The Get Repository API Request **************");
		given()
              .header(authorizationName, authorizationType + bearerToken)
		      .contentType(contentType)
		.when()
		       .delete(baseURI+deleteRepositoryEndPoint+ownerNameEndPoint+repositoryName)
		       .then()
		       .statusCode(responseCode_204)
		 	   .statusLine(statusLineOfNoContent)
		 	   .time(lessThan(responseTimeInInteger)).log().all();
		 
		logger.info("************** Checking Status Code **************");
		logger.info("The Status Code Is ==> "+responseCode_204);
		logger.info("************** Checking Status Line **************");
		logger.info("The Status Line Is ==> "+statusLineOfNoContent);
		logger.info("************** Checking Respons Time **************");
		logger.info("The Time Taken To Create Repository Is ==> "+responseTimeInInteger+"Milliseconds");

	      }
	
	@AfterClass
	public void TearDown() {
		logger.info("************** Finished TC004 Deleted GitHub Repository **************");
	  }
}
