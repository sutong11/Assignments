package com.wust.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wust.dao.BaseDao;
import com.wust.model.Comment;

@Service
@Transactional
public class CommentService {
	@Resource
	public BaseDao baseDao;
	public void save(Comment comment) {
		this.baseDao.saveObject(comment);
	}
	public List findCommentByPhotoId(int photoId) {
		String hql="from Comment where comment_photo_id=?";
		Object[] values=new Object[]{photoId};
		return this.baseDao.findListByHql(hql, values);
	}

}
