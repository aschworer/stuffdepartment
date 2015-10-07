<%--
  Nakuru. 2007.
  Created: 11.10.2007 || 15:09:09 by A.Zainullina
--%>
<%@ page isErrorPage="true" %>
<%
session.setAttribute("javax.servlet.error.exception",
                        request.getAttribute("javax.servlet.error.exception"));
%>

<script language="JavaScript" type="text/javascript">
    window.location = "<%=request.getContextPath()%>/pages/exception.jsp";
</script>