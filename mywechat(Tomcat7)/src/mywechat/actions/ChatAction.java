package mywechat.actions;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.pr.cayenne.ext.EntityUtils;
import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;

import mywechat.dao.ChatDAO;
import mywechat.store.Messages;
import mywechat.store.VLastSessionMessage;
import mywechat.vo.VO_MessageDetail;
import mywechat.vo.auto.VO_VLastSessionMessage;

public class ChatAction extends ActionSupport {

	// 获得会话记录(微信页显示的记录)
	@Request(url = "/getLastSessionMessage")
	public ActionResult getLastSessionMessage(@Inject ChatDAO dao, @Param(name = "selfId") String selfId) {
		List<Messages> messages = dao.getLastSessionMessage(selfId);
		return ActionResult.success(EntityUtils.packVOList(messages, VO_MessageDetail.class));
	}

	// 获得最近和某人之间的聊天记录
	@Request(url = "/getRecentChatByFriend")
	public ActionResult getRecentChatByFriend(@Inject ChatDAO dao, @Param(name = "selfId") String selfId,
			@Param(name = "friendId") String friendId) {
		List<Messages> messages = dao.getRecentChatByFriend(selfId, friendId);
		return ActionResult.success(EntityUtils.packVOList(messages, VO_MessageDetail.class));
	}

	// 向指定的人发送一条消息
	@Request(url = "/sendMessage")
	public ActionResult sendMessage(@Inject ChatDAO dao, @Param(name = "selfId") String selfId,
			@Param(name = "friendId") String friendId, @Param(name = "msgContent") String msgContent, @Param(name = "msgType") String msgType) {
		try {
			Messages message = dao.sendMessage(selfId, friendId, msgType, msgContent);
			ActionResult result = ActionResult.success(EntityUtils.packVO(message, VO_MessageDetail.class));

			WebSocketTest.broadcast(new Gson().toJson(result)); // websocket广播

			return result;
		} catch (Exception e) {
			return ActionResult.failure();
		}
	}
}
