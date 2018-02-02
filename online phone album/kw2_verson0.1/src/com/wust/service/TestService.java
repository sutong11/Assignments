package com.wust.service;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wust.dao.BaseDao;
import com.wust.model.Test;
@Service
@Transactional
public class TestService {
	@Resource
	private BaseDao baseDao;

	public boolean save(Test test) {
		try {
			baseDao.saveObject(test);
		} catch (Exception e) {
			e.toString();
			return false;
		}
		return true;
	}

}
