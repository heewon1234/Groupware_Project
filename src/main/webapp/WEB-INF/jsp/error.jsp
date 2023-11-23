<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 NotFound</title>
</head>
<body>
	<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
	 <style>
        *{box-sizing: border-box;}

        .container{
            border-radius: 5px;
            padding:0px;
            margin-top:150px;
            border:3px solid gainsboro;
            width:600px;
            height:600px;
        }
        .container>div{width:100%;}

        .icon{
            height:40%;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        img{height:85%;}
        .text{height:40%; }
        .text-top{height:60%; }
        .text-top>div{
            width:100%;
            height:50%;
            font-weight: bold;
            font-size:xx-large;
        }
        .text-top>div:first-child{
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .text-top>div:last-child{
            display: flex;
            align-items: start;
            justify-content: center;
        }
        .text-bottom{
            height:40%;
            width:100%;
        }
        .text-bottom>div{ height:50%; }
        .text-bottom>div:first-child{
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .text-bottom>div:last-child{
            display: flex;
            align-items: start;
            justify-content: center;
        }
        .btns{
            height:20%;
            display: flex;
            justify-content: space-evenly;
            margin-top:20px;
        }
        .btns>button{
            width:130px;
            height:40px;
            border-radius: 10px;
            background-color: rgb(246, 244, 244);
            border:1px solid gray;
        }
    </style>
</head>
<body>
	<div class="container">
        <div class="icon">
            <img src="/images/commons/error/notfound.png">
        </div>
        <div class="text">
            <div class="text-top">
                <div>페이지를 찾을 수 없습니다.</div>
                <div>(404 Not Found)</div>
            </div>
            <div class="text-bottom">
                <div>페이지가 존재하지 않거나, 사용할 수 없는 페이지입니다.</div>
                <div>입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</div>
 
            </div>           
        </div>
        <div class="btns">
            <button id="back">이전화면</button>
            <button id="home">홈으로가기</button>
        </div>
    </div>
    <script>
        let back = document.getElementById("back");
        let home = document.getElementById("home");
        back.onclick = function(){
            history.back();
        }
        home.onclick = function(){
            location.href = "/"
        }
    </script>
</body>
</html>