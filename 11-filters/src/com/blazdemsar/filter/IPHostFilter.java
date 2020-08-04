package com.blazdemsar.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class IPHostFilter
 */
@WebFilter("/IPHostFilter")
public class IPHostFilter implements Filter {

    /**
     * Default constructor. 
     */
    public IPHostFilter() {

    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("1) IPHostFilter....");
		System.out.println("Local protocol: " + request.getProtocol() + ", Host: " + request.getLocalName()
		+ ", Port: " + request.getLocalPort() + ", IP Address: " + request.getLocalAddr());
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
		
	}

}
