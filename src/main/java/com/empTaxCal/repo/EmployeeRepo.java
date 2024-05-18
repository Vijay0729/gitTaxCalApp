package com.empTaxCal.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empTaxCal.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer >{


}
