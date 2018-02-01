package org.crazycake.shiroredis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import redis.clients.jedis.Jedis;

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
		List<String> roles = new ArrayList<String>();
		roles.add("schwartz");
		authInfo.addRoles(roles);
		return authInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;

		AuthenticInfoExample authenticInfoExample = new AuthenticInfoExample();
		authenticInfoExample.setUsername(usernamePasswordToken.getUsername());
		// Expect password is "123456"
		return new SimpleAuthenticationInfo(authenticInfoExample, "123456", usernamePasswordToken.getUsername());
	}
	
	@PostConstruct
	public void initCredentialsMatcher() {
		setCredentialsMatcher(new SimpleCredentialsMatcher());
	}

}
