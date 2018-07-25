package mywechat.actions;


import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;


import mywechat.dao.RegInfoDAO;


public class RegInfoAction extends ActionSupport {

	@Request(url="/regUserInfo")
	public ActionResult doLogin(@Inject RegInfoDAO dao, @Param(name="cellPhone") String cellPhone, @Param(name="password") String password,@Param(name="nickName") String nickName) {
			dao.regInfo(cellPhone, password, nickName);
		return ActionResult.success();
	}
}
