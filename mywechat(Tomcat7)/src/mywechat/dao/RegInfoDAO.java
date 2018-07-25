package mywechat.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.SQLTemplate;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Circle;

public class RegInfoDAO {
	
	public void regInfo(String cellphone,String password,String nickName) {

		
		
		String portrait = "/portraits/john.jpg";
		ServerRuntime runtime = new ServerRuntime("cayenne-project.xml");
		ObjectContext context = runtime.getContext();
		String sqlone = "INSERT INTO mywechat.users"
				+ "(cell_phone,"
				+ "password,"
				+ "nick_name,"
				+ "portrait) ";
		String sqltwo  = "values ("
				+cellphone +",'"
				+password +"','"
				+nickName +"','"
				+portrait +"')";
		
		SQLTemplate insert  = new SQLTemplate(Circle.class,sqlone+sqltwo);
		insert.setFetchLimit(1000);
		context.performGenericQuery(insert);
	
	}
}
