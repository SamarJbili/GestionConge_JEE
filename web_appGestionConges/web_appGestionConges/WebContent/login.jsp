<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f5f5f5;
            background-image: url('src/assets/images/1.jpg');
            background-position: center;
            background-repeat: no-repeat;
            background-size: cover;
            background-attachment: fixed;
            font-family: 'Poppins', sans-serif;
        }
        .warper {
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: transparent;
        }
        .login-box {
            position: relative;
            width: 450px;
            backdrop-filter: blur(3px);
            border: 2px solid #c6c3c3;
            border-radius: 15px;
            padding: 2.5em;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0px 0px 10px 2px rgba(0, 0, 0, 0.3);
        }
        .login-header {
            position: absolute;
            top: -40px;
            left: 50%;
            transform: translateX(-50%);
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #c6c3c3;
            width: 140px;
            height: 70px;
            border-radius: 0 0 20px 20px;
        }
        .login-header span {
            font-size: 30px;
            color: rgb(0, 20, 134);
        }
        .login-header::before {
            content: "";
            position: absolute;
            top: 0;
            left: -30px;
            width: 30px;
            height: 30px;
            border-top-right-radius: 50%;
            background: transparent;
            box-shadow: 15px 0 0 0 #c6c3c3;
        }
        .login-header::after {
            content: "";
            position: absolute;
            top: 0;
            right: -30px;
            width: 30px;
            height: 30px;
            border-top-left-radius: 50%;
            background: transparent;
            box-shadow: -15px 0 0 0 #c6c3c3;
        }
        .input-box {
            position: relative;
            display: flex;
            flex-direction: column;
            margin: 20px 0;
        }
        .input-field {
            width: 100%;
            height: 55px;
            font-size: 16px;
            background: transparent;
            color: black;
            padding: 0 50px 0 20px;
            border: 2px solid #c6c3c3;
            border-radius: 30px;
            outline: none;
        }
        .label {
            position: absolute;
            top: 15px;
            left: 20px;
            transition: .2s;
            color: rgb(0, 20, 134);
        }
        .input-field:focus ~ .label,
        .input-field:valid ~ .label {
            top: -10px;
            left: 20px;
            font-size: 14px;
            background-color: #c6c3c3;
            border-radius: 30px;
            padding: 0 10px;
        }
        .icon {
            position: absolute;
            top: 7px;
            right: 25px;
            font-size: 20px;
            color: rgb(0, 20, 134);
        }
        .input-submit {
            width: 100%;
            height: 50px;
            color: rgb(0, 20, 134);
            background-color: #c6c3c3;
            font-size: 16px;
            border: none;
            border-radius: 30px;
            cursor: pointer;
            transition: .3s;
        }
        .input-submit:hover {
            background-color: rgb(230, 175, 103);
        }
    </style>
    <script type="text/javascript">
        function showError(message) {
            alert(message);
        }
    </script>
</head>
<body>
    <div class="warper">
        <div class="login-box">
            <div class="login-header">
                <span>Login</span>
            </div>
            <form action="UserWebEJB" method="post">
                <div class="input-box">
                    <input type="text" id="login" name="login" class="input-field" required>
                    <label for="login" class="label">Login</label>
                </div>
                <div class="input-box">
                    <input type="password" id="password" name="password" class="input-field" required>
                    <label for="password" class="label">Password</label>
                </div>
                <button type="submit" class="input-submit">Login</button>
            </form>
            <% 
                String errorMessage = (String) request.getAttribute("errorMessage");
                if (errorMessage != null) {
            %>
            <script type="text/javascript">
                showError("<%= errorMessage %>");
            </script>
            <% 
                }
            %>
        </div>
    </div>
</body>
</html>
