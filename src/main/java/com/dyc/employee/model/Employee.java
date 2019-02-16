package com.dyc.employee.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Employee {

	@Id
	@GeneratedValue(generator = "employeeGenerator")
	@GenericGenerator(name = "employeeGenerator", strategy = "assigned")
	@Column(length = 64)
	private String id;
	private String organizationId;
	private String departmentId;
	private String name;
	private Integer age;
	private String position;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id='" + id + '\'' +
				", organizationId='" + organizationId + '\'' +
				", departmentId='" + departmentId + '\'' +
				", name='" + name + '\'' +
				", age=" + age +
				", position='" + position + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		com.dyc.employee.model.Employee employee = (com.dyc.employee.model.Employee) o;
		return Objects.equals(id, employee.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}
}
