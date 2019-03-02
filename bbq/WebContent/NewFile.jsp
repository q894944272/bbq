<%@page import="com.one.servlet.pageBean"%>
<%@ page language="java" import="java.util.*" import="java.io.File"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style>
.bb{
width:800px;
}
</style>

<title>Insert title here</title>
</head>
<body >



<%
File file = new File("/Users/bbq/tup");
String[] filelist;
int length = 0;
filelist = file.list();
length = filelist.length;
String pagestr = request.getParameter("page");
int currentPage = 1;
if(pagestr==null){
	currentPage = 1;
}else if(Integer.parseInt(pagestr)<=0){
    currentPage = 1;
}else if(Integer.parseInt(pagestr)>length/10){
    currentPage =length/10;
}else{
    currentPage=Integer.parseInt(pagestr);
}
pageBean pb = new pageBean(currentPage,25,length);
%>

<br>
图片总数<%=length %>张 
<br>
当前页/总页数<%=currentPage%>/<%=pb.getTotalPage()%>
<br>
 每页显示<%=pb.getPageSize()%>张
<br>
<a href="NewFile.jsp?page=1">首页 </a>
<a href="NewFile.jsp?page=<%=(currentPage - 1)%>">上页 </a>
<a href="NewFile.jsp?page=<%=(currentPage + 1)%>">下页 </a>
<a href="NewFile.jsp?page=<%=pb.getTotalPage()%>">末页 </a>
<br>
<%
for(int i = pb.getStartIndex();i<pb.getStartIndex()+25;i++){
	if(i<length){
	%>
	<img class="bb" alt="showImage" src="ShowImageServlet?str=<%=filelist[i] %>">
	<%
	}
}
%>
<br>
图片总数<%=length %>张 
<br>
当前页/总页数<%=currentPage%>/<%=pb.getTotalPage()%>
<br>
 每页显示<%=pb.getPageSize()%>张
<br>
<a href="NewFile.jsp?page=1">首页 </a>
<a href="NewFile.jsp?page=<%=(currentPage - 1)%>">上页 </a>
<a href="NewFile.jsp?page=<%=(currentPage + 1)%>">下页 </a>
<a href="NewFile.jsp?page=<%=pb.getTotalPage()%>">末页 </a>

</body>
</html>