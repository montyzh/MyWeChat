package mywechat.servlet;

import javax.servlet.http.HttpServletRequest;

public class SendMessageActionHandler extends ActionHandler {

	public Object execute(HttpServletRequest req) {
		return "这是SendMessageActionHandler执行后的返回值";
	}
}
