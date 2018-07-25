package mywechat.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SQLTemplate;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Circle;

public class SaveCircleInfo {
	
	public void saveCircleInfo(Integer userId,String text,String image,String Position) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String time = df.format(new Date());
		
		
		
		ServerRuntime runtime = new ServerRuntime("cayenne-project.xml");
		ObjectContext context = runtime.getContext();
		String sqlone = "INSERT INTO mywechat.circle"
				+ "(user_id,"
				+ "text,"
				+ "time,"
				+ "image,"
				+ "position) ";
		String sqltwo  = "values ("
				+userId +",'"
				+text +"','"
				+time +"','"
				+image +"','"
				+Position +"')";
		
		SQLTemplate insert  = new SQLTemplate(Circle.class,sqlone+sqltwo);
		insert.setFetchLimit(1000);
		context.performGenericQuery(insert);
	
	}
}
