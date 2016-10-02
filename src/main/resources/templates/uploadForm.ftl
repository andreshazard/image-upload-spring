<html>

<head>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<#if message??>
    <div>
        <h2>${message}</h2>
    </div>
</#if>

<div>
    <form method="POST" enctype="multipart/form-data" action="/">
        <table>
            <tr>
                <td>File to upload:</td>
                <td><input type="file" name="file" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload" /></td>
            </tr>
        </table>
    </form>
</div>

<div>
   <ul>
       <#list files as file>
            <li>
                <a href="${file}">${file}</a>
            </li>

       </#list>
   </ul>
</div>

</body>
</html>