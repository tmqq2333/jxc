package com.rz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class picadd
 */
@WebServlet("/picadd")
public class picadd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public picadd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	
	public String getDateFormat(){
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	    return df.format(new Date());    
	} 
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		        
				String title="";//图片标题
				String contents="";//图片的描述
				String picname="";//文件的路径
				
/**************************以下是文件上传的代码****************************************************************************/			
				//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
				//String savePath="D:\\rznews\\WebContent\\upload";        
				String savePath = this.getServletContext().getRealPath("/upload");
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
	                //使用Apache文件上传组件处理文件上传步骤：
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
	                        	case "title":title= item.getString("UTF-8"); break;
	                        	case "contents":contents= item.getString("UTF-8"); break;
	                        }
	                    }else{//如果fileitem中封装的是上传文件
	                        //得到上传的文件名称，
	                        String filename = item.getName();
	                        System.out.println(filename);
	                        if(filename==null || filename.trim().equals("")){
	                            continue;
	                        }
	                        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
	                        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
	                        String fileextendname = filename.substring(filename.lastIndexOf("."));
	                        
	                        filename=getDateFormat()+fileextendname;
	                        //获取item中的上传文件的输入流
	                        InputStream in = item.getInputStream();
	                        String fullname=savePath + "\\" + filename;
	                        picname=filename;
	                        //创建一个文件输出流
	                        FileOutputStream out = new FileOutputStream(fullname);
	                        //创建一个缓冲区
	                        byte buffer[] = new byte[1024];
	                        //判断输入流中的数据是否已经读完的标识
	                        int len = 0;
	                        //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
	                        while((len=in.read(buffer))>0){
	                            //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
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
	         
/**********************************************以上是文件上传的代码*******************************************************/
	            
	            
	            /*********************************数据保存到到数据库*************************************/
	            
	         DBHelper Dal=new DBHelper();
	   		 String strSql=" insert into tbpiclist (title,contents,imgurl) values (?,?,?) "; 
	   		 List<Object> params = new ArrayList<Object>();
	   		 params.add(title);
	   		 params.add(contents);
	   		 params.add(picname);	   		
	   		 Dal.excuteSql(strSql, params);
	            
	      
	         /*********************************数据保存到到数据库*************************************/
	            
	            
	            response.setCharacterEncoding("utf-8");
	       		response.setContentType("text/html;charset=utf-8");
	       		response.getWriter().write("<font color='green'>保存成功,3秒之后发生跳转!</font>");
	       		response.setHeader("Refresh", "3;URL="+request.getContextPath()+"/admin/picadd.jsp");
	}

}
