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

import com.Library.globle.Constant;

/**
 * Servlet Filter implementation class PowerFilter
 */
public class PowerFilter implements Filter {
	
	private String lowPower = null; //非管理员将要跳转的URL

    /**
     * Default constructor. 
     */
    public PowerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.lowPower = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest s_request, ServletResponse s_response, FilterChain chain) throws IOException, ServletException {
		
		//httpServlet类型转换
		HttpServletRequest request = (HttpServletRequest) s_request;
		HttpServletResponse response = (HttpServletResponse) s_response;
		String userType = request.getSession().getAttribute(Constant.USER_TYPE).toString();
		if("0".equals(userType))
		{
			chain.doFilter(request, response);
			return;
		}
		else
		{
			System.out.println("超越权限");
			response.sendRedirect(request.getContextPath()+this.lowPower);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.lowPower = fConfig.getInitParameter("lowPower");
		System.out.println("lowPower "+ this.lowPower);
	}

}
