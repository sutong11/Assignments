package com.wust.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wust.model.User;
import com.wust.dao.BaseDao;
import com.wust.util.PageModel;

@Service
@Transactional
public class UserService {	
	@Resource
	public BaseDao baseDao;
	/**
	 * userLogin
	 * @param name
	 * @param pass
	 * @return List
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List userLogin(User user){
		String hql="from User where user_name=? and password=?";
		Object[] values=new Object[]{user.getUserName(),user.getPassword()};
		List list=this.baseDao.findListByHql(hql, values);
		return list;
	}
	/**
	 * addUser
	 * @param user
	 */
	public void addUser(User user){
		this.baseDao.saveObject(user);
	}
	/**
	 * updateUser
	 * @param user
	 */
	public void updateUser(User user){
		this.baseDao.updateObject(user);
	}
	
	
	/**
	 * mngLogin
	 * @param name
	 * @param pass
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List mngLogin(String name,String pass){
		String hql="from Manager as u where u.mng_name=? and u.password=?";
		Object[] values=new Object[]{name,pass};
		List list=this.baseDao.findListByHql(hql, values);
		return list;
	}
	/**
	 * findUser
	 * @param offset
	 * @param line
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public PageModel findUser(int offset, int line) {
		PageModel model = new PageModel();
		String hql="from User";
		String countHql="select count(*) from User";
		int allLine = this.getAllLine(countHql);
		//
		int allPage = 0;
		if(allLine%line!=0)
			allPage=allLine / line +1;
		else
			allPage=allLine / line;
		List<?> userList = this.baseDao.findObjByFenye(hql, offset, line);
		model.setEntityList(userList);
		model.setNextLine(offset+line);
		model.setOnLine(offset);
		model.setPrevLine(offset-line);
		model.setAllPage(allPage);
		model.setAllLine(allLine);
		return model;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findUserById(int id){
		String hql="from User where user_id=?";
		Object[] values=new Object[]{id};
		return this.baseDao.findListByHql(hql, values);
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findUserByName(String name){
		String hql="from User where user_name=?";
		Object[] values=new Object[]{name};
		return this.baseDao.findListByHql(hql, values);
	}
	
	/**
	 * findMng
	 * @param offset
	 * @param line
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public PageModel findMngFenye(int offset, int line) {
		PageModel model = new PageModel();
		String hql="from Manager";
		String countHql="select count(*) from Manager";
		int allLine = this.getAllLine(countHql);
		//
		int allPage = 0;
		if(allLine%line!=0)
			allPage=allLine / line +1;
		else
			allPage=allLine / line;
		List<?> mngList = this.baseDao.findObjByFenye(hql, offset, line);
		model.setEntityList(mngList);
		model.setNextLine(offset+line);
		model.setOnLine(offset);
		model.setPrevLine(offset-line);
		model.setAllPage(allPage);
		model.setAllLine(allLine);
		return model;
	}
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public List findMng(){
		String hql="from Manager";
		List<?> list = this.baseDao.findListByHql(hql, null);
		return list;
	}
	/**
	 * 
	 * @param uid(用户id)
	 * @return list(video_id列表)
	 */
	public PageModel findMyCourse(int uid,int offset, int line){
		PageModel model = new PageModel();
		String hql="select video_id from Collection as c where c.user_id='"+uid+"'";
		String countHql="select count(*) from Collection as c where c.user_id='"+uid+"'";
		int allLine = this.getAllLine(countHql);
		int allPage = 0;
		if(allLine%line!=0)
			allPage=allLine / line +1;
		else
			allPage=allLine / line;
		List list=this.baseDao.findObjByFenye(hql, offset, line);
		model.setEntityList(list);
		model.setNextLine(offset+line);
		model.setOnLine(offset);
		model.setPrevLine(offset-line);
		model.setAllPage(allPage);
		model.setAllLine(allLine);
		return model;
	}
	/**
	 * getAllLine
	 * @param hql
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly = true)
	public int getAllLine(String hql){
		return Integer.parseInt(this.baseDao.singerResult(hql).toString());	
	}
	/**
	 * deleteUser
	 * @param id
	 */
	public void deleteUser(int id ){
		try {
			this.baseDao.deleteObject(User.class, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
