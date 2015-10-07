<%--
  Nakuru. 2007.
  Created: 10.07.2007 || 15:07:39 by A.Zainullina
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/init.jsp" %>

<HTML>
<HEAD>
    <TITLE>
        Staff Department Login
    </TITLE>
    <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/common.css'/>">
    <%--<script type="text/javascript" src="<html:rewrite page='/common/common.js'/>"></script>--%>
</HEAD>
<BODY>

<p align="right"><b>Staff Department</b><br></p>
<hr>
<br><br>

<table width="100%">
    <tr>
        <td valign="top">
            <center>
                <table class="header">
                    <tr>
                        <td>
                            Staff Department
                        </td>
                    </tr>
                </table>

                <%--<logic:present name="org.apache.struts.action.ERROR">
                    <table class="errors">
                        <tr>
                            <td>
                                <html:errors/>
                            </td>
                        </tr>
                    </table>
                </logic:present>--%>

                <table class="entity">
                    <tr>
                        <td>
                            <html:form action="/Login">
                                <html:hidden property="method" value="submit"/>
                                <br><br>
                                <table width=100%>
                                    <tr>
                                        <td colspan="2">
                                            <strong>Please login:</strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%">
                                            Username:
                                        </td>
                                        <td width="80%">
                                            <html:text property="username" style="width:50%;background:white"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%">
                                            Password:
                                        </td>
                                        <td width="80%">
                                            <html:password property="password" style="width:50%;background:white"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right" colspan="2">
                                            <html:submit styleClass="btn">
                                                <bean:message key="button.login.label"/>
                                            </html:submit>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
            </center>
        </td>
    </tr>
</table>
</BODY>
</HTML>