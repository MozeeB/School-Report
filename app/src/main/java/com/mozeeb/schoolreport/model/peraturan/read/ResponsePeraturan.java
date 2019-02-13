package com.mozeeb.schoolreport.model.peraturan.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePeraturan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemPeraturan> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemPeraturan> data){
		this.data = data;
	}

	public List<DataItemPeraturan> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}