package com.wust.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wust.dao.BaseDao;
import com.wust.model.Photo;

@Service
@Transactional
public class PhotoService {
	@Resource
	private BaseDao baseDao;
	
	/**
	 * 保存照片信息 
	 * 
	 * @author 徐艇
	 */
	public void save(Object ectity){
		baseDao.saveObject(ectity);
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findPhotoByAlbumId(int albumId) {
		String hql="from Photo where photo_album_id=?";
		Object[] values=new Object[]{albumId};
		return this.baseDao.findListByHql(hql, values);
	}

	public void deletePhotoByPhotoId(int photoId) throws Exception {
		this.baseDao.deleteObject(Photo.class, photoId);		
	}

	public List findPhotoById(int photoId) {
		String hql="from Photo where photo_id=?";
		Object[] values=new Object[]{photoId};
		return this.baseDao.findListByHql(hql, values);
	}

}
