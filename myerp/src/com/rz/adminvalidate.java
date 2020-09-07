package com.rz;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Servlet implementation class adminvalidate
 */
@WebServlet("/adminvalidate")
public class adminvalidate implements Filter {
	private static final long serialVersionUID = 1L;
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String referer = req.getHeader("referer"); 
        String url=req.getRequestURI().toString();
        Object currentmember=req.getSession().getAttribute("currentuser");
        if(currentmember == null||currentmember == ""||currentmember.equals("")){
        	if(!url.contains("login"))
        	{
        		
        		
				try{	
				    response.reset();   
				    request.setCharacterEncoding("UTF-8");
				    response.setContentType("text/html; charset=UTF-8");
				    ServletOutputStream servletOutputStream  = res.getOutputStream();
				    //String s = "咨询成功！";
				    //用js实现转页
				    //servletOutputStream.println("<script language='JavaScript'>alert('"+s+"')         </script>");
				    servletOutputStream.println("<script language=javascript>window.location.href=\"/myerp/admin/login.jsp\"</script>");
				
				  }catch (Exception e) {
				}

        		
        		//String path = "/myerp/admin/login.jsp";
    	        //res.sendRedirect(path);
    	        
    	        
    	        
    	        
        	}
        	else
        	{
        		chain.doFilter(req, res);
        	}
        }else{
        	chain.doFilter(req, res);
        }
    }
	public void init(FilterConfig arg0) throws ServletException {
	}
}
