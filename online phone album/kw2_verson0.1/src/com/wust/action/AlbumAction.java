package com.wust.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wust.model.Album;
import com.wust.model.Photo;
import com.wust.model.User;
import com.wust.service.AlbumService;
import com.wust.service.PhotoService;
import com.wust.service.UserService;
import com.wust.util.PageModel;

@Controller("albumAction")
@Scope("prototype")
public class AlbumAction {
	@Resource
	private AlbumService albumService;
	@Resource
	private UserService userService;
	@Resource
	private PhotoService photoService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Album album;
	private User user;
	PageModel model = null;

	/**
	 * 创建相册 author 徐艇
	 */
	public String addAlbum() {
		int a_userId = Integer.parseInt(request
				.getParameter("album.albumuserid"));
		System.out.println("---start addAlbum---");
		System.out.println("---a_userId=" + a_userId);
		List l = null;
		try {
			l = userService.findUserById(a_userId);
			System.out.println("---find user no error---");
		} catch (Exception e) {
			System.out.println("---find user exception---");
			e.printStackTrace();
		}
		user = (User) l.get(0);
		System.out.println("---l.size=" + l.size());
		album.setUser(user);
		album.setAlbumAuthority(Integer.parseInt(request
				.getParameter("album.authority")));
		Date dt = new Date();
		album.setAlbumDate(dt);
		album.setPhotoNum(0);
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String name = album.getAlbumName();
		JSONObject json = new JSONObject();
		PrintWriter writer = null;
		List tempL = null;
		try {
			System.out.println("---try:find album---");
			tempL = albumService.findAlbumByNameAndUID(name, a_userId);
			System.out.println("---try:find album over ---");
		} catch (Exception e) {
			System.out.println("---try:find album exception ---");
			System.out.println(e.toString());
		}
		if (tempL.size() > 0) {
			System.out.println("---tempL.size()=" + tempL.size());
			json.put("succ", "0");
			writer.print(json.toString());
			writer.flush();
			writer.close();
			return null;
		}
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("---this.albumService.addAlbum(album)------");
			this.albumService.addAlbum(album);
		} catch (Exception e) {
			System.out.println(e.toString());
			json.put("succ", "0");
			request.getSession().setAttribute("album", null);
			writer.print(json.toString());
			writer.flush();
			writer.close();
			return null;
		}
		json.put("succ", "1");
		request.getSession().setAttribute("album", album);
		writer.print(json.toString());
		writer.flush();
		writer.close();
		System.out.println("---addAlbum(album)------over ,return null;");
		return null;
	}

	public String findAlbums() {
		int album_userId = ((User) request.getSession().getAttribute("user"))
				.getUserId();
		System.out.println("album_userId=" + album_userId);
		JSONObject json = new JSONObject();
		PrintWriter writer = null;
		int a_totl = 0;
		List list = null;
		try {
			list = albumService.findAlbumsByUID(album_userId);
		} catch (Exception e) {
			json.put("succ", "0");
			request.getSession().setAttribute("albumNum", 0);
			System.out.println("exception:---albumNum=0");
		}
		if (list.size() > 0) {
			json.put("succ", "1");
			a_totl = list.size();
			request.getSession().setAttribute("albumNum", a_totl);
			int temp = 0;
			while (temp < a_totl) {
				String s = "album" + temp;
				Album tempAlbum = (Album) list.get(temp);
				request.getSession().setAttribute(s, tempAlbum);
				if (tempAlbum.getPhotoNum() > 0) {
					Photo temoPhoto = (Photo) (photoService
							.findPhotoByAlbumId(tempAlbum.getAlbumId())).get(0);
					String pid = "photo" + temp;
					request.getSession().setAttribute(pid, temoPhoto);
				}
				temp++;
			}
		} else {
			json.put("succ", "2");
			request.getSession().setAttribute("albumNum", 0);
		}
		writer.print(json.toString());
		writer.flush();
		writer.close();
		System.out.println("return hello world");
		return "hello world";
	}

	public void deleteAlbum() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		String ts = request.getParameter("tempdelete");
		System.out.println("---deleteAlbums------ts=" + ts);
		int albumId = Integer.parseInt(ts);
		System.out.println("---deleteAlbums------albumId=" + albumId);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		try {
			albumService.deleteAlbum(albumId);
			json.put("succ", 0);
		} catch (Exception e) {
			json.put("succ", 1);
			System.out.println(e.toString());
		}
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();

	}

	/*----------------------getter,setter-------------*/
	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
