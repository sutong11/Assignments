package com.wust.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wust.util.PageModel;
import com.wust.model.User;
import com.wust.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction {
	@Resource
	private UserService userService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private User user;
	PageModel model = null;
	private String pagePath = null;
	private String urlAndQuery = null;

	/**
	 * 用户登录
	 */
	public String userLogin() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// System.out.println(request.getRequestURL());
		List list = null;
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();		
		try{
			list = this.userService.userLogin(user);
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		if (list.size() > 0) {
			json.put("succ", 1);
			this.user = (User) list.get(0);
			request.getSession().setAttribute("user", user);
		} else {
			json.put("succ", 0);
			request.getSession().setAttribute("user", null);
		}
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	public String userLeave() {
		request.getSession().setAttribute("user", null);
		this.pagePath = "/sx.jsp";
		return "pagePath";
	}

	/**
	 * 增加用户
	 * 
	 * @return
	 */
	public String addUser() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String name = user.getUserName();
		JSONObject json = new JSONObject();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean temp = false;
		List list = null;
		try {
			list = this.userService.findUserByName(name);
		} catch (Exception e) {
			temp = true;
		}
		if (temp || list.size()<=0) {
			try{
				this.userService.addUser(user);
			}catch (Exception e) {
				System.out.println(e.toString());
			}
			json.put("succ", "1");
			System.out.println(user.getUserId());
			request.getSession().setAttribute("user", user);
		}else{
			json.put("succ", "0");
			request.getSession().setAttribute("user", null);
		}
		writer.print(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	/**
	 * 个人设置-修改用户信息
	 * 
	 * @return
	 */
	public String editUserInfo() {
		int uid;
		if (request.getParameter("user.userid") != null) {
			uid = Integer.parseInt(request.getParameter("user.userid"));
		} else {
			uid = user.getUserId();
		}
		
		List<User> list = this.userService.findUserById(uid);
		User userTemp = list.get(0);
		// 修改用户信息
		userTemp.setEmail(user.getEmail());
		userTemp.setPhone(user.getPhone());
		userTemp.setSignnature(user.getSignnature());
		PrintWriter writer = null;
		try {
			this.userService.updateUser(userTemp);
			writer = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("succ", "1");
		// 更新session中用户信息
		request.getSession().setAttribute("user", userTemp);
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	/**
	 * 修改密码
	 * 
	 */
	public String editUserPassword(){
		int uid;
		if (request.getParameter("uid") != null) {
			uid = Integer.parseInt(request.getParameter("uid"));
		} else {
			uid = user.getUserId();
		}
		List<User> list = this.userService.findUserById(uid);
		User userTemp = list.get(0);
		// 修改用户密码
		userTemp.setPassword(request.getParameter("psw"));
		PrintWriter writer = null;
		try {
			this.userService.updateUser(userTemp);
			writer = response.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("succ", "1");
		// 更新session中用户信息
		request.getSession().setAttribute("user", userTemp);
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();
		return null;
	}
	/**
	 * 查找用户
	 * 
	 * @return
	 */
	public String findUser() {
		int offset = 0;
		int line = 5;
		if (request.getParameter("offset") != null) {
			offset = Integer.parseInt(request.getParameter("offset"));
		}
		model = this.userService.findUser(offset, line);
		PrintWriter writer = null;
		response.setCharacterEncoding("UTF-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		json.put("model", model);
		System.out.println(json.toString());
		writer.print(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String deleteUser() {
		int id = new Integer(request.getParameter("id"));
		this.userService.deleteUser(id);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		System.out.println(json.toString());
		writer.print(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	/************************* getter setter ***********************/
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPagePath() {
		return pagePath;
	}

	public void setPagePath(String pagePath) {
		this.pagePath = pagePath;
	}

	public String getUrlAndQuery() {
		return urlAndQuery;
	}

	public void setUrlAndQuery(String urlAndQuery) {
		this.urlAndQuery = urlAndQuery;
	}

}
