<%--
  Created by IntelliJ IDEA.
  User: Sboy
  Date: 2017/8/19
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>json交互测试</title>
    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
    </script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}"></script>--%>
    <script type="text/javascript">
        //请求json，输出json
        function requestJson() {
            $.ajax({
                type:"post",
                url:'${pageContext.request.contextPath}/requestJson.do',
                contentType:'application/json;charset=utf-8',
                data:'{"name":"笔记本","price":7778}',  //数据格式是json串，商品信息
                success:function (data) {    //返回json结果
                    alert(data);
                }

            });

        }
        //请求key/value，输出json
        function responseJson() {
            $.ajax({
                type:"post",
                url:'${pageContext.request.contextPath}/responseJson.do',
//              请求是key/value这里不需要指定contentType，因为默认就是key/value类型
//              contentType:'application/json;charset=utf-8',
                data:'name=笔记本&price=7778',
                success:function (data) {
                    alert(data.name);
                }

            });
        }
    </script>
</head>
<body>
<input type="button" onclick="requestJson()" value="请求json，输出json"/>
<input type="button" onclick="responseJson()" value="请求key/value，输出json"/>

</body>
</html>
