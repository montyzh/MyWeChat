package mywechat.dao;


import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;

import com.pr.cayenne.ext.CayenneUtil;

import mywechat.store.Users;

public class AccountDAO {
	
	public Users doLogin(String cellPhone, String password) {
		Expression exp = ExpressionFactory.matchExp(Users.CELL_PHONE_PROPERTY, cellPhone);
		
		exp = exp.andExp( ExpressionFactory.matchExp(Users.PASSWORD_PROPERTY, password) );
		
		SelectQuery query = new SelectQuery(Users.class, exp);
		
//		List<Users> users=	CayenneUtil.getContext().performQuery(query);
		
		Users user = (Users) Cayenne.objectForQuery(CayenneUtil.getContext(), query);
		
		return user;
	}
}
