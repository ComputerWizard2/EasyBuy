<%@page import="com.yz.bean.UserBean"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base path="http://${header['host']} ${pageContext.request.contextPath}/WEB-INF/manage/user-modify.jsp"/>
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
		管理员${ user}您好，今天是{date}，欢迎回到管理后台。
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
				<dd><em><a href="user-add.html">新增</a></em><a href="user.html">用户管理</a></dd>
				<dt>商品信息</dt>
				<dd><em><a href="productClass-add.html">新增</a></em><a href="productClass.html">分类管理</a></dd>
				<dd><em><a href="product-add.html">新增</a></em><a href="manageProduct.manage?currentPage=1">商品管理</a></dd>
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
		<h2>修改用户</h2>
		<div class="manage">
			<form action="updateUser.user" method="post">
				<table class="form">
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input type="text" class="text" name="userName" value="${userbean.eu_user_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">真实姓名(*)：</td>
						<td><input type="text" class="text" name="name" value="${userbean.eu_user_name }" /></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${userbean.eu_password }" /></td>
					</tr>
                    <tr>
						<td class="field">确认密码(*)：</td>
						<td><input type="text" class="text" name="passWord" value="${userbean.eu_password }" /></td>
					</tr>
					<tr>
						<td class="field">性别(*)：</td>
						<c:if test="${userbean.eu_sex == 'B' }">
						
						<td><input type="radio" name="sex" value="B" checked="checked" />男 
						<input type="radio" name="sex" value="G"  />女  </td>
						
						</c:if>
						
						<c:if test="${userbean.eu_sex== 'G'}">
						<td><input type="radio" name="sex" value="B"  />男 
						
						<input type="radio" name="sex" value="G" checked="checked" />女 </td>
						</c:if>
					
						
					
					</tr>
					<tr>
								<% Date date =new Date();
									pageContext.setAttribute("date1", date);
								
								%>
						<td class="field">出生日期：${pageScope.date1.date}</td>
						<td>
							<select name="birthyear">
							<c:forEach begin="1900" end="${pageScope.date1.year+1900}" var="i" >
							<c:choose>
							<c:when test="${(userbean.eu_birthday.year+1900)== i}">
							<option value="${i}" selected="selected">${i}</option>
							</c:when>
							<c:otherwise>
							<option value="${i}" >${i}</option>
							</c:otherwise>
							</c:choose>
							</c:forEach>
							</select>年
							<select name="birthmonth">
							<c:forEach begin="1" end="12" var="m" >
							<c:choose>
							<c:when test="${userbean.eu_birthday.month+1 ==m }== i}">
							<option value="${m }" selected="selected" >${m}</option>
							</c:when>
							<c:otherwise>
								<option value="${m }" selected="selected" >${m}</option>
							</c:otherwise>
							</c:choose>
							</c:forEach>
							</select>月
							<select name="birthday">
							
							<c:forEach begin="1" end="31" var="m" >
							<c:choose>
							<c:when test="${userbean.eu_birthday.date ==m }">
							<option value="${m}" selected="selected" >${m}</option>
							</c:when>
							<c:otherwise>
								<option value="${m}" selected="selected" >${m}</option>
							</c:otherwise>
							</c:choose>
							</c:forEach>
							
								
							
								
							</select>日
						</td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>
						<td><input type="text" class="text" name="mobile" value="${userbean.eu_mobile}" /></td>
					</tr>
					<tr>
						<td class="field">地址(*)：</td>
						<td><input type="text" class="text" name="address" value="${userbean.eu_address}" /></td>
					</tr>					
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2013 北大青鸟 All Rights Reserved. 京ICP证1000001号
</div>
</body>
</html>
