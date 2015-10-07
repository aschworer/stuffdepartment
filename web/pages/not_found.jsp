<%--
  Nakuru. 2007.
  Created: 06.07.2007 || 15:18:59 by A.Zainullina
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/init.jsp" %>
<html>
<head><title>Страница не найдена</title>
    <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/common.css'/>">
</head>
<body>
<p align="center" style="margin:200px;font-size:150%;font-weight:bold;">
    Страница не найдена.
</p>
<p align="center" style="font-size:90%;font-weight:bold;">
    <a href="javascript:history.back()"><< Назад</a>
</p>
<p align="center" style="font-size:90%;font-weight:bold;padding-top:5px;">
    <a href="<html:rewrite page='/ManageEmployees.do'/>"><<
    </a>
</p>
<%--<center><a href="<html:rewrite page='/NakuruHome.do'/>">Goto Main Configuration Page</a></center>--%>
</body>
</html>