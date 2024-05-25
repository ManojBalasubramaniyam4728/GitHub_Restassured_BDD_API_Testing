package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String getRandomRepositoryName() {
		String repositoryName = RandomStringUtils.randomNumeric(3);
		return("Test"+repositoryName);
	}
	
	public static String getRandomRepositorDescription() {
		String descriptionName = RandomStringUtils.randomAlphabetic(3);
		return("This Is For Testing"+descriptionName);
	}
	
	public static boolean getRepositoryIsInPrivate() {
		return true;
	}
}
