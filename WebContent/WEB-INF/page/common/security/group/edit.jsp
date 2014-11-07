<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>用户组编辑</title>
	<jsp:include page='/comresource/inifile/pageresource.jsp'/>
    <script type="text/javascript" src='<s:url value="/comresource/js/group/edit.js"/>'></script>
    <script type="text/javascript">
    	<jsp:include page='/comresource/inifile/alertmessage.jsp'/>
       $(function(){
           var groupEdit = new GroupEdit({
              detailUrl:'<s:url action="detail"/>',
              hasNameUrl:'<s:url action="hasGroupname"/>'
           });
         
           groupEdit.init({
               addSaveState:<s:property value="addSaveState"/>,
               name:'<s:property value="fullname"/>'
           });
       });
            
    </script>
</head>
<body class="easyui-layout" >
        <div region="center" border="false">
            <div class="easyui-tabs" border="false" fit="true">
                     <s:form action="save" method="post">
                        <table class="formtable">
                            <tr>
                                <td>名称：</td>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td style="border: none;">GROP_</td>
                                            <td id="name-td" class="formFieldError" style="border: none;">
                                                <s:if test="eventOP=='add'">
                                                <s:textfield cssClass="inputtext" name="name" cssStyle="width:350px;" />
                                                </s:if>
                                                <s:else>
                                                <s:textfield cssClass="inputtext" name="name" readonly="true" cssStyle="width:350px;" />
                                                </s:else>
                                                <s:fielderror><s:param value="%{'name'}"/></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td>备注：</td>
                                <td class="formFieldError">
                                    <s:textarea name="remark" cssStyle="width:400px;height:120px;" />
                                    <s:fielderror><s:param value="%{'remark'}" /> </s:fielderror>
                                </td>
                            </tr>
                        </table>
                        <s:hidden name="eventOP" />
                        <s:iterator value="newGroupNames" var="newName">
                            <s:hidden name="newGroupNames" value="%{newName}"/>
                        </s:iterator>
                     </s:form>
            </div>
        </div>
</body>
</html>