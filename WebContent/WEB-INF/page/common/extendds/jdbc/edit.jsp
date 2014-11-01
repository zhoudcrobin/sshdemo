<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>jdbc数据源设置</title>
		<jsp:include page='/comresource/inifile/pageresource.jsp'/>
        <script type="text/javascript">
	        $(function(){
	        	<jsp:include page='/comresource/inifile/alertmessage.jsp'/>
	        });
        </script>		
	</head>
	<body>
		<s:form action="save" namespace="/extendds/jdbc">
			<table class="formtable" >
				<tr>
					<td>名称：</td>
					<td class="formFieldError">
						<s:textfield name="jdbcDSVo.name" cssClass="inputtext" maxlength="10" />
						<s:fielderror><s:param value="%{'jdbcDSVo.name'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>驱动名：</td>
					<td class="formFieldError">
						<s:textfield name="jdbcDSVo.driver" cssClass="inputtext" size="70" maxlength="127" />
						<s:fielderror><s:param value="%{'jdbcDSVo.driver'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>数据库连接URL：</td>
					<td class="formFieldError">
						<s:textfield name="jdbcDSVo.connUrl" cssClass="inputtext" size="70" maxlength="127" />
						<s:fielderror><s:param value="%{'jdbcDSVo.connUrl'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td class="formFieldError">
						<s:textfield name="jdbcDSVo.userName" cssClass="inputtext" size="70" maxlength="127" />
						<s:fielderror><s:param value="%{'jdbcDSVo.userName'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
						<s:textfield name="jdbcDSVo.passWord" cssClass="inputtext" size="70" maxlength="127" />
					</td>
				</tr>
				<tr>
					<td>描述：</td>
					<td>
						<s:textarea name="jdbcDSVo.remarks" cols="60"/>
					</td>
				</tr>
			</table>
			<s:hidden name="jdbcDSVo.id"/>
            <s:iterator value="selections" var="id">
                <s:hidden name="selections" value="%{id}"/>
            </s:iterator>			
		</s:form>
	</body>
</html>