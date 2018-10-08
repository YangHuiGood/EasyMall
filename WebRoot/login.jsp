<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import= "java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${app }/css/login.css"/>
		<title>EasyMall欢迎您登录</title>
	</head>
	<body>
	   <%
	      //1.获取用户本次请求携带的所有cookie
	      Cookie[] cs = request.getCookies();
	      //2.判断用户是否携带了记住用户名的cookie
	      Cookie findC = null;
	      if(cs != null){
	         for(Cookie c:cs){
	            if("remname".equals(c.getName())){
	               findC = c;//找到了记住的用户名的cookie
	               break;
	            }
	         }
	      }
	      //3.如果携带了，获取cookie中保存的用户名
	      String username = "";
	      if(findC != null){
	         //username = findC.getValue();
	         username = URLDecoder.decode(findC.getValue(),"utf-8");
	      }
	      //4.将用户名添加到username的input中
	   
	    %>
	    <%--<c:if test="${cookie.remname.name}"></c:if>--%>
	  
	
		<h1>欢迎登录EasyMall</h1>
		<form action="/LoginServlet" method="POST">
			<table>
			    <tr>
			       <td style="text-align: center;color:red">${requestScope.errMsg}</td>
			    </tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="<%=username%>"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox"
						${empty cookie.remname.value ? "" : "checked='checked'"}
						 name="remname" value="true"/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登录
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 录"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
