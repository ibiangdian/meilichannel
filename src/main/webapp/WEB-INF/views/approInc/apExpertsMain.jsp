<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE9" />
<title>美丽频道</title>
</head>
<script type="text/javascript">
$(function() {
     window.open("${ctx}/reviewConditions.htm","_blank","alwaysRaised=1,scrollbars=1");
     window.open("${ctx}/valuationPrinciple.htm","_blank","alwaysRaised=1,scrollbars=1");
  });
</script>

<frameset rows="110, *, 30" frameborder="0" framespacing="0" id="main_content">
   <frame src="${ctx}/approInc/apExpertsTop" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" /> 
	<frameset cols="220, *" frameborder="no" framespacing="0">  
		<frame src="${ctx}/nav/left2" name="leftFrame" scrolling="auto" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${ctx}/userInfo/showUserInfo" name="mainFrame" id="mainFrame" title="mainFrame" noresize="noresize"/>
	</frameset> 
  <frame src="${ctx}/inc/foot" name="footFrame" scrolling="no" noresize="noresize" id="footFrame" title="footFrame" />
</frameset><noframes></noframes>
<body >
</body>

</html>
