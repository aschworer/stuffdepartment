<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 22.11.2007
  Time: 21:51:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <table width=100%>
        <tr>
            <td align="left" style="font-size:80%;font-weight:bold;padding-top:5px" valign="top" height="20%">
                <a href="<html:rewrite page='/ManageEmployees.do'/>"><< На главную</a>
                <br>
                <br>
                <a href="javascript:history.back()"><< Назад</a>
            </td>
        </tr>
        <tr>
            <td align="center" style="font-size:120%;font-weight:bold;height:40px;" valign="top">
                Примечание: возникла ошибка при обработке вашего запроса:
            </td>
        </tr>
        <tr>
            <td style="font-size:90%;color:red;padding-left:100px;" valign="top">
                <!--<strong>Возможно вы попытались:<br><br></strong>
                <li> Удалить должность или подразделение, которое удалить невозможно.
                <li> Создать подразделение или должность, которые уже существуют.
                <li> Создать дату в неверном формате. Формат даты должен соответствовать YYYY-MM-DD.
                <li> Возникла другая внутренняя ошибка системы.-->

                <html:errors/>
            </td>
        </tr>

    </table>