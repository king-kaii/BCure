package com.bcure.contact;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


public class ContactManagerTest {

	private ContactManager contactManager;

	@BeforeAll
	public static void BeforeAllTestMethod() {
		System.out.println("Before all.....One Time SetUp");
	}

	@BeforeEach
	public void forEachTest() {

		contactManager = new ContactManager();
		System.out.println("Before Each Test Method");
	}

	@Test
	@DisplayName("Test case for Create Contact Successfully....")
	public void createContactTest() {

		contactManager.addContact("King", "Kaii", "08338879733");

		Assertions.assertEquals(1, contactManager.getAllContacts().size());
		Assertions.assertNotEquals(true, contactManager.getAllContacts().isEmpty());

	}

	@Test
	@DisplayName("Test case for not Creating Contact if FirstName isEmpty....")
	public void validateFirstNameIsEmpty() {

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact(null, "Kaii", "08338879733");
		});
	}

	@Test
	@DisplayName("Test case for not Creating Contact if LastName isEmpty....")
	public void validateLastNameIsEmpty() {

		Assertions.assertThrows(NullPointerException.class, () -> {

			contactManager.addContact("King", null, "08338879733");
		});
	}

	@Test
	@DisplayName("Test case for not Creating Contact if Number Size is Greater than 11....")
	public void validateNumberSizeISGreater() {

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact("King", "Kaii", "0833887973563");
		});
	}

	@Test
	@DisplayName("Test case for not Creating Contact if phone Number Not Starts with Zero....")
	public void checkIsNumberStrtsWithZero() {

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact("King", "Kaii", "8338879733");
		});
	}

	@Test
	@DisplayName("Test case for not Creating Contact if phone Number is Empty....")
	public void checkIsNumberEmpty() {

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact("King", "Kaii", null);
		});
	}

	@Test
	@DisplayName("Test case for not Creating Contact if phone Number is Empty....")
	public void checkNumberEmpty() {

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact("King", "Kaii", null);
		}, "PhoneNumber Shouldn't be empty");
	}

	@Test
	@DisplayName("Test case for not Creating Contact if Contact Already Exists....")
	public void checkIfContactAlreadyExists() {

		contactManager.addContact("King", "Kaii", "08338879733");

		Assertions.assertThrows(RuntimeException.class, () -> {

			contactManager.addContact("King", "Kaii", "08338879733");
		}, "Contact Already Exists....");
	}

	@ParameterizedTest
	@ValueSource(strings = { "08338879733", "08338879734", "08338879735" })
	// @CsvSource({ "08338879733", "08338879734", "08338879735" })
	// @CsvFileSource(resources = "/data.csv")
	public void shouldTestNumberWithValueSource(String phoneNumber) {
		contactManager.addContact("King", "Kaii", phoneNumber);
		Assertions.assertEquals(1, contactManager.getAllContacts().size());
		Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
	}

	@ParameterizedTest
	@MethodSource("phoneNumberList")
	public void shouldTestNumberWithMethodSource(String phoneNumber) {
		contactManager.addContact("King", "Kaii", phoneNumber);
		Assertions.assertEquals(1, contactManager.getAllContacts().size());
		Assertions.assertFalse(contactManager.getAllContacts().isEmpty());

	}

	private static List<String> phoneNumberList() {

		return Arrays.asList("08338879733", "08338879734", "08338879735");
	}

	@AfterEach
	public void AfterEachTestMethod() {
		contactManager = null;
		System.out.println("After Each Test Method");
	}

	@AfterAll
	public static void AfterAllTestMethod() {
		System.out.println("After all.....Clean Up");
	}

}

