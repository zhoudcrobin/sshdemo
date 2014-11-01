<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
	<head>
		<title>文字报表参数</title>
		<jsp:include page='/comresource/inifile/pageresource.jsp'/>
		<script type="text/javascript">
			$(function(){
				ewcmsBOBJ = new EwcmsBase();
				ewcmsBOBJ.setQueryURL('<s:url namespace="/report/category/detail" action="query"/>?categoryId=<s:property value="categoryId"/>');
	
				ewcmsBOBJ.setWinWidth(700);
				ewcmsBOBJ.setWinHeight(200);
				
                $('#tt').propertygrid({
					width:700,
			        url:ewcmsBOBJ.getQueryURL(),
			        showGroup:true,
			        scrollbarSize:0,
			        singleSelect:false,
			        columns:[[
			            {field:'ck',checkbox:true},
			            {field:'name',title:'名称',width:150},
			            {field:"value",title:'描述',width:260}
			        ]],
			        toolbar:[
			            {id:'btnadd',text:'添加',iconCls:'icon-add',handler:addCallBack},
			            {id:'btnremove',text:'移除',iconCls:'icon-remove',handler:delCallBack},
			            {id:'btnBack',text:'缺省查询',iconCls:'icon-back',handler:defQueryCallBack}
			        ]
				});
				
				ewcmsOOBJ = new EwcmsOperate();
				ewcmsOOBJ.setQueryURL(ewcmsBOBJ.getQueryURL());
				ewcmsOOBJ.setInputURL('<s:url namespace="/report/category/detail" action="input"/>?categoryId=<s:property value="categoryId"/>');
				ewcmsOOBJ.setDeleteURL('<s:url namespace="/report/category/detail" action="delete"/>?categoryId=<s:property value="categoryId"/>');
			})
			function saveReport(){
				saveOperator();
				$.messager.alert('提示', '保存成功', 'info');
				$('#tt').propertygrid('reload');
			}
        </script>
	</head>
	<body class="easyui-layout">
		<div region="center" style="padding:2px;" border="false">
			<table id="tt" fit="true"></table>
		</div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" title="&nbsp;参数设置" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                   <iframe id="editifr"  name="editifr" class="editifr" frameborder="0" onload="iframeFitHeight(this);" scrolling="no"></iframe>
                </div>
                <div region="south" border="false" style="text-align:center;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0)" onclick="saveReport();">保存</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);" onclick="javascript:$('#editifr').attr('src','');$('#edit-window').window('close');">关闭</a>
                </div>
            </div>
        </div>	
	</body>
</html>