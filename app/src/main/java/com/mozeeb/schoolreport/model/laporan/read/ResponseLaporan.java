package com.mozeeb.schoolreport.model.laporan.read;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLaporan {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("data")
	private List<DataItemLapor> data;

	@SerializedName("sukses")
	private boolean sukses;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setData(List<DataItemLapor> data){
		this.data = data;
	}

	public List<DataItemLapor> getData(){
		return data;
	}

	public void setSukses(boolean sukses){
		this.sukses = sukses;
	}

	public boolean isSukses(){
		return sukses;
	}
}