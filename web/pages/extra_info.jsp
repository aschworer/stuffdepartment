<%--
  Nakuru. 2007.
  Created: 19.11.2007 || 13:33:19 by A.Zainullina
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<html:form action="/EditExtraInfoValidate" method="post" enctype="multipart/form-data">
    <html:hidden property="method" value="updateList"/>

    <table width=100%>
        <tr>
            <td colspan="2">
                <strong>Сотрудник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>${sessionScope.employee.fullName}
                <br><br><br>
                <bean:define id="employee" name="ExtraInfoForm" property="newHistoryItem.employee"/>

                <bean:define id="form" name="ExtraInfoForm"/>
                <c:choose>
                    <c:when test="${form.history!=null && not empty form.history}">

                        <table width="100%" class="table">
                            <tr>
                                <td class="head" width="30%">Достижение</td>
                                <td class="head" width="30%">Описание</td>
                                <td class="head" width="30%">Номер документа</td>
                                <td class="head" width="30%" colspan="2">Документ</td>
                            </tr>
                            <logic:iterate id="historyItem" name="ExtraInfoForm" property="history"
                                           indexId="index">
                                <tr>
                                    <td>
                                        <html:text name="ExtraInfoForm"
                                                   property="history[${index}].name" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:text name="ExtraInfoForm"
                                                   property="history[${index}].description" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:text name="ExtraInfoForm"
                                                   property="history[${index}].documentNO" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:hidden name="historyItem" property="documentCopyFilename"/>
                                        <c:choose>
                                            <c:when test="${historyItem.documentCopyFilename!=null && ''!=historyItem.documentCopyFilename}">
                                                <a href="../staff_uploads/${employee.id}/${historyItem.documentCopyFilename}"
                                                   target="_blank">скачать</a>
                                            </c:when>
                                            <c:otherwise>
                                                загрузить:
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td width="15%">
                                        <html:file name="ExtraInfoForm" property="history[${index}].documentCopy"/>
                                    </td>
                                </tr>
                            </logic:iterate>
                            <tr>
                                <td colspan="6" align="right">
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
            <td width="20%" colspan="2">
                <strong>Добавить запись в историю достижений:</strong>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Достижение&nbsp;(степерь,&nbsp;награда,&nbsp;поощрение,&nbsp;бонус,&nbsp;и&nbsp;т.п.):
            </td>
            <td>
                <html:text name="ExtraInfoForm"
                           property="newHistoryItem.name"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td>
                Описание:
            </td>
            <td>
                <html:text name="ExtraInfoForm"
                           property="newHistoryItem.description"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>
        <tr>
            <td>
                Номер документа:
            </td>
            <td>
                <html:text name="ExtraInfoForm"
                           property="newHistoryItem.documentNO"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td align="left">
                Загрузить копию документа:
            </td>
            <td>
                <html:file name="ExtraInfoForm" property="fileToAdd"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <html:submit value="Добавить" styleClass="btn" onclick="setMethodField('addHistoryItem')"/>
            </td>
        </tr>
    </table>
</html:form>