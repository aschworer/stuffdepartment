<%--
  Pelican Configuration Manager. 2007.
  Created: 20.10.2007 || 16:43:40 by A.Zainullina
--%>
<%@ include file="../common/init.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %><%@ page errorPage="jsp_error_handler.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html:form action="/AddEditEmployeeValidate" method="post" enctype="multipart/form-data">
    <html:hidden property="method" value="unspecified"/>

    <html:hidden property="employee.id"/>

    <bean:define id="employee" name="AddEditEmployeeForm"
                 property="employee"/>

    <table width=100% cellpadding="0" cellspacing="3">
        <tr>
            <td style="padding:0"  width=20% rowspan="5">
                <table>
                    <tr>
                        <html:hidden property="employee.photoCopyFilename"/>
                        <td style="padding:0"  style="padding:0;margin:0">
                            <c:choose>
                                <c:when test="${employee.photoCopyFilename!=null && ''!=employee.photoCopyFilename}">
                                    <img src="../staff_uploads/${employee.id}/${employee.photoCopyFilename}"
                                         alt="photo">
                                </c:when>
                            </c:choose>

                        </td>
                    </tr>
                    <tr>
                        <td style="padding:0"  style="padding:0;padding-top:40px;margin-top:5px;">
                            Загрузить фото:
                            <html:file name="AddEditEmployeeForm" property="photoCopy"/>
                        </td>
                    </tr>
                </table>
            </td>
            <td style="padding:0"  align="left" width="30%">Фамилия</td>
            <td style="padding:0"  align="left" width="50%">
                <html:text name="AddEditEmployeeForm"
                           property="employee.lastname"
                           styleClass="fieldInput"/>

            </td>
        </tr>

        <tr>
            <td style="padding:0"  align="left" >Имя</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.firstname"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" >Отчество</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.middlename"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" >&nbsp;</td>
            <td style="padding:0"  align="left" >&nbsp;</td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" >&nbsp;</td>
            <td style="padding:0"  align="left" >&nbsp;</td>
        </tr>
        <tr>
            <td style="padding:0"  colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  rowspan=6><strong>Паспорт</strong></td>
            <td style="padding:0"  align="left" > Номер</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.passport.no"
                           styleClass="fieldInput"/>
            </td>
        </tr>

        <tr>
            <td style="padding:0"  align="left" > Серия</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.passport.ser"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" > Кем выдан</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.passport.receivedFrom"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" > Где выдан</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.passport.receivedWhere"
                           styleClass="fieldInput"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" > Когда выдан</td>
            <td style="padding:0"  align="left" >
                <html:text name="AddEditEmployeeForm"
                           property="employee.passport.receivedWhen"
                           styleClass="fieldInput"/>
            </td>
        </tr>

        <html:hidden property="employee.passport.passportCopyFilename"/>

        <tr>
            <td style="padding:0"  align="left">
                <c:choose>
                    <c:when test="${employee.passport.passportCopyFilename!=null && ''!=employee.passport.passportCopyFilename}">

                        <a href="../staff_uploads/${employee.id}/${employee.passport.passportCopyFilename}"
                           target="_blank">Копия паспорта</a>
                    </c:when>
                    <c:otherwise>
                        Копия паспорта
                    </c:otherwise>
                </c:choose>
            </td>
            <td style="padding:0"  align="left">Загрузить:
                <html:file name="AddEditEmployeeForm" property="passportCopy"/>

            </td>
        </tr>

        <tr>
            <td style="padding:0"  colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" rowspan="2"> <strong>Позиция</strong></td>
            <td style="padding:0"  align="left"> Текущий отдел</td>
            <td style="padding:0" >
                <html:select name="AddEditEmployeeForm"
                             property="employee.currentDepartment.id"
                             styleClass="fieldInput">
                    <html:optionsCollection name="AddEditEmployeeForm" property="departments" label="name" value="id"/>
                </html:select>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left"> Текущая должность</td>
            <td style="padding:0" >

                <html:select name="AddEditEmployeeForm"
                             property="employee.currentPosition.id"
                             styleClass="fieldInput">
                    <html:optionsCollection name="AddEditEmployeeForm" property="positions" label="name" value="id"/>
                </html:select>
            </td>
        </tr>

        <html:hidden property="employee.resumeCopyFilename"/>
        <tr>
            <td style="padding:0"  colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  align="left" rowspan="2"><strong>Резюме</strong></td>
            <td style="padding:0"  align="left">
                <c:choose>
                    <c:when test="${employee.resumeCopyFilename!=null && ''!=employee.resumeCopyFilename}">

                        <a href="../staff_uploads/${employee.id}/${employee.resumeCopyFilename}"
                           target="_blank">Копия резюме</a>
                    </c:when>
                    <c:otherwise>
                        Копия резюме
                    </c:otherwise>
                </c:choose>
            </td>
            <td style="padding:0"  align="left">Загрузить:
                <html:file name="AddEditEmployeeForm" property="resumeCopy"/>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  colspan="3">
                <hr>
            </td>
        </tr>

        <tr>
            <td style="padding:0"  align="left"><strong>Характеристика</strong></td>
            <td style="padding:0"  colspan="2">
                <html:textarea name="AddEditEmployeeForm" rows="5" cols="20"
                               property="employee.characteristics"
                               styleClass="fieldInput"/>
            </td>
        </tr>

        <tr>
            <td style="padding:0"  colspan="3">
                <hr>
            </td>
        </tr>
        <tr>
            <td style="padding:0"  rowspan="4"><strong>Расписание</strong></td>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.mondayFlag"/>
                Пн
            </td>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.sundayFlag"/>
                Вс

            </td>
        </tr>
        <tr>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.tuesdayFlag"/>
                Вт
            </td>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.fridayFlag"/>
                Пт
            </td>
        </tr>

        <tr>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.wednesdayFlag"/>
                Ср
            </td>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.saturdayFlag"/>
                Сб
            </td>
        </tr>
        <tr>
            <td style="padding:0" >
                <html:checkbox name="AddEditEmployeeForm" property="employee.shedule.thursdayFlag"/>
                Чт
            </td>
            <td style="padding:0" >
            </td>
        </tr>

        <tr>
            <td style="padding:0"  colspan="3" align="right">
                <html:submit styleClass="btn" onclick="setMethodField('submit')" value="Сохранить"/>
            </td>
        </tr>
    </table>
</html:form>