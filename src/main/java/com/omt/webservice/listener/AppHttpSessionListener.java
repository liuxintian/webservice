package com.omt.webservice.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
1. Session time out kills the session only if time gap between two concussive request is more then the configured time.
2. web.xml take timeout in Minutes while session.setMaxInactiveInterval() takes time in Seconds.
3. web.xml configuration is suitable if you want to set timeout globally.
4. session.setMaxInactiveInterval() is suitable if you want to set timeout based on some condition, e.g based on user role.
5. avoid to set infinite timeout. 

 * @author tonyliu
 *
 */
public class AppHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// Allocate session time for a current event session, top level than in MySuccessHandler.java
		event.getSession().setMaxInactiveInterval(30 * 60); 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// session destroyed
	}
}
