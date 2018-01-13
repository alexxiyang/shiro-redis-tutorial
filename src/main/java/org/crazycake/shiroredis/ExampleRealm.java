package org.crazycake.shiroredis;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * This realm is only for tutorial
 * @author Alex Yang
 *
 */
public class ExampleRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
		// only for tutorial
		authInfo.addRoles(Arrays.asList("schwartz"));
		return authInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
		// Expect password is "123456"
		return new SimpleAuthenticationInfo(usernamePasswordToken.getUsername(), "123456", usernamePasswordToken.getUsername());
	}
	
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(new SimpleCredentialsMatcher());
	}
	
	

}
