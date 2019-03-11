package com.mozeeb.schoolreport.model.berita.read;

import com.google.gson.annotations.SerializedName;

public class BeritaItem{

	@SerializedName("konten")
	private String konten;

	@SerializedName("penerbit")
	private String penerbit;

	@SerializedName("tgl_terbit")
	private String tglTerbit;

	@SerializedName("id")
	private String id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("gambar")
	private String gambar;

	public void setKonten(String konten){
		this.konten = konten;
	}

	public String getKonten(){
		return konten;
	}

	public void setPenerbit(String penerbit){
		this.penerbit = penerbit;
	}

	public String getPenerbit(){
		return penerbit;
	}

	public void setTglTerbit(String tglTerbit){
		this.tglTerbit = tglTerbit;
	}

	public String getTglTerbit(){
		return tglTerbit;
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

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}
}