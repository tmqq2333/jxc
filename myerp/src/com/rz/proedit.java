package com.rz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * Servlet implementation class proedit
 */
@WebServlet("/proedit")
public class proedit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proedit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id=request.getParameter("id"); 
		 DBHelper Dal=new DBHelper();
		 String strSql=" select * from tbproduct where id=? "; 
		 Map<String, Object> objbyid = null;
		 List<Object> params = new ArrayList<Object>();
		 params.add(id);
		 objbyid=Dal.getSingleObject(strSql, params);
		 request.setAttribute("objbyid", objbyid);
		 request.getRequestDispatcher("/admin/proedit.jsp").forward(request, response);
	}
	
	public String getDateFormat(){
		 SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		 return df.format(new Date());    
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id="";
		String proname="";
		String pronum="";
		String price="";
		String brief="";
		String descriptions="";
		String address="";
		String istop="0";
		String isstar="0";
		String picname="";
		String oldurl="";
		String typeid="";
		
/**************************文件上传开始***************************************************************************/			
		String savePath = this.getServletContext().getRealPath("/upload");
		System.out.print(savePath);
        File file = new File(savePath);
        
        if (!file.exists() && !file.isDirectory()) {
            file.mkdir();
        }
        
        String message = "";
        try{
            
            DiskFileItemFactory factory = new DiskFileItemFactory();
           
            ServletFileUpload upload = new ServletFileUpload(factory);
             
            upload.setHeaderEncoding("UTF-8"); 
           
            if(!ServletFileUpload.isMultipartContent(request)){
                
                return;
            }
            
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    switch(name)
                    {
                    	case "id":id= item.getString("UTF-8"); break;	
                    	case "oldurl":oldurl= item.getString("UTF-8"); break;	
                    	case "proname":proname= item.getString("UTF-8"); break;
                    	case "pronum":pronum= item.getString("UTF-8"); break;                    	
                    	case "price":price= item.getString("UTF-8"); break;
                    	case "brief":brief= item.getString("UTF-8"); break;
                    	case "descriptions":descriptions= item.getString("UTF-8"); break;
                    	case "address":address= item.getString("UTF-8"); break;
                    	case "istop":istop= item.getString("UTF-8"); break;
                    	case "isstar":isstar= item.getString("UTF-8"); break;
                    	case "typeid":typeid= item.getString("UTF-8"); break;
                    }
                }else{
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    String fileextendname = filename.substring(filename.lastIndexOf("."));
                    filename=getDateFormat()+fileextendname;
                    InputStream in = item.getInputStream();
                    String fullname=savePath + "\\" + filename;
                    picname=filename;
                    FileOutputStream out = new FileOutputStream(fullname);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while((len=in.read(buffer))>0){
                        out.write(buffer, 0, len);
                    }                    
                    in.close();                   
                    out.close();              
                    item.delete();
                    message = "上传成功";
                }
            }
        }catch (Exception e) {
            message= "上传失败";
            e.printStackTrace(); 
        }   	
     
/********************************文件上传结束*******************************************************/

/********************生成二维码图片开始********************/ 
        String twocodeimgPath = this.getServletContext().getRealPath("/twocode");   
        String twocodefilename=getDateFormat()+".jpg";
        try {			
		     String content =pronum;		    	
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     Map hints = new HashMap();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		     File file1 = new File(twocodeimgPath,twocodefilename);
		     MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);			
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
        
/********************生成二维码图片结束********************/
        
/*********************************保存数据开始*************************************/
         DBHelper Dal=new DBHelper();
		 String strSql=" update tbproduct set proname=?,price=?,brief=?,descriptions=?,address=?,imgurl=?,typeid=? ,pronum=?,procodeurl=? where id=?"; 
		 List<Object> params = new ArrayList<Object>();
		 params.add(proname);
		 params.add(price);
		 params.add(brief);	  
		 params.add(descriptions);	
		 params.add(address);
		 if(picname==null||picname.equals(""))
		 {
			 picname=oldurl;
		 }
		 params.add(picname);
		 params.add(typeid);
		 params.add(pronum);
		 params.add(twocodefilename);
		 params.add(id);
		 Dal.excuteSql(strSql, params);
		 
/*********************************保存数据结束*************************************/
        response.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		response.getWriter().write("<font color='green'>保存成功，3秒后跳转!</font>");
   		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/proedit?id="+id);
	
	}

}
