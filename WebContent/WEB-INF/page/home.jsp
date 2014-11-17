<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>sshdemo</title>
        <jsp:include page='/comresource/inifile/pageresource.jsp'/>
        <link rel="stylesheet" type="text/css" href='<s:url value="/comresource/css/home.css"/>'/>
        <script type="text/javascript" src='<s:url value="/comresource/js/home.js"/>'></script>
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
        		<tr style="background:url('<s:url value="/comresource/image/topbg.gif"/>');">
        			<td width="50%" style="text-align: left;"><font style="FONT-SIZE: 40px;color:white">  sshdemo 框架演示</font></td>
        			<td width="50%">
        				<table width="100%">
        					<tr>
			        			<td height="30px" width="97%" style="text-align: right"><span style="font-size:13px;font-weight: bold;color:white;">标签1|标签2|标签3|<s:property value="realName"/> </span></td>
        						<td width="2%"><a id="button-main" href="#" style="border:0;padding:0;"><img src="<s:url value='/comresource/image/exit.png'/>" width="17" height="17" style="border:0;"/></a></td>
        						<td width="1%"></td>
        					</tr>
        					<tr>
        						<td height="20px" colspan="2" >
        							<table width="100%">
        								<tr>
        								<td></td>
			        						<td width="330" style="text-align:right">
			        							<div class="bs">
													<a class="styleswitch a1" style="cursor: pointer" title="黄色" rel="sunny"></a>
													<a class="styleswitch a3" style="cursor: pointer" title="蓝色" rel="default"></a>	
													<a class="styleswitch a4" style="cursor: pointer" title="黑色" rel="dark-hive"></a>	
													<a class="styleswitch a5" style="cursor: pointer" title="灰色" rel="pepper-grinder"></a>		
												</div>
												<span style="color:white" id="clock"/>
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
        <div region="west" split="true" title="基本功能菜单" style="width:180px;padding:1px;overflow:hidden;">
              <div id="mainmenu" class="easyui-accordion" fit="true" border="false">
               	<div title="报表管理" style="overflow:auto;">
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表制作','report/show/index.do')">
                            <img src="comresource/image/report_text.png" style="border:0"/><br/>
                            <span>报表制作</span>
                        </a>
               	    </div>                  	
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('文字报表','report/text/index.do')">
                            <img src="comresource/image/report_text.png" style="border:0"/><br/>
                            <span>文字报表</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('图型报表','report/chart/index.do')">
                            <img src="comresource/image/report_chart.png" style="border:0"/><br/>
                            <span>图型报表</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表分类','report/category/index.do')">
                            <img src="comresource/image/report_category.png" style="border:0"/><br/>
                            <span>报表分类</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('报表存储','report/repository/index.do')">
                            <img src="comresource/image/report_repository.png" style="border:0"/><br/>
                            <span>报表存储</span>
                        </a>
               	    </div>
               	    <div class="nav-item">
                         <a href="javascript:_home.addTab('数据源','extendds/index.do')">
                            <img src="comresource/image/report_ds.png" style="border:0"/><br/>
                            <span>数据源</span>
                        </a>
               	    </div> 
               	</div>              
                <div title="任务计划" style="overflow:auto;">
                	 <div class="nav-item">
                        <a href="javascript:_home.addTab('任务设置','schedule/jobinfo/index.do')"> 
                            <img src="comresource/image/scheduling_job.png" style="border: 0" /><br/>
                            <span>任务设置</span>
                         </a>
                    </div>
                    <div class="nav-item">
                        <a  href="javascript:_home.addTab('作业设置','schedule/jobclass/index.do')"> 
                             <img src="comresource/image/scheduling_jobclass.png" style="border: 0" /><br/>
                             <span>作业设置</span>
                        </a>
                    </div>
                </div>
                <div title="权限管理" style="overflow:auto;">
                   <div class="nav-item">
                        <a href="javascript:_home.addTab('权限列表','security/authority/index.do')">
                            <img src="comresource/image/role.png" style="border:0;"/><br/>
                            <span>权限列表</span>
                        </a>
                   </div>
                   <div class="nav-item">
                       <a href="javascript:_home.addTab('用户组管理','security/group/index.do')">
                            <img src="comresource/image/group.png" style="border:0;"/><br/>
                            <span>用户组管理</span>
                        </a>
                   </div>
                   <div class="nav-item">
                       <a href="javascript:_home.addTab('用户管理','security/user/index.do')">
                            <img src="comresource/image/user.png" style="border:0;"/><br/>
                            <span>用户管理</span>
                        </a>
                    </div>
                </div>     
            </div>
        </div>
        <div region="center" style="overflow:hidden;">
            <div class="easyui-tabs" id="systemtab" fit="true" border="false">
                <div title="首页" style="padding:5px;overflow:hidden;">
                  <center><h1> 欢迎试用本系统!</h1> </center>
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