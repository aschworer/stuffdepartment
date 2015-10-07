<%--
  Pelican Configuration Manager. 2007.
  Created: 30.10.2007 || 17:58:37 by A.Zainullina
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/init.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<html:form action="/EducationHistoryValidate" method="post" enctype="multipart/form-data">
    <html:hidden property="method" value="updateList"/>

    <table width=100%>
        <tr>
            <td colspan="2">
                <strong>Сотрудник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>${sessionScope.employee.fullName}
                <br><br><br>
                <bean:define id="employee" name="EducationHistoryForm" property="newHistoryItem.employee"/>

                <bean:define id="form" name="EducationHistoryForm"/>
                <c:choose>
                    <c:when test="${form.history!=null && not empty form.history}">

                        <table width="100%" class="table">
                            <tr>
                                <td class="head" width="25%">Образование</td>
                                <td class="head" width="25%">Где учился</td>
                                <td class="head" width="10%">Дата&nbsp;поступления</td>
                                <td class="head" width="10%">Дата&nbsp;окончания</td>
                                <td class="head" width="10%">Номер&nbsp;документа</td>
                                <td class="head" colspan="2">Копия документа</td>
                            </tr>
                            <logic:iterate id="historyItem" name="EducationHistoryForm" property="history"
                                           indexId="index">
                                <tr>
                                    <td>
                                        <html:select name="EducationHistoryForm"
                                                     property="history[${index}].type.id" styleClass="fieldInput">
                                            <html:optionsCollection name="EducationHistoryForm" property="types"
                                                                    label="name"
                                                                    value="id"/>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:select name="EducationHistoryForm"
                                                     property="history[${index}].place.id" styleClass="fieldInput">
                                            <html:optionsCollection name="EducationHistoryForm" property="places"
                                                                    label="name"
                                                                    value="id"/>
                                        </html:select>
                                    </td>
                                    <td>
                                        <html:text name="EducationHistoryForm"
                                                   property="history[${index}].from" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:text name="EducationHistoryForm"
                                                   property="history[${index}].till" styleClass="fieldInput"/>
                                    </td>
                                    <td>
                                        <html:text name="EducationHistoryForm"
                                                   property="history[${index}].documentNO" styleClass="fieldInput"/>
                                    </td>
                                    <td width="15%">
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
                                        <html:file name="EducationHistoryForm" property="history[${index}].documentCopy"/>
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
                <strong>Добавить запись в историю образования:</strong>
            </td>
        </tr>

        <tr>
            <td width="20%">
                Образование:
            </td>
            <td width="50%">
                <html:select name="EducationHistoryForm"
                             property="newHistoryItem.type.id"
                             styleClass="fieldInputLowerWidth">
                    <html:optionsCollection name="EducationHistoryForm" property="types" label="name"
                                            value="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="20%">
                Где учился:
            </td>
            <td>
                <html:select name="EducationHistoryForm"
                             property="newHistoryItem.place.id"
                             styleClass="fieldInputLowerWidth">
                    <html:optionsCollection name="EducationHistoryForm" property="places" label="name"
                                            value="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td width="20%">&nbsp;</td>
            <td>
                <html:checkbox name="EducationHistoryForm"
                               styleId="checkbox" property="addPlace" onclick="changed()"/>
                Другое место
                <p id="newPlace" style="margin-left:20px;">
                    <html:text name="EducationHistoryForm"
                               property="newPlaceName"
                               styleClass="fieldInputLowerWidth"/>
                </p>
            </td>
        </tr>

        <tr>
            <td>
                Дата поступления (yyy-mm-dd):
            </td>
            <td>
                <html:text name="EducationHistoryForm"
                           property="newHistoryItem.from"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td>
                Дата окончания (yyy-mm-dd):
            </td>
            <td>
                <html:text name="EducationHistoryForm"
                           property="newHistoryItem.till"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td>
                Номер документа:
            </td>
            <td>
                <html:text name="EducationHistoryForm"
                           property="newHistoryItem.documentNO"
                           styleClass="fieldInputLowerWidth"/>
            </td>
        </tr>

        <tr>
            <td align="left">
                Загрузить копию документа:
            </td>
            <td>
                <html:file name="EducationHistoryForm" property="documentCopy"/>
            </td>
        </tr>

        <tr>
            <td colspan="2" align="center">
                <html:submit value="Добавить" styleClass="btn" onclick="setMethodField('addHistoryItem')"/>
            </td>
        </tr>
    </table>
</html:form>

<script language="JavaScript" type="text/javascript">
    document.getElementById("newPlace").style.visibility = 'hidden';
    document.getElementById("checkbox").checked = false;
    function changed() {
        if (document.getElementById("checkbox").checked) {
            document.getElementById("newPlace").style.visibility = 'visible';
        } else {
            document.getElementById("newPlace").style.visibility = 'hidden';
        }
    }

</script>