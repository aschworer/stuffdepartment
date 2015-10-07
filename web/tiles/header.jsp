<%@ include file="../common/init.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%--<%@ page errorPage="../jsp_error_handler.jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td align="right" width="100%" height="45px;" class="pink_back"><b>Отдел&nbsp;Кадров&nbsp;&nbsp;</b></td>
    </tr>
</table>

<table width="100%">
    <!--<tr>-->
        <%--<td align="right" width="50%" style="min-width:500px;margin-bottom:10px;"><b>Отдел кадров&nbsp;&nbsp;</b></td>--%>
    <!--</tr>-->
    <tr>
        <td width="100%" align="center">
            <table class="menu">
                <tr>
                    <td width="20%">
                        <a href="<html:rewrite page='/ManageEmployees.do'/>">Редактировать штат</a>
                    </td>
                    <td width="20%">
                        <a href="<html:rewrite page='/ManageDepartments.do'/>">Подразделения</a>
                    </td>
                    <td width="20%">
                        <a href="<html:rewrite page='/ManagePositions.do'/>">Должности</a>
                    </td>
                    <td width="20%">
                        <a href="<html:rewrite page='/Search.do'/>">Поиск сотрудников</a>
                    </td>
                    <td width="20%">
                        <a href="<html:rewrite page='/Vacancies.do'/>">Список вакансий</a>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
