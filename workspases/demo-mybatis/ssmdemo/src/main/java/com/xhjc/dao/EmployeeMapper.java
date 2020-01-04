package com.xhjc.dao;


import com.xhjc.bean.Employee;

import java.util.List;


public interface EmployeeMapper {
	
	public Employee getEmpById(Integer id);
	
	public List<Employee> getEmps();
	

}
