package com.wust.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wust.dao.BaseDao;
import com.wust.model.Album;

@Service
@Transactional
public class AlbumService {
	@Resource
	public BaseDao baseDao;
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findAlbumByName(String name) {
		String hql="from Album where album_name=?";
		Object[] values=new Object[]{name};
		return this.baseDao.findListByHql(hql, values);
	}

	public void addAlbum(Album album) {
		this.baseDao.saveObject(album);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findAlbumsByUID(int albumUserId) {
		String hql="from Album where album_user_id=?";
		Object[] values=new Object[]{albumUserId};
		return this.baseDao.findListByHql(hql, values);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findAlbumByNameAndUID(String name, int aUserId) {
		String hql="from Album where album_user_id=? and album_name=?";
		Object[] values=new Object[]{aUserId,name};
		return this.baseDao.findListByHql(hql, values);
	}
	
	public void updateAlbum(Album album){
		this.baseDao.updateObject(album);
	}

	public void deleteAlbum(int albumId) throws Exception {
		this.baseDao.deleteObject(Album.class, albumId);
	}

	public List findAlbumById(int albumId) {
		String hql="from Album where album_id=?";
		Object[] values=new Object[]{albumId};
		return this.baseDao.findListByHql(hql, values);
	}
}
