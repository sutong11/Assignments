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
import com.wust.util.PageModel;

@Controller("photoAction")
@Scope("prototype")
public class PhotoAction {
	@Resource
	private PhotoService photoservice;
	@Resource
	private AlbumService albumservice;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Photo photo;

	PageModel model = null;

	public String encToString(String str) {
		if (str == null || "".equals(str) || str.isEmpty())
			return "";
		String s = new String("failed!");
		try {
			byte[] b = str.getBytes("ISO-8859-1");
			s = new String(b);
			return s;
		} catch (Exception e) {
			return s;
		}
	}

	public boolean changeAlbumPhotonum(int photo_id, int n) {
		if (photo_id < 0)
			return false;
		Album album = null;
		Photo photo = null;
		int album_photo_num = 0;
		int album_Id = -1;
		try {

			photo = (Photo) ((photoservice.findPhotoById(photo_id)).get(0));

			album = (Album) (albumservice.findAlbumById(photo.getAlbum()
					.getAlbumId()).get(0));

			album_photo_num = album.getPhotoNum();
		} catch (Exception e) {
			System.out.println("ERROR:changeAlbumPhotonum---" + e.toString());
		}
		album_photo_num += n;
		album.setPhotoNum(album_photo_num);
		albumservice.updateAlbum(album);
		return true;
	}

	public String savePhotoInf() {
		System.out.println("start save photo");
		User user = null;
		Object obj = request.getSession().getAttribute("user");
		if (null == obj) {
			System.out.println("null user");
			return "error";
		}
		user = (User) obj;
		int user_id = user.getUserId();
		System.out.println("Photo ACTION :user_id=" + user_id);

		String albumName = request.getParameter("tfeestype");
		System.out.println("albumName=" + albumName);
		String temp = request.getParameter("realpath");
		String picName = temp.substring(0, temp.indexOf("//"));
		String picPath = temp.substring(temp.indexOf("//") + 2);

		System.out.println("picName=" + picName);
		System.out.println("picPath=" + picPath);
		Album album = null;
		int album_photo_num = 0;
		int albumid;
		List albumList = null;
		try {
			albumList = albumservice.findAlbumByNameAndUID(albumName, user_id);
		} catch (Exception e) {

		}
		if (albumList.size() > 0) {
			System.out.println("找到相册！");
			album = (Album) albumList.get(0);
			albumid = album.getAlbumId();
			album_photo_num = album.getPhotoNum() + 1;
			album.setPhotoNum(album_photo_num);
		} else {
			System.out.println("未找到相册！");
			return null;
		}
		Date dt = new Date();
		photo = new Photo();
		photo.setPhotoName(picName);
		photo.setAlbum(album);
		photo.setPhotoUrl(picPath);
		photo.setPhotoInfo("");
		photo.setPhotoDate(dt);
		try {
			photoservice.save(photo);
			albumservice.updateAlbum(album);
		} catch (Exception e) {
			System.out.println("保存失败" + e.toString());
		}
		return null;
	}

	/**
	 * 根据相册ID找到该相册下面的所有照片
	 * 
	 * Id_forFindPhotos是相册ID
	 * @author xuting 
	 */
	public void findPhotosById() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");		
		Object obj = request.getSession().getAttribute("Id_forFindPhotos");
		String ts = "-1";
		int albumId = -1;
		if (null != obj) {
			ts = obj.toString();
			albumId = Integer.parseInt(ts);
			System.out.println("---findPhotosById------albumId=" + albumId);
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		List photoList = null;
		if (albumId != -1) {
			try {
				photoList = photoservice.findPhotoByAlbumId(albumId);
			} catch (Exception e) {
				json.put("succ", 1);
				request.getSession().setAttribute("photoList", null);
				System.out.println("---findPhotosById------exception");
				
			}
			if (photoList.size() > 0) {
				json.put("succ", 0);
				request.getSession().setAttribute("photoList", photoList);
			}
		} else {
			json.put("succ", 1);
			request.getSession().setAttribute("photoList", null);
			System.out.println("---findPhotosById------NULL:albumId");
		}
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();

	}
	
	/**
	 * 根据照片ID找到该照片
	 * 
	 * 
	 * @author xuting 
	 */
	public void findPhotoById() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("commentphoto",null);
		Object obj = request.getSession().getAttribute("commentphotoid");
		String ts = "-1";
		int photoId = -1;
		if (null != obj) {
			ts = obj.toString();
			photoId = Integer.parseInt(ts);
			System.out.println("---findPhotoById------photoId=" + photoId);
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		List photoList = null;
		if (photoId != -1) {
			try {
				photoList = photoservice.findPhotoById(photoId);
			} catch (Exception e) {
				json.put("succ", 1);
				request.getSession().setAttribute("photo", null);
				System.out.println("---findPhotoById------exception");
				
			}
			if (photoList.size() > 0) {
				json.put("succ", 0);
				request.getSession().setAttribute("photo", (Photo)(photoList.get(0)));
			}
		} else {
			json.put("succ", 1);
			request.getSession().setAttribute("photo", null);
			System.out.println("---findPhotoById------NULL:pId");
		}
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();

	}

	public void deletePhoto() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String ts = request.getParameter("tempdelete");
		int photoId = -1;
		if (null != ts) {
			photoId = Integer.parseInt(ts);
			System.out.println("---deletePhoto------photoId=" + photoId);
		}
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject json = new JSONObject();
		if (photoId != -1) {
			try {
				System.out.println("---deletePhoto------try");
				changeAlbumPhotonum(photoId, -1);
				System.out.println("---deletePhoto------changeAlbumPhotonum");
				photoservice.deletePhotoByPhotoId(photoId);
				System.out.println("---deletePhoto------deletePhotoByPhotoId");
				json.put("succ", 0);
				request.getSession().setAttribute("photoList", null);
			} catch (Exception e) {
				json.put("succ", 1);
				System.out.println(e.toString());
			}

		} else {
			json.put("succ", 1);
			System.out.println("---deletePhoto------NULL:photoId");
		}
		writer.print(json.toString());
		System.out.println(json.toString());
		writer.flush();
		writer.close();
	}

	public void photo() {
		System.out.println("start photo");
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

}
