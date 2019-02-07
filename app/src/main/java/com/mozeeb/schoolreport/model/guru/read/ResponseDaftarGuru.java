package com.mozeeb.schoolreport.model.guru.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDaftarGuru{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemGuru> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemGuru> data){
		this.data = data;
	}

	public List<DataItemGuru> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}