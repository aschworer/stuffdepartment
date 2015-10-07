<%--
  Created: 12.10.2007 || 12:55:11 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/ManageEmployees">
    <html:hidden property="method" value="submit"/>


    <table width=100%>
        <bean:define id="form" name="ManageEmployeesForm"/>
        <c:choose>
            <c:when test="${form.employees!=null && not empty form.employees}">
                <tr>
                    <td width=20%>
                        Список&nbsp;сотрудников:
                    </td>
                    <td width="60%">
                        <html:select property="employeeID" styleClass="fieldInput" styleId="select">
                            <html:optionsCollection name="ManageEmployeesForm" property="employees" value="id"
                                                    label="fullName"/>
                        </html:select>
                    </td>
                    <td width="20%">
                        <html:submit value="Удалить" styleClass="btn" onclick="setMethodField('deleteEmployee')"/>
                    </td>
                </tr>
                <tr>
                    <td width=100% colspan="3">
                        <hr>
                    </td>
                </tr>
                <tr>
                    <td width="20%">
                    </td>
                    <td colspan="2">
                        Выберите область, которую вы бы хотели редактировать:<br>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editEmployee');"
                                        value="editEmployee" styleId="firstRadio"/>
                            &nbsp;&nbsp;&nbsp;Личные данные
                        </p>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editJobHistory')"
                                        value="editJobHistory"/>
                            &nbsp;&nbsp;&nbsp;Трудовая деятельность
                        </p>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editEducationHistory')"
                                        value="editEducationHistory"/>
                            &nbsp;&nbsp;&nbsp;Сведения об образовании
                        </p>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editDepartmentHistory');"
                                        value="editDepartmentHistory"/>
                            &nbsp;&nbsp;&nbsp;История подразделений
                        </p>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editPositionHistory');"
                                        value="editPositionHistory"/>
                            &nbsp;&nbsp;&nbsp;История должностей
                        </p>

                        <p>
                            <html:radio property="choice" onclick="setMethodField('editExtra');"
                                        value="editExtra"/>
                            &nbsp;&nbsp;&nbsp;Дополнительно
                        </p>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <html:submit value="Редактировать" styleClass="btn"/>

                    </td>
                    <td>
                        <html:submit value="Добавить сотрудника" styleClass="btn"
                                     onclick="setMethodField('addEmployee')"/>
                    </td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr>
                    <td align="center">
                        <strong>Сотрудников нет</strong><br><br><br>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">
                        <html:submit value="Добавить сотрудника" styleClass="btn"
                                     onclick="setMethodField('addEmployee')"/>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
</html:form>

<script language="JavaScript" type="text/javascript">
    if (document.getElementById('firstRadio')) {
        document.getElementById('firstRadio').checked = true;
        setMethodField('editEmployee');
    }

</script>