package mywechat.actions;

import java.util.List;

import com.pr.cayenne.ext.EntityUtils;
import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;

import mywechat.dao.FriendDAO;
import mywechat.store.Friends;
import mywechat.store.Users;
import mywechat.vo.VO_FriendDetail;
import mywechat.vo.auto.VO_Users;

public class FriendAction extends ActionSupport {
	@Request(url="/getMyFriends")
	public ActionResult getMyFriends(@Inject FriendDAO dao, @Param(name = "userId") String curUserId) {
		List<Users> friends = dao.getMyFriends(curUserId);
		return ActionResult.success(EntityUtils.packVOList(friends, VO_Users.class));
	}
	
	@Request(url="/addFriend")
	public ActionResult addFriend(@Inject FriendDAO dao, @Param(name = "selfId") String curUserId,  @Param(name = "friendId") String friendId) {
		
		try {
			Users friend = dao.addFriend(curUserId, friendId);
			if (friend == null) return ActionResult.failure("哟, 没加成~ 再试试...");
			return ActionResult.success(EntityUtils.packVO(friend, VO_Users.class));
		} catch (Exception e) {
			return ActionResult.failure(e.getMessage());
		}
	}
}
