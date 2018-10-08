<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="css/regist.css"/>
		 <script type="text/javascript" src="/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
		  var formObj = {
		      "checkForm":function(){
		         //1.非空验证
		         var flag = true;//控制表单提交的变量，默认为true
		         flag = this.checkNull("username","用户名不能为空") && flag;
		         flag = this.checkNull("password","密码不能为空") && flag;
		         flag = this.checkNull("password2","确认密码不能为空") && flag;
		         flag = this.checkNull("nickname","昵称不能为空") && flag;
		         flag = this.checkNull("email","邮箱不能为空") && flag;
		         flag = this.checkNull("valistr","验证码不能为空") && flag;
		         
		         //2.两次密码一致验证
		         flag = this.checkPassword("password","两次密码不一致") && flag;
		         //3.邮箱格式验证
		         flag = this.checkEmail("email","邮箱格式不正确") && flag;
		         return flag;
		      },
		      "checkEmail":function(name,msg){
		         var email = $("input[name='"+name+"']").val();
		         //当邮箱的值不为空的时候再进行格式判断
		         if($.trim(email) != ""){
		            var reg = /^\w+@\w+(\.\w+)+$/;
		            if(!reg.test(email)){
		              //设置错误提示信息
		              this.setMsg(name, msg);
		              return false;
		            }else{
		              this.setMsg(name,"");
		              return true;
		            }
		         }
		         return false;
		      },
		      "checkPassword":function(name,msg){
		         //获取password和password2的input的值
		         var psd1 = $("input[name='"+name+"']").val();
		         var psd2 = $("input[name='"+name+"2']").val();
		         //当两个都不为空串时再进行密码一致验证
		         if($.trim(psd1) != "" && $.trim(psd2) != ""){
		           if(psd1 != psd2){
		              //添加错误提示信息
		              this.setMsg(name+"2",msg);
		              return false;
		           }else{
		             //清空之前添加的错误提示信息
		             this.setMsg(name+"2", "");
		             return true;
		           }
		         }
		         return false;
		      },
		      "checkNull":function(name,msg){
		        //1)拿到对应的的input框的值
		         var value = $("input[name='"+name+"']").val();
		         //2)判断是否为空
		         if($.trim(value) == ""){
		           this.setMsg(name,msg);
		           return false;
		      }else{
		           //清空之前添加的错误提示
		           this.setMsg(name, "");
		           return true;
		      }
		    },
		    "setMsg":function(name,msg){
		         //获取name指定的input后面的span，然后将传入的错误提示信息
		         //显示在该span内部，并设置css样式
		         $("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
		    }
		  };
		  //当input失去焦点时马上执行对应的表单验证
		  //需要为每个input添加一个失去焦点的事件
		  //添加一个文档就绪事件，在事件中位每个input添加
		  $(function(){
		    //文档就绪事件，当当前文档加载完成后，会自动调用
		    $("input[name='username']").blur(function(){
		       //验证用户是否为空
		       var  flag = formObj.checkNull("username","用户名不能为空");
		       //发送ajxa请求
		       if(flag){//说明username不为空
		          var url = "${app}/AjaxCheckUsernameServlet?username="+$(this).val();
		          //load方法时通过一个组件来调用
		          //当ajax请求收到一个应答后，会将收到的应答内容自动填充到该组件内
		          $("#msg_username").load(url);
		       }
		    });
		     $("input[name='password']").blur(function(){
		       formObj.checkNull("password","密码不能为空");
		    });
		     $("input[name='password2']").blur(function(){
		       formObj.checkNull("password2","确认密码不能为空");
		       formObj.checkPassword("password","两次密码不一致");
		    });
		     $("input[name='nickname']").blur(function(){
		       formObj.checkNull("nickname","昵称不能为空");
		    });
		     $("input[name='email']").blur(function(){
		       formObj.checkNull("email","邮箱不能为空");
		       formObj.checkEmail("email","邮箱格式不正确");
		    });
		     $("input[name='valistr']").blur(function(){
		       formObj.checkNull("valistr","验证码不能为空");
		    });
		    
		    //为img标签添加一个点击事件
		    $("#valiImage").click(function(){
		       //每次点击修改src的值，在后面拼接一个不同的参数
		       //获取当前的时间戳
		       var timeStr = new Date().getTime();
		       //将时间戳拼接在url后面，实现每次点击都不一样
		       var url = "${app}/ValiImageServlet?time="+timeStr;
		       //修改img的src的属性
		       $(this).attr("src",url);
		    });
		  });
		</script>	
	</head>
	<body>
		<form action="${app}/RegistServlet" method="POST" onSubmit="return formObj.checkForm()">
			<h1>欢迎注册EasyMall</h1>
			<table>
			    <tr>
			        <td colspan="2" style="text-align: center;color:red">
			           ${requestScope.errMsg }
			        </td>
			    </tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value="${param.username}"/>
						<span id="msg_username"></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password" value="${param.password}"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2" value="${param.password2}"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value="${param.nickname}"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="${param.email}"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr"/>
						<img id="valiImage" src="${app }/ValiImageServlet" width="" height="" alt="" />
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

