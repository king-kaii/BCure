package com.bcure.mockito.service;

import com.bcure.mockito.dao.PatientDao;

public class PatientService {
	
	private PatientDao patientDao;

	// Inject the PatientDao Object into PatientService
	public PatientService(PatientDao patientDao) {
		this.patientDao = patientDao;
	}
	
	public String validateLogin(String username, String password) {
		
		return patientDao.getLoginStatus(username, password);
	}
	
	public void sendEmail() {
		patientDao.sendEmailNotifications(05);
	}
	
	

}
