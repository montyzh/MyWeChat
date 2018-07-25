package mywechat.vo;

import mywechat.vo.auto.VO_Friends;
import mywechat.vo.auto.VO_Users;

public class VO_FriendDetail extends VO_Friends{
	private VO_Users toFriend;
	public VO_Users getToFriend() {
		return toFriend;
	}
	public void setToFriend(VO_Users toFriend) {
		this.toFriend = toFriend;
	}
}