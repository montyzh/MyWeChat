package mywechat.vo;

import java.lang.Integer;
import java.lang.String;
import java.util.Date;



public class VO_Circle {
    private Integer id;
    private Integer userId;
    private String text;
    private String time;
    private String image;
    private String position;
    private String like;
    

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}


    

	
}