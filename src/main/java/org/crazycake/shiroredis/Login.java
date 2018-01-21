package org.crazycake.shiroredis;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Login Servlet. Adapted from shiro tutorial.
 * https://shiro.apache.org/tutorial.html#final-tutorial-class
 * @author Alex Yang
 */
public class Login extends HttpServlet {
	
	private static final transient Logger log = LoggerFactory.getLogger(Login.class);
	
	/**
	 * Use shiro-redis to handle session and cached
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		// Try to get session from redis
        Session session = currentUser.getSession();
        
        // Try to set value to redis-based session
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("Retrieved the correct value! [" + value + "]");
        }
        
        String username = request.getParameter("username");
        
        // let's login the current user so we can check against roles and permissions:
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, request.getParameter("password"));
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("There is no user with username of " + token.getPrincipal());
                throw uae;
            } catch (IncorrectCredentialsException ice) {
                log.info("Password for account " + token.getPrincipal() + " was incorrect!");
                throw ice;
            }
        }

        //say who they are:
        //print their identifying principal (in this case, a username):
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
        
        //test a role:
        if ( currentUser.hasRole( "schwartz" ) ) {
            log.info("May the Schwartz be with you!" );
        } else {
            log.info( "Hello, mere mortal." );
        }
        
        // show welcome page
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello " + username + "</h1>");
        response.getWriter().println("<p>session=" + session.getId());
        response.getWriter().println("<p>Redis session and cache are been created!");
        response.getWriter().println("<p>Please use your redis-client to check redis data.");
        response.getWriter().println("<p>You will see the following keys:");
        response.getWriter().println("<p>shiro:cache:" + username);
        response.getWriter().println("<p>shiro:session:" + session.getId());
        response.getWriter().println("<p><a href=\"/logout\">LogOut</a>");
    }
}
