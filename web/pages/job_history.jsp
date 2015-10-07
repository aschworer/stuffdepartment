<%--
  Pelican Configuration Manager. 2007.
  Created: 30.10.2007 || 16:19:53 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/JobHistoryValidate">
    <html:hidden property="method" value="unspecified"/>

    <table width=100%>
        <tr>
            <td colspan="2">
                <strong>Сотрудник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>${sessionScope.employee.fullName}
                <br><br><br>

                <bean:define id="form" name="JobHistoryForm"/>
                <c:choose>
                    <c:when test="${form.historyCurrentCompany!=null && not empty form.historyCurrentCompany}">
                        <strong>История текущей компании:</strong> (Чтобы изменить сведения истории сотрудника в текущей компании, перейдите в главное меню.)
                        <br>
                        <br>

                        <table width="100%" class="table">
                            <tr>
                                <td class="head" width="10%">Должность была изменена на</td>
                                <td class="head" width="10%">Дата изменения</td>
                            </tr>
                            <logic:iterate id="historyItemCurrent" name="JobHistoryForm" property="historyCurrentCompany">
                                <tr>
                                    <td>
                                        <bean:write name="historyItemCurrent" property="changedTo.name"/>
                                    </td>
                                    <td>
                                        <bean:write name="historyItemCurrent" property="changedWhen"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                        </table>

                    </c:when>
                    <c:otherwise>
                        <strong>История пуста</strong>
                    </c:otherwise>
                </c:choose>

                <br><br>

                <c:choose>
                    <c:when test="${form.historyEarlier!=null && not empty form.historyEarlier}">
                        <br>
                        <br>
                        <strong>История ранней трудовой деятельности:</strong>
                        <br>
                        <br>
                        <table width="100%" class="table">
                            <tr>
                                <td class="head" width="20%">Организация</td>
                                <td class="head" width="20%">Должность</td>
                                <td class="head" width="20%">Работал с (yyyy-mm-dd)</td>
                                <td class="head" width="20%">Работал до (yyyy-mm-dd)</td>
                                <td class="head" width="20%">Примечания</td>
                            </tr>
                            <logic:iterate id="historyItemEarlier" name="JobHistoryForm" property="historyEarlier"
                                           indexId="index">
                                <tr>
                                    <td>
                                        <html:textarea rows="2" name="JobHistoryForm"
                                                   property="historyEarlier[${index}].company" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:textarea rows="2" name="JobHistoryForm"
                                                   property="historyEarlier[${index}].position"
                                                   styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:textarea rows="2" name="JobHistoryForm"
                                                   property="historyEarlier[${index}].from" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:textarea rows="2" name="JobHistoryForm"
                                                   property="historyEarlier[${index}].till" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:textarea rows="2" name="JobHistoryForm"
                                                       property="historyEarlier[${index}].note"
                                                       styleClass="fieldInput"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                            <tr>
                                <td colspan="5" align="right">
                                    <html:submit value="Сохранить" styleClass="btn"
                                                 onclick="setMethodField('updateList')"/>
                                </td>
                            </tr>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <strong>История пуста</strong>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <br>
                <strong>Добавить запись о ранней трудовой деятельности:</strong>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Организация:
            </td>
            <td>
                <html:text name="JobHistoryForm" property="newHistoryItem.company" styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Должность:
            </td>
            <td>
                <html:text name="JobHistoryForm" property="newHistoryItem.position" styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Работал с (yyyy-mm-dd):
            </td>
            <td>
                <html:text name="JobHistoryForm" property="newHistoryItem.from" styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Работал до (yyyy-mm-dd):
            </td>
            <td>
                <html:text name="JobHistoryForm" property="newHistoryItem.till" styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Примечания:
            </td>
            <td>
                <html:textarea name="JobHistoryForm" property="newHistoryItem.note" styleClass="fieldInputLowerWidth" rows="5"
                               cols="1"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <html:submit value="Добавить" styleClass="btn" onclick="setMethodField('submit')"/>
            </td>
        </tr>


    </table>
</html:form>