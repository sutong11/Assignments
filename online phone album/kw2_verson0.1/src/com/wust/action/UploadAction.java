package com.wust.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.wust.util.UploadConfigurationRead;

import com.opensymphony.xwork2.ActionSupport;
@Controller("uploadAction")
@Scope("prototype")
public class UploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private File fileInput[];// ʵ���ϴ��ļ�
	private String fileInputFileName;//�ϴ��ļ���
	private String fileInputContentType; // �ļ�����������
	private static List<UploadFiles> uploadFiles = new ArrayList<UploadFiles>();

	public static List<UploadFiles> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<UploadFiles> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	@Override
	public String execute() throws Exception {
		String myrealPath = "";
		String mypicName = "";
		System.out.println("cccrrraaassssyyyy");
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("utf-8");

			//�ϴ��ļ�����·��
			String targetDirectory = ServletActionContext.getServletContext()
					.getRealPath(
							"/"
									+ UploadConfigurationRead.getInstance()
											.getConfigItem("uploadFilePath")
											.trim());// ���·��

			String fileName = fileInputFileName;// �ϴ����ļ���
			String type = fileInputContentType;// �ļ�����
			String realName = UUID.randomUUID().toString() + getExt(fileName);// ������ļ���ƣ�ʹ��UUID+��׺���б���

			System.out.println("targetDirectory="+targetDirectory);
			System.out.println("fileName="+fileName);
			System.out.println("type="+type);
			System.out.println("realName="+realName);
			
			mypicName = fileName;
			myrealPath = realName;
			File target = new File(targetDirectory, realName);
			FileUtils.copyFile(fileInput[0], target);// �ϴ�����������Ŀ¼��

			UploadFiles uf = new UploadFiles();// �����ļ�
			uf.setUploadContentType(type);
			uf.setUploadFileName(fileName);
			uf.setUploadRealName(realName);

			uploadFiles.add(uf);// ��ӵ���Ҫ�����ļ���List������

			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			application.setAttribute("uploadFiles", uploadFiles);

		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());

			return INPUT;
		}

		String result = mypicName + "//" + myrealPath;
		ServletActionContext.getResponse().getWriter().print(result);//�ɹ��󣬷���success.

		return null;

	}

	public static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public String getFileInputFileName() {
		return fileInputFileName;
	}

	public void setFileInputFileName(String fileInputFileName) {
		this.fileInputFileName = fileInputFileName;
	}

	public File[] getFileInput() {
		return fileInput;
	}

	public void setFileInput(File[] fileInput) {
		this.fileInput = fileInput;
	}

	public String getFileInputContentType() {
		return fileInputContentType;
	}

	public void setFileInputContentType(String fileInputContentType) {
		this.fileInputContentType = fileInputContentType;
	}

}
