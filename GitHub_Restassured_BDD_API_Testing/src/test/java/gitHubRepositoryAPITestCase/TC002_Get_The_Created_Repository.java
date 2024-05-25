package gitHubRepositoryAPITestCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import base.TestBase;

public class TC002_Get_The_Created_Repository extends TestBase {
	
	
	 
	@Test(dependsOnMethods = "gitHubRepositoryAPITestCase.TC001_Create_GitHub_Repository.CraeteTheRepository")
	public void GetTheCraetedRepository() {
		logger.info("************** Started TC002 Get The Created GitHub Repository **************");
		// Pre Conduction first creating the repository then geting the repository.
		logger.info("************** Successfully Created GitHub Repository **************");
		
		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		String getCreatedRepositoryEndPoint = prop.getProperty("getCreatedRepository");
	    String ownerNameEndPoint = prop.getProperty("ownerName");
	    String contentType = prop.getProperty("contentType");
	    String bearerToken = prop.getProperty("bearerToken");
	    String authorizationName = prop.getProperty("authorizationName");
	    String authorizationType = prop.getProperty("authorizationType");
	    String responseStatusCode_200 = prop.getProperty("responseStatusCode_200");
	    int responseCode_200=Integer.parseInt(responseStatusCode_200);
	    String responseTimeInString = prop.getProperty("responseTime");
	    long responseTimeInInteger=Long.parseLong(responseTimeInString);
	    String statusLineOfOK = prop.getProperty("statusLineOfOK");
	    String Get_repositoryNamePath = prop.getProperty("Get_repositoryNamePath");
	    String Get_repositoryDescriptionPath = prop.getProperty("Get_repositoryDescriptionPath");
	    String Get_repositoryPrivatePath = prop.getProperty("Get_repositoryPrivatePath");
	   
	    //Getting Data To Serialization Deserialization.txt file
	    String  repositoryName=getRepositoryName();
	    String  repositoryDescription=getRepositoryDescription();
	    boolean repositoryIsInPrivate=getRepositoryIsInPrivate();
	 	

	    logger.info("************** Executing The Get Repository API Request **************");
		given()
              .header(authorizationName, authorizationType + bearerToken)
		      .contentType(contentType)
		.when()
		       .get(baseURI+getCreatedRepositoryEndPoint+ownerNameEndPoint+repositoryName)
		       .then()
		       .statusCode(responseCode_200)
		 	   .statusLine(statusLineOfOK)
		 	   .time(lessThan(responseTimeInInteger))
		 	   .assertThat()
		 	   .body(Get_repositoryNamePath, equalTo(repositoryName))
		 	   .body(Get_repositoryDescriptionPath, equalTo(repositoryDescription))
		 	   .body(Get_repositoryPrivatePath, equalTo(repositoryIsInPrivate));
		 
		logger.info("************** Checking Status Code **************");
		logger.info("The Status Code Is ==> "+responseCode_200);
		logger.info("************** Checking Status Line **************");
		logger.info("The Status Line Is ==> "+statusLineOfOK);
		logger.info("************** Checking Respons Time **************");
		logger.info("The Time Taken To Create Repository Is ==> "+responseTimeInInteger+"Milliseconds");
		logger.info("************** Checking Repository Name **************");
		logger.info("The Repository Name is ==> "+repositoryName);
		logger.info("************** Checking Repository Description **************");
		logger.info("The Repository Description is ==> "+repositoryDescription);
		logger.info("************** Checking Repository Is In Privat **************");
		logger.info("The Repository Is In Private ==> "+repositoryIsInPrivate);
	 }
	
	@AfterClass
	public void TearDown() {
		logger.info("************** Finished TC002 Getting GitHub Repository **************");
	  }
}
