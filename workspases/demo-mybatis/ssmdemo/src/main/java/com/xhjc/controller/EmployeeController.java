package com.xhjc.controller;

import java.util.List;
import java.util.Map;

import com.xhjc.bean.Employee;
import com.xhjc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping("/getemps")
	public String emps(Map<String,Object> map){
		List<Employee> emps = employeeService.getEmps();
		System.out.println("测试jrebel");
		map.put("allEmps", emps);
		return "list";
	}

}
