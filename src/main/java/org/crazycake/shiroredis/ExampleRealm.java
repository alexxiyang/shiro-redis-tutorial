package org.crazycake.shiroredis;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * This realm is only for tutorial
 * @author Alex Yang
 *
 */
public class ExampleRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Subject subject = SecurityUtils.getSubject();
		// get user and do whatever you want to do
		UserInfo user = (UserInfo) subject.getPrincipal();

		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// only for tutorial
		List<String> roles = new ArrayList<String>();
		roles.add("schwartz");
		authInfo.addRoles(roles);
		return authInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(usernamePasswordToken.getUsername());
		userInfo.setAge(22);
		// set password to 123456
		return new SimpleAuthenticationInfo(userInfo, "123456", getName());
	}
	
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(new SimpleCredentialsMatcher());
	}
}
