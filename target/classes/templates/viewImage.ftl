<#if existError??>
    <div>
        <p>File does not exist on server, perro culiao</p>
    </div>
<#else>
    <img src = "${ip}${filename}">
    <br/>
</#if>

<form action="/">
    <input type="submit" value="Back to the Home Page" />
</form>


