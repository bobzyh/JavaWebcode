<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>
<form class="layui-form" action="">
	<!-- 提示：如果你不想用form，你可以换成div等任何一个普通元素 -->
	<div class="layui-form-item">
		<div class="layui-input-inline">
			<input type="text" name="" placeholder="请输入产品名" autocomplete="off" class="layui-input">
		</div>
		<div class="layui-input-inline">
			<select name="type" lay-verify="">
				<option value='0'>请选择类型</option>
			</select>
		</div>

		<div class="layui-input-inline">
			<button class="layui-btn" lay-submit lay-filter="search">搜索</button>
		</div>
	</div>
</form>

<style>
.layui-table-cell{  /*最后的pic为字段的field*/
       height: 100%;
       max-width: 100%;
} 
</style>
<table class="layui-table" id="test" lay-filter="demo" lay-data="{id: 'plist'}"></table>

<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看图片</a>
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
layui.use([ 'table', 'form'], function(){
	  var table = layui.table
	  ,form = layui.form; //表格
	  
	  //执行一个 table 实例
	  table.render({
	    elem: '#test'
	    ,url: 'product/select.action' //数据接口
	    ,page: true //开启分页
	    ,id:'plist'
	    ,cols: [[ //表头
	      {type:'checkbox', style:'height:80px;', fixed:'left'}
	      ,{field: 'picList', title: '图片', width: 180, templet: '<div><img src="{{d.picList[0].picurl}}" style="height:48px;"/></div>',style:'height:80px;'}
	      ,{field: 'pid', title: 'ID', width:180, sort: true}
	      ,{field: 'pname', title: '产品名', width:180}
	      ,{field: 'ptype', title: '类别', width:180, sort: true}
	      ,{fixed: 'right', width: 185, align:'center', toolbar: '#barDemo'}
	    ]]
	  });
	  
	  //监听工具条
	  table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
	    var data = obj.data //获得当前行数据
	    ,layEvent = obj.event; //获得 lay-event 对应的值
	    if(layEvent === 'detail'){
	      layer.msg('查看操作');
	      
	      // 可以跳转到详情页面
	      $.ajax({
	    	 url : "product/productview.action",
	    	 "data" : {"pid" : data.pid},
	    	 dataType : "html",
	    	 success : function(html){
	    		 layer.open({
	    			 title: false,
	    			 type : 1,
	    			 area: ['500px', '300px'],
	    			 content : html
	    		 });
	    	 }
	      });
	      
	    } else if(layEvent === 'del'){
	      layer.confirm('真的删除行么', function(index){
	        layer.close(index);
	        
	        //向服务端发送删除指令
	        table.reload('plist',{
	        	
	        });
	        
	      });
	    } else if(layEvent === 'edit'){
	      // 可以跳转到编辑页面
	    	updateView("product/updateview.action");
	    }
	  });
	  
	  // 异步加载数据
	  getType();
	  
	  form.render();
	  
	  form.on('submit(search)', function(data){
		  
		  layer.msg("点击提交");
		  
		  return false;
	  });
	  
	  function getType(){
			$.ajax({
				url : "type/getAll.action",
				type : "post",
				dataType : "JSON",
				success : function(types){
					
					
					var html = "<option value='0'>请选择类型</option>";
					$.each(types, function (index, type){
						html += "<option value='"+type.tid+"'>"+type.tname+"</option>";
					});
					
					$("select[name='type']").html(html);
					
					form.render();
				}
			});
		}
	  
	});
	
	
</script>