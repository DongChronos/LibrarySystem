package com.Library.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class UserFilter
 */
public class UserFilter implements Filter {
	
	private String check = null; //免验证的url
	private String error_url = null; //出现错误跳转的URL
	private String userConstants = null; //保存用户信息的字段
	private Object user = null; //用户对象

    /**
     * Default constructor. 
     */
    public UserFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.check = null;
		this.error_url = null;
		this.userConstants = null;
		this.user = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest s_request, ServletResponse s_response, FilterChain chain) throws IOException, ServletException {
		
		//httpServlet类型转换
		HttpServletRequest request = (HttpServletRequest) s_request;
		HttpServletResponse response = (HttpServletResponse) s_response;
		
		user = request.getSession().getAttribute(userConstants);
		String URL = request.getRequestURI();
		System.out.println("URL " + URL);
		
		String[] checked = check.split(";");
		
		System.out.println("用户是否为空"+user!=null?true:false);
		System.out.println("用户是否为空 " + user);
		
		//用户不为空，检验通过
		if(isChecked(request, checked, URL) && user != null)
		{
			System.out.println("用户不为空，检验通过");
			chain.doFilter(request, response);
			return;
		}
		
		//验证通过,免验证
		if(!isChecked(request, checked, URL))
		{
			System.out.println("验证通过");
			chain.doFilter(request, response);
			return;
		}
		
		System.out.println("UserFilter Error");
		response.sendRedirect(request.getContextPath() + this.error_url);
	}

	/**
	 * 判断是否为免验证URL
	 * @param request
	 * @param checked 免验证的URL集合
	 * @param URL 当前访问的URL
	 * @return
	 */
	private boolean isChecked(HttpServletRequest request, String[] checked, String URL)
	{
		for(String item:checked)
		{
			if((request.getContextPath()+item).equals(URL) || URL.endsWith(".css") || URL.endsWith(".js") || URL.endsWith(".png")|| URL.endsWith(".jpg"))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.check = fConfig.getInitParameter("check");
		this.error_url = fConfig.getInitParameter("error_url");
		this.userConstants = fConfig.getInitParameter("userConstants");
		System.out.println("Filter:"+error_url+"  UserConstants: "+userConstants+"  check: "+this.check);
	}

}
