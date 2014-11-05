<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>Bean数据源设置</title>
		<jsp:include page='/comresource/inifile/pageresource.jsp'/>	
	</head>
	<body>
		<s:form action="save" namespace="/extendds/bean">
			<table class="formtable" >
				<tr>
					<td>名称：</td>
					<td class="formFieldError">
						<s:textfield name="beanDSVo.name" cssClass="inputtext" maxlength="10" />
						<s:fielderror><s:param value="%{'beanDSVo.name'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>Bean名称：</td>
					<td class="formFieldError">
						<s:textfield name="beanDSVo.beanName" cssClass="inputtext" size="70" maxlength="127" />
						<s:fielderror><s:param value="%{'beanDSVo.beanName'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>Bean名称：</td>
					<td class="formFieldError">
						<s:textfield name="beanDSVo.beanMethod" cssClass="inputtext" size="70" maxlength="127" />
						<s:fielderror><s:param value="%{'beanDSVo.beanMethod'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>描述：</td>
					<td>
						<s:textarea name="beanDSVo.remarks" cols="60"/>
					</td>
				</tr>
			</table>
			<s:hidden name="beanDSVo.id"/>
            <s:iterator value="selections" var="id">
                <s:hidden name="selections" value="%{id}"/>
            </s:iterator>			
		</s:form>
	</body>
</html>