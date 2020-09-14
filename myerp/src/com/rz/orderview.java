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

import sun.management.counter.Variability;

/**
 * Servlet implementation class orderview
 */
@WebServlet("/orderview")
public class orderview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String orderid=request.getParameter("orderid"); 
		 DBHelper Dal=new DBHelper();
		 String strSql1=" select * from tborderhead where orderid=? "; 
		 Map<String, Object> objbyid = null;
		 List<Object> params = new ArrayList<Object>();
		 params.add(orderid);
		 objbyid=Dal.getSingleObject(strSql1, params);
		 
		 String strSql2=" select * from tborderitems where orderid=? "; 
		 List<Map<String,Object>> listitems=new ArrayList<Map<String, Object>>();
		 try {
			 listitems=Dal.executeQuery(strSql2, params);
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 
		//视图数据放进材料表里 
		 String strSqp=" select * from v_orderitems"; 
		 List<Map<String,Object>> listitemsp=new ArrayList<Map<String, Object>>();
		 List<Object> paramsp = new ArrayList<Object>();
		 try {
			 listitemsp=Dal.executeQuery(strSqp, paramsp);
		 } catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 for (Object item : listitemsp) {
		 	Map<String, Object> temp=(Map<String, Object>)item;
			String proname=temp.get("proname").toString();
//			Float sumprocount=temp.get("sumprocount").Float.valueOf();
			double sumprocount=(double)temp.get("sumprocount");
			double sumprice=(double)temp.get("sumprice");
			String  strSqlt="update tbcliaozong set zprocount=?,zprice=?  where proname=?";
			
			List<Object> params9 = new ArrayList<Object>();
			params9.add(sumprocount);
			params9.add(sumprice);	
			params9.add(proname);
			Dal.excuteSql(strSqlt, params9);
			
			
		 }		
		 /*	request.setCharacterEncoding("utf-8");
//			//接收参数
//			String username=request.getParameter("username");
//			String password=request.getParameter("password");
//			String truename=request.getParameter("truename");	
//			String tel=request.getParameter("tel");
//			String memo=request.getParameter("memo");
//			String id=request.getParameter("id");	*/	
//		 for (Object item : listitems) {
//			 	Map<String, Object> temp=(Map<String, Object>)item;
//			    String proname=temp.get("proname").toString()();
//			 	
//				 String strSql3=" select procount,price from tborderitems where pronum=? "; 
////				 List<Map<String,Object>> listitems6=new ArrayList<Map<String, Object>>();
////				 listitems6[item]=Dal.executeQuery(strSql2, params);
////				 float a=0;
////				 for (Object item2 : listitems6[item]) {
////					float b=item2.get("procount");
////					a=a+b;
////					
//				
//			 }
//				 
//				 
//			}
			
					
			//int procountres=Integer.parseInt(tbprocount)*Integer.parseInt(t);	
//			List<Object> params2 = new ArrayList<Object>();			
//			params2.add(tbpronum);
//			params2.add(tbpronum);
//			Dal.excuteSql(strSql2, params2);
		
		 
	
		 
		 
		 request.setAttribute("orderhead", objbyid);
		 request.setAttribute("orderitems", listitems);
		 request.getRequestDispatcher("/admin/orderview.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
