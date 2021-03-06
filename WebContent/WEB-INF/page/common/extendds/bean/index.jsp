<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>Bean数据源</title>	
		<jsp:include page='/comresource/inifile/pageresource.jsp'/>
		<script type="text/javascript" src='<s:url value="/comresource/js/extendds.js"/>'></script>
		<script type="text/javascript">
			connectTest = "<s:url namespace='/extendds/connect' action='test'/>";
			$(function(){
				ewcmsBOBJ = new EwcmsBase();
				ewcmsBOBJ.setQueryURL('<s:url namespace="/extendds/bean" action="query"/>');
	
				ewcmsBOBJ.setWinWidth(700);
				ewcmsBOBJ.setWinHeight(240);
				
				ewcmsBOBJ.openDataGrid('#tt',{
	                columns:[[
							{field:'id',title:'编号',width:50,sortable:true},
			                {field:'name',title:'名称',width:100},
			                {field:'beanName',title:'Bean名称',width:200},
			                {field:'beanMethod',title:'Bean方法',width:200},
			                {field:'remarks',title:'说明',width:100},
			                {field:'connectTest',title:'测试',width:30,
			                	formatter : function(val, rec) {
			                		return "&nbsp;<a href='javascript:void(0);' onclick='test(" +  rec.id + ");'><img src=<s:url value='/comresource/image/ds/connect_test.png'/> width='13px' height='13px' title='测试' style='border:0'/></a>";
			                	}
			                }
	                  ]]
				});
	
				ewcmsOOBJ = new EwcmsOperate();
				ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
				ewcmsOOBJ.setInputURL('<s:url namespace="/extendds/bean" action="input"/>');
				ewcmsOOBJ.setDeleteURL('<s:url namespace="/extendds/bean" action="delete"/>');
			});
		</script>		
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
	 		<table id="tt" fit="true"></table>	
	 	</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="&nbsp;作业设置" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                   <iframe id="editifr"  name="editifr" class="editifr" frameborder="0" onload="iframeFitHeight(this);" scrolling="no"></iframe>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0)" onclick="saveOperator()">保存</a>
                </div>
            </div>
        </div>	
        <div id="query-window" class="easyui-window" closed="true" icon='icon-search' title="查询"  style="display:none;">
            <div class="easyui-layout" fit="true"  >
                <div region="center" border="false" >
                <form id="queryform">
                	<table class="formtable">
                            <tr>
                                <td class="tdtitle">编号：</td>
                                <td class="tdinput">
                                    <input type="text" id="id" name="id" class="inputtext"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="tdtitle">Bean名称：</td>
                                <td class="tdinput">
                                    <input type="text" id="beanName" name="beanName" class="inputtext"/>
                                </td>
                            </tr>
               		</table>
               	</form>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="querySearch('');">查询</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="javascript:$('#query-window').window('close');">取消</a>
                </div>
            </div>
        </div>      	
	</body>
</html>