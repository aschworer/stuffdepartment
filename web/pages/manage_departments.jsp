<%--
  Nakuru. 2007.
  Created: 14.11.2007 || 13:20:31 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/ManageDepartmentsValidate">
    <html:hidden property="method" value="submit"/>

    <table width=100%>
        <tr>
            <td width="70%">
                <bean:define id="form" name="ManageDepartmentsForm"/>
                <c:choose>
                <c:when test="${form.departments!=null && not empty form.departments}">

                <html:select property="departmentIDChoosed" styleClass="fieldInput">
                    <html:optionsCollection name="ManageDepartmentsForm" property="departments"
                                            label="name" value="id"/>
                </html:select>
            </td>
            <td width="20%">
                <html:submit styleClass="btn" value="Удалить" onclick="setMethodField('deleteDepartment')"/>
            </td>
            </c:when>
            <c:otherwise>
                <strong>Подразделений нет</strong>
            </c:otherwise>
            </c:choose>
        </tr>

        <tr>
            <td>
                <html:text property="newDepartment.name" styleClass="fieldInput"/>
            </td>
            <td valign="top">
                <html:submit styleClass="btn" value="Добавить" onclick="setMethodField('addDepartment')"/>
            </td>
        </tr>
    </table>
</html:form>