package com.mozeeb.schoolreport.model.kegiatan.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKegiatan{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemKegiatan> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemKegiatan> data){
		this.data = data;
	}

	public List<DataItemKegiatan> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}