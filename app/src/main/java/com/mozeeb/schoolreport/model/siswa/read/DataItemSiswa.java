package com.mozeeb.schoolreport.model.siswa.read;

import com.google.gson.annotations.SerializedName;

public class DataItemSiswa {

	@SerializedName("umur")
	private String umur;

	@SerializedName("nama")
	private String nama;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("id")
	private int id;

	@SerializedName("tgl_lahir")
	private String tglLahir;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("foto")
	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public void setUmur(String umur){
		this.umur = umur;
	}

	public String getUmur(){
		return umur;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTglLahir(String tglLahir){
		this.tglLahir = tglLahir;
	}

	public String getTglLahir(){
		return tglLahir;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}
}