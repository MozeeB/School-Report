package com.mozeeb.schoolreport.model.guru.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseGuru{

	@SerializedName("guru")
	private List<GuruItem> guru;

	@SerializedName("status")
	private boolean status;

	public void setGuru(List<GuruItem> guru){
		this.guru = guru;
	}

	public List<GuruItem> getGuru(){
		return guru;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}


}