package edu.autocar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import edu.autocar.dao.GalleryDao;
import edu.autocar.domain.Gallery;
import edu.autocar.domain.Image;
import edu.autocar.domain.PageInfo;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class GalleryServiceImpl implements GalleryService {
	@Autowired
	GalleryDao dao;
	@Autowired
	ImageService imageService;

	@Override
	public PageInfo<Gallery> getPage(int page) throws Exception {
		int totalCount = dao.count();
		PageInfo<Gallery> pi = new PageInfo<>(page, totalCount);
		List<Gallery> list = dao.getPage(pi.getStart(), pi.getEnd());
		for (Gallery gallery : list) {
			List<Image> temp = imageService.getGalleryImages(gallery.getGalleryId());
			gallery.setList(temp);
		}
		pi.setList(list);
		return pi;
	}

	@Override
	public Gallery getGallery(int galleryId) throws Exception {
		// TODO Auto-generated method stub
		Gallery gallery = dao.findById(galleryId);
		gallery.setList(imageService.getGalleryImages(gallery.getGalleryId()));
		
		String description = gallery.getDescription();
		description = description.replace("\r\n", "<br>");
		description = description.replace(" ", "&nbsp");
		gallery.setDescription(description);
		
		return gallery;
	}

	@Override
	public List<Gallery> findByOwner(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Gallery gallery, List<MultipartFile> list) throws Exception {
		// TODO Auto-generated method stub
		dao.insert(gallery);

		for (int ix = 0; ix < list.size(); ix++) {
			MultipartFile file = list.get(ix);
			Image image = Image.builder().galleryId(gallery.getGalleryId()).orginalName(file.getOriginalFilename())
					.fileSize((int) file.getSize()).mimeType(file.getContentType()).build();

			imageService.create(image); // imageId 발급 받음
			imageService.saveImage(image, file);
		}

	}

	@Override
	public boolean update(Gallery gallery) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int galleryId, String password) throws Exception {
		if (getGallery(galleryId).getPassword().equals(password))
			return dao.delete(galleryId) == 1;
		else
			return false;
	}

	@Override
	public List<Image> getImageList(int galleryId) throws Exception {
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
}