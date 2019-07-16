package com.mastek.training.hrapp.apis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mastek.training.hrapp.entities.Project;
import com.mastek.training.hrapp.repositories.ProjectRepository;

@Component
@Scope("singleton")
public class ProjectService {
	
	@Autowired
	private ProjectRepository projRepository;

	public Project registerOrUpdateProject(Project proj) {
		proj = projRepository.save(proj);
		System.out.println("Employee Registered" + proj);
		return proj;
	}

	public Project findByProjno(int projno) {
		try {
			return projRepository.findById(projno).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Project> fetchProjectByCustomerName(String customerName) {
		return projRepository.findByCustomerName(customerName);
	}
	
	
	 
	

}
