<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Responsive Nav &middot; Advanced Left Navigation Demo</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <!--[if lte IE 8]><link rel="stylesheet" href="../css/responsive-nav.css"><![endif]-->
    <!--[if gt IE 8]><!--><link rel="stylesheet" href="../css/styles.css"><!--<![endif]-->
    <script src="../js/responsive-nav.js"></script>
    <!-- Loading Bootstrap -->
    <script src="../dist/js/vendor/jquery.min.js"></script>
    <script type="text/javascript" src="../dist/bootstrap/js/bootstrap.min.js"></script>
    <link href="../dist/css/vendor/bootstrap.min.css" rel="stylesheet">

    <!-- Loading Flat UI -->
    <link href="../dist/css/flat-ui.css" rel="stylesheet">
    <link href="../docs/assets/css/demo.css" rel="stylesheet">


    <!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
    <!--[if lt IE 9]>
    <script src="../dist/js/vendor/html5shiv.js"></script>
    <script src="../dist/js/vendor/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>

    <div role="navigation" id="foo" class="nav-collapse">
      <ul>
        <li class="active"><a href="user.html">主页</a></li>
        <li><a href="manageFrom.html">考核管理</a></li>
        <li><a href="addFrom.html">添加考核</a></li>
        <li><a href="#">Blog</a></li>
      </ul>
    </div>

    <div role="main" class="main">
      <a href="#nav" class="nav-toggle">Menu</a>

      <br>
      <br>
      <div class="jumbotron">
        <h1>欢迎回来</h1>
        <p>admin <span class="label label-success">管理员</span></p>

        <p>您上次登陆的时间为：2015年11月26日</p>
        <p><a class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" href="#" role="button">修改密码</a> <a class="btn btn-warning btn-lg" href="#" role="button">注销登陆</a></p>
        <p></p>
      </div>
    </div>



    <script>
      var navigation = responsiveNav("foo", {customToggle: ".nav-toggle"});
    </script>



    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">修改密码</h4>
          </div>
          <div class="modal-body">
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            <button type="button" class="btn btn-primary">保存</button>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
