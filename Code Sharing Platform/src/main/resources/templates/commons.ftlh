<#import "/spring.ftl" as spring/>

<#macro page title>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>${title}</title>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/style.css'/>">
        <link rel="stylesheet" type="text/css"
              href="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/styles/default.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/highlight.js/10.4.1/highlight.min.js"></script>
        <#--noinspection JSUnresolvedVariable,JSUnresolvedFunction-->
        <script>hljs.initHighlightingOnLoad();</script>
    </head>
    <body>
    <#nested/>
    </body>
    </html>
</#macro>

<#macro code code>
<#-- @ftlvariable name="code" type="platform.domain.Code" -->
    <div class="code-box">
        <span id="load_date">${code.date}</span>
        <#if code.viewsRestricted>
            <br/>
            <span id="views_restriction">${code.views}</span> more views allowed
        </#if>
        <#if code.timeRestricted>
            <br/>
            The code will be available for <span id="time_restriction">${code.time}</span> second(s)
        </#if>
        <pre id="code_snippet"><code>${code.code}</code></pre>
    </div>
</#macro>
