<%--
  Pelican Configuration Manager. 2007.
  Created: 25.10.2007 || 18:21:01 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/Vacancies">
    <html:hidden property="method" value="submit"/>

    <bean:define id="form" name="VacanciesForm"/>
    <c:choose>
        <c:when test="${form.vacancies!=null && not empty form.vacancies}">

            <table width="100%" class="table">
                <tr>
                    <td class="head" width="30%%">Название</td>
                    <td class="head" width="30%">Условия</td>
                    <td class="head" width="30%">Требования</td>
                </tr>
                <logic:iterate id="position" name="VacanciesForm" property="vacancies">
                    <tr>
                        <td>
                            <bean:write name="position" property="name"/>
                        </td>
                        <td>
                            <bean:write name="position" property="requirements"/>
                        </td>
                        <td>
                            <bean:write name="position" property="conditions"/>
                        </td>
                    </tr>
                </logic:iterate>
            </table>
        </c:when>
        <c:otherwise>
            <tr>
                <td align="center">
                    <strong>Вакансий нет</strong>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>

    </table>
</html:form>