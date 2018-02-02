package com.wust.action;


public class UploadFiles
{
	private String uploadFileName;//�ϴ����ļ����
	private String uploadContentType;//����
	private String uploadRealName;//������ļ���ʵ��ƣ�UUID
	//���ʹ����ݿ�Ļ�������������ֶζ����б���
	public String getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType()
	{
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public String getUploadRealName()
	{
		return uploadRealName;
	}
	public void setUploadRealName(String uploadRealName)
	{
		this.uploadRealName = uploadRealName;
	}
	
	
}
