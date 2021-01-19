package osymtakip.yakuprnk.com.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SinavDetay{

	@SerializedName("sinavlar")
	private List<SinavlarItem> sinavlar;

	@SerializedName("status")
	private boolean status;

	public void setSinavlar(List<SinavlarItem> sinavlar){
		this.sinavlar = sinavlar;
	}

	public List<SinavlarItem> getSinavlar(){
		return sinavlar;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

}