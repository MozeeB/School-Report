package com.mozeeb.schoolreport.model.laporan.read;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLaporanRead {

	@SerializedName("laporan")
	private List<LaporanItem> laporan;

	@SerializedName("status")
	private boolean status;

	public void setLaporan(List<LaporanItem> laporan){
		this.laporan = laporan;
	}

	public List<LaporanItem> getLaporan(){
		return laporan;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}