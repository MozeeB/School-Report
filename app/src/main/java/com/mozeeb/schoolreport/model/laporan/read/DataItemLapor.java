package com.mozeeb.schoolreport.model.laporan.read;

import com.google.gson.annotations.SerializedName;

public class DataItemLapor {

	@SerializedName("wali")
	private String wali;

	@SerializedName("poin")
	private String poin;

	@SerializedName("tgl_lapor")
	private String tglLapor;

	@SerializedName("nama")
	private String nama;

	@SerializedName("foto")
	private String foto;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("pelapor")
	private String pelapor;

	@SerializedName("id")
	private String id;

	@SerializedName("melanggar")
	private String melanggar;

	@SerializedName("keterangan")
	private String keterangan;

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public void setWali(String wali){
		this.wali = wali;
	}

	public String getWali(){
		return wali;
	}

	public void setPoin(String poin){
		this.poin = poin;
	}

	public String getPoin(){
		return poin;
	}

	public void setTglLapor(String tglLapor){
		this.tglLapor = tglLapor;
	}

	public String getTglLapor(){
		return tglLapor;
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

	public void setPelapor(String pelapor){
		this.pelapor = pelapor;
	}

	public String getPelapor(){
		return pelapor;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setMelanggar(String melanggar){
		this.melanggar = melanggar;
	}

	public String getMelanggar(){
		return melanggar;
	}
}