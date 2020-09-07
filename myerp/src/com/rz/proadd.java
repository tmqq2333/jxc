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
 * Servlet implementation class proadd
 */
@WebServlet("/proadd")
public class proadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public proadd() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String getDateFormat(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return df.format(new Date());    
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
		
		String proname="";
		String pronum="";
		String price="";
		String brief="";
		String descriptions="";
		String address="";
		String istop="0";
		String isstar="0";
		String picname="";
		String typeid="";
		
		
		//String typename=request.getParameter("typename"); 
		
/**************************以下是文件上传的代码****************************************************************************/			
		String savePath = this.getServletContext().getRealPath("/upload");
		System.out.print(savePath);
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        try{
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                if(item.isFormField()){
                    String name = item.getFieldName();
                    switch(name)
                    {
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
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace(); 
        }   	
     
/********************************以上是文件上传的代码*******************************************************/
        
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
        
        
/*********************************数据保存到到数据库*************************************/
         DBHelper Dal=new DBHelper();
		 String strSql=" insert into tbproduct(proname,price,brief,descriptions,address,imgurl,typeid,pronum,procodeurl) values (?,?,?,?,?,?,?,?,?) "; 
		 List<Object> params = new ArrayList<Object>();
		 params.add(proname);
		 params.add(price);
		 params.add(brief);	  
		 params.add(descriptions);	
		 params.add(address);	
		 params.add(picname);	
		 params.add(typeid);	
		 params.add(pronum);	
		 params.add(twocodefilename);	
		 Dal.excuteSql(strSql, params);
/*********************************数据保存到到数据库*************************************/
        response.setCharacterEncoding("utf-8");
   		response.setContentType("text/html;charset=utf-8");
   		response.getWriter().write("<font color='green'>保存成功,3秒之后发生跳转!</font>");
   		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/proadd.jsp");
		
	}

}
