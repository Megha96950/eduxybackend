package com.eduxy.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.eduxy.demo.dao.StudentDAO;

import com.eduxy.demo.model.Teacher;
import com.eduxy.demo.model.User;

@Service( value = "studentService" )
@Transactional
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	public StudentDAO studentDAO;
	

	
	@Override
	public Integer uploadIdPhoto(String userEmailId, MultipartFile idPhoto, Integer id) throws Exception {
		User user=null;
        Integer dataID = studentDAO.uploadIdPhoto(userEmailId , compressBytes(idPhoto.getBytes()), id) ;

      return dataID;  
	
	}
	@Override
	public List<Teacher> searchTeacher(String Keyword) {
		List<Teacher> teachers=studentDAO.searchTeacher(Keyword);
		
		return teachers; 
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
	@Override
	public Integer uplaodDisplayImg(String userEmailId, MultipartFile displayImg,Integer id) throws Exception {
		User user=null;
		System.out.println("uihfaJKHOIA    "+ Base64.getEncoder().encodeToString(displayImg.getBytes()));
        Integer dataID = studentDAO.displayImage(userEmailId , Base64.getEncoder().encodeToString(displayImg.getBytes()),id) ;

      return dataID;
		
	}

	


}
