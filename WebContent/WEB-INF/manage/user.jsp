<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base path="http://${header['host']}${pageContext.request.contextPath}/WEB-INF/manage/user.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理 - 易买网</title>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>


</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="index.html">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="index.html">首页</a></li>
			<li class="current"><a href="user.html">用户</a></li>
			<li><a href="product.html">商品</a></li>
			<li><a href="order.html">订单</a></li>
			<li><a href="guestbook.html">留言</a></li>
			<li><a href="news.html">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${user}您好，今天是${date}，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="index.html">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="findByPage.user?currentPage=1">用户管理</a></dd>
			  <dt>商品信息</dt>
				<dd><em><a href="productClass-add.html">新增</a></em><a href="productClass.html">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="product.html">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="order.html">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="guestbook.html">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="news-add.html">新增</a></em><a href="news.html">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>用户管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>用户名</th>
					<th>真实姓名</th>
					<th>性别</th>
					<th>Email</th>
					<th>手机</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userList}" var ="user">
				<tr>
					<td class="first w4 c">${user.eu_user_id }</td>
					<td class="w1 c">${user.eu_user_name}</td>
					<c:choose>
					<c:when test="${user.eu_sex == '1'}">
					<td class="w2 c">男</td>
					</c:when>
					<c:otherwise>
					
					<td class="w2 c">女</td>
					</c:otherwise>
					</c:choose>
					<td>${user.eu_email}</td>
					<td class="w4 c">${user.eu_mobile}</td>
					<td class="w1 c"><a href="findUserById.user?id=${user.eu_user_id }">修改</a> <a class="manageDel" href="deleteUserById.user?id=${user.eu_user_id }">删除</a></td>
				</tr>
				</c:forEach>
				
			</table>
		</div>
	</div>
	<div class="clear"></div>
     <div class="pager">
				<ul class="clearfix">
					<c:if test="${page.currentPage!=1 }">
					<li><a href="findByPage.user?currentpage=1">首页</a></li>
					<li> <a href="findByPage.user?currentPage=${page.currentPage-1 }">上一页</a></li>
					</c:if>
					
					<c:forEach begin="${page.currentPage-5>0?page.currentPage-5:1 }" end="${page.currentPage+4<page.pageCount?page.currentPage+4:page.pageCount }" var ="i">
					<c:if test="${page.currentPage==i}">
					<li class="current"><a>${ i}</a></li>
					</c:if>
					<c:if test="${page.currentPage!=i }">
					<li><a href="findByPage.user?currentPage=${i}">${i}</a></li>

					</c:if>
					</c:forEach>
					<c:if test="${page.currentPage!=page.pageCount }">
					<li> <a href="findByPage.user?currentPage=${page.currentPage+1 }">下一页</a></li>
					<li><a href="findByPage.user?currentpage=${page.pageCount }">尾页</a></li>
					</c:if>
				</ul>
			</div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
