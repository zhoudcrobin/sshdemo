<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>Bean数据源设置</title>
		<s:include value="../../../taglibs.jsp"/>
        <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/codemirror/lib/codemirror.css"/>'/>
        <script type="text/javascript" src='<s:url value="/ewcmssource/codemirror/lib/codemirror.js"/>'></script>
        <script type="text/javascript" src='<s:url value="/ewcmssource/codemirror/mode/plsql/plsql.js"/>'></script>
		<style type="text/css">
	      .CodeMirror {
	        border: 1px solid #eee;
	        height: 105px;
	        width: 400px;
	      }
	      .CodeMirror-scroll {
	        height: 105px;
	        width: 400px;
	        overflow-y: auto;
	        overflow-x: auto;
	      }
    	</style>
    	<script type="text/javascript">
	        $(function(){
	            <s:include value="../../../alertMessage.jsp"/>
	        });
        </script>		
	</head>
	<body>
		<s:form action="save" namespace="/report/chart">
			<table class="formtable" >
				<tr>
					<td>名称：</td>
					<td class="formFieldError">
						<s:textfield name="chartReportVo.name" cssClass="inputtext" maxlength="10" />
						<s:fielderror><s:param value="%{'chartReportVo.name'}" /></s:fielderror>&nbsp;&nbsp;<label style="color: red;">*</label>
					</td>
					<td>数据源类型：</td>
					<td>
						<s:select list="baseDSList" name="chartReportVo.baseDS.id" listKey="id" listValue="name" headerKey="0" headerValue="默认数据源" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>SQL表达式：<br/><a id="regexHelp" href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-help" onclick="parent.helpOperate();"></a></td>
					<td class="formFieldError" colspan="3">
						<s:textarea id="chartSql" name="chartReportVo.chartSql"/><s:fielderror><s:param value="%{'chartReportVo.chartSql'}" /></s:fielderror>
					</td>
					
				</tr>
				<tr>
					<td>图表类型：</td>
					<td>
						<s:select list="@com.ewcms.plugin.report.model.ChartReport$Type@values()" listValue="description" name="chartReportVo.type" id="chartReportVo.type"></s:select>
					</td>
					<td>工具提示：</td>
					<td>
						<s:checkbox name="chartReportVo.showTooltips" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图表标题：</td>
					<td>
						<s:textfield name="chartReportVo.chartTitle" cssClass="inputtext"/>
					</td>
					<td>图表标题字体：</td>
					<td>
						<s:select name="chartReportVo.fontName" list="fontNameMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.fontStyle" list="fontStyleMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.fontSize" list="fontSizeMap" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>横坐标轴标题：</td>
					<td>
						<s:textfield name="chartReportVo.horizAxisLabel" cssClass="inputtext"/>
					</td>
					<td>纵坐标轴标题：</td>
					<td>
						<s:textfield name="chartReportVo.vertAxisLabel" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>数据字体：</td>
					<td>
						<s:select name="chartReportVo.dataFontName" list="fontNameMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.dataFontStyle" list="fontStyleMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.dataFontSize" list="fontSizeMap" cssClass="inputtext"/>
					</td>
					<td>坐标轴字体：</td>
					<td>
						<s:select name="chartReportVo.axisFontName" list="fontNameMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.axisFontStyle" list="fontStyleMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.axisFontSize" list="fontSizeMap" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>坐标轴尺值字体：</td>
					<td>
						<s:select name="chartReportVo.axisTickFontName" list="fontNameMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.axisTickFontStyle" list="fontStyleMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.axisTickFontSize" list="fontSizeMap" cssClass="inputtext"/>
					</td>
					<td>数据轴标签角度：</td>
					<td>
						<s:select name="chartReportVo.tickLabelRotate" list="rotateMap" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图示说明：</td>
					<td>
						<s:checkbox name="chartReportVo.showLegend" cssClass="inputtext"/>
					</td>
					<td>图示位置：</td>
					<td>
						<s:select name="chartReportVo.legendPosition" list="positionMap" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图示字体：</td>
					<td>
						<s:select name="chartReportVo.legendFontName" list="fontNameMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.legendFontStyle" list="fontStyleMap" cssClass="inputtext"/>&nbsp;
						<s:select name="chartReportVo.legendFontSize" list="fontSizeMap" cssClass="inputtext"/>
					</td>
					<td>图表高度：</td>
					<td>
						<s:textfield name="chartReportVo.chartHeight" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>图表宽度：</td>
					<td>
						<s:textfield name="chartReportVo.chartWidth" cssClass="inputtext"/>
					</td>
					<td>RGB背景色：</td>
					<td>
						<s:textfield name="chartReportVo.bgColorR" maxLength="3" size="3" cssClass="inputtext"/>&nbsp;&nbsp;
						<s:textfield name="chartReportVo.bgColorG" maxLength="3" size="3" cssClass="inputtext"/>&nbsp;&nbsp;
						<s:textfield name="chartReportVo.bgColorB" maxLength="3" size="3" cssClass="inputtext"/>
					</td>
				</tr>
				<tr>
					<td>备注：</td>
					<td colspan="3">
						<s:textarea name="chartReportVo.remarks" cols="60"/>
					</td>
				</tr>
			</table>
			<s:hidden name="chartReportVo.id"/>
            <s:iterator value="selections" var="id">
                <s:hidden name="selections" value="%{id}"/>
            </s:iterator>			
		</s:form>
		<script type="text/javascript">
      		var editor = CodeMirror.fromTextArea(document.getElementById("chartSql"), {
        		lineNumbers: true,
        		matchBrackets: true,
        		indentUnit: 4,
        		mode: "text/x-plsql"
      		});
    	</script>
	</body>
</html>