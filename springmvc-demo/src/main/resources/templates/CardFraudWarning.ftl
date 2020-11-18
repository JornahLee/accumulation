<#setting url_escaping_charset='utf-8'>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Panda</title>
</head>
<style>body{margin:0}.advantage{border-bottom:1px solid #cfcfcf;padding-bottom:25px;margin-bottom:35px}.advantage>p{margin:0}.advantage>.tips{margin:25px auto 0;color:#999999;text-align: center;}.advantage>ul{padding-left:0;margin:0}.advantage>ul>li{padding-left:30px;list-style:none;position:relative}.content h3{margin:35px 0}.content>.purchase-url{display:block;width:258px;height:35px;line-height:35px;text-align:center;background:#47B96D;color:#fff;border:none;border-radius:4px;margin:25px 0 30px;cursor:pointer;outline:none;text-decoration:none}footer{margin-top:68px;width:100%;height:5px;background-image:linear-gradient(31deg,#9de686 0,#2fad66 100%)}@media screen and (min-width:900px){.container{width:600px;margin:0 auto;padding-top:30px}header img{width:123px;margin-bottom:30px}.content{border-top:1px solid #cfcfcf;border-top:1px solid #cfcfcf;font-size:16px;letter-spacing:.8px;color:#333;line-height:28px}.title{margin-bottom:30px}.tips{margin:30px 0 60px}}@media screen and (max-width:900px){.container{padding:22.5px 10% 0}header img{width:123px;margin-bottom:22.5px}.content{border-top:1px solid #cfcfcf;font-size:14px;letter-spacing:.8px;color:#333;line-height:24px}.title{margin-bottom:22.5px}.tips{margin:22.5px 0 45px}}</style>
<style>
    .advantage>ul>li>i{display: inline-block;width: 5px;height: 5px;background: #47b96d;border-radius: 50%;position: absolute;top: 50%;left: 10px;transform: translateY(-2.5px);}
    .subscribe{font-weight: bolder;}.subscribe a{color: #3499de;}.menu{text-align: center;}.menu a{color:#3499de;text-decoration: none;}.menu i {display: inline-block;width: 4px;height: 4px;background: #3499de;border-radius: 50%;transform: translateY(-2px);}
</style>
<body>
<div class="container">
    <header>
        <img src="https://pkg.pandacabin.pw/payment-method-logo/panda-logo.png" alt=""/>
    </header>
    <div class="content">
            <#list data?keys as key>
                <p>${key}</p>

                <table border="1">
                    <tr>
                        <th>Month</th>
                        <th>Savings</th>
                    </tr>
<#--                    <#assign orders = data[key]>-->
                    <#list data[key] as order>
                        <td>${order.id!'id-d'}</td>
                        <td>${order.payname!'id-d'}</td>
                    </#list>
                </table>

            </#list>
        
    </div>
</div>
<footer></footer>
</body>
</html>
