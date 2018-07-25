package mywechat.dao;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SQLTemplate;
import org.apache.cayenne.query.SelectQuery;

import com.pr.cayenne.ext.CayenneUtil;
import com.pr.cayenne.ext.EntityUtils;

import mywechat.store.AddFriendTemp;
import mywechat.store.Users;
import mywechat.vo.VO_AddFriendTemp;
import mywechat.vo.VO_Circle;

public class ShakeFriendDAO {
	//向数据库中添加你的摇一摇记录
	public void saveShakeFriend(String userId,String position) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
		ServerRuntime runtime = new ServerRuntime("cayenne-project.xml");
		ObjectContext context = runtime.getContext();
		String sqlone = "INSERT INTO mywechat.add_friend_temp"
				+ "(user_id,"
				+ "time,"
				+ "position) ";
		String sqltwo  = "values ("
				+userId +",'"
				+time +"','"	
				+position +"')";
		
		SQLTemplate insert  = new SQLTemplate(AddFriendTemp.class,sqlone+sqltwo);
		insert.setFetchLimit(1000);
		context.performGenericQuery(insert);	
	}
	//从数据库中获取记录
	//可能表记录多后会查询会卡..很
	public List<VO_AddFriendTemp> getShakeFriend() {
		Expression exp = ExpressionFactory.matchExp("","");
		SelectQuery query = new SelectQuery(AddFriendTemp.class);
		 return	EntityUtils.packVOList(CayenneUtil.getContext().performQuery(query)
				 , VO_AddFriendTemp.class);
				 
	}
}
