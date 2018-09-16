<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%> 

<div class="layui-container layui-bg-gray" style="magin:0px; height:100%">
	<div class="layui-row" style="padding: 150px 0px">
		<div class="layui-col-md-offset4 layui-col-md4">
			<div class="layui-card">
			  <div class="layui-card-header"><i class="layui-icon" style="font-size: 30px; color: #FF1E9F;">&#xe613;</i>访问受限</div>
			  <div class="layui-card-body">
			    	对不起!<br>
			    	您没有权限访问该页面!
			  </div>
			</div>
		</div>
	</div>
</div>
