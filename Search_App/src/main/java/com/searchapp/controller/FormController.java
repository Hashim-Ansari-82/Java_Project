package com.searchapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.searchapp.entity.Student;

@Controller
public class FormController {

	@RequestMapping("complex")
	public String showForm() {
		return "complexform";
	}
	@RequestMapping(path = "submitForm" , method=RequestMethod.POST)
	public String submitForm(@ModelAttribute("student") Student student,BindingResult result) {
		
		if(result.hasErrors()) {
			return "complexform";
		}
		System.out.println(student.getName());
		System.out.println(student.getId());
		System.out.println(student.getCourses());
		System.out.println(student.getDob());
		System.out.println(student.getGender());
		System.out.println(student.getType());
		System.out.println(student.getAddress().getCity());
		System.out.println(student.getAddress().getState());
		System.out.println(student.getAddress().getPinCode());
		return "success"; 
	}
}
