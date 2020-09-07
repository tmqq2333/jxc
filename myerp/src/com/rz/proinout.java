package com.rz;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class proinout
 */
@WebServlet("/proinout")
public class proinout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proinout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				request.setCharacterEncoding("utf-8");
				String t=request.getParameter("t");
				String tbproid=request.getParameter("tbproid");
				String tbpronum=request.getParameter("tbpronum");
				String tbproname=request.getParameter("tbproname");
				String tbpropath=request.getParameter("tbpropath");
				String tbproimg=request.getParameter("tbproimg");				
				String tbprooldcount=request.getParameter("tbprooldcount");		
				String tbprocount=request.getParameter("tbprocount");		
				String tbprounit=request.getParameter("tbprounit");	
				Date time1=new Date();
			    SimpleDateFormat df1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    String ctime=df1.format(time1);
				String strSql1=" insert into tbinout (opttype,proid,pronum,proname,protype,proimgurl,prooldnum,proaddnum,unitname,ctime) values (?,?,?,?,?,?,?,?,?,?)";
				List<Object> params = new ArrayList<Object>();
				
				params.add(t);
				params.add(tbproid);
				params.add(tbpronum);	
				params.add(tbproname);				
				params.add(tbpropath);				
				params.add(tbproimg);				
				params.add(tbprooldcount);
				params.add(tbprocount);
				params.add(tbprounit);
				params.add(ctime);	
				
				DBHelper Dal=new DBHelper();		
				Dal.excuteSql(strSql1, params);	
				
				// strSql2="update tbproduct set procount=procount+? where pronum=?";
				String strSql2="update tbproduct set procount=(SELECT SUM(opttype*proaddnum) from tbinout where pronum=?) where pronum=?";		
				//int procountres=Integer.parseInt(tbprocount)*Integer.parseInt(t);	
				List<Object> params2 = new ArrayList<Object>();			
				params2.add(tbpronum);
				params2.add(tbpronum);
				Dal.excuteSql(strSql2, params2);
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<font color='green'>入库成功！</font>");
				response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/proinout.jsp?t="+t);	
	}

}
