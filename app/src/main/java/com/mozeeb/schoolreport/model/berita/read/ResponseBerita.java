package com.mozeeb.schoolreport.model.berita.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBerita{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemBerita> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemBerita> data){
		this.data = data;
	}

	public List<DataItemBerita> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}