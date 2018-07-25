package mywechat.actions;

import java.util.ArrayList;
import java.util.List;

import com.pr.cayenne.ext.EntityUtils;
import com.pr.web.lighter.action.ActionResult;
import com.pr.web.lighter.action.ActionSupport;
import com.pr.web.lighter.annotation.Inject;
import com.pr.web.lighter.annotation.Param;
import com.pr.web.lighter.annotation.Request;
import mywechat.dao.ShakeFriendDAO;
import mywechat.store.AddFriendTemp;
import mywechat.vo.VO_AddFriendTemp;

public class ShakeFriendAction extends ActionSupport {
	@Request(url="/shakeFriend")
	public ActionResult shakeFriend(@Inject ShakeFriendDAO dao, @Param(name = "userId") String curUserId, @Param(name = "positions") String position) {		
		//插入数据
		dao.saveShakeFriend(curUserId, position);
		List<VO_AddFriendTemp> ret = matchShakeFriend(dao, curUserId);
		if(ret == null || ret.size() == 0) {
			return ActionResult.failure("啥朋友都没有摇到，换个姿势吧！");
		}
		for (VO_AddFriendTemp i : ret) {
			System.out.println(i.getUserId());
		}
		
		return ActionResult.success(ret);
	}
	
	public List<VO_AddFriendTemp> matchShakeFriend(ShakeFriendDAO dao,String curUserId) {
		List<VO_AddFriendTemp> ret = null;
		
		//得到摇一摇列表
		List<VO_AddFriendTemp> sf = dao.getShakeFriend();
		List<VO_AddFriendTemp> removeList = new ArrayList<VO_AddFriendTemp>();
		//要保存一下自己最近一条摇一摇的信息
		Integer maxMyId = 0;
		VO_AddFriendTemp mySelf = new VO_AddFriendTemp();
		
		for (VO_AddFriendTemp i : sf) {
			//找到是自己的
			if(i.getUserId().equals(Integer.valueOf(curUserId))) {
				if(i.getId() > maxMyId) {
					maxMyId = i.getId();
					mySelf = i;
				}
				removeList.add(i);
			}
		}
		//除去自己
		if(removeList.size() > 0) {
			sf.removeAll(removeList);
		}
			
		if(sf.size()>0) {
			
			ret = MatchNearFriend.match(sf, mySelf);
			
		}
		
		
		//System.out.println(mySelf.getId());
		return ret;
	}
}
