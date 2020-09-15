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
 * Servlet implementation class jgongforsearch
 */
@WebServlet("/jgongforsearch")
public class jgongforsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jgongforsearch() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			int currentpage = 1;
			request.setCharacterEncoding("utf-8");
			String key=request.getParameter("key");
			if(key==null||key.equals("")||key.equals("null"))
//				"null"没有找到字符的null，所以下一页没有数据
			{
				key="";
			}
			else {
				key = new String(key.getBytes("iso-8859-1"), "utf-8");//解决中文乱码的问题
			}
			try {
				//分页
				String p = request.getParameter("p");
				currentpage = Integer.valueOf(p);
			} catch (Exception e) {
				currentpage = 1;
			}
			DBHelper Dal = new DBHelper();
			String strSql = " select id from v_canpitem order by id desc ";
			//所有的id
			if(!(key==null||key.equals("")))
			{
				strSql = " select id from v_canpitem where proname like '%"+key+"%' or pronum like '%"+key+"%' order by id desc ";
			//匹配proname or pronum
			}
			
			List<Map<String, Object>> listall = null;
			List<Object> params = new ArrayList<Object>();
			try {
				listall = Dal.executeQuery(strSql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
            //前面就是为了获取数据长度
			
	
			
			Pager pageobj = new Pager();//类
			pageobj.allrecordcount = listall.size();//数据长度
			pageobj.pagesize = 10;
			pageobj.currentpage = currentpage;
			pageobj.urlname = "";
			pageobj.w="key="+key+"&classid=0";

			
			int startindex = pageobj.pagesize * (pageobj.currentpage - 1);
			String strSqlpager = " select * from v_canpitem order by id desc limit "+startindex + "," + pageobj.pagesize + "";
			//文本怎么显示
			if(!(key==null||key.equals("")))
			{
				strSqlpager = " select * from v_canpitem where proname like '%"+key+"%' or pronum like '%"+key+"%' order by id desc limit "+startindex + "," + pageobj.pagesize + "";
			//如果input不空，查询数据
			}
			
			List<Map<String, Object>> listpage = null;
			try {
				listpage = Dal.executeQuery(strSqlpager, params);//放入listpage
			} catch (SQLException e) {
				e.printStackTrace();
			}
			String pagestr = pageobj.GetPageInfo();
			request.setAttribute("pagestr", pagestr);
			request.setAttribute("list", listpage);//传递
			request.getRequestDispatcher("/admin/jgongforsearch.jsp").forward(request,
					response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
