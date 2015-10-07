<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 11.07.2007
  Time: 22:28:41
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../common/init.jsp" %>

<html:form action="/UsersConfHome">
    <html:hidden property="method" value="submit"/>
    <html:hidden property="action" value="edit"/>

    <p style="margin-top:30px;margin-bottom:30px;">
        Please Choose the User you want to edit or add a new one
    </p>

    <p>
        <html:radio property="user" value="1"/>
        Vickie
    </p>

    <p>
        <html:radio property="user" value="2"/>
        Tarek
    </p>

    <p>
        <html:radio property="user" value="3"/>
        Stan
    </p>
    <p align="right">
        <html:submit styleClass="btn" onclick="addAction()">
            <bean:message key="button.submit.label"/>
        </html:submit>

        <html:submit styleClass="btn">
            <bean:message key="button.submit.label"/>
        </html:submit>
    </p>
</html:form>

<script language="JavaScript">
    function addAction() {
        document.forms[0].action.value = "add";
    }
</script>