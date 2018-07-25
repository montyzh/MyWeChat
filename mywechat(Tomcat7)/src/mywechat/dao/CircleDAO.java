package mywechat.dao;


import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SQLTemplate;
import org.apache.cayenne.query.SelectQuery;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Circle;
import mywechat.store.Rely;





public class CircleDAO {
	
	public List<Circle> getCircleInfo(String curUserId) {
		Expression exp = ExpressionFactory.matchExp(Circle.USER_ID_PROPERTY,curUserId);
		SelectQuery query = new SelectQuery(Circle.class, exp);
		//System.out.println(query);
		@SuppressWarnings("unchecked")
		List<Circle> circle =  CayenneUtil.getContext().performQuery(query);
		return circle;
	}
	
	
	public void updateLikePerson(String circleId,String likeperson) {

		
		ServerRuntime runtime = new ServerRuntime("cayenne-project.xml");
		ObjectContext context = runtime.getContext();

		String sql = "UPDATE`circle` SET `like`='"+ likeperson +"' WHERE `Id`='" + circleId + "';";
		SQLTemplate update  = new SQLTemplate(Circle.class,sql);
		update.setFetchLimit(1000);
		context.performGenericQuery(update);
		
	}
	
	public void insertRely(String circleId,String content,String nick) {

		
		ServerRuntime runtime = new ServerRuntime("cayenne-project.xml");
		ObjectContext context = runtime.getContext();
		String sqlone = "INSERT INTO mywechat.rely"
				+ "(circle_id,"
				+ "content,"
				+ "nick) ";
		String sqltwo  = "values ("
				+circleId +",'"
				+content +"','"
				+nick +"')";
		SQLTemplate insert  = new SQLTemplate(Circle.class,sqlone+sqltwo);
		insert.setFetchLimit(1000);
		context.performGenericQuery(insert);
		
	}
	public List<Rely> getRely(String CircleId){
		Expression exp = ExpressionFactory.matchExp(Rely.CIRCLE_ID_PROPERTY,CircleId);
		SelectQuery query = new SelectQuery(Rely.class, exp);
		
		@SuppressWarnings("unchecked")
		List<Rely> relys =  CayenneUtil.getContext().performQuery(query);
		return relys;
	}
}

