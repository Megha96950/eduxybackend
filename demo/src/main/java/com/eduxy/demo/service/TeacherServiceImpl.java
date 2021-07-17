package com.eduxy.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.TeacherDAO;
import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;


@Service( value = "teacherService" )
@Transactional
public class TeacherServiceImpl implements TeacherService{
    
	@Autowired
	TeacherDAO teacherDAO;
	

	
	@Override
	public Integer uploadIdPhoto(String userEmailId, MultipartFile idPhoto, Integer id) throws Exception {
		User user=null;
        Integer dataID = teacherDAO.uploadIdPhoto(userEmailId , compressBytes(idPhoto.getBytes()), id) ;

      return dataID;  
	
	}

	@Override
	public Integer uploadDegree(String userEmailId, MultipartFile degreePhoto, Integer id) throws Exception {
        User user=null;
        Integer dataID = teacherDAO.uploadDegree(userEmailId , compressBytes(degreePhoto.getBytes()), id) ;
       
	 return dataID;
	}
   

	@Override
	public void updateDescription(Integer id, String newDescription) {
		  teacherDAO.updateDescription(id,newDescription);
		
	}
	
	


	  public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater(); 
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		          while (!deflater.finished()) {
		              int count = deflater.deflate(buffer);
		              outputStream.write(buffer, 0, count);
		          }
		          try {		  
		             outputStream.close();
		          } catch (IOException e) {
		          }		  
		          System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		  
		          return outputStream.toByteArray();
		  
		      }

	


	
	

}
