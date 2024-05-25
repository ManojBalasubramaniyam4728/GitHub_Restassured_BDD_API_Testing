package gitHubRepositoryAPITestCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.TestBase;
import io.restassured.RestAssured;
import utils.RestUtils;

public class TC003_Update_The_Created_Repository extends TestBase {

	// Declaring Has map to added json body to the api
	public static HashMap map = new HashMap();

	@BeforeClass
	public void Updatedata() {
		logger.info("************** Started TC003 Update The Created GitHub Repository **************");
		// Pre Conduction first creating the repository then geting the repository.
		logger.info("************** Successfully Created GitHub Repository **************");
		
		//Getting Data To Serialization Deserialization.txt file
	    String  GetOldRepositoryName=getRepositoryName();
	
	   		
		// Getting Random data from utils file by crating Object
		RestUtils ru = new RestUtils();

		String newrepositoryName = ru.getRandomRepositoryName();
		String newrepositoryDescription = ru.getRandomRepositorDescription();
		boolean newrepositoryIsInPrivate = ru.getRepositoryIsInPrivate();

		// Setting Data To Serialization Deserialization.txt file
		setRepositoryName(newrepositoryName);
		setRepositoryDescription(newrepositoryDescription);
		setRepositoryIsInPrivate(newrepositoryIsInPrivate);
		
		// Adding the json body to map
		map.put("name", newrepositoryName);
		map.put("description", newrepositoryDescription);
		map.put("private", newrepositoryIsInPrivate);

		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		String updatingRepositoryEndPoint = prop.getProperty("updatingRepository");
		String ownerNameEndPoint = prop.getProperty("ownerName");
		 

		// Specify base URI
		RestAssured.baseURI = baseURI;

		// Specify end ponit
		RestAssured.basePath = updatingRepositoryEndPoint+ownerNameEndPoint+GetOldRepositoryName;
	}

	@Test(dependsOnMethods = "gitHubRepositoryAPITestCase.TC001_Create_GitHub_Repository.CraeteTheRepository")
	public void UpdateTheCraetedRepository() {

		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		String getCreatedRepositoryEndPoint = prop.getProperty("getCreatedRepository");
		String contentType = prop.getProperty("contentType");
		String bearerToken = prop.getProperty("bearerToken");
		String authorizationName = prop.getProperty("authorizationName");
		String authorizationType = prop.getProperty("authorizationType");
		String responseStatusCode_200 = prop.getProperty("responseStatusCode_200");
		int responseCode_200 = Integer.parseInt(responseStatusCode_200);
		String responseTimeInString = prop.getProperty("responseTime");
		long responseTimeInInteger = Long.parseLong(responseTimeInString);
		String statusLineOfOK = prop.getProperty("statusLineOfOK");
		String Update_repositoryNamePath = prop.getProperty("Update_repositoryNamePath");
		String Update_repositoryDescriptionPath = prop.getProperty("Update_repositoryDescriptionPath");
		String Update_repositoryPrivatePath = prop.getProperty("Update_repositoryPrivatePath");

		// Getting Data To Serialization Deserialization.txt file
		String updatedRepositoryName = getRepositoryName();
		String updatedRepositoryDescription = getRepositoryDescription();
		boolean updatedRepositoryIsInPrivate = getRepositoryIsInPrivate();

		logger.info("************** Executing The Update Repository API Request **************");
		given()
	          .header(authorizationName, authorizationType + bearerToken)
		      .contentType(contentType)
		      .body(map)
		.when()
		       .patch()
		       .then()
		       .statusCode(responseCode_200)
		 	   .statusLine(statusLineOfOK)
		 	   .time(lessThan(responseTimeInInteger))
		 	   .assertThat()
		 	   .body(Update_repositoryNamePath, equalTo(updatedRepositoryName))
		 	   .body(Update_repositoryDescriptionPath, equalTo(updatedRepositoryDescription))
		 	   .body(Update_repositoryPrivatePath, equalTo(updatedRepositoryIsInPrivate));
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
		logger.info("************** Finished TC003 Updated GitHub Repository **************");
	}
}
