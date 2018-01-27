package org.crazycake.shiroredis;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Login Servlet. Adapted from shiro tutorial.
 * https://shiro.apache.org/tutorial.html#final-tutorial-class
 * @author Alex Yang
 */
public class Logout extends HttpServlet {
	
	private static final transient Logger log = LoggerFactory.getLogger(Logout.class);
	
	/**
	 * Use shiro-redis to handle session and cached
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {

		Subject currentUser = SecurityUtils.getSubject();

        // let's login the current user so we can check against roles and permissions:
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }

        // jump to login age
        response.sendRedirect("/index.html");
    }

    public static void main(String[] args) {
        int temp1 = 100;
        int temp2 = 200;
        int temp3 = temp1 + temp2;
        List<String> array = new ArrayList();
        array.add("1");
        array.add("2");
        for (int i = 0; i < array.size(); i++) {
            String s =  array.get(i);
            System.out.println(s);
        }

        System.out.println(temp3);
        System.out.println(temp2);
    }
}
