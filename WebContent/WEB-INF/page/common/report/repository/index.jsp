<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
	<head>
		<title>报表存储</title>	
		<jsp:include page='/comresource/inifile/pageresource.jsp'/>
		<script type="text/javascript">
		$(function(){
			ewcmsBOBJ = new EwcmsBase();
			ewcmsBOBJ.setQueryURL('<s:url namespace="/report/repository" action="query"/>');
			
			ewcmsBOBJ.delToolItem('新增');
			ewcmsBOBJ.delToolItem('修改');
			ewcmsBOBJ.addToolItem('发布资源', 'icon-publish', publishResource, 'btnPub');

			ewcmsBOBJ.openDataGrid('#tt',{
                columns:[[
						{field:'id',title:'编号',width:50,sortable:true},
		                {field:'name',title:'名称',width:300},
		                {field:'type',title:'类型',width:50},
		                {field:'updateDate',title:'更新时间',width:145},
		                {field:'publishDate',title:'发布时间',width:145},
		                {field:'description',title:'说明',width:300},
		                {field:'download',title:'下载',width:30,
		                	formatter:function(val,rec){
		                		return "&nbsp;<a href='javascript:void(0);' onclick='download(" +  rec.id + ");'><img src='../../ewcmssource/css/icons/download.png' width='13px' height='13px' title='下载' style='border:0'/></a>";
		                	}
		                }
		                ]]
			});

			ewcmsOOBJ = new EwcmsOperate();
			ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
			ewcmsOOBJ.setDeleteURL('<s:url namespace="/report/repository" action="delete"/>');
		});
		function download(id){
			window.open('<s:url namespace="/report/repository" action="download"/>?repositoryId=' + id,'popup','width=1280,height=700,resizable=yes,toolbar=no,directories=no,location=no,menubar=no,status=no,scrollbars=yes,left=' + (window.screen.width - 1280)/ 2 + ',top=' + (window.screen.height - 700) / 2);
		}
		function publishResource(){
			var rows = $('#tt').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert('提示', '请选择发布的资源记录', 'info');
				return;
			}
			var parameter = '';
			var rows = $('#tt').datagrid('getSelections');
			for ( var i = 0; i < rows.length; i++) {
				parameter = parameter + '&selections=' + rows[i].id;
			}
			$.post('<s:url namespace="/report/repository" action="publish"/>', parameter, function(data) {
				if (data == 'true') {
					$('#tt').datagrid('reload');
					$.messager.alert('提示', '资源发布成功', 'info');
				} else if (data == 'notinstate') {
					$.messager.alert('提示', '资源发布失败', 'info');
				}
				return;
			});
			return false;
		}
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
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);" onclick="javascript:$('#editifr').attr('src','');$('#edit-window').window('close');">关闭</a>
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
                                <td class="tdtitle">名称：</td>
                                <td class="tdinput">
                                    <input type="text" id="name" name="name" class="inputtext"/>
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