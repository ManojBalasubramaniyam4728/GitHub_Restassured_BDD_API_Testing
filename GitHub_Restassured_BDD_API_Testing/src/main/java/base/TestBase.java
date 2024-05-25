package base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;

public class TestBase {

	//To access through out the project
	public static Response response;
	public static String credentialFilePath = "C:/Users/user/eclipse-workspace/GitHub_Restassured_BDD_API_Testing/src/main/java/base/config.properties";
	public static String repositoryNameFilePath="C:/Users/user/eclipse-workspace/GitHub_Restassured_BDD_API_Testing/src/main/java/base/SerializationDeserializationForRepositoryName.txt";
	public static String repositoryDescriptionFilePath="C:/Users/user/eclipse-workspace/GitHub_Restassured_BDD_API_Testing/src/main/java/base/SerializationDeserializationForRepositoryDescriptionFilePath.txt";
	public static String repositoryIsInPrivateFilePath="C:/Users/user/eclipse-workspace/GitHub_Restassured_BDD_API_Testing/src/main/java/base/SerializationDeserializationForRepositoryIsInPrivateFilePath";
	
	//Serialization Deserialization variable
	public static  String repositoryName;
	public static String repositoryDescription;
	public static boolean repositoryIsInPrivate;
	
	// Logger
	public Logger logger;
	public Properties prop;
	
//**************************************************************************************************************************************
	@BeforeClass
	public void setUp() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(credentialFilePath);
		prop.load(fis);
		// add logger
		logger = Logger.getLogger("GitHubRestAPI");
		// add logger
		PropertyConfigurator.configure("log4j.properties");
		logger.setLevel(Level.DEBUG);
	}
	
//**************************************************************************************************************************************
	//Serialization
	public static void setRepositoryName(String repositoryName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryNameFilePath))) {
			oos.writeObject(repositoryName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
	//Deserialization
	public static String getRepositoryName() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryNameFilePath))) {
			repositoryName = (String) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return repositoryName;
	}
	
//**************************************************************************************************************************************
	//Serialization
		public static void setRepositoryDescription(String repositoryDescriptionName) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryDescriptionFilePath))) {
				oos.writeObject(repositoryDescriptionName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		
		//Deserialization
		public static String getRepositoryDescription() {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryDescriptionFilePath))) {
				repositoryDescription = (String) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return repositoryDescription;
		}
		
//**************************************************************************************************************************************
		//Serialization
		public static void setRepositoryIsInPrivate(Boolean repositoryIsInPrivate) {
			try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(repositoryIsInPrivateFilePath))) {
					oos.writeObject(repositoryIsInPrivate);
				} catch (IOException e) {
						e.printStackTrace();
				}
			}
				
		//Deserialization
		public static boolean getRepositoryIsInPrivate() {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(repositoryIsInPrivateFilePath))) {
				repositoryIsInPrivate = (boolean) ois.readObject();
				} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
				 }
				return repositoryIsInPrivate;
			}
				
//**************************************************************************************************************************************

}
