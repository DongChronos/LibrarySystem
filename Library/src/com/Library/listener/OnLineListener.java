package com.Library.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.Library.globle.Constant;
import com.Library.utils.Utils;

/**
 * Application Lifecycle Listener implementation class OnLineListener
 * 单机监听，控制统一用户不会多可呼段同时在线
 */
@WebListener
public class OnLineListener implements HttpSessionListener, HttpSessionAttributeListener {
	
    /**
     * Default constructor. 
     */
    public OnLineListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    @Override
    public void sessionCreated(HttpSessionEvent event)  { 
    	System.out.println(getTime() + "  创建session " + event.getSession().getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent event)  { 
    	System.out.println(getTime() + " 销毁session " + event.getSession().getId());
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	//添加用户
		String name = event.getName();
		if(name.equals(Constant.USER_KEY))
		{
			Object object = event.getValue();
			String userInfoID = null;
			userInfoID = Utils.getUserInfor(object).getUserID() + "";
			addToApplication(userInfoID, event.getSession());
		}
    }

    /**
	 * 添加在线新用户到session队列中
	 * @param userInfoID
	 * @param session
	 */
	@SuppressWarnings("unchecked")
	private void addToApplication(String userInfoID, HttpSession session) {
		//获取application作用域
		ServletContext servletContext = session.getServletContext();
		//若在线用户为空，初始化
		if(servletContext.getAttribute(Constant.ONLINE_USERS) == null)
		{
			//初始化一个队列
			servletContext.setAttribute(Constant.ONLINE_USERS, new HashMap<String,String>());
		}
		
		if(servletContext.getAttribute(Constant.ONLINE_SESSION) == null)
		{
			servletContext.setAttribute(Constant.ONLINE_SESSION, new HashMap<String,HttpSession>());
		}
		
		//存放入session中
		HashMap<String, String> userMap = (HashMap<String, String>) servletContext.getAttribute(Constant.ONLINE_USERS);
		HashMap<String, HttpSession> sessionMap = (HashMap<String, HttpSession>) servletContext.getAttribute(Constant.ONLINE_SESSION);
		if(isAvailable(userMap, userInfoID, session))
		{
			userMap.put(userInfoID, session.getId());
			sessionMap.put(userInfoID, session);
		}
		else
		{
			
			HttpSession session1 = sessionMap.get(userInfoID);
			System.out.println("session1: " + session1);
			userMap.put(userInfoID, session.getId());
			sessionMap.put(userInfoID, session);
			session1.invalidate();
		}

	}
	
	/**
	 * 检测用户是否已经登录
	 * @return
	 */
	private boolean isAvailable(HashMap<String, String> userMap,String userInfoID,HttpSession session)
	{
		if(!"".equals(userInfoID) && userMap.get(userInfoID) != null && userMap.get(userInfoID) != session.getId())
		{
			session.setAttribute(Constant.ERROR, "您的账号在其他地方登录，您被迫下线");
			System.out.println("您的账号在其他地方登录，您被迫下线");
			return false;
		}
		return true;
	}

	/**
     * 移除Session中已存在的属性时触发
	 * @event session绑定该属性的事件
     */
    @SuppressWarnings("unchecked")
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	String name = event.getName();
		if(name.equals(Constant.USER_KEY))
		{
			//session中移除用户
			Object object = event.getValue();
			String userInforID = null;
			userInforID = Utils.getUserInfor(object).getUserID() + "";
			HashMap<String, String>userMap = (HashMap<String, String>) event.getSession().getServletContext().getAttribute(Constant.ONLINE_USERS);
			userMap.remove(userInforID);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
    
    /**

	 * 获取系统时间

	 * @return

	 */

	private String getTime() {

		long l = System.currentTimeMillis();

		// new日期对象

		Date date = new Date(l);

		// 转换日期输出格式

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return dateFormat.format(date);

	}
}
