package osymtakip.yakuprnk.com.models;

import com.google.gson.annotations.SerializedName;

public class DuyurularItem{

	@SerializedName("duyuru_date")
	private String duyuruDate;

	@SerializedName("link")
	private String link;

	@SerializedName("id")
	private String id;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("content")
	private String content;

	public void setDuyuruDate(String duyuruDate){
		this.duyuruDate = duyuruDate;
	}

	public String getDuyuruDate(){
		return duyuruDate;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}

	public String getCreatedDate(){
		return createdDate;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
}