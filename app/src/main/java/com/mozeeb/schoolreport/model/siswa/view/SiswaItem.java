package com.mozeeb.schoolreport.model.siswa.view;

import com.google.gson.annotations.SerializedName;

public class SiswaItem{

	@SerializedName("pelanggaran")
	private String pelanggaran;

	@SerializedName("poin")
	private String poin;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("id_siswa")
	private String idSiswa;

	@SerializedName("ustad")
	private String ustad;

	public void setPelanggaran(String pelanggaran){
		this.pelanggaran = pelanggaran;
	}

	public String getPelanggaran(){
		return pelanggaran;
	}

	public void setPoin(String poin){
		this.poin = poin;
	}

	public String getPoin(){
		return poin;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setIdSiswa(String idSiswa){
		this.idSiswa = idSiswa;
	}

	public String getIdSiswa(){
		return idSiswa;
	}

	public void setUstad(String ustad){
		this.ustad = ustad;
	}

	public String getUstad(){
		return ustad;
	}
}