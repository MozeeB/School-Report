package com.mozeeb.schoolreport.model.peraturan.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePeraturan{

	@SerializedName("peraturan")
	private List<PeraturanItem> peraturan;

	@SerializedName("status")
	private boolean status;

	public void setPeraturan(List<PeraturanItem> peraturan){
		this.peraturan = peraturan;
	}

	public List<PeraturanItem> getPeraturan(){
		return peraturan;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}