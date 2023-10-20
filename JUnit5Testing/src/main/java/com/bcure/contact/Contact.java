package com.bcure.contact;

public class Contact {

	private String firstName;

	private String lastName;

	private String phoneNumber;

	public Contact() {

	}

	public Contact(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void validateFirstName() {
		if (firstName.isBlank()) {
			throw new RuntimeException("FirstName Shouldn't be empty");
		}
	}

	public void validateLastName() {
		if (lastName.isBlank()) {
			throw new RuntimeException("LastName Shouldn't be empty");
		}
	}

	public void validatePhoneNumber() {
		if (phoneNumber.isBlank()) {
			throw new RuntimeException("PhoneNumber Shouldn't be empty");
		}
		if (!phoneNumber.startsWith("0")) {
			throw new RuntimeException("PhoneNumber Should start with zero");
		}
		if (phoneNumber.length() != 11) {
			throw new RuntimeException("PhoneNumber Should be 11 digit with first digit zero");
		}
	}

}
