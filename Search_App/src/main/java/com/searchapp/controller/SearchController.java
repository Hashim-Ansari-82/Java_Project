package com.searchapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller class SearchController {

	@RequestMapping("/user/{userId}/{userName}")
	public String getUserDetail(@PathVariable("userId") int userId,@PathVariable("userName") String userName) {
		System.out.println(userId);
		System.out.println(userName);
		Integer.parseInt(userName);
		return "home";
	}
	
	@RequestMapping("/home")
	public String home() {
//		String str=null;
//		System.out.println(str.length());
		System.out.println("Home controller");
		return "home";
	}
	@RequestMapping("/search")
	public RedirectView search(@RequestParam("querybox") String query) {

	    if(query == null || query.trim().isEmpty()) {
	        return new RedirectView("home");   // same page
	    }
		String url="https://www.google.com/search?q="+query;
		RedirectView view = new RedirectView();
		view.setUrl(url);
		return view;
	}
	/*
	 * @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(value=NullPointerException.class) public String
	 * nullPointerException(Model model) {
	 * model.addAttribute("msg","Null Pointer Exception has been occured !!");
	 * return "nullPage"; }
	 * 
	 * @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(value=NumberFormatException.class) public String
	 * numberFormatException(Model model) {
	 * model.addAttribute("msg","Number Format Exception has been occured !!");
	 * return "nullPage"; }
	 * 
	 * @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	 * 
	 * @ExceptionHandler(value=Exception.class) public String
	 * exceptionHandlerGeneric(Model model) {
	 * model.addAttribute("msg","Exception has been occured !!"); return "nullPage";
	 * }
	 */
}
