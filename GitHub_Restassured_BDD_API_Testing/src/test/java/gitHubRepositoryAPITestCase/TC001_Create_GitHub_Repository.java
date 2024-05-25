package gitHubRepositoryAPITestCase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

import base.TestBase;
import io.restassured.RestAssured;
import utils.RestUtils;

public class TC001_Create_GitHub_Repository extends TestBase {

	// Declaring Has map to added json body to the api
	public static HashMap map = new HashMap();

	@BeforeClass
	public void Postdata() {
		logger.info("************** Started TC001 Create GitHub Repository **************");

		// Getting Random data from utils file by crating Object
		RestUtils ru = new RestUtils();

		String repositoryName = ru.getRandomRepositoryName();
		String repositoryDescription = ru.getRandomRepositorDescription();
		boolean repositoryIsInPrivate = ru.getRepositoryIsInPrivate();

		// Setting Data To Serialization Deserialization.txt file
		setRepositoryName(repositoryName);
		setRepositoryDescription(repositoryDescription);
		setRepositoryIsInPrivate(repositoryIsInPrivate);

		// Adding the json body to map
		map.put("name", repositoryName);
		map.put("description", repositoryDescription);
		map.put("private", repositoryIsInPrivate);

		// Getting Data from propertyfile
		String baseURI = prop.getProperty("baseURI");
		String createRepositoryEndPoint = prop.getProperty("createRepository");

		// Specify base URI
		RestAssured.baseURI = baseURI;

		// Specify end ponit
		RestAssured.basePath = createRepositoryEndPoint;
	}

	 @Test
	public void CraeteTheRepository() {
		
		// Getting Data from propertyfile
	    String contentType = prop.getProperty("contentType");
	    String bearerToken = prop.getProperty("bearerToken");
	    String authorizationName = prop.getProperty("authorizationName");
	    String authorizationType = prop.getProperty("authorizationType");
	    String responseStatusCode_201 = prop.getProperty("responseStatusCode_201");
	    int responseCode_201=Integer.parseInt(responseStatusCode_201);
	    String responseTimeInString = prop.getProperty("responseTime");
	    long responseTimeInInteger=Long.parseLong(responseTimeInString);
	    String statusLineOfCreated = prop.getProperty("statusLineOfCreated");
	    String Create_repositoryNamePath = prop.getProperty("Create_repositoryNamePath");
	    String Create_repositoryDescriptionPath = prop.getProperty("Create_repositoryDescriptionPath");
	    String Create_repositoryPrivatePath = prop.getProperty("Create_repositoryPrivatePath");
	    
	    //Getting Data To Serialization Deserialization.txt file
	    String  repositoryName=getRepositoryName();
	    String  repositoryDescription=getRepositoryDescription();
	    boolean repositoryIsInPrivate=getRepositoryIsInPrivate();
	 	

	    logger.info("************** Executing The Crate Repository API Request **************");
		given()
	          .header(authorizationName, authorizationType + bearerToken)
		      .contentType(contentType)
		      .body(map)
		.when()
		       .post()
		       .then()
		       .statusCode(responseCode_201)
		 	   .statusLine(statusLineOfCreated)
		 	   .time(lessThan(responseTimeInInteger))
		 	   .assertThat()
		 	   .body(Create_repositoryNamePath, equalTo(repositoryName))
		 	   .body(Create_repositoryDescriptionPath, equalTo(repositoryDescription))
		 	   .body(Create_repositoryPrivatePath, equalTo(repositoryIsInPrivate));
		logger.info("************** Checking Status Code **************");
		logger.info("The Status Code Is ==> "+responseCode_201);
		logger.info("************** Checking Status Line **************");
		logger.info("The Status Line Is ==> "+statusLineOfCreated);
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
		logger.info("************** Finished TC001 Create GitHub Repository **************");
	  }
    }
