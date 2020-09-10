/**
 * 
 */
package com.labbol.cocoon.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yelong.support.spring.mvc.interceptor.AbstractHandlerInterceptor;

import com.labbol.core.check.CurrentLoginUserInfo;
import com.labbol.core.check.CurrentLoginUserInfoHolder;
import com.labbol.core.platform.org.model.Org;
import com.labbol.core.platform.user.model.User;

/**
 * @author PengFei
 */
public class TestLoginCheckInterceptor extends AbstractHandlerInterceptor {

	private static final CurrentLoginUserInfo CURRENT_LOGIN_USER_INFO;

	static {
		CURRENT_LOGIN_USER_INFO = new CurrentLoginUserInfo();
		User user = new User();
		user.setId("1");
		user.setIsSuper("1");
		user.setUsername("projectManage");
		user.setRealName("项目开发人员");
		user.setOrgId("1");

		Org org = new Org();
		org.setId("1");
		org.setOrgNo("-1");
		org.setOrgName("项目开发人员");
		CURRENT_LOGIN_USER_INFO.setOrg(org);
		CURRENT_LOGIN_USER_INFO.setUser(user);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		CurrentLoginUserInfoHolder.setCurrentLoginUserInfo(CURRENT_LOGIN_USER_INFO);
		return true;
	}

}
