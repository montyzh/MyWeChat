package mywechat.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.apache.cayenne.query.SortOrder;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Friends;
import mywechat.store.Messages;
import mywechat.store.Users;
import mywechat.store.VLastSessionMessage;

public class ChatDAO {
	
	// 获得会话记录(微信页显示的记录)
	public List<Messages> getLastSessionMessage(String selfId) {

		Expression exp1 = ExpressionFactory.matchExp(VLastSessionMessage.SENDER_ID_PROPERTY , selfId);
		Expression exp2 = ExpressionFactory.matchExp(VLastSessionMessage.RECEIVER_ID_PROPERTY, selfId);

		Expression exp = exp1.orExp(exp2);
		SelectQuery query = new SelectQuery(VLastSessionMessage.class, exp);
		List<VLastSessionMessage>  sessionMessages = CayenneUtil.getContext().performQuery(query);
		
		List<Integer> messageIds = new ArrayList<>();
		for (VLastSessionMessage sm : sessionMessages) {
			messageIds.add(sm.getId());
		}
		
		Expression expMessage = ExpressionFactory.inExp(Messages.ID_PROPERTY , messageIds);
		SelectQuery queryMessage = new SelectQuery(Messages.class, expMessage);
		queryMessage.addOrdering(Messages.SEND_TIME_PROPERTY, SortOrder.DESCENDING);
		return CayenneUtil.getContext().performQuery(queryMessage);
	}

	// 获得最近和某人之间的聊天记录
	public List<Messages> getRecentChatByFriend(String selfId, String friendId) {

		Expression exp1 = ExpressionFactory.matchExp(Messages.TO_SENDER_PROPERTY + "." + Users.ID_PROPERTY, selfId)
				.andExp(ExpressionFactory.matchExp(Messages.TO_RECEIVER_PROPERTY + "." + Users.ID_PROPERTY, friendId));
		Expression exp2 = ExpressionFactory.matchExp(Messages.TO_SENDER_PROPERTY + "." + Users.ID_PROPERTY, friendId)
				.andExp(ExpressionFactory.matchExp(Messages.TO_RECEIVER_PROPERTY + "." + Users.ID_PROPERTY, selfId));

		Expression exp = exp1.orExp(exp2);
		SelectQuery query = new SelectQuery(Messages.class, exp);
		query.addOrdering(Messages.SEND_TIME_PROPERTY, SortOrder.DESCENDING);
		query.setFetchLimit(20);

		return CayenneUtil.getContext().performQuery(query);
	}

	// 向指定的人发送一条消息
	public Messages sendMessage(String selfId, String friendId, String msgType,String msgContent) {
		ObjectContext context = null;
		Messages message = null;
		try {
			context = CayenneUtil.getContext();
			Users sender = Cayenne.objectForPK(context, Users.class, selfId);
			Users receiver = Cayenne.objectForPK(context, Users.class, friendId);

			if (sender == null || receiver == null) throw new Exception();

			message = context.newObject(Messages.class);
			message.setToSender(sender);
			message.setToReceiver(receiver);
			message.setMsgType(msgType);
			message.setMsgContent(msgContent);
			message.setSendTime(new Date());

			context.commitChanges();
		} catch (Exception e) {
			if (context != null) context.rollbackChanges();
		}
		return message;
	}
}
