package com.wust.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.wust.model.Test;
import com.wust.service.TestService;
import com.wust.util.PageModel;

@Controller("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport{
	@Resource
	private TestService testService;
	private HttpServletRequest request = ServletActionContext.getRequest();
	private HttpServletResponse response = ServletActionContext.getResponse();
	private Test test;
	PageModel model = null;
	
	public String photo(){
		System.out.println("i'm  saving photo...");
		execute();
		return null;
	}
	
	private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;
   
    //注意，文件上传时<s:file/>同时与myFile，myFileContentType,myFileFileName绑定
    //所以同时要提供myFileContentType,myFileFileName的set方法
    
    private File myFile;	//上传文件
    private String contentType;//上传文件类型
    private String fileName;	//上传文件名
    private String imageFileName;
    private String caption;//文件说明，与页面属性绑定
   
    public void setMyFileContentType(String contentType)  {
   	 System.out.println("contentType : " + contentType);
        this .contentType = contentType;
   } 

    public void setMyFileFileName(String fileName)  {
   	 System.out.println("FileName : " + fileName);
        this .fileName = fileName;
   } 
       
    public void setMyFile(File myFile)  {
        this .myFile = myFile;
   } 
   
    public String getImageFileName()  {
        return imageFileName;
   } 
   
    public String getCaption()  {
        return caption;
   } 

     public void setCaption(String caption)  {
        this .caption = caption;
   } 
   
    private static void copy(File src, File dst)  {
        try  {
           InputStream in = null ;
           OutputStream out = null ;
            try  {                
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                while (in.read(buffer) > 0 )  {
                   out.write(buffer);
               } 
            } finally  {
                if ( null != in)  {
                   in.close();
               } 
                 if ( null != out)  {
                   out.close();
               } 
           } 
        } catch (Exception e)  {
           e.printStackTrace();
       } 
        
   } 
   
    private static String getExtention(String fileName)  {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
   } 

   @Override
    public String execute()      {        
       imageFileName = new Date().getTime() + getExtention(fileName);
       File imageFile = new File(ServletActionContext.getServletContext().getRealPath("/UploadImages" ) + "/" + imageFileName);
       copy(myFile, imageFile);
        return SUCCESS;
   }

	
	
	/**
	 * check
	 */
	public String check() {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		// System.out.println(request.getRequestURL());
		System.out.println("name="+test.getName());
		System.out.println("value="+test.getValue());
		testService.save(test);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject json = new JSONObject();
		json.put("succ", 1);
		writer.print(json.toString());
		writer.flush();
		writer.close();
		return null;
	}

	
	
	
	
	
	
	public void setTest(Test test) {
		this.test = test;
	}
	public Test getTest() {
		return test;
	}
}
