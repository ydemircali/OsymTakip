package osymtakip.yakuprnk.com.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class DuyuruModel{

	@SerializedName("duyurular")
	private List<DuyurularItem> duyurular;

	@SerializedName("status")
	private boolean status;

	public void setDuyurular(List<DuyurularItem> duyurular){
		this.duyurular = duyurular;
	}

	public List<DuyurularItem> getDuyurular(){
		return duyurular;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}