package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Department;
import com.mastek.training.hrapp.repositories.DepartmentRepository;

@Component
@Scope("singleton")
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository deptRepository;

	public Department registerOrUpdateDepartment(Department dept) {
		dept = deptRepository.save(dept);
		System.out.println("Employee Registered" + dept);
		return dept;
	}

	public Department findByDeptno(int deptno) {
		try {
			return deptRepository.findById(deptno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Department> fetchDeparmetByLocation(String location){
        return deptRepository.findByLocation(location);
	}


	
	
	
	
}
