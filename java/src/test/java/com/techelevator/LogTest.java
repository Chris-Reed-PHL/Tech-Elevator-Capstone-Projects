package com.techelevator;

import static org.junit.Assert.assertNotNull;

import java.io.FileNotFoundException;

import org.junit.Test;

public class LogTest {
	
	@Test
	public void log_class_should_create_new_file() throws FileNotFoundException {
		Log logger = new Log();
		logger.salesLog("New File Was Created Successfully!");
		assertNotNull(logger.logFile);
	}

}
