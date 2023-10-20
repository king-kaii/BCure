package com.bcure.mockito.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bcure.mockito.dao.PatientDao;

// Test Class For PatientService with JUnit and Mockito
public class PatientServiceTest {

	@InjectMocks
	private PatientService patientService;

	@Mock
	private PatientDao patientDao;

	@BeforeEach
	public void setUPEachTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Test Case For Validating Patient Login Credential with Success")
	public void validateLoginCredential() {
		Assertions.assertNotNull(patientDao);
		// Stubbing or Setting the Behaviour of Dao Methods
		Mockito.when(patientDao.getLoginStatus(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn("Login Success");

		// Calling Original Bussiness Method
		String validateLoginStatus = patientService.validateLogin("King", "kaii");

		// Comparing the Results Using Assertion
		Assertions.assertNotNull(validateLoginStatus);
		Assertions.assertEquals(validateLoginStatus, "Login Success");

	}

	@Test
	@DisplayName("Test Case For Validating Patient Login Credential with Failure")
	public void validateLoginCredential_Failure() {
		Assertions.assertNotNull(patientDao);

		// Stubbing or Setting the Behaviour of Dao Methods
		Mockito.when(patientDao.getLoginStatus(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn("Login Failure");

		// Calling Original Bussiness Method
		String validateLoginStatus = patientService.validateLogin("King12", "kaii12");

		// Comparing the Results Using Assertion
		Assertions.assertNotNull(validateLoginStatus);
		Assertions.assertEquals(validateLoginStatus, "Login Failure");
	}

	@Test
	@DisplayName("Test Case For Validating Patient Login Credential with Exception")
	public void validateLoginCredential_Exception() {

		Assertions.assertNotNull(patientDao);

		// Stubbing or Setting the Behaviour of Dao Methods
		Mockito.when(patientDao.getLoginStatus(null, null))
				.thenThrow(new RuntimeException("Please Provide Valid Credential"));

		// Comparing the Exception Using Assertion
		Assertions.assertThrows(RuntimeException.class, () -> {
			patientService.validateLogin(null, null);
		});

	}

	@Test
	@DisplayName("Test Case For Validating Void Method....!!!!!!!!!!!!!!!!")
//	@Disabled
	public void validatingVoidMethod() {

		Assertions.assertNotNull(patientDao);

		// Call doNothing() for void methods......
		doNothing().when(patientDao).sendEmailNotifications(anyInt());
		patientService.sendEmail();
		// Stubbing or Setting the Behaviour of Dao Methods
		Mockito.verify(patientDao, Mockito.times(1)).sendEmailNotifications(anyInt());

	}

}
