<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String errMsg = (String) request.getParameter("errMsg");
String url = (String) request.getParameter("url");
if(url == null || url.trim().equalsIgnoreCase("")){
	url = "http://www.360sdk.com";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gamesdk-本页面禁止访问!</title>
<style>
html,body{height:100%;margin:0;padding:0;font-size:14px;text-align:center;}
.notice{display:inline-block;*display:inline;*zoom:1;height:100%;}
.notice p{display:inline-block;*display:inline;*zoom:1;padding:30px;border-radius:5px;box-shadow:0 0 5px rgba(0,0,0,.2);background:#eee;}
.notice .after{display:inline-block;width:0;height:100%;font-size:0;vertical-align:middle;}
</style>

</head>
<body>
<div class="notice">
	<p>
        <%=errMsg %><br/>
        该页面将在3秒后自动跳转；若不能，请<a href="<%=url%>">点击此处</a>
    </p>
	<span class="after"></span>
</div>
<script>
    setTimeout(function(){
    	 var url = '<%=url%>';
        location.href =url;
    }, 3000);
</script>
</body>
</html>