<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
.img-parent{
	float: left;
	position : relative;
}
.img-parent .img-delete{
	background-color: #00000051;
	cursor:pointer;
	position: absolute;
	top: 0px;
	left: 0px;
	width: 92px;
	height: 92px;
	line-height: 90px;
	text-align: center;
	color: #FFF;
	display: none;
}
.img-parent .layui-upload-img {
	width: 92px;
	height: 92px;
	margin: 0 10px 10px 0;
}
.img-parent:hover .img-delete{
	display: block;
}
</style>
<h1 style="margin: 30px;">添加产品</h1>
<form class="layui-form" action="">
	<div class="layui-form-item">
		<label class="layui-form-label">产品名</label>
		<div class="layui-input-inline">
			<input type="text" name="pname" required lay-verify="required" placeholder="请输入产品名" autocomplete="off" class="layui-input">
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">产品类型</label>
		<div class="layui-input-inline">
			<select name="ptype" lay-verify="required">
				<!-- ajax生成类型下拉列表 -->
			</select>
		</div>
	</div>

	<div class="layui-form-item">
		<label class="layui-form-label">产品图片</label>
		<div class="layui-input-block">
			<div class="layui-upload-drag" id="test10">
				<i class="layui-icon"></i>
				<p>点击上传，或将文件拖拽到此处</p>
			</div>
			<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px; display: table;">
				预览图：
				<div class="layui-upload-list" id="demo2"></div>
			</blockquote>
		</div>
	</div>

	<div class="layui-form-item">
		<div class="layui-input-block">
			<button class="layui-btn" lay-submit lay-filter="add">立即提交</button>
		</div>
	</div>
</form>

<script>
//Demo

	layui.use([ 'form', 'upload' ], function() {
		var form = layui.form
		,upload = layui.upload;

		//监听提交
		form.on('submit(add)', function(data) {
			layer.msg(JSON.stringify(data.field));
			
			// 添加产品
			var pname = data.field.pname;
			var ptype = data.field.ptype;
			var pics = new Array();
			
			// 选择所有的图片
			$(".layui-upload-img").each(function(){
				pics.push($(this).attr("src"));
			});
			
			// ajax 添加产品请求
			
			$.ajax({
				url : "product/add.action",
				type : "post",
				data : {"pname": pname, "ptype":ptype, "pics": pics},
				traditional : true,
				dataType : "JSON",
				success : function(data){
					if (data.code == -1 ){
						layer.msg(data.msg);
					} else {
						layer.msg("添加产品成功!");
						// 
					}
				}
			});
			
			
			
			// 拦截掉表单的原有提交方式
			return false;
		});
		
		// 异步加载产品类型
		$.ajax({
			url : "type/getAll.action",
			type : "post",
			dataType : "JSON",
			success : function(types){
				
				var html = "<option value='0'>请选择类型</option>";
				// 遍历数组
				$.each(types, function(index, type){
					html +=  '<option value="'+type.tid+'">'+type.tname+'</option>';
				});
				
				$("select[name='ptype']").html(html);
				
				form.render(); //更新全部
			}
		});
		
		// 
		//拖拽上传
		upload.render({
			elem : '#test10',
			url : 'product/uploadPic.action',
			multiple : true,
			before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			    layer.load(); //上传loading
			},
			allDone : function(obj){	// 所有文件完成后触发
				layer.closeAll('loading'); //关闭loading
				layer.msg("上传完成");
			},
			done : function(res) {	// 第个文件提交完成都会触发
				console.log(res)
				
				var imgDiv = $("<div class=\"img-parent\">" +
						"	<img src=\"" + res.data.picurl + "\" class=\"layui-upload-img\">" +
						"	<div class=\"img-delete\">点击删除</div>"+
						"</div>");
				
				
				$("#demo2").append(imgDiv);
				
				imgDiv.click(function(){
					$(this).remove();
				});
			}
			,error: function(index, upload){
				layer.msg("上传失败");
			}
		});

	});
</script>