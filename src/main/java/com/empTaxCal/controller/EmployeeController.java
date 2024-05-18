package com.empTaxCal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.empTaxCal.entity.EmpTax;
import com.empTaxCal.entity.Employee;
import com.empTaxCal.service.EmpValidate;
import com.empTaxCal.service.EmplyoyeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmpValidate empVal;

	@Autowired
	private EmplyoyeeService empServ;

	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) throws Exception {
		 System.out.println("inside validatemp 1");
		if (empVal.validateEmployee(employee)) {
			Employee emp = this.empServ.addEmployee(employee);
			return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
		}
		else 
		{
			
			throw new IllegalArgumentException("Invalid employee data");
		}

	}
	
	
	@GetMapping("/employee/tax-deduction")
	public EmpTax returnCalculateTaxDeduction(@RequestParam(value = "employeeId")  Integer employeeId)
	{
		EmpTax emp = empServ.getEmployeeTax(employeeId);
		
        return emp;
    }	
	

}
