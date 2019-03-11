package com.mozeeb.schoolreport.model.kegiatan.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseKegiatan{

	@SerializedName("kegiatan")
	private List<KegiatanItem> kegiatan;

	@SerializedName("status")
	private boolean status;

	public void setKegiatan(List<KegiatanItem> kegiatan){
		this.kegiatan = kegiatan;
	}

	public List<KegiatanItem> getKegiatan(){
		return kegiatan;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}