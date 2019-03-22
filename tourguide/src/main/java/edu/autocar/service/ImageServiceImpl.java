package edu.autocar.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.autocar.dao.ImageDao;
import edu.autocar.domain.FileInfo;
import edu.autocar.domain.Image;
import edu.autocar.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ImageServiceImpl implements ImageService {
	final static String IMAGE_PATH = "c:/temp/gallery/%05d";
	// 원본은 gif, jpg 등 다를 수 있음
	final static String THUMB_PATH = "c:/temp/gallery/thumb/%05d.jpg";

	@Autowired
	ImageDao dao;

	@Transactional
	@Override
	public void create(Image image) throws Exception {
		dao.insert(image); // imageId 발급 받음
	}

	@Override
	public void saveImage(Image image, MultipartFile file) throws Exception {
		String imagePath = String.format(IMAGE_PATH, image.getImageId());
		String thumbPath = String.format(THUMB_PATH, image.getImageId());
		File imageFile = new File(imagePath);
		File thumbFile = new File(thumbPath);
		file.transferTo(imageFile); // 업로드 파일 이동
		ImageUtil.thumb(imageFile, thumbFile); // 썸네일 이미지 파일 생성
	}

	@Override
	public List<Image> getGalleryImages(int galleryId) throws Exception {
		return dao.getGalleryImages(galleryId);
	}

	@Override
	public FileInfo getFileInfo(int imageId) throws Exception {
		Image image = getImage(imageId);

		return new FileInfo(String.format(IMAGE_PATH, image.getImageId()), image.getOrginalName(), image.getMimeType(),
				image.getFileSize());
	}

	@Override
	public FileInfo getThumbFileInfo(int imageId) throws Exception {
		Image image = getImage(imageId);
		return new FileInfo(String.format(THUMB_PATH, image.getImageId()), image.getOrginalName(), image.getMimeType());
	}

	@Override
	public Image getImage(int imageId) throws Exception {
		// TODO Auto-generated method stub
		return dao.getImage(imageId);
	}

	@Override
	public int deleteByGalleryId(int galleryId) throws Exception {
		return dao.delete(galleryId);
	}

}
