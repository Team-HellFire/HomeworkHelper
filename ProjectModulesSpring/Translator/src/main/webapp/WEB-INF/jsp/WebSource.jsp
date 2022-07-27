<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ProjectModules</title>
    <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="js/AjaxCode.js"></script>
    <script src="js/CountText.js"></script>
    <script src="js/SpellCheckerTool.js"></script>
    <script src="js/SpellCheckerLibrary.js"></script>
    <link href="css/PageCSS.css" rel="stylesheet"/>

    <script type = "text/javascript">
        var options = {
        "textarea": document.getElementById("textarea"),
        "submitBtn": document.getElementById("submitBtn"),
        "resultarea": document.getElementById("result"),
        "callback": function(data) {
            $('#resultarea').val() = data.getResult();
        }
    };
    var spell = new spell(options);
    spell.init();

    var text = "아녕하세요";
    var result;

    spell.spellCheck(text);
    result = spell.getResult();
    alert(result);
    </script>
</head>
<body>
    <div> <h2>번역기</h2>
        <textarea name="번역 전" id="beforeTranslate" cols="30" rows="10"></textarea>

        <select name="languageSelect" id="selectLanguage">
            <option value="en-ko" id="en-ko">영어->한글</option>
            <option value="ko-en" id="ko-en">한글->영어</option>
        </select>
        <br>
        <button id="translateButton">번역</button>
        <br>
        <textarea name="번역 후" id="afterTranslate" cols="30" rows="10"></textarea>
    </div>
    <br>

    <div> <h2>글자수 세기</h2>
        <textarea name="글자 수 세기" id="countText" cols="30" rows="10"></textarea><br>
        글자 수 : <span id = "countResult">0</span>
    </div>
    <br>

    <!--
    <div> <h2>맞춤법 검사기</h2>
        <textarea name="맞춤법 검사전" id="textarea" cols="30" rows="10"></textarea>
        <button id="submitBtn">검사 버튼</button><br>
        <textarea name="맞춤법 검사후" id="resultarea" cols="30" rows="10"></textarea>
    </div>
    -->
    <iframe id = "checkGrammer" src="http://speller.cs.pusan.ac.kr/" frameborder="1"></iframe>
</body>
</html>