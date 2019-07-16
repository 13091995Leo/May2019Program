package com.mastek.training.hrapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // one copy for each test case.
@Entity // Declares the class as an entity.
@Table(name = "JPA_DEPARTMENT") // declaring the table name for the class.
@NamedQueries({
	@NamedQuery(name = "Department.findByName",
			query = "select d from Department d where d.location = :location") // JPA query language.
})
public class Department implements Serializable {
	
	private int deptno;
	private String name;
	private String location;
	
//////////////////////////////////////////////////////////////////// ASSOCIATION	
	//ONE to MANY : One department has many employees.
	private Set<Employee> members = new HashSet<>();
	
	//@Onetoany: used used on the get method of st to configure the association
	// fetch=LAZY : delay the initialization [DEFAULT!!]
	// until method getMembers() is called
	//
	//EAGER : initilaize the collection on entity find Post load event.
	// cascade  = All the entity operation done on department would be performed on each associated employee object.
	//    ALL : [Persist, Merge , Delete, Refresh]
	// mappedBy: identifies the propety name in Child class 
	// 			whre the JoiCollumn configuration is resent
	// 			JoinColumn:FeoreignKey
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "currentDepartment")
	public Set<Employee> getMembers() {
		return members;
	}

	public void setMembers(Set<Employee> members) {
		this.members = members;
	}
/////////////////////////////////////////////////////////////////// ASSOCIATION

	@Id
	@Column(name = "department_number")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getDeptno() {
		return deptno;
	}
	
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	@Column(name = "department_name", nullable = false, length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "location_name", nullable = false, length = 45)
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
