package mywechat.vo;

import mywechat.vo.auto.VO_Messages;
import mywechat.vo.auto.VO_Users;

public class VO_MessageDetail extends VO_Messages {

	private VO_Users toSender;
	private VO_Users toReceiver;

	public VO_Users getToSender() {
		return toSender;
	}

	public void setToSender(VO_Users toSender) {
		this.toSender = toSender;
	}

	public VO_Users getToReceiver() {
		return toReceiver;
	}

	public void setToReceiver(VO_Users toReceiver) {
		this.toReceiver = toReceiver;
	}

}