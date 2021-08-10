<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<html>
<head>
    <style>
    </style>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="<%=basePath%>jui/js/jquery-1.7.2.js" type="text/javascript"></script>

    <title>设备运行状态</title>
</head>
<body >
    <div>
          播放声音       
    </div>
    <audio id="alarmWav" src="<c:url value='/resources/audio/alarm1.ogg'/>" autoplay="autoplay" ></audio>
    <form method="post" id="formID" action="<c:url value='/rm/reportBoard.sp?method=equipmentStatus'/>&baseFactory=${vo.baseFactory}&baseArea=${vo.baseArea}"  >
    
    <table class="eqlist" border="1" cellpadding="0" cellspacing="0" width="80%">
        <thead class="title">
            <tr>
                <th width="5%" height="16.6%">线</th>
                <th width="95%" id="boardTitle">设备运行状态跟踪</th>
            </tr>
        </thead>
        <tbody id="eqstatusTbody">
        <tr>
            <td colspan="2" id="ttt"></td>
        </tr>
        </tbody>
    </table>
    <input type="button" id="voiceBtn" value="test" onclick="playAlarm()">
    </form>
    </body>
        <script type="text/javascript">
    var i = 0;
    jQuery(document).ready(function(){
            //setTimeout("playAlarm()",10000);
            //document.getElementById("voiceBtn").click();  
             playAlarm();
    });

   function playAlarm()
   {
       try
       {
           var m = document.getElementById("alarmWav");  
           m.volume = 1;
           m.play();
           i ++;
       }catch(e){
           alert(e.description);
       }
       $("#ttt").html("play" + i);
           setTimeout("playAlarm()",10000);
   }
        
    </script>
</html>

