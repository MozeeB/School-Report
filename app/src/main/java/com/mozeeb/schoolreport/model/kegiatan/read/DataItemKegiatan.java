package com.mozeeb.schoolreport.model.kegiatan.read;

import com.google.gson.annotations.SerializedName;

public class DataItemKegiatan {

	@SerializedName("foto")
	private String foto;

	@SerializedName("nama_kegiatan")
	private String namaKegiatan;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("jam")
	private String jam;

	@SerializedName("tgl")
	private String tgl;

	@SerializedName("id")
	private String id;

	@SerializedName("tujuan")
	private String tujuan;

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setNamaKegiatan(String namaKegiatan){
		this.namaKegiatan = namaKegiatan;
	}

	public String getNamaKegiatan(){
		return namaKegiatan;
	}

	public void setLokasi(String lokasi){
		this.lokasi = lokasi;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setJam(String jam){
		this.jam = jam;
	}

	public String getJam(){
		return jam;
	}

	public void setTgl(String tgl){
		this.tgl = tgl;
	}

	public String getTgl(){
		return tgl;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTujuan(String tujuan){
		this.tujuan = tujuan;
	}

	public String getTujuan(){
		return tujuan;
	}
}