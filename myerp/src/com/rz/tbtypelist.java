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
 * Servlet implementation class tbtypelist
 */
@WebServlet("/tbtypelist")
public class tbtypelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<tbtype> listall = new ArrayList<tbtype>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tbtypelist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listall.clear();
		getData("0");
		request.setAttribute("list", listall);
		request.getRequestDispatcher("/admin/tbtypelist.jsp").forward(request,response);
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
		//List<Map<String, Object>> name =new List<Map<String, Object>>(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
