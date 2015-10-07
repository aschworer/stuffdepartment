<%--
  Nakuru. 2007.
  Created: 14.11.2007 || 14:37:34 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/ManagePositionsValidate">
    <html:hidden property="method" value="submit"/>

    <table width=100%>
        <tr>
            <td colspan="2">
                <strong>Редактировать должности:</strong>
            </td>
        </tr>
        <tr style="margin-bottom:20px;">
            <bean:define id="form" name="ManagePositionsForm"/>
            <c:choose>
                <c:when test="${form.positions!=null && not empty form.positions}">
                    <td>
                        <strong>Должность:</strong>
                    </td>
                    <td>
                        <html:select property="currentPositionID" styleClass="fieldInput"
                                     onchange="setMethodField('update');submit();">
                            <html:optionsCollection name="ManagePositionsForm" property="positions"
                                                    label="name" value="id"/>
                        </html:select>
                    </td>
                </c:when>
                <c:otherwise>
                    <td colspan="2">
                        <strong>Должностей нет</strong>
                        <br><br>
                    </td>
                </c:otherwise>
            </c:choose>

        </tr>

        <html:hidden property="currentPosition.id"/>
        <tr>
            <td width="10%">
                Название
            </td>
            <td valign="top" width="90%" colspan="2">
                <html:text property="currentPosition.name" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td>
                Требования
            </td>
            <td valign="top" colspan="2">
                <html:textarea property="currentPosition.requirements" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td>
                Условия
            </td>
            <td valign="top" colspan="2">
                <html:textarea property="currentPosition.conditions" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="center">
                <c:choose>
                    <c:when test="${form.positions!=null && not empty form.positions}">
                        <html:submit value="Изменить" styleClass="btn" onclick="setMethodField('updatePosition')"/>
                        <html:submit styleClass="btn" value="Удалить" onclick="setMethodField('deletePosition')"/>
                    </c:when>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <strong>Добавить новую должность:</strong>
            </td>
        </tr>


        <tr>
            <td width="10%">
                Название
            </td>
            <td valign="top" width="90%" colspan="2">
                <html:text property="newPosition.name" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td>
                Требования
            </td>
            <td valign="top" colspan="2">
                <html:textarea property="newPosition.requirements" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td>
                Условия
            </td>
            <td valign="top" colspan="2">
                <html:textarea property="newPosition.conditions" styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td colspan="3" align="center">
                <html:submit value="Добавить" styleClass="btn" onclick="setMethodField('addPosition')"/>
            </td>
        </tr>

    </table>

</html:form>