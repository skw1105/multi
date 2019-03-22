package edu.autocar.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.autocar.domain.FileInfo;
import edu.autocar.domain.Image;

public interface ImageService {

	List<Image> getGalleryImages(int galleryId) throws Exception;

	public void create(Image image) throws Exception;

	public void saveImage(Image image, MultipartFile file) throws Exception;

	public FileInfo getFileInfo(int imageId) throws Exception;

	public FileInfo getThumbFileInfo(int imageId) throws Exception;
	
	public Image getImage(int imageId) throws Exception;
	
	int deleteByGalleryId(int galleryId) throws Exception;

}
