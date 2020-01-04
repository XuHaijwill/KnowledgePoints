package com.test.spring.springmvc_test_one.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.test.spring.springmvc_test_one.entities.Department;
import com.test.spring.springmvc_test_one.entities.Employee;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

	public EmployeeConverter() {
		
	}
	
	@Override
	public Employee convert(String source) {
		if(source != null){
			String [] vals = source.split("-");
			//GG-gg@atguigu.com-0-105
			if(vals != null && vals.length == 4){
				String lastName = vals[0];
				String email = vals[1];
				Integer gender = Integer.parseInt(vals[2]);
				Department department = new Department();
				department.setId(Integer.parseInt(vals[3]));
				
				Employee employee = new Employee(null, lastName, email, gender, department);
				System.out.println(source + "--convert--" + employee);
				return employee;
			}
		}
		return null;
	}

}
