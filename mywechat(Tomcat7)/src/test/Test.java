package test;

import java.io.PrintWriter;
import java.util.List;

import org.apache.cayenne.query.SelectQuery;

import com.google.gson.Gson;
import com.pr.cayenne.ext.CayenneUtil;
import com.pr.cayenne.ext.EntityUtils;
import com.pr.utils.re_engineer.ReEngineerForVoUtil;

import mywechat.store.Users;
import mywechat.vo.auto.VO_Users;

public class Test {
	public static void main(String[] args) {
		SelectQuery query = new SelectQuery(Users.class);
		List<Users> users = CayenneUtil.getContext().performQuery(query);
		
		List<VO_Users> list = EntityUtils.packVOList(users, VO_Users.class);

		Gson gson = new Gson();
		
		String json = gson.toJson(list);
		
		System.out.println(json);
	}
}
