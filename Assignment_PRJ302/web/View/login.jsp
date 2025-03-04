<%-- 
    Document   : login
    Created on : Mar 3, 2025, 6:53:52 PM
    Author     : Phanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        body{
            margin:0;
            color:#6a6f8c;
            background:#F9F7F7;
            font:600 16px/18px 'Open Sans',sans-serif;
        }
        *{
            box-sizing:border-box
        }
        .login-wrap{
            width:100%;
            margin:auto;
            max-width:525px;
            min-height:670px;
            border-radius: 25px;
            position:relative;
            box-shadow:0 12px 15px 0 rgb(17, 45, 78),0 17px 50px 0 rgba(0,0,0,.19);
        }
        .login-html{
            width:100%;
            height:100%;
            position:absolute;
            border-radius: 25px;
            padding:90px 70px 50px 70px;
            background:rgba(40,57,101,.9);
        }

        .login-form .group .label,
        .login-form .group .input,
        .login-form .group .button{
            width:100%;
            color:#fff;
            display:block;
        }
        .login-form .group .input,
        .login-form .group .button{
            border:none;
            padding:15px 20px;
            border-radius:25px;
            background:rgba(255,255,255,.1);
        }
        .login-form .group input[data-type="password"]{
            text-security:circle;
            -webkit-text-security:circle;
        }
        .login-form .group .label{
            text-transform:uppercase;
            color:#aaa;
            font-size:12px;
        }
        .login-form .group .button{
            background:#1161ee;
        }

        .hr{
            height:2px;
            margin:50px 0 50px 0;
            background:rgb(255, 255, 255);
        }
        .foot-lnk{
            text-align:center;
        }
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
    <div class="login-wrap">
        <div class="login-html">
            <h1 style="color: white">Login</h1>
            <div class="login-form">
                <div class="sign-in-htm">
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input id="pass" type="password" class="input" data-type="password">
                    </div>
                    <div class="hr"></div>
                    <div class="group">
                        <input type="submit" class="button" value="Sign In"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
