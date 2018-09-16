<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%> 
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath %>"/>
    <meta charset="UTF-8">
 
    <title>登录页</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/login.css">
 
</head>
<body>

<div>
	<div class="layui-container">
		<div class="login-main">
		    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
				<legend>登录</legend>
			</fieldset>
		    <form class="layui-form" action="user/login.action" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">账号</label>
					<div class="layui-input-inline">
				    	<input type="text" name="username" required  lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
				    </div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码</label>
					<div class="layui-input-inline">
						<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off"	class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

 
 
<script src="../layui/layui.js"></script>
<script type="text/javascript">
    layui.use(['form','layer','jquery'], function () {
 
        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;
        form.on('submit(login)',function (data) {
            // console.log(data.field);
            $.ajax({
                url:'login.php',
                data:data.field,
                dataType:'text',
                type:'post',
                success:function (data) {
                    if (data == '1'){
                        location.href = "../index.php";
                    }else{
                        layer.msg('登录名或密码错误');
                    }
                }
            })
            return false;
        })
 
    });
</script>
</body>
</html>