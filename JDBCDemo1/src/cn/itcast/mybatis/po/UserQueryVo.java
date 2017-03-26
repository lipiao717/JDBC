package cn.itcast.mybatis.po;

public class UserQueryVo {
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
