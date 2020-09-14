package com.rz;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class editpassword
 */
@WebServlet("/editpassword")
public class editpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editpassword() {
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
		String oldpassoword=request.getParameter("oldpassoword");
		String newpassword=request.getParameter("newpassword");
		
		String currentid=((Map<String,Object>)request.getSession().getAttribute("currentuser")).get("id").toString();
		//修改密码的条件：新密码是多少   要修改哪个人的密码
		//思路：第一步 验证旧密码是否正确  ，第二步：验证通过后把旧密码改为新密码
		String strsql1="select * from tbusers where id=? and password=?";
		
		List<Object> params = new ArrayList<Object>();
		params.add(currentid);
		params.add(oldpassoword);
		
		DBHelper Dal=new DBHelper();	
		List<Map<String, Object>> list = null;
		try {
			list=Dal.executeQuery(strsql1, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(list.size()>0)
		{
			//旧密码输入正确的情况
			String strsql2="update tbusers set password=? where id=?";
			List<Object> params2 = new ArrayList<Object>();
			params2.add(newpassword);
			params2.add(currentid);
			Dal.excuteSql(strsql2, params2);
			tblog.addmsg(2, "正常修改密码,用户id为:"+currentid, request);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("<font color='green'>密码修改成功 ！</font>");
			response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/passwordedit.jsp");	
			
		}
		else {
			//密码输入错误的 情况
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String msg="修改密码失败,用户id为:"+currentid;
			tblog.addmsg(2, msg, request);
			response.getWriter().write("<font color='green'>你输入的旧密码不正确 ！</font>");
			response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/passwordedit.jsp");	
			
		}
		
		
		
		
	}

}