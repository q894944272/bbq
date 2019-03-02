package com.one.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
	private static final String JPG="image/jpeg;charset=GB2312";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");  
        response.setCharacterEncoding("utf-8");
		String str = "/Users/bbq/tup/"+new String(request.getParameter("str").getBytes("ISO-8859-1"), "utf-8");
		System.out.println("开始请求后台!!!!"+str);
//		File file=new File("/Users/bbq/tup/27.jpg");
		File file=new File(str);

		BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
		response.setHeader("Content-Type","image/jpeg");
		byte b[]=new byte[1024];
		int read;
		try {
			while((read=bis.read(b))!=-1){
				bos.write(b, 0, read);
			}
			request.getRequestDispatcher("message.jsp").forward(request, response); 
		} catch (Exception e) {
		// TODO: handle exception
		} finally{
			
			if(bos!=null){
				bos.close();
			}
			if(bis!=null){
				bis.close();
			}
		}

//		String filePath = File.separator+"Users"+File.separator+"bbq"+File.separator+"tup"+File.separator+"27.jpg";
//	
//	        File file = new File(filePath);
//	        // 获取输出流
//	        OutputStream outputStream = response.getOutputStream();
//	        FileInputStream fileInputStream = new FileInputStream(file);
//	        // 读数据
//	        byte[] data = new byte[fileInputStream.available()];
//	        fileInputStream.read(data);
//	        fileInputStream.close();
//	        // 回写
//	        response.setContentType(JPG);
//	        outputStream.write(data);
//	        outputStream.flush();
//	        outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
