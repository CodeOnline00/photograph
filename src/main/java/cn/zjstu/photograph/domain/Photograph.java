package cn.zjstu.photograph.domain;

import java.io.Serializable;
import java.util.Date;

public class Photograph implements Serializable{
	private Integer id;
	private Integer categoryid;
	private String title;
	private String description;
	private String keywords;
	private String image;
	private String icon;
	private Date datetime;
	
	//用于service层和controller层数据交换
	private boolean flag;
	private String tem_msg;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getTem_msg() {
		return tem_msg;
	}
	public void setTem_msg(String tem_msg) {
		this.tem_msg = tem_msg;
	}
	@Override
	public String toString() {
		return "Photograph [id=" + id + ", categoryid=" + categoryid + ", title=" + title + ", description="
				+ description + ", keywords=" + keywords + ", image=" + image + ", icon=" + icon + ", datetime="
				+ datetime + "]";
	}
	
	
	

}
