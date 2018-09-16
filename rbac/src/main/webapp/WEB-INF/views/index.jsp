<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>
 <link rel="stylesheet" href="layui/css/layui.css">
 <!-- 注意要放在layui.css后面. CSS作用的就近原则 -->
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>

<script src="layui/layui.js"></script>

<script type="text/javascript">
	$(function () {
		
		// 创建Websocket
		var url = 'ws://' + window.location.host + "/rbac/msgSocket.action";
		var socket = new WebSocket(url);
		
		socket.onmessage = function(msg){
			// 当用户接收到消息时, 可以根据消息的内容, 修改气泡
			var message = JSON.parse(msg.data);
			
			var count = parseInt($(".msg-count").text());
			
			count++;
			
			$(".msg-count").text(count);
			
			alert(message.msg);
			
		};
		
		$.ajax({
			url : "view/getmenu.action",
			type : "post",
			data : {},
			dataType : "JSON",
			success : function(menus){
				
				$.each(menus, function(index, menu){
					
					if (menu.pmid == 0){
						// 构造一级菜单
						var p = $(".menu_parent").clone();
						
						// 防止叠加生成
						p.removeClass("menu_parent");
						
						p.find(".menuname").text(menu.mname);
						
						
						
						// 生成二级菜单
						$.each(menus, function(index, child){
							
							if (child.pmid == menu.mid){
								var c = $(".menu_child").clone();
								
								c.removeClass("menu_child");
								c.find(".menuname").text(child.mname);
								
								c.find("a").attr("onclick", "updateView('"+child.murl+"')");
								
								p.find(".layui-nav-child").append(c);
								
							}
							
						})
						
						$(".menu_context").append(p);
						
					}
						
				});
				
				
				layui.use('element', function(){
					  var element = layui.element;
				});
			}
		});
	});
	
	function updateView(viewurl){
		
		// 
		$.ajax({
			url : viewurl,
			type : "post",
			dataType : "HTML",
			success : function( h ){
				$("#jsp_context").html(h);
			}
		});
		
	}

</script>

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin ">
		<div class="layui-header">
			<div class="layui-logo">客户管理系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="http://t.cn/RCzsdCq" class="layui-nav-img"> 贤心
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="">消息<span class="layui-badge msg-count">0</span> </a></li>
				<li class="layui-nav-item"><a href="">退了</a></li>
			</ul>
		</div>

		<div class="layui-side">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree menu_context" lay-filter="test">
					
				</ul>
			</div>
		</div>

		<div class="layui-body">
			<!-- 内容主体区域 -->
			<div id = "jsp_context" style="padding:15px; height:100%">
				
				<!-- 引入Echarts.js -->
				<script type="text/javascript" src="js/echarts.js"></script>
				<div id="main" style="width: 500px;height:300px;"></div>
				
				<script type="text/javascript">
					var myChart = echarts.init(document.getElementById("main"));
					
					
			        
			        
			        $.ajax({
			        	url : "product/getSalesCount.action",
			        	type : "post",
						dataType : "JSON",
						success : function(data){
							
							var pnames = new Array();
							var counts = new Array();
							
							$.each(data, function(index, item){
								pnames.push(item.pname);
								counts.push(item.count);
							});
							
							// 指定图表的配置项和数据
					        var option = {
					            title: {
					                text: '产品销量'
					            },
					            tooltip: {},
					            legend: {
					                data:['销量']
					            },
					            xAxis: {
					                data: pnames
					            },
					            yAxis: {},
					            series: [{
					                name: '销量',
					                type: 'line', // bar 柱状图
					                data: counts
					            }]
					        };

					        // 使用刚指定的配置项和数据显示图表。
					        myChart.setOption(option);
						}
			        });
				</script>
				

			</div>
		</div>

		<div class="layui-footer">
			<!-- 底部固定区域 -->
			© layui.com - 底部固定区域
		</div>
	</div>


	<!-- 一级菜单样式 -->
	<li class="layui-nav-item menu_parent">
		<a class=""href="javascript:;"> <i class="layui-icon layui-icon-rmb"
			style="font-size: 15px; color: #FFF; font-weight: 700;"></i> <span class="menuname">123</span>
		</a>
		<dl class="layui-nav-child">
			
		</dl>
	</li>
					
	<!-- 二级菜单样式 -->			
	<dd class="menu_child">
		<a href="javascript:;" class="child_action"> <i
			class="layui-icon layui-icon-add-1 "
			style="font-size: 15px; color: #FFF;"></i> <span class="menuname">123</span>
		</a>
	</dd>
	
	<script type="text/javascript">
		
	
	</script>
</body>
</html>