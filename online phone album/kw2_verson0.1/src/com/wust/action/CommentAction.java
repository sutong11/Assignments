package com.wust.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wust.model.Comment;
import com.wust.model.Photo;
import com.wust.model.User;
import com.wust.service.CommentService;
import com.wust.service.PhotoService;
import com.wust.service.UserService;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction {
	@Resource
	private CommentService commentService;
	@Resource
	private UserService userService;
	@Resource
	private PhotoService photoService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Comment comment;
	/**
	 * comment_addcomment
	 * 添加照片评论
	 * @author 徐艇
	 */
	public String addcomment(){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		System.out.println("----addcomment---");
		String commentInf = comment.getCommentInfo();
		System.out.println("----addcomment---commentInf="+commentInf);
		String commentUserId = comment.getUser().getUserId().toString();
		System.out.println("----addcomment---commentUserId="+commentUserId);
		String commentPhotoId = comment.getPhoto().getPhotoId().toString();
		System.out.println("----addcomment---commentPhotoId="+commentPhotoId);
		User user = null;
		try{
			int i = -1;
			i = Integer.parseInt(commentUserId);
			System.out.println(i);
			List l = null;
			l = userService.findUserById(i);
			System.out.println(l.size());
			user = (User) (l.get(0));
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		Photo photo = (Photo) ((photoService.findPhotoById(Integer.parseInt(commentPhotoId))).get(0));
		System.out.println(user.getUserName());
		Date dt = new Date();
		comment.setUser(user);
		comment.setPhoto(photo);
		comment.setCommentDate(dt);
		comment.setCommentInfo(commentInf);
		JSONObject json = new JSONObject();
		PrintWriter writer = null;
		try{
			commentService.save(comment);
			json.put("succ", "1");
		}catch (Exception e) {
			json.put("succ", "0");
			System.out.println(e.toString());
		}
		writer.print(json.toString());
		writer.flush();
		writer.close();

		return null;
	}
	
	
	/**
	 * 根据照片ID找到该照片的所有评论
	 * 
	 * @author 徐艇
	 * 20150319
	 */
	public void findCommentByphotoId() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String sphotoId = request.getParameter("pid");
		System.out.println("----findCommentByphotoId---photoId="+sphotoId);
		int photoId = -1;
		if(null != sphotoId){
			photoId = Integer.parseInt(sphotoId);
		}
		int commentNum = 0;
		List tempList = null;
		List<User> userList = new LinkedList<User>();
		try {
			tempList = commentService.findCommentByPhotoId(photoId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(null != tempList){
			commentNum = tempList.size();
		}
		System.out.println("#######commentNum="+commentNum);
		for(int i = 0;i<commentNum;i++){
			Comment tc = (Comment) tempList.get(i);
			int id = tc.getUser().getUserId();
			System.out.println("-----id="+id);
			User tempUser = null;
			try{
				List templ = userService.findUserById(id);
				if(null != templ){
					System.out.println("------------------templ.size="+templ.size());
					tempUser = (User) templ.get(0);
					userList.add(tempUser);
				}
			}catch (Exception e) {
				System.out.println("no-----"+e.toString());
			}
		}
		System.out.println("----findCommentByphotoId---commentNum="+commentNum);
		request.getSession().setAttribute("tempList", tempList);
		System.out.println("----findCommentByphotoId---userListNum="+userList.size());
		request.getSession().setAttribute("userList", userList);
	}
	
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
