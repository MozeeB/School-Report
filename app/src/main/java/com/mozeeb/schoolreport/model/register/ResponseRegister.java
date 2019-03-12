package com.mozeeb.schoolreport.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("success")
	private int success;

	@SerializedName("message")
	private String message;

	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}
}