package com.bcure.mockito.dao;

public class PatientDao {

	public String getLoginStatus(String username, String password) {

		if (username != null && password != null) {
			if ("King".equalsIgnoreCase(username) && "Kaii".equalsIgnoreCase(password)) {
				return "Login Success";
			} else {
				return "Login Failure";
			}
		} else {
			throw new RuntimeException("Please Provide Valid Credential");
		}

	}

	public void sendEmailNotifications(int count) {

		System.out.println("Email Send for Today Count::::" + count);

	}

}
