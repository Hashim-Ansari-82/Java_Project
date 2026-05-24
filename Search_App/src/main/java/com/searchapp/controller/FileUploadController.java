package com.searchapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping("/fileform")
	public String fileUpload() {
		System.out.println("File Upload Form");
		String str=null;
		System.out.println(str.charAt(0));
		return "fileform";
	}
   @RequestMapping(path="uploadimage" ,method=RequestMethod.POST)
	public String success(@RequestParam("image") CommonsMultipartFile file,HttpSession session,Model model){
	   System.out.println("Uploaded Successfully");
	   System.out.println(file.getName());
	   System.out.println(file.getSize());
	   System.out.println(file.getOriginalFilename());
	   System.out.println(file.getContentType());
	   System.out.println(file.getStorageDescription());
	   byte[] data = file.getBytes();
	   String path = session.getServletContext().getRealPath("/")+
			   "WEB-INF"+File.separator+"resources"+File.separator+"image"+File.separator
			   +file.getOriginalFilename();
	   System.out.println("Path : "+path);
	   try {
	   FileOutputStream fos=new FileOutputStream(path);
	   fos.write(data);
	   fos.close();
	   System.out.println("Uploaded Successfully ....");
	   
	   model.addAttribute("msg","Uploaded Successfully");
	   model.addAttribute("filename",file.getOriginalFilename());
	   
	   }
	   catch(IOException e) {
		   System.out.println("Uploading Error ....");
		   model.addAttribute("msg","Uploaded Failed");
	   }

	   return "uploadimage";
	}
}
