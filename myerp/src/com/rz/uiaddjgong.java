package com.rz;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/uiaddjgong")
public class uiaddjgong extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uiaddjgong() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	public String getDateFormat(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return df.format(new Date());    
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> obj=(Map<String,Object>)request.getSession().getAttribute("currentuser");	
		String memberid=obj.get("id").toString();
		String tbname=request.getParameter("tbname");		
		String tbaddress=request.getParameter("tbaddress");
		String tbtel=request.getParameter("tbtel");
        String sessionid=request.getSession().getId();
	    
	    String strSqlcarpros="select * from tbjgongcar where sessionid=? ";
	    DBHelper db=new DBHelper();
	    List<Object> params = new ArrayList<Object>();
	    params.add(sessionid);
	    List<Map<String,Object>> carprolist=null;
	    try {
	    	carprolist=db.executeQuery(strSqlcarpros,params);
//	 executeQuery插入   	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    if(!(carprolist.size()>0))
	    {
	    	return;
	    }
//	    	订单没有加入商品，return空    
	    Date t=new Date();
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String orderid=df1.format(t);
	    String createtime=df2.format(t);

	    float sum=0;
//	    循环购物车查询信息，商品加入订单明细
	    for (Map<String, Object> m : carprolist) {
//	    	sum=sum+Float.parseFloat(m.get("price").toString())*Float.parseFloat(m.get("procount").toString());//鍟嗗搧鍗曚环*鍟嗗搧涓暟
//	    	累加商品价格
	    	String strSqlitems="insert into tbsaleorderitems (orderid,proid,proname,procount,imgurl) values (?,?,?,?,?)";
	    	List<Object> paramsitems = new ArrayList<Object>();
	    	paramsitems.add(orderid);
	    	paramsitems.add(m.get("proid"));
	    	paramsitems.add(m.get("proname"));
//	    	paramsitems.add(m.get("price"));
	    	paramsitems.add(m.get("procount"));
	    	paramsitems.add(m.get("imgurl"));
	    	db.excuteSql(strSqlitems, paramsitems);
	    	
	    	//循环修改原料
	    	String strSqll2="update tbcliaozong set zprocount=zprocount-(SELECT procount from tbjgongcar where proid=?) where id=?";
	    	List<Object> paramsitems2 = new ArrayList<Object>();
	    	paramsitems2.add(m.get("proid"));	    	
	    	paramsitems2.add(m.get("proid"));
//	 
	    	db.excuteSql(strSqll2, paramsitems2);
	    	
	    	
	    	
	    }
	    
	   
//	  订单抬头
	    String StrSql1="insert into tbsaleorderhead (orderid,sname,stel,saddress,memberid,ctime) values (?,?,?,?,?,?)";
	    List<Object> params1 = new ArrayList<Object>();
	    params1.add(orderid);
	    params1.add(tbname);
	    params1.add(tbtel);
	    params1.add(tbaddress);
//	    params1.add(sum);
	    params1.add(memberid);
	    params1.add(createtime);
	    db.excuteSql(StrSql1, params1);
	    
	  
	    String strSqlClearCar="delete from tbjgongcar where sessionid=?";
	    db.excuteSql(strSqlClearCar, params);
	    
	    response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
	}

}
