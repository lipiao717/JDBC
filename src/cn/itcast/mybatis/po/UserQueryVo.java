package cn.itcast.mybatis.po;

import java.util.List;

public class UserQueryVo {
	//传入多个id
	private List<Integer> ids;
	
	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}
	//在这里包装所需要的查询条件
	//查询用户条件
	private UserCustom userCustom;
	//可以包装其他查询条件
	//.....

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
	
}
