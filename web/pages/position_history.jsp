<%--
  Pelican Configuration Manager. 2007.
  Created: 26.10.2007 || 17:09:34 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/PositionHistoryValidate">
    <html:hidden property="method" value="submit"/>

    <table width=100%>
        <tr>
            <td colspan="2">
                <strong>Сотрудник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>${sessionScope.employee.fullName}
                <br><br><br>
                <bean:define id="form" name="PositionHistoryForm"/>
                <c:choose>
                    <c:when test="${form.history!=null && not empty form.history}">
                        <table width="100%" class="table">
                            <tr>
                                <td class="head" width="10%">Изменена на</td>
                                <td class="head" width="10%">Дата изменения</td>
                            </tr>
                            <logic:iterate id="historyItem" name="PositionHistoryForm" property="history"
                                           indexId="index">
                                <tr>
                                    <td>
                                        <html:select name="PositionHistoryForm"
                                                     property="history[${index}].changedTo.id"
                                                     styleClass="fieldInput">
                                            <html:optionsCollection name="PositionHistoryForm" property="positions"
                                                                    label="name"
                                                                    value="id"/>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:text name="PositionHistoryForm"
                                                   property="history[${index}].changedWhen"
                                                   styleClass="fieldInput"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                            <td colspan="2" align="right">
                                <html:submit value="Сохранить изменения" styleClass="btn"
                                             onclick="setMethodField('updateList')"/>
                            </td>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <strong>История пуста</strong>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <tr>
            <td colspan="2">
                <strong>Изменитиь текущую должность сотрудника:</strong>
            </td>
        </tr>
            <td width="20%">
                Изменить на:
            </td>
            <td width="80%">
                <html:select name="PositionHistoryForm"
                             property="newHistoryItem.changedTo.id"
                             styleClass="fieldInputLowerWidth">
                    <html:optionsCollection name="PositionHistoryForm" property="positions" label="name"
                                            value="id"/>
                </html:select>
            </td>
        </tr>

        <tr>
            <td>
                Дата изменения:
            </td>
            <td>
                <html:text name="PositionHistoryForm"
                           property="newHistoryItem.changedWhen"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <html:submit value="Добавить" styleClass="btn" onclick="setMethodField('submit')"/>
            </td>
        </tr>
    </table>
</html:form>