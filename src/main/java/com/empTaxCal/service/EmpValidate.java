package com.empTaxCal.service;

import org.springframework.stereotype.Service;

import com.empTaxCal.entity.Employee;
@Service
public class EmpValidate {
	
	 public boolean validateEmployee(Employee employee) {
	        // Validate all fields are present

		 System.out.println(" employee.getFirstName()"+ employee.getFirstName());
		 System.out.println(employee.getLastName());
		 System.out.println( employee.getEmail());
		 System.out.println(employee.getPhoneNumber());
		 System.out.println(employee.getDoj());
		 System.out.println(employee.getSalary());
	        if (
	            employee.getFirstName() == null || employee.getFirstName().isEmpty() ||
	            employee.getLastName() == null || employee.getLastName().isEmpty() ||
	            employee.getEmail() == null || employee.getEmail().isEmpty() ||
	            employee.getPhoneNumber() == null || employee.getPhoneNumber().isEmpty() ||
	            employee.getDoj() == null || employee.getSalary() <= 0) {
	            return false;
	        }

	        return true;
	    }

}
