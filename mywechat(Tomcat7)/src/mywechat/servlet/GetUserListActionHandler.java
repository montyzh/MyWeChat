package mywechat.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.cayenne.query.SelectQuery;

import com.pr.cayenne.ext.CayenneUtil;
import com.pr.cayenne.ext.EntityUtils;

import mywechat.store.Users;
import mywechat.vo.auto.VO_Users;

public class GetUserListActionHandler extends ActionHandler {

	public Object execute(HttpServletRequest req) {
		
		SelectQuery query = new SelectQuery(Users.class);
		List<Users> users = CayenneUtil.getContext().performQuery(query);
		
		List<VO_Users> list = EntityUtils.packVOList(users, VO_Users.class);

		return list;
	}
}
