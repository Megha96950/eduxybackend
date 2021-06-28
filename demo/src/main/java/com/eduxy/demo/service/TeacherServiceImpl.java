package com.eduxy.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.TeacherDAO;
import com.eduxy.demo.dao.UserDAO;
import com.eduxy.demo.model.User;


@Service( value = "teacherService" )
@Transactional
public class TeacherServiceImpl implements TeacherService{
    
	@Autowired
	TeacherDAO teacherDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Integer uploadIdPhoto(String userEmailId, MultipartFile idPhoto, Integer id) throws Exception {
		User user=null;
        Integer dataID = teacherDAO.uploadIdPhoto(userEmailId , compressBytes(idPhoto.getBytes()), id) ;
//       if(dataID!=null){
//			
//			user = userDAO.getUserByEmailId(userEmailId);
//	}
//	else
//		throw new Exception ("UserService.INVALID_CREDENTIALS");
//	
//	    return user;
      return dataID;  
	
	}

	@Override
	public Integer uploadDegree(String userEmailId, MultipartFile degreePhoto, Integer id) throws Exception {
        User user=null;
        Integer dataID = teacherDAO.uploadDegree(userEmailId , compressBytes(degreePhoto.getBytes()), id) ;
//       if(dataID!=null){
//			
//			user = userDAO.getUserByEmailId(userEmailId);
//	       }
//	    else
//		throw new Exception ("UserService.INVALID_CREDENTIALS");
//	
//	    return user;
       
	 return dataID;
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
