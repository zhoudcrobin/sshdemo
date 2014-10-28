<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>卫生厅保健处管理平台</title>
        <s:include value="taglibs.jsp"/>
        <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/page/home.css"/>'/>
        <script type="text/javascript" src='<s:url value="/ewcmssource/page/home.js"/>'></script>
        <script type="text/javascript" src='<s:url value="/ewcmssource/fcf/js/FusionCharts.js"/>'></script>
        <script type="text/javascript">
            var _home = new home();
            $(function(){
            	var clock = new Clock();
            	clock.display(document.getElementById("clock"));
            	
                ewcmsBOBJ = new EwcmsBase();
                ewcmsOOBJ = new EwcmsOperate();
                _home.init({
                    user:'<s:url action="user" namespace="/account"/>',
                    password:'<s:url action="password" namespace="/account"/>',
                    exit:'<s:url value="/logout.do"/>'
                });
                
            });
        </script>
    </head>
    <body class="easyui-layout">
        <div region="north" split="true" class="head">
        	<table width="100%">
        		<tr>
        			<td width="50%" style="text-align: left"><img src='<s:url value="/ewcmssource/image/top_bg_ewcms.gif"/>' height="35px" border="0" style="border:0;padding-left:4px;padding-top:13px;"/> | 卫生厅保健处管理系统V1.1</td>
        			<td width="50%">
        				<table width="100%">
        					<tr>
			        			<td height="30px" width="97%" style="text-align: right"><span style="font-size:13px;font-weight: bold;"><span id="user-name"><s:property value="realName"/></span> <s:property value="siteName"/>欢迎你</span> | <span id="clock"></span></td>
        						<td width="2%"><a id="button-main" href="#" style="border:0;padding:0;"><img src="<s:url value='/ewcmssource/image/exit.png'/>" width="17" height="17" style="border:0;"/></a></td>
        						<td width="1%"></td>
        					</tr>
        					<tr>
        						<td height="20px" colspan="2" >
        							<table width="100%">
        								<tr>
        									<td width="65%" style="text-align:right;">
			        							<span id="tipMessage" style="color:red;font-size:13px;"></span>
			        						</td>
			        						<td width="35%" style="text-align:left">
			        							<div class="bs">
													<a class="styleswitch a1" style="cursor: pointer" title="谈黄色" rel="sunny"></a>
													<a class="styleswitch a2" style="cursor: pointer" title="浅蓝色" rel="cupertino"></a>
													<a class="styleswitch a4" style="cursor: pointer" title="黑色" rel="dark-hive"></a>	
													<a class="styleswitch a5" style="cursor: pointer" title="灰色" rel="pepper-grinder"></a>		
												</div>
			        						</td>
			        					</tr>
			        				</table>
			        			</td>
        					</tr>
        				</table>
        			</td>
        		</tr>
        	</table>
             <div id="mm" class="easyui-menu" style="width:120px;display:none;">
                <div id="user-menu" iconCls="icon-edit">修改用户信息</div>
                <div id="password-menu" iconCls="icon-password">修改密码</div>
                <div class="menu-sep"></div>
                <div id="exit-menu" iconCls="icon-exit">退出</div>
             </div>
        </div>
        <div region="south" split="true" style="height:2px;background:#efefef;overflow:hidden;"></div>
        <div region="west" split="true" title="EWCMS平台菜单" style="width:180px;padding:1px;overflow:hidden;">
            <div id="mainmenu" class="easyui-accordion" fit="true" border="false">
            	<sec:authorize ifAnyGranted="ROLE_ADMIN">
            	<div title="业务管理" style="overflow:auto;">
            		<div class="nav-item">
                       <a href="javascript:_home.addTab('省级人员','bjc/yiliaozheng/index.do')">
                            <img src="ewcmssource/image/shengji.png" style="border:0;"/><br/>
                            <span>省级人员</span>
                        </a>
                    </div>
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('汇总统计查询','bjc/huizong/index.do')">
                            <img src="ewcmssource/image/huizong.png" style="border:0;"/><br/>
                            <span>汇总统计查询</span>
                        </a>
                    </div>
            		<div class="nav-item">
                       <a href="javascript:_home.addTab('门诊报销','bjc/baoxiao/menzhen/index.do')">
                            <img src="ewcmssource/image/baoxiaomenzhen.png" style="border:0;"/><br/>
                            <span>门诊报销</span>
                        </a>
                    </div> 
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('住院报销','bjc/baoxiao/zhuyuan/index.do')">
                            <img src="ewcmssource/image/baoxiaozhuyuan.png" style="border:0;"/><br/>
                            <span>住院报销</span>
                        </a>
                    </div> 
            	</div>
                <div title="历史查询管理" selected="true" style="overflow:auto;">
               		<div class="nav-item">
                       <a href="javascript:_home.addTab('门诊历史费用','bjc/feiyong/menzhen/index.do')">
                            <img src="ewcmssource/image/menzhen.png" style="border:0;"/><br/>
                            <span>门诊历史费用</span>
                        </a>
                    </div>
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('住院历史登记','bjc/feiyong/dengji/index.do')">
                            <img src="ewcmssource/image/dengji.png" style="border:0;"/><br/>
                            <span>住院历史登记</span>
                        </a>
                    </div>
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('住院历史费用','bjc/feiyong/zhuyuan/index.do')">
                            <img src="ewcmssource/image/zhuyuan.png" style="border:0;"/><br/>
                            <span>住院历史费用</span>
                        </a>
                    </div>
                </div>
                <div title="实时查询管理" style="overflow:auto;">
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('门诊实时费用','bjc/shishifeiyong/menzhen/index.do')">
                            <img src="ewcmssource/image/shishimenzhen.png" style="border:0;"/><br/>
                            <span>门诊实时费用</span>
                        </a>
                    </div>
                	<div class="nav-item">
                       <a href="javascript:_home.addTab('住院实时登记','bjc/shishifeiyong/dengji/index.do')">
                            <img src="ewcmssource/image/shishidengji.png" style="border:0;"/><br/>
                            <span>住院实时登记</span>
                        </a>
                    </div>
                    <div class="nav-item">
                       <a href="javascript:_home.addTab('住院实时费用','bjc/shishifeiyong/zhuyuan/index.do')">
                            <img src="ewcmssource/image/shishizhuyuan.png" style="border:0;"/><br/>
                            <span>住院实时费用</span>
                        </a>
                    </div>
                    
                </div>
              	<div title="报表统计" style="overflow:auto;">
	                <s:iterator value="categoryReportList" status="st">
	                    <div class="nav-item">
	                       <a href="javascript:_home.addTab('<s:property value="name"/>','report/category/categoryreport.do?categoryId=<s:property value="id"/>')">
	                            <img src="ewcmssource/image/report_show.png" style="border:0;"/><br/>
	                            <span><s:property value="name"/></span>
	                        </a>
	                    </div>	
	               	</s:iterator>                  
                </div>     
                <div title="任务管理" style="overflow:auto;">
                	 <div class="nav-item">
                        <a href="javascript:_home.addTab('任务设置','scheduling/jobinfo/index.do')"> 
                            <img src="ewcmssource/image/scheduling_job.png" style="border: 0" /><br/>
                            <span>任务设置</span>
                         </a>
                    </div>
                    <div class="nav-item">
                        <a  href="javascript:_home.addTab('作业设置','scheduling/jobclass/index.do')"> 
                             <img src="ewcmssource/image/scheduling_jobclass.png" style="border: 0" /><br/>
                             <span>作业设置</span>
                        </a>
                    </div>
                </div>
                <div title="权限管理" style="overflow:auto;">
                   <div class="nav-item">
                        <a href="javascript:_home.addTab('权限列表','security/authority/index.do')">
                            <img src="ewcmssource/image/role.png" style="border:0;"/><br/>
                            <span>权限列表</span>
                        </a>
                   </div>
                   <div class="nav-item">
                       <a href="javascript:_home.addTab('用户组管理','security/group/index.do')">
                            <img src="ewcmssource/image/group.png" style="border:0;"/><br/>
                            <span>用户组管理</span>
                        </a>
                   </div>
                   <div class="nav-item">
                       <a href="javascript:_home.addTab('用户管理','security/user/index.do')">
                            <img src="ewcmssource/image/user.png" style="border:0;"/><br/>
                            <span>用户管理</span>
                        </a>
                    </div>
                </div>     
               	<div title="报表管理" style="overflow:auto;">
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('文字报表','report/text/index.do')">
                            <img src="ewcmssource/image/report_text.png" style="border:0"/><br/>
                            <span>文字报表</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('图型报表','report/chart/index.do')">
                            <img src="ewcmssource/image/report_chart.png" style="border:0"/><br/>
                            <span>图型报表</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表分类','report/category/index.do')">
                            <img src="ewcmssource/image/report_category.png" style="border:0"/><br/>
                            <span>报表分类</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表存储','report/repository/index.do')">
                            <img src="ewcmssource/image/report_repository.png" style="border:0"/><br/>
                            <span>报表存储</span>
                        </a>
               	    </div>
               	    <!--  
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表集','report/show/index.do')">
                            <img src="ewcmssource/image/report_show.png" style="border:0"/><br/>
                            <span>报表集</span>
                        </a>
               	    </div>
               	    -->
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('数据源','ds/index.do')">
                            <img src="ewcmssource/image/report_ds.png" style="border:0"/><br/>
                            <span>数据源</span>
                        </a>
               	    </div>
               	</div>
                </sec:authorize>
            </div>
        </div>
        <div region="center" style="overflow:hidden;">
            <div class="easyui-tabs" id="systemtab" fit="true" border="false">
                <div title="首页" style="padding:5px;overflow:hidden;">
                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                    	<tr>
                    		<td class="portal-column-td" width="32%">
                    			<div style="overflow:hidden;padding:0 0 0 0">
	        						<div class="panel" style="margin-bottom:2px;">
	        							<div class="panel-header">
	        								<div class="panel-title">门诊医疗费</div>
	        								<div class="panel-tool">
												<s:select id="yearCreate" list="years" name="yearCreate" onchange="selectMzfYear(this);"/>
											</div>
	        							</div>
	        							<div style="height: 300px; padding: 5px;" closable="true" collapsible="false" class="portal-p panel-body">
	        								<div id="chartMzfDiv" align="center">门诊医疗费</div>
											<script type="text/javascript">
												function selectMzfYear(select){
													$('#yearCreate').val(select.value);
													showMzfChart();
											  	}
											  	function showMzfChart(){
												  	$.post("<s:url action='createMzf'/>", {yearCreate : $('#yearCreate').val()}, function(result) {
														var myChart = new FusionCharts("<s:url value='/ewcmssource/fcf/swf/Column3D.swf'/>?ChartNoDataText=无数据显示", "myChartId", "900", "300");
												      	myChart.setDataXML(result);      
												      	myChart.render("chartMzfDiv");
												   	});  
											  	}
											  	showMzfChart();
											</script>
				    					</div>
				    				</div>
        						</div>
        					</td>
        				</tr>
        					<td class="portal-column-td" width="32%">
                    			<div style="overflow:hidden;padding:0 0 0 0">
	        						<div class="panel" style="margin-bottom:2px;">
	        							<div class="panel-header">
	        								<div class="panel-title">住院医疗费</div>
	        								<div class="panel-tool">
												<s:select id="yearCreate" list="years" name="yearCreate" onchange="selectZyfYear(this);"/>
											</div>
	        							</div>
	        							<div style="height: 300px; padding: 5px;" closable="true" collapsible="false" class="portal-p panel-body">
	        								<div id="chartZyfDiv" align="center">住院医疗费</div>
											<script type="text/javascript">
												function selectZyfYear(select){
													$('#yearCreate').val(select.value);
													showZyfChart();
											  	}
											  	function showZyfChart(){
												  	$.post("<s:url action='createZyf'/>", {yearCreate : $('#yearCreate').val()}, function(result) {
														var myChart = new FusionCharts("<s:url value='/ewcmssource/fcf/swf/Column3D.swf'/>?ChartNoDataText=无数据显示", "myChartId", "900", "300");
												      	myChart.setDataXML(result);      
												      	myChart.render("chartZyfDiv");
												   	});  
											  	}
											  	showZyfChart();
											</script>
				    					</div>
				    				</div>
        						</div>
        					</td>
                    	<tr>
        				</tr>
			         </table>
                </div>
            </div>
        </div>
        <div id="edit-window" class="easyui-window" closed="true" icon="icon-winedit" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false" style="padding: 10px;" fit="true">
                    <iframe id="editifr" name="editifr" class="editifr" frameborder="0" onload="iframeFitHeight(this);" scrolling="auto"></iframe>
                </div>
                <div region="south" border="false" style="padding-right:20px;text-align:right;height:28px;line-height:28px;background-color:#f6f6f6">
                    <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0)" onclick="window.frames['editifr'].pageSubmit();">确定</a>
                    <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="$('#edit-window').window('close');">取消</a>
                </div>
            </div>
        </div>
		<div id="detail-window" class="easyui-window" icon="" closed="true" style="display:none;">
            <div class="easyui-layout" fit="true">
                <div region="center" border="false">
                	<iframe id="editifr_detail"  name="editifr_detail" frameborder="0" width="100%" height="100%" scrolling="auto" style="width:100%;height:100%;"></iframe>
                </div>
            </div>
        </div>
    </body>
</html>