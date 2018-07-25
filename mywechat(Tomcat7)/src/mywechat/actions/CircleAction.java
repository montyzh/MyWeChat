package mywechat.actions;

import java.util.List;

import com.pr.cayenne.ext.EntityUtils;
import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;

import mywechat.dao.CircleDAO;
import mywechat.store.Circle;
import mywechat.store.Rely;
import mywechat.vo.VO_Circle;
import mywechat.vo.VO_Rely;



public class CircleAction extends ActionSupport {
	@Request(url="/getCircleInfo")
	public ActionResult getCircleInfo(@Inject CircleDAO dao, @Param(name = "userId") String curUserId) {
		List<Circle> circle = dao.getCircleInfo(curUserId);
		return ActionResult.success(EntityUtils.packVOList(circle, VO_Circle.class));
	}
	
	@Request(url="/updateLikePerson")
	public ActionResult updateLikePerson(@Inject CircleDAO dao, @Param(name = "circleId") String circleId,@Param(name = "likePerson") String likeperson ) {
		dao.updateLikePerson(circleId, likeperson);
		return ActionResult.success();
	}
	@Request(url="/insertRely")
	public ActionResult insertRely(@Inject CircleDAO dao, @Param(name = "circleId") String circleId,@Param(name = "content") String content,@Param(name = "nick") String nick ) {
		dao.insertRely(circleId, content, nick);
		return ActionResult.success();
	}
	

	@Request(url="/getRely")
	public ActionResult getRely(@Inject CircleDAO dao, @Param(name = "circleId") String circleId) {
		List<Rely> relys = dao.getRely(circleId);
		if(relys.isEmpty()) {
			return ActionResult.failure();
		}
		else {
			return ActionResult.success(EntityUtils.packVOList(relys, VO_Rely.class));

		}
	}
	
}
