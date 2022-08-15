package com.hillrent.service;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hillrent.domain.ImageFile;
import com.hillrent.exception.ImageFileException;
import com.hillrent.repository.ImageFileRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImageFileService {

	private ImageFileRepository imageFileRepository;
	
	
	public String saveImage(MultipartFile file) {
		String fileName=StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
		ImageFile imageFile=null;
		try {
			imageFile = new ImageFile(fileName,file.getContentType(),file.getBytes());
		} catch (IOException e) {
			
			throw new ImageFileException(e.getMessage());
			
		}
		imageFileRepository.save(imageFile);
		return imageFile.getId();
		
	
	
	
	}
	
	
	
}
