<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/tags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="project.name" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<div class="msg">
 ${SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
<form action="${ctx}/j_spring_security_check?redirect=${redirect}" method="post" id="loginForm">
 <label>
   <input name="j_username" autofocus="true" type="text" id="_username" />
  </label>
  <label>
  <input name="j_password" type="password" id="_pwd" />
  </label>
  <label>
  <input name="submit" type="submit" id="submit" />
  </label>
</form>

</body>
</html>