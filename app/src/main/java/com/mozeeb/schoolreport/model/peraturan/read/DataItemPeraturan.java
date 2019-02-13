package com.mozeeb.schoolreport.model.peraturan.read;

import com.google.gson.annotations.SerializedName;

public class DataItemPeraturan {

	@SerializedName("penulis")
	private String penulis;

	@SerializedName("id")
	private String id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("isi")
	private String isi;

	public void setPenulis(String penulis){
		this.penulis = penulis;
	}

	public String getPenulis(){
		return penulis;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setIsi(String isi){
		this.isi = isi;
	}

	public String getIsi(){
		return isi;
	}
}