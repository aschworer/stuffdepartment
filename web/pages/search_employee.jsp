<%--
  Pelican Configuration Manager. 2007.
  Created: 25.10.2007 || 16:27:10 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/SearchValidate">
    <html:hidden property="method" value="submit"/>

    <table width=100%>
        <tr>
            <td align="left" width="20%">
                Фамилия
            </td>
            <td width="80%">
                <html:text name="SearchForm"
                           property="lastname"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td align="left">
                Имя
            </td>
            <td>
                <html:text name="SearchForm"
                           property="firstname"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td align="left">
                Отчество
            </td>
            <td>
                <html:text name="SearchForm"
                           property="middlename"
                           styleClass="fieldInput"/>
            </td>
        </tr>

        <tr>
            <td align="left">
                Подразделение
            </td>
            <td>
                <html:select name="SearchForm"
                             property="departmentID"
                             styleClass="fieldInput">
                    <html:optionsCollection name="SearchForm" property="departments" label="name" value="id"/>
                </html:select>
            </td>
        </tr>

        <tr>
            <td align="left">
                Дата поступления на работу
            </td>
            <td>
                <html:text name="SearchForm"
                           property="date"
                           styleClass="fieldInput"/>
            </td>
        </tr>

        <tr>
            <td>&nbsp;
            </td>
            <td align="right">
                <html:submit value="Поиск" styleClass="btn"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <hr>
            </td>
        </tr>

        <bean:define id="form" name="SearchForm"/>
        <c:choose>
            <c:when test="${form.result!=null && not empty form.result}">

                <table width="100%" class="table" style="margin-top:50px;">
                    <tr>
                        <td class="head" width="10%">Полное имя</td>
                    </tr>
                    <logic:iterate id="employee" name="SearchForm" property="result">
                        <tr>
                            <td align="left" style="padding:10px;">
                                <a href="<html:rewrite page="/ManageEmployees.do?method=editEmployee&id=${employee.id}"/>">
                                    <bean:write name="employee" property="fullName"/>
                                </a>
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="2" align="center">
                        <strong>Сотрудники не найдены</strong>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>

    </table>
</html:form>