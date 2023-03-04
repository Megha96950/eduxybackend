package com.eduxy.demo.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.eduxy.demo.model.Student;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;
import com.eduxy.demo.service.TeacherService;


@CrossOrigin
@RestController
@RequestMapping("TeacherAPI")
public class TeacherAPI {

	 @Autowired
	 private TeacherService teacherService;
	 
	 @Autowired
	 private Environment environment;
		
	 static Logger logger = LogManager.getLogger(UserAPI.class.getName());
	 
	 @PostMapping("/uploadId/{userEmailId:.+}/{id:.+}")
	 
     public ResponseEntity<String> uplaodId(@PathVariable("userEmailId") String userEmailId,@RequestParam("id_Photo") MultipartFile idPhoto,@PathVariable("id") Integer id) throws Exception {


		
			try
			{
				
//				User user
				Integer dataID= teacherService.uploadIdPhoto(userEmailId, idPhoto,id);
			
				String message=environment.getProperty("teacherAPI.TEACHER_DATA_ADDED_SUCCESS");
				String s=message+dataID;
				
				
				return new ResponseEntity<String>(s, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		 //System.out.println("Original Image Byte Size - " + idPhoto.getBytes().length);
        // return ResponseEntity.status(HttpStatus.OK);
 
     }
	 
	 
 @PostMapping("/uploadDegree/{userEmailId:.+}/{id:.+}")
	 
     public ResponseEntity<String> uplaodDegree(@PathVariable("userEmailId") String userEmailId,@RequestParam("degree_Photo") MultipartFile degreePhoto,@PathVariable("id") Integer id) throws Exception {

		
			try
			{
				
	
				Integer dataID= teacherService.uploadDegree(userEmailId, degreePhoto,id);
			
				String message=environment.getProperty("teacherAPI.TEACHER_DATA_ADDED_SUCCESS");
			String s=message+dataID;
				
				
				return new ResponseEntity<String>(s, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		
 
     }
 
 @PostMapping("/updateAbout/{id:.+}")
 
 public ResponseEntity<String> updateDescription(@PathVariable("id")Integer id ,@RequestBody String newDescription) throws Exception {
 
	
		try
		{
		
			teacherService.updateDescription(id,newDescription);
		    String message=environment.getProperty("teacherAPI.TEACHER_About_CHANGE");
		    return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		  }	 
	

 }
 
 @PostMapping("/uploadImage/{userEmailId:.+}/{id:.+}")
 public  ResponseEntity<String> uplaodDisplayImg(@PathVariable("userEmailId") String userEmailId,
		  @RequestParam("Display_Image") MultipartFile displayImage,@PathVariable("id") Integer id){
	  try
		{
			
			Integer dataID= teacherService.uplaodDisplayImg(userEmailId, displayImage,id);
		
			String message=environment.getProperty("studentAPI.STUDENT_DISPLAYIMAGE_SUCCESS");
			String s=message+dataID;
			
			
			return new ResponseEntity<String>(s, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		  }	 

 }
 
 @PostMapping("/searchTeacher/{keyword:.+}")
	 
 public ResponseEntity<List<Student>> searchTeacher(@PathVariable String keyword) throws Exception {

            try{
         
			List<Student> students= teacherService.searchStudent(keyword);
			
			String message=environment.getProperty("teacherAPI.STUDENT_SEARCH_STUDENT");
			
			return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
	    }	 
	

 }
 

}
