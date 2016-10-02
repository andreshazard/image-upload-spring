<html>

<body>
<script src="image_script.js"></script>
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

<script type="text/javascript">
    var img = document.getElementById('image');
    var width = img.clientWidth;
    var height = img.clientHeight;
    var image_title = String(width) + " X " + String(height);
    document.getElementById("image").title = image_title;

    function checkIfThumbnails(width, height) {
        if (width > 128 && height > 128) {
            createThumbnails(width, height);
        }
    }

    function createThumbnails(width, height) {
        var thumbnail_width_one = (width * 32)/128;
        var thumbnail_heeight_one = (height * 32)/128;
        var thumbnail_one_title = String(thumbnail_width_one) + " X " + String(thumbnail_heeight_one);
        var thumbnail_width_two = (width * 64)/128;
        var thumbnail_heeight_two = (height * 64)/128;
        var thumbnail_two_title = String(thumbnail_width_two) + " X " + String(thumbnail_heeight_two);
        document.getElementById("image_thumbnail_one").style.width = thumbnail_width_one;
        document.getElementById("image_thumbnail_one").style.height = thumbnail_heeight_one;
        document.getElementById("image_thumbnail_one").style.visibility = "visible";
        document.getElementById("image_thumbnail_one").title = thumbnail_one_title;
        document.getElementById("image_thumbnail_two").style.width = thumbnail_width_two;
        document.getElementById("image_thumbnail_two").style.height = thumbnail_heeight_two;
        document.getElementById("image_thumbnail_two").style.visibility = "visible";
        document.getElementById("image_thumbnail_two").title = thumbnail_two_title;

        }

    checkIfThumbnails(width, height);
</script>

</body>

</html>


