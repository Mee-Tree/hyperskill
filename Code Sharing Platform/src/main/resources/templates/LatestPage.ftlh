<#-- @ftlvariable name="latest" type="platform.domain.Code[]" -->
<#import "commons.ftlh" as c>
<@c.page "Latest">
    <#if latest?size == 0>
        <h3>No code yet</h3>
    </#if>
    <#list latest as code>
        <@c.code code/>
    </#list>
</@c.page>
