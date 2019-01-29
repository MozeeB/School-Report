package com.mozeeb.schoolreport.model.siswa.insert;

public class ResponseInsertData{
	private int code;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}
