<#-- @ftlvariable name="code" type="platform.domain.Code" -->
<#import "commons.ftlh" as c>
<@c.page "Code">
    <@c.code code/>

<#--    <script>-->
<#--        setInterval(function () {-->
<#--            const xhr = new XMLHttpRequest();-->
<#--            xhr.open("GET", "/api/code/${code.id}");-->
<#--            xhr.send();-->

<#--            xhr.onload = function() {-->
<#--                if (xhr.status !== 200) {-->
<#--                    location.reload();-->
<#--                } else {-->
<#--                    const jsonResponse = JSON.parse(xhr.response);-->
<#--                    document.getElementById("time_restriction").innerText = jsonResponse["time"];-->
<#--                }-->
<#--            };-->
<#--        }, 750);-->
<#--    </script>-->
</@c.page>
