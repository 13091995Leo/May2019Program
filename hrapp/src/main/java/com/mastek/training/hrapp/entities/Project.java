package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // one copy for each test case.
@Entity // Declares the class as an entity.
@Table(name = "JPA_PROJECT") // declaring the table name for the class.
@NamedQueries({
	@NamedQuery(name = "Project.findByCustomerName",
			query = "select p from Project p where p.customerName  = :customerName ") // JPA query language.
	
})
public class Project implements Serializable {
	private int projectId;
	private String name;
	private String customerName;
	
	///////////////////////////////////////////////////////////////// For the MANY TO MANY ASSOCIATION
	
	private Set<Employee> team = new HashSet<>();
	
	// mappedBy: check the configuration for Many to Many.
	// In employee class get assignments() method.
	// If this method is missed out then another table will be created.
	
	@ManyToMany(mappedBy = "assignments")
	public Set<Employee> getTeam() {
		return team;
	}
	public void setTeam(Set<Employee> team) {
		this.team = team;
	}
	
	
	
	//////////////////////////////////////////////////////////////////// For the MANY TO MANY ASSOCIATION END
	
	
	
	@Id
	@Column(name = "project_number")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	@Column(name = "project_name", nullable = false, length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "customer_name", nullable = false, length = 45)
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	} 
	
	
	
}
