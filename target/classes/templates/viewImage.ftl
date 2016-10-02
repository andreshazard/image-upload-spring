<html>

<body>
<#--<script src="image_script.js"></script>-->
<#if existError??>
    <div>
        <p>File does not exist on server, perro culiao</p>
    </div>
<#else>
    <div id="image_div">
        <img id="image" src = "${ip}${filename}">
        <img id="image_thumbnail_one" src = "${ip}${filename}" style="visibility: hidden">
        <img id="image_thumbnail_two" src = "${ip}${filename}" style="visibility: hidden">
        <br/>
    </div>
</#if>

<form action="/">
    <input type="submit" value="Back to the Home Page" />
</form>

<script type="text/javascript" src="/js/image_script.js"></script>
</body>

</html>


