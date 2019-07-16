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

import com.mastek.training.hrapp.apis.DepartmentService;
import com.mastek.training.hrapp.apis.EmployeeService;
import com.mastek.training.hrapp.apis.ProjectService;
import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.entities.Employee;
import com.mastek.training.hrapp.entities.Project;

//Initialise the Junit test with Spring boot application environment
// Spring boot test : used to test spring ApplicationContext
// With all the test cases identified

@RunWith(SpringRunner.class)
@SpringBootTest
public class HrAppApplicationTests {
	
	//////////////////////////////////////
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
		int empno = 58 ;
		empService.deleteByEmployeeNumber(empno);
		assertNull(empService.findByEmpno(empno));
	}
	
	@Test
	public void checkFetchBySalary() {
		List<Employee> emps = empService.fetchEmployeesBySalaryRange(0, 100);
		for (Employee employee : emps) {
			System.out.println(employee);
		}
		
		assertEquals(emps.size(), 1);
	}
	
	///////////////////////////////////
	
	
	@Autowired
	DepartmentService deptService;
	
	@Autowired
	Department dept;
	
	@Test
	public void addDepartmentUsingService() {
		dept.setDeptno(1);
		dept.setName("Dept Test");
		dept.setLocation("Leeds");
		dept = deptService.registerOrUpdateDepartment(dept)  ;  //.registerOrUpdateEmployee(emp)
	}
	
	@Test
	public void findByDeptnoUsingService() {
		int deptno = 44 ;
		assertNotNull(deptService.findByDeptno(deptno));
	}
	
	@Test
	public void checkFetchByLocation() {
		List<Department> depts = deptService.fetchDeparmetByLocation("Leeds");
		for (Department department : depts) {
			System.out.println(department);
		}
		
		assertEquals(depts.size(), 8);
	}
	
	///////////////////////////////////////////////
	
	@Autowired
	ProjectService projService;

	@Autowired
	Project proj;

	@Test
	public void addProjectUsingService() { 
		proj.setProjectId(1);
		proj.setName("nameForProject Tests");
		proj.setCustomerName("Customer Name Test");
		proj = projService.registerOrUpdateProject(proj);
	}
	
	@Test
	public void findByProjnoUsingService() {
		int projno = 42 ;
		assertNotNull(projService.findByProjno(projno));
	}
	
	@Test
	public void checkFetchByCustomerName() {
		List<Project> cust = projService.fetchProjectByCustomerName("James");
		for (Project project : cust) {
			System.out.println(project);
		}
		
		assertEquals(cust.size(), 1);
	}
	
	@Test
	public void manageAssociation() {
		Department d1 = new Department();
		d1.setName("Admin");
		d1.setLocation("UK");
		
		Employee emp1 = new Employee();
		emp1.setName("Admin Emp 1");
		emp1.setSalary(2342);
		
		Employee emp2 = new Employee();
		emp2.setName("Admin Emp 2");
		emp2.setSalary(2837);
		
		Project p1 = new Project();
		p1.setName("UK Project");
		p1.setCustomerName("UK Customer");
		
		Project p2 = new Project();
		p2.setName("US Project");
		p2.setCustomerName("US Customer");
		
		// one to Many : one Deparment has many employees
		d1.getMembers().add(emp1);
		d1.getMembers().add(emp2);
		
		// many to One for each Employye to assing the department
		emp1.setCurrentDepartment(d1);
		emp2.setCurrentDepartment(d1);
		
		//Many to Many 
		emp1.getAssignments().add(p2);
		emp1.getAssignments().add(p1);
		emp2.getAssignments().add(p1);
		
		deptService.registerOrUpdateDepartment(d1);
	}
	
	

}
