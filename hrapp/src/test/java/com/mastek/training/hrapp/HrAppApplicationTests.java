package com.mastek.training.hrapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.entities.Employee;

//Initialise the Junit test with Spring boot application environment
// Spring boot test : used to test spring ApplicationContext
// With all the test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	Employee emp;
	
	@Test
	public void addEmployeeUsingService() {
		emp.setEmpno(38);
		emp.setName("Emp_service test Up");
		emp.setSalary(55);
		emp = empService.registerOrUpdateEmployee(emp);
	}
	
	@Test
	public void simpleTest() {
		System.out.println("System Test Executed");
	}
	
	@Test
	public void findByEmpnoUsingService() {
		int empno = 38 ;
		assertNotNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void deleteByEmpnoUsingService() {
		int empno = 39 ;
		empService.deleteByEmployeeNumber(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void chceckFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 100);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		
		assertEquals(emps.size(), 1);
	}
	



}
