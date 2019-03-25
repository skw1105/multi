package edu.autocar.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.autocar.domain.Image;

public interface ImageDao {
	List<Image> getGalleryImages(int boardId) throws Exception;

	Image getImage(int imageId) throws Exception;

	int insert(Image image) throws Exception;

	int delete(int imageId) throws Exception;
}
