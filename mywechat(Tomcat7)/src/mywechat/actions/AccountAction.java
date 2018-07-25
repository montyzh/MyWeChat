package mywechat.actions;

import com.pr.cayenne.ext.EntityUtils;
import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;

import mywechat.dao.AccountDAO;
import mywechat.store.Users;
import mywechat.vo.auto.VO_Users;

public class AccountAction extends ActionSupport {

	@Request(url="/doLogin")
	public ActionResult doLogin(@Inject AccountDAO dao, @Param(name="cellPhone") String cellPhone, @Param(name="password") String password) {
		Users user = dao.doLogin(cellPhone, password);
		if (user != null) {
			return ActionResult.success( EntityUtils.packVO(user, VO_Users.class) );
		} else {
			return ActionResult.failure("登录信息不对!");
		}
	}
}
