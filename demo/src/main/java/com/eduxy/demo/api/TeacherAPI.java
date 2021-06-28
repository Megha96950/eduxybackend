package com.eduxy.demo.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
	 
     public ResponseEntity<String> uplaodDegree(@PathVariable("userEmailId") String userEmailId,@RequestParam("degree_Photo") MultipartFile idPhoto,@PathVariable("id") Integer id) throws Exception {

		
			try
			{
				
			//	User user 
				Integer dataID= teacherService.uploadDegree(userEmailId, idPhoto,id);
			
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
}
