<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
<script type="text/javascript">

	var url = 'ws://' + window.location.host + "/rbac/msgSocket.action";
	var socket = new WebSocket(url);
	/* new WebSocket()会创建一个Socket对象
		向URL请求与服务器之间建立一个长连接
	*/
	
	// 监听连接成功
	socket.onopen = function(){
		alert("连接成功");
	}
	
	// 监听关闭连接
	socket.onclose = function(){
		alert("连接关闭");
	}
	
	// socket监听服务器发送过来的数据
	socket.onmessage = function(msg){
		
		
		$("#msg-list").append(msg.data);
	}
	
	function sendMsg(){
		var msg = $("input").val();
		// 向服务端发送数据
		socket.send(msg);
	}

</script>
</head>
<body>
	<div id="msg-list">
	</div>
	<input type="text" />
	<button onclick="sendMsg()">发送</button>
</body>
</html>