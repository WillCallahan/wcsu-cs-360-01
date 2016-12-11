package edu.wcsu.cs360.battleship.common.controller;

import edu.wcsu.cs360.battleship.common.repository.UserRepositoryTest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runs all the unit tests in the {@link edu.wcsu.cs360.battleship.common} package
 */
public class CommonJUnitTestRunner {
	
	private static Log log = LogFactory.getLog(CommonJUnitTestRunner.class);
	
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(UserRepositoryTest.class);
		if (result.wasSuccessful())
			log.info("All tests completed successfully!");
		else {
			log.error("Not all tests passed!");
			for (Failure failure : result.getFailures())
				log.error("Failed test: " + failure.toString());
		}
	}
	
}
