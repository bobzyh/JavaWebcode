<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%   
    String path = request.getContextPath();   
    String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>
<style>
.demo-carousel {
	height: 100%;
	line-height: 100%;
	text-align: center;
	
}
</style>
<div class="layui-carousel" id="test1" style="width: 500px; height: 300px">
	<div carousel-item >
		<c:forEach items="${requestScope.product.picList }" var="pic">
			<div>
				<div class="demo-carousel"><img src="${pic.picurl }" style="max-width: 100%; max-height:100%;"/></div>
			</div>
		</c:forEach>
		
	</div>
</div>

<script>
	layui.use('carousel', function(){
		var carousel = layui.carousel;
		
		// 异步获取图片
		
		//执行一个轮播实例
		carousel.render({
			elem : '#test1'
			,width : '100%' //设置容器宽度
			,height : '100%'
			,arrow : 'always' //不显示箭头
			,anim : 'fade' //切换动画方式
		});
	});
</script>