package com.empTaxCal.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empTaxCal.entity.EmpTax;
import com.empTaxCal.entity.Employee;
import com.empTaxCal.repo.EmployeeRepo;


@Service
public class EmplyoyeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	
	public Employee addEmployee(Employee emp) throws Exception{
		Employee employeetoSave=this.employeeRepo.save(emp);
		return employeetoSave;
	}


    
    public double calculateTotalSalary(Employee employee) 
    {    	
    	 LocalDate dateOfJoining = employee.getDoj();
    	
        LocalDate currentDate = LocalDate.now();
        LocalDate fiscalYearStart;
        LocalDate fiscalYearEnd;

        if (currentDate.getMonthValue() >= 4) {
            fiscalYearStart = LocalDate.of(currentDate.getYear(), 4, 1);
            fiscalYearEnd = LocalDate.of(currentDate.getYear() + 1, 3, 31);
        } else {
            fiscalYearStart = LocalDate.of(currentDate.getYear() - 1, 4, 1);
            fiscalYearEnd = LocalDate.of(currentDate.getYear(), 3, 31);
        }

        LocalDate startDate = dateOfJoining.isAfter(fiscalYearStart) ? dateOfJoining : fiscalYearStart;

        long daysWorked = ChronoUnit.DAYS.between(startDate, fiscalYearEnd) + 1;

        return (employee.getSalary())*(daysWorked/30);
    }

    public double calculateTax(double totalSalary) {
        if (totalSalary <= 250000) {
            return 0;
        } else if (totalSalary <= 500000) {
            return (totalSalary - 250000) * 0.05;
        } else if (totalSalary <= 1000000) {
            return 12500 + (totalSalary - 500000) * 0.1;
        } else {
            return 62500 + (totalSalary - 1000000) * 0.2;
        }
    }

    public double calculateCess(double totalSalary) {
        if (totalSalary > 2500000) {
            return (totalSalary - 2500000) * 0.02;
        } else {
            return 0;
        }
    }



	public EmpTax getEmployeeTax(Integer employeeId) {
		
		
		
//		Employee emp = employeeRepo.getById(employeeId);
		Employee emp = employeeRepo.getReferenceById(employeeId);

		
            double totalSalary = calculateTotalSalary(emp);
            double taxAmount = calculateTax(totalSalary);
            double cessAmount = calculateCess(totalSalary);
            EmpTax taxDetails = new EmpTax(emp.getEmployeeId(), emp.getFirstName(),
            		emp.getLastName(), totalSalary, taxAmount, cessAmount);

            return taxDetails;
		
	}

}
