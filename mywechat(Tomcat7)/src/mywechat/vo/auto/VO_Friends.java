package mywechat.vo.auto;

import java.lang.Integer;

public class VO_Friends {
    private Integer id;
    private Integer userIdA;
    private Integer userIdB;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserIdA() {
		return userIdA;
	}
	public void setUserIdA(Integer userIdA) {
		this.userIdA = userIdA;
	}
	public Integer getUserIdB() {
		return userIdB;
	}
	public void setUserIdB(Integer userIdB) {
		this.userIdB = userIdB;
	}
}