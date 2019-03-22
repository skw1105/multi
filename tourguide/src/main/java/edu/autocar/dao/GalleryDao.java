package edu.autocar.dao;

import java.util.List;

import edu.autocar.domain.Gallery;

public interface GalleryDao extends CruDao<Gallery, Integer> {
	List<Gallery> findByOwner(int galleryId) throws Exception;
}
