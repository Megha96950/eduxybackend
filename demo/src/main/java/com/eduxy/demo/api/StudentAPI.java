package com.eduxy.demo.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.service.StudentService;



@CrossOrigin
@RestController
@RequestMapping("StudentAPI")
public class StudentAPI {


	 @Autowired
	 private StudentService studentService;
	 
	 @Autowired
	 private Environment environment;
		
	 static Logger logger = LogManager.getLogger(UserAPI.class.getName());
	 
      @PostMapping("/uploadId/{userEmailId:.+}/{id:.+}")
	 
     public ResponseEntity<String> uplaodId(@PathVariable("userEmailId") String userEmailId,@RequestParam("id_Photo") MultipartFile idPhoto,@PathVariable("id") Integer id) throws Exception {


		
			try
			{
				
				Integer dataID= studentService.uploadIdPhoto(userEmailId, idPhoto,id);
			
				String message=environment.getProperty("studentAPI.STUDENT_IDPHOTO_SUCCESS");
				String s=message+dataID;
				
				
				return new ResponseEntity<String>(s, HttpStatus.OK);
			}
			catch (Exception e) {
				
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
			  }	 
		
 
     }
      @PostMapping("/searchTeacher/{keyword:.+}")
 	 
      public ResponseEntity<List<Teacher>> searchTeacher(@PathVariable String keyword) throws Exception {
    
                 try{
              
 				List<Teacher> teachers= studentService.searchTeacher(keyword);
 			
 				System.out.println(teachers);
 				
 				String message=environment.getProperty("studentAPI.STUDENT_IDPHOTO_SUCCESS");
 				
 				return new ResponseEntity<List<Teacher>>(teachers, HttpStatus.OK);
 			}
 			catch (Exception e) {
 				
 				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
 			  }	 
 		
  
      }
	 
}
