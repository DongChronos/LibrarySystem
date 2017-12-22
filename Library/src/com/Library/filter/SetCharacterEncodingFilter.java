package com.Library.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 编码格式类
 * @author ubuntu
 *
 */
public class SetCharacterEncodingFilter implements Filter {

	protected String encoding = null;
	
	@Override
	public void destroy() {
		this.encoding = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//判断当前请求是否单独设置编码格式
		if(request.getCharacterEncoding() == null)
		{
			//获取默认编码格式
			String encoding = this.encoding;
			if(encoding != null)
			{
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");
	}

}
