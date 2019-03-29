package edu.autocar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.autocar.dao.BlogDao;
import edu.autocar.domain.BlogBoard;
import edu.autocar.domain.Image;
import edu.autocar.domain.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class BlogServiceImpl implements BlogService {
	@Autowired
	BlogDao dao;
	
	@Autowired
	ImageService imageService;

	@Override
	public PageInfo<BlogBoard> getPage(String blogHost, int page) throws Exception {
		int totalCount = dao.count(blogHost);
		PageInfo<BlogBoard> pi = new PageInfo<>(page, totalCount);
		List<BlogBoard> list = dao.getPage(blogHost, pi.getStart(), pi.getEnd());
		for (BlogBoard blog : list) {
			List<Image> temp = imageService.getGalleryImages(blog.getBoardId());
			blog.setList(temp);
		}
		pi.setList(list);
		return pi;
	}
	@Override
	public void create(BlogBoard blogBoard, List<MultipartFile> list) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(blogBoard);

		for (int ix = 0; ix < list.size(); ix++) {
			MultipartFile file = list.get(ix);
			Image image = Image.builder().boardId(blogBoard.getBoardId()).orginalName(file.getOriginalFilename())
					.fileSize((int) file.getSize()).mimeType(file.getContentType()).build();
			
			if(image.getOrginalName().equals(null) || image.getOrginalName().equals("")) {
				break;
				
			}else {
				imageService.create(image); // imageId 발급 받음
				imageService.saveImage(image, file);
			}
		}

	}
	
	@Transactional
	@Override
	public BlogBoard getBlog(int boardId) throws Exception {
		// TODO Auto-generated method stub
		dao.increaseReadCnt(boardId);
		BlogBoard blogBoard = dao.findById(boardId);
		
		blogBoard.setList(imageService.getGalleryImages(blogBoard.getBoardId()));
		
		String content = blogBoard.getContent();
		content = content.replace("\r\n", "<br>");
		content = content.replace(" ", "&nbsp");
		blogBoard.setContent(content);
		
		return blogBoard;
	}
	@Override
	public boolean update(BlogBoard blogBoard) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(int boardId, String password) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Image> getImageList(int boardId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Image getImage(int imageId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int insertImage(Image image) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean deleteImage(int imageId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<BlogBoard> findByOwner(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BlogBoard> getBlogBoardList(String blogHost) throws Exception {
		// TODO Auto-generated method stub
		return dao.getBlogBoardListByBlogHost(blogHost);
	}
}