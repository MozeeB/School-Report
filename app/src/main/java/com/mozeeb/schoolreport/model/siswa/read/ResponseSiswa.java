package com.mozeeb.schoolreport.model.siswa.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSiswa{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemSiswa> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemSiswa> data){
		this.data = data;
	}

	public List<DataItemSiswa> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}