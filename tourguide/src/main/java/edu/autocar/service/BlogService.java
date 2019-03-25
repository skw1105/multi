package edu.autocar.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import edu.autocar.domain.BlogBoard;
import edu.autocar.domain.Image;
import edu.autocar.domain.PageInfo;

public interface BlogService {
	PageInfo<BlogBoard> getPage(String blogHost, int page) throws Exception;

	void create(BlogBoard blogBoard, List<MultipartFile> list) throws Exception;

	BlogBoard getBlog(int boardId) throws Exception;

	boolean update(BlogBoard blogBoard) throws Exception;

	boolean delete(int boardId, String password) throws Exception;

	List<Image> getImageList(int boardId) throws Exception;

	Image getImage(int imageId) throws Exception;

	int insertImage(Image image) throws Exception;

	boolean deleteImage(int imageId) throws Exception;

	List<BlogBoard> findByOwner(String userId) throws Exception;

}
