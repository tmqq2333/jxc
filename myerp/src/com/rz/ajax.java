package com.rz;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

/**
 * Servlet implementation class ajax
 */
@WebServlet("/ajax")
public class ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<tbtype> listall = new ArrayList<tbtype>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajax() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rnum= request.getParameter("rnum");
		switch(rnum){
		    case "1":rolehasmenuadd(request,response);break; 
		    case "2":rolehasmenuremove(request,response);break; 
		    case "3":userhasroleadd(request,response);break; 
		    case "4":userhasroleremove(request,response);break; 
		    case "5":getprotypehtml(request,response);break; 
		    case "6":getprotypehtmlforedit(request,response);break; 
		    case "7":getproviewbynum(request,response);break; 
		    case "8":addtocarbatch(request,response);break; 
		    case "9":getSaleSumPricesByMonth(request,response);break; 
		    default : break;
		}
		
		/**
		 * response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
		
		String jsonstr =JSON.toJSONString(listall); //把查询的结果对象转为json格式的字符串。
		response.getWriter().write(jsonstr);//把字符串发送给小程序。
		 * */
		
	}
	//每月进货总金额报表
	protected void getSaleSumPricesByMonth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String StrSql="SELECT DATE_FORMAT(ctime,'%Y-%m') as ctime,sum(sumprice) as sumprice FROM tborderhead where year(ctime)=? GROUP BY  ctime";
		String cyear=request.getParameter("cyear");
		List<Object> params= new ArrayList<Object>();
		params.add(cyear);
		DBHelper db=new DBHelper();
		List<Map<String, Object>> reslist = null;
		String html="";
		//{"datamonths":["1月","2月","3月"],"dataitems":[100,200,150]}
		try {
			reslist=db.executeQuery(StrSql, params);
			String html_datamonths="[";
			String html_dataitems="[";
			int i=1;
			for (Map<String, Object> m : reslist) {
				if(i==reslist.size())
				{
					html_datamonths+="\""+m.get("ctime")+"\"";
					html_dataitems+=m.get("sumprice");
				}
				else
				{
					html_datamonths+="\""+m.get("ctime")+"\",";
					html_dataitems+=m.get("sumprice")+",";
				}
				i++;
			}
			html_datamonths+="]";
			html_dataitems+="]";
			html="{\"datamonths\":"+html_datamonths+",\"dataitems\":"+html_dataitems+"}";
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(html);
		
	}
	
	
	protected void addtocarbatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idstr=request.getParameter("idstr");
		String[] idarray = idstr.split(",");
		for(String s:idarray)
        {
        	if(!s.equals("0"))
        	{
        		addtocarsinge(request,response,s);
        	}
        }		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
		
	}
	
	protected void addtocarsinge(HttpServletRequest request, HttpServletResponse response,String id) throws ServletException, IOException {
		DBHelper db=new DBHelper();
		String sessionid=request.getSession().getId();
		Boolean flag=false;
    	String StrSqlexist="select * from tbshoppingcar where sessionid=? and proid=? ";
    	List<Map<String, Object>> carrecordlist = null;
    	List<Object> paramsexist = new ArrayList<Object>();
    	paramsexist.add(sessionid);
    	paramsexist.add(id);
    	try {
			carrecordlist=db.executeQuery(StrSqlexist, paramsexist);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	if(carrecordlist.size()>0)
    	{
    		flag=true;
    	}
		if(flag)
		{
			//修改个数
			String strSql1="update tbshoppingcar set procount=procount+1 where sessionid=? and proid=? ";
			List<Object> paramsupdate = new ArrayList<Object>();
			paramsupdate.add(sessionid);
			paramsupdate.add(id);
			db.excuteSql(strSql1, paramsupdate);
		}
		else
		{
			//根据id把商品信息查询出来。
			List<Object> params = new ArrayList<Object>();
			String StrSqlpro="select * from tbproduct where id="+id;
			Map<String, Object> obj=db.getSingleObject(StrSqlpro, params);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String createtime=df.format(new Date());
			//新增
			String strSql2="insert into tbshoppingcar (sessionid,proname,proid,procount,ctime,imgurl,price) values(?,?,?,?,?,?,?) ";
			params.add(sessionid);
			params.add(obj.get("proname"));
			params.add(id);
			params.add(1);
			params.add(createtime);
			params.add(obj.get("imgurl"));
			params.add(obj.get("price"));
			db.excuteSql(strSql2, params);
			
		}
	}
	
	
	public String getDateFormat(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return df.format(new Date());    
	} 

	/**
	 * 进货下单
	 */
	protected void addtoorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Object> obj=(Map<String,Object>)request.getSession().getAttribute("currentmember");	
		String memberid=obj.get("id").toString();
		String tbname=request.getParameter("tbname");
		String tbtel=request.getParameter("tbtel");
		String tbaddress=request.getParameter("tbaddress");
	    String sessionid=request.getSession().getId();
	    
	    String strSqlcarpros="select * from tbshoppingcar where sessionid=? ";
	    DBHelper db=new DBHelper();
	    List<Object> params = new ArrayList<Object>();
	    params.add(sessionid);
	    List<Map<String,Object>> carprolist=null;
	    try {
	    	carprolist=db.executeQuery(strSqlcarpros,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    if(!(carprolist.size()>0))
	    {
	    	return;
	    }
	    	    
	    Date t=new Date();
	    SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String orderid=df1.format(t);
	    String createtime=df2.format(t);

	    int sum=0;
	    for (Map<String, Object> m : carprolist) {
	    	sum=sum+Integer.parseInt(m.get("price").toString())*Integer.parseInt(m.get("procount").toString());//鍟嗗搧鍗曚环*鍟嗗搧涓暟
	    	
	    	String strSqlitems="insert into tborderitems (orderid,proid,proname,price,procount) values (?,?,?,?,?)";
	    	List<Object> paramsitems = new ArrayList<Object>();
	    	paramsitems.add(orderid);
	    	paramsitems.add(m.get("proid"));
	    	paramsitems.add(m.get("proname"));
	    	paramsitems.add(m.get("price"));
	    	paramsitems.add(m.get("procount"));
	    	db.excuteSql(strSqlitems, paramsitems);
	    }
	    
	   
	  
	    String StrSql1="insert into tborderhead (orderid,sname,stel,saddress,sumprice,memberid,ctime) values (?,?,?,?,?,?,?)";
	    List<Object> params1 = new ArrayList<Object>();
	    params1.add(orderid);
	    params1.add(tbname);
	    params1.add(tbtel);
	    params1.add(tbaddress);
	    params1.add(sum);
	    params1.add(memberid);
	    params1.add(createtime);
	    db.excuteSql(StrSql1, params1);
	    
	  
	    String strSqlClearCar="delete from tbshoppingcar where sessionid=?";
	    db.excuteSql(strSqlClearCar, params);
	    
	    response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\"ok\"}");
	}
	
	
	
	
	protected void getproviewbynum(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String pronum=request.getParameter("pronum");
		DBHelper Dal = new DBHelper();
		String strSql = " select * from v_product where pronum=?";		
		List<Object> params = new ArrayList<Object>();
		params.add(pronum);
		Map<String, Object> objbyid = null;
		objbyid=Dal.getSingleObject(strSql, params);	
		String html="{";
		html+="\"id\":\""+objbyid.get("id")+"\",";
		html+="\"proname\":\""+objbyid.get("proname")+"\",";
		html+="\"pronum\":\""+objbyid.get("pronum")+"\",";
		html+="\"fullpath\":\""+objbyid.get("fullpath")+"\",";
		html+="\"price\":\""+objbyid.get("price")+"\",";
		html+="\"imgurl\":\""+objbyid.get("imgurl")+"\",";
		html+="\"procount\":\""+objbyid.get("procount")+"\"";
		html+="}";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write(html);	
	}
	
	
	/*
	 添加菜单权限
	*/
	protected void rolehasmenuadd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		DBHelper Dal=new DBHelper();
		String idstr=request.getParameter("idstr");
		String roleidstr=request.getParameter("roleid");
        String[] idarray = idstr.split(",");
        Connection conn=Dal.getConnection();
        PreparedStatement pstmt;
        try {
        	pstmt = conn.prepareStatement("insert into tbrolehasmenu (menuid,roleid) values (?, ?)");
            for(String s:idarray)
            {
            	if(!s.equals("0"))
            	{
            		pstmt.clearParameters();
                	int sv=Integer.valueOf(s);
                	int roleid= Integer.valueOf(roleidstr);
                	pstmt.setInt(1, sv);
                	pstmt.setInt(2,roleid);
                	pstmt.execute();
            	}
            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		response.getWriter().write("ok");
	}
	
	/*
	 移除菜单权限
	*/
	protected void rolehasmenuremove(HttpServletRequest request,HttpServletResponse response) throws IOException{
		DBHelper Dal=new DBHelper();
		String idstr=request.getParameter("idstr");
		String roleid=request.getParameter("roleid");
		String strSql= "delete from tbrolehasmenu where roleid=? and menuid in ("+idstr+")";
		List<Object> params = new ArrayList<Object>();
		params.add(roleid);
		Dal.excuteSql(strSql, params);		 
		response.getWriter().write("ok");
	}
	
	/*
	 添加菜单权限
	*/
	protected void userhasroleadd(HttpServletRequest request,HttpServletResponse response) throws IOException{
		DBHelper Dal=new DBHelper();
		String idstr=request.getParameter("idstr");
		String useridstr=request.getParameter("userid");
       String[] idarray = idstr.split(",");
       Connection conn=Dal.getConnection();
       PreparedStatement pstmt;
       try {
       	pstmt = conn.prepareStatement("insert into tbuserhasrole (roleid,userid) values (?, ?)");
           for(String s:idarray)
           {
           	if(!s.equals("0"))
           	{
           		pstmt.clearParameters();
               	int sv=Integer.valueOf(s);
               	int userid= Integer.valueOf(useridstr);
               	pstmt.setInt(1, sv);
               	pstmt.setInt(2,userid);
               	pstmt.execute();
           	}
           }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		response.getWriter().write("ok");
	}
	
	/*
	 移除菜单权限
	*/
	protected void userhasroleremove(HttpServletRequest request,HttpServletResponse response) throws IOException{
		DBHelper Dal=new DBHelper();
		String idstr=request.getParameter("idstr");
		String userid=request.getParameter("userid");
		String strSql= "delete from tbuserhasrole where userid=? and roleid in ("+idstr+")";
		List<Object> params = new ArrayList<Object>();
		params.add(userid);
		Dal.excuteSql(strSql, params);		 
		response.getWriter().write("ok");
	}
	
	public void getData(String pid)
	{
		DBHelper Dal = new DBHelper();
		String strSql = " select * from tbtype where parentid=?";		
		List<Object> params = new ArrayList<Object>();
		params.add(pid);
		List<Map<String, Object>> listcurrent = null;
		try {
			listcurrent = Dal.executeQuery(strSql, params);
			for (Object item : listcurrent) {
				Map<String, Object> temp=(Map<String, Object>)item;
				tbtype item2=new tbtype();
				item2.setId((int)temp.get("id"));
				item2.setTypeName(temp.get("typename").toString());
				item2.setParentid((int)temp.get("parentid"));
				item2.setParentName(temp.get("parentname").toString());
				item2.setFullPath(temp.get("fullpath").toString());
				item2.setlevelnum((int)temp.get("levelnum"));
				listall.add(item2);
				getData(temp.get("id").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 获取商品类别html字符串for添加页面
	*/
	protected void getprotypehtml(HttpServletRequest request,HttpServletResponse response) throws IOException{
		listall.clear();
		getData("0");
		String html="<select name='typeid'>";
		for(tbtype item:listall)
		{
			String spacehtml="|--";
			for(int i=1;i<=item.getlevelnum();i++)
			{
				spacehtml+="|--";
			}
			
			html+="<option value='"+item.getId()+"'>"+spacehtml+item.getTypeName()+"</option>";
		}
		html+="</select>";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\""+html+"\"}");		
	}
	
	/*
	 获取商品类别html字符串for编辑页面
	*/
	protected void getprotypehtmlforedit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String typeid=request.getParameter("typeid");
		listall.clear();
		getData("0");
		String html="<select name='typeid'>";
		for(tbtype item:listall)
		{
			
			String spacehtml="|--";
			for(int i=1;i<=item.getlevelnum();i++)
			{
				spacehtml+="|--";
			}
			if(String.valueOf(item.getId()).equals(typeid))
			{
				html+="<option value='"+item.getId()+"' selected='selected'>"+spacehtml+item.getTypeName()+"</option>";
			}
			else
			{
				html+="<option value='"+item.getId()+"'>"+spacehtml+item.getTypeName()+"</option>";
			}
		}
		html+="</select>";
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().write("{\"msg\":\""+html+"\"}");		
	}
	
	

}
