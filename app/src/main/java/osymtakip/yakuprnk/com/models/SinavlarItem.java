package osymtakip.yakuprnk.com.models;

import com.google.gson.annotations.SerializedName;


public class SinavlarItem{

	@SerializedName("sonuc_date")
	private String sonucDate;

	@SerializedName("sinav_date")
	private String sinavDate;

	@SerializedName("name")
	private String name;

	@SerializedName("link")
	private String link;

	@SerializedName("gec_basvuru_date")
	private String gecBasvuruDate;

	@SerializedName("basvuru_end_date")
	private String basvuruEndDate;

	@SerializedName("id")
	private String id;

	@SerializedName("created_date")
	private String createdDate;

	@SerializedName("basvuru_start_date")
	private String basvuruStartDate;

	@SerializedName("content")
	private String content;

	public void setSonucDate(String sonucDate){
		this.sonucDate = sonucDate;
	}

	public String getSonucDate(){
		return sonucDate;
	}

	public void setSinavDate(String sinavDate){
		this.sinavDate = sinavDate;
	}

	public String getSinavDate(){
		return sinavDate;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setGecBasvuruDate(String gecBasvuruDate){
		this.gecBasvuruDate = gecBasvuruDate;
	}

	public String getGecBasvuruDate(){
		return gecBasvuruDate;
	}

	public void setBasvuruEndDate(String basvuruEndDate){
		this.basvuruEndDate = basvuruEndDate;
	}

	public String getBasvuruEndDate(){
		return basvuruEndDate;
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

	public void setBasvuruStartDate(String basvuruStartDate){
		this.basvuruStartDate = basvuruStartDate;
	}

	public String getBasvuruStartDate(){
		return basvuruStartDate;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}
}