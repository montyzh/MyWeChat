package mywechat.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class DispatcherServlet extends HttpServlet {
	final private static Map<String, String> ACTION_MAP = new HashMap<>();
	
	static {
		initActionMap();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		try {
			String action = req.getRequestURI();
			String className = ACTION_MAP.get(action);
			
			Object result = null;
			ActionHandler handler = (ActionHandler) Class.forName(className).newInstance();
			result  = handler.execute(req);
			
			String json = new Gson().toJson(result);
			
			resp.getWriter().print(json);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			resp.getWriter().print("{\"code\":-1}");
		}
	}
	
	
	private static void initActionMap() {
		ACTION_MAP.put("/mywechat/getUserList.action", "mywechat.servlet.GetUserListActionHandler");
		ACTION_MAP.put("/mywechat/sendMessage.action", "mywechat.servlet.SendMessageActionHandler");
	}
}
