package com.javainuse.service;

import org.springframework.stereotype.Service;

import com.javainuse.model.Employee;

@Service
public class EmployeeService {

	public Employee createEmployee(String name, String empId) {
		Employee emp = new Employee();
		emp.setName(name);
		emp.setEmpId(empId);
		return emp;
	}
	public void deleteEmployee(String empId) {}
	public void getEmployee() throws Exception{
		String str=null;
			System.out.println("$$$$$ in get Emp service");
			if (null != str) {
				System.out.println("not equal to null");
			} else {
				System.out.println("@@@@@@@@@@@@ else");
				throw new Exception("Customer Id is invlaid");
			}
	}
}