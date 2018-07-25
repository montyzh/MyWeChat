package mywechat.actions;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import mywechat.vo.VO_AddFriendTemp;

public class MatchNearFriend {
	public static List<VO_AddFriendTemp> match(List<VO_AddFriendTemp> temp,VO_AddFriendTemp mySelf) {

		//拆分自己的经度纬度
		String[] myp = mySelf.getPosition().split("-");
		BigDecimal myjd = new BigDecimal(myp[0]);
		BigDecimal mywd = new BigDecimal(myp[1]);
		java.util.Date mydate = null;
		List<VO_AddFriendTemp> ret = new ArrayList<VO_AddFriendTemp>();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			mydate =  df.parse(mySelf.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (VO_AddFriendTemp i : temp) {
			//拆分经度纬度
			String[] p = i.getPosition().split("-");
			//精度处理
			BigDecimal jd = new BigDecimal(p[0]);
			BigDecimal wd = new BigDecimal(p[1]);
			//10公里内
			if((Math.abs(jd.subtract(myjd).doubleValue())<0.1) 
					&& (Math.abs(wd.subtract(mywd).doubleValue())<0.1)) {
				
				
				java.util.Date date = null;
				long diff = 0;
				try {
					date =  df.parse(i.getTime());
					diff= mydate.getTime() - date.getTime();
					
					diff = diff/1000;//转换为秒
					
				}catch (Exception e) {
					// TODO: handle exception
				}
				//60秒内
				if(diff < 30) {
					//符合标准
					ret.add(i);
				}
				
			}
			
		}
		return ret;
	}
	
	
}
