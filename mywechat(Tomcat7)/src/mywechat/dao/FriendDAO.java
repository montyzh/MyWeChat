package mywechat.dao;


import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Friends;
import mywechat.store.Messages;
import mywechat.store.Users;

public class FriendDAO {
	
	// 查询我的好友
	public List<Users> getMyFriends(String curUserId) {
		Expression exp = ExpressionFactory.matchExp(Users.AS_FRIENDS_ARRAY_PROPERTY + "." + Friends.TO_MY_SELF_PROPERTY + "." + Users.ID_PROPERTY, curUserId);
		SelectQuery query = new SelectQuery(Users.class, exp);
		return	CayenneUtil.getContext().performQuery(query);
	}

	// 加好友
	public Users addFriend(String curUserId, String friendId) throws Exception {
		ObjectContext context = null;
		
		try {
			context = CayenneUtil.getContext();
			
			// 判断是否已是好友
			Expression exp = ExpressionFactory.matchExp(Friends.TO_MY_SELF_PROPERTY + "." + Users.ID_PROPERTY, curUserId).andExp(ExpressionFactory.matchExp(Friends.TO_FRIEND_PROPERTY + "." + Users.ID_PROPERTY, friendId));
			SelectQuery query = new SelectQuery(Friends.class, exp);
			query.setFetchLimit(1);
			List<Friends> friends=	CayenneUtil.getContext().performQuery(query);
			if (!friends.isEmpty()) {
				throw new Exception("你们已经是好友了, 还要怎么样?");
			}
			
			Users self = Cayenne.objectForPK(context, Users.class, curUserId);
			Users friend = Cayenne.objectForPK(context, Users.class, friendId);

			if (self == null || friend == null) throw new Exception("见鬼了~ 你扫的什么呀！");

			Friends friendRec = context.newObject(Friends.class);
			friendRec.setToMySelf(self);
			friendRec.setToFriend(friend);

			context.commitChanges();
			
			return friend;
		} catch (Exception e) {
			if (context != null) context.rollbackChanges();
			throw e;
		}
	}
}
