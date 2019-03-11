package com.mozeeb.schoolreport.model.siswa.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseSiswa{

	@SerializedName("siswa")
	private List<SiswaItem> siswa;

	@SerializedName("status")
	private boolean status;

	public void setSiswa(List<SiswaItem> siswa){
		this.siswa = siswa;
	}

	public List<SiswaItem> getSiswa(){
		return siswa;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}