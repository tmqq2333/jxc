package com.rz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class tbtypeadd
 */
@WebServlet("/tbtypeadd")
public class tbtypeadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tbtypeadd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				String typename=request.getParameter("typename");//当前类别的名称
				String parentid=request.getParameter("parentid");//父亲id
				String fullpath="";//全路径
				int levelnum=0;//深度
				String parentname="";//父亲名称
				if(parentid==null||parentid.equals("null")||parentid.equals(""))
				{
					parentid="0";
					levelnum=0;
					parentname="/";
					fullpath="/"+typename;
				}
				else
				{
					//根据父亲id查询父亲信息
					String strSqlforparent="select typename,parentname,parentid,fullpath,levelnum from tbtype where id=?";
					List<Object> paramsforparent = new ArrayList<Object>();
					paramsforparent.add(parentid);
					DBHelper Dal = new DBHelper();
					Map<String, Object> item=Dal.getSingleObject(strSqlforparent, paramsforparent);
					fullpath=item.get("fullpath").toString()+"/"+typename;
					int plevelnum=Integer.valueOf(item.get("levelnum").toString());
					levelnum=plevelnum+1;				
					parentname=item.get("typename").toString();
					
				}
				
				String strSql=" insert into tbtype (typename,parentname,parentid,fullpath,levelnum) values (?,?,?,?,?)";
				//定义参数对象
				List<Object> params = new ArrayList<Object>();
				params.add(typename);
				params.add(parentname);
				params.add(parentid);	
				params.add(fullpath);
				params.add(levelnum);
					
				DBHelper Dal=new DBHelper();		
				Dal.excuteSql(strSql, params);		
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write("<font color='green'>保存成功！</font>");
				response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/tbtypelist");	
	}

}
