<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="org.apache.struts.Globals" %>
<%@ page import="java.util.Locale" %>
<%--
  Nakuru. 2007.
  Created: 20.07.2007 || 15:32:01 by A.Zainullina
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/init.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
    <TITLE>
        Ошибка.
    </TITLE>
    <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/common.css'/>">
</HEAD>

<body>
<center>
    <table class="entity" style="width:90%;height:90%;padding-bottom:50px;">
        <tr>
            <td align="left" style="font-size:80%;font-weight:bold;padding-top:50px" valign="top" height="20%">
                <a href="<html:rewrite page='/ManageEmployees.do'/>"><< На главную</a>
                <br>
                <br>
                <a href="javascript:history.back()"><< Назад</a>
            </td>
        </tr>
        <tr>
            <td align="center" style="font-size:150%;font-weight:bold;height:40px;" valign="top">
                Примечание: возникла ошибка при обработке вашего запроса.
            </td>
        </tr>
        <tr>
            <td style="font-size:90%;padding-left:100px;" valign="top">
                <strong>Возможно вы попытались:<br><br></strong>
                <li> Удалить должность или подразделение, которое удалить невозможно.
                <li> Создать подразделение или должность, которые уже существуют.
                <li> Создать дату в неверном формате. Формат даты должен соответствовать YYYY-MM-DD.
                <li> Возникла другая внутренняя ошибка системы.
            </td>
        </tr>
    </table>
</center>


</body>
</html>
