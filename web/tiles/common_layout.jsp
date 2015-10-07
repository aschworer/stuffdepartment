<%--
  Nakuru. 2007.
  Created: by A.Zainullina
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page errorPage="../jsp_error_handler.jsp" %>--%>
<%@ include file="../common/init.jsp" %>
<%@ page errorPage="jsp_error_handler.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<HTML>
<HEAD>
    <!--<meta http-equiv=Content-Type content="text/html;charset=UTF-8">-->
    <TITLE>
        <tiles:getAsString name="title"/>
    </TITLE>
    <link rel="stylesheet" type="text/css" href="<html:rewrite page='/css/common.css'/>">
    <script type="text/javascript" src="<html:rewrite page='/common/common.js'/>"></script>
</HEAD>
<%--<BODY onload="pointLocation()">--%>
<BODY>
<tiles:insert component="header.jsp"/>
<center>
    <table width="90%" style="margin-top:30px;">
        <tr>
            <td valign="top">

                <table class="header">
                    <tr>
                        <td>
                            <tiles:getAsString name="headerText"/>
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
                        <td style="padding:30px;">
                            <tiles:insert attribute='main'/>
                        </td>
                    </tr>
                </table>

            </td>
        </tr>
    </table>
</center>
</BODY>
</HTML>