$(document).ready(function(){
	$('#countText').on("propertychange change keyup paste input", function(){
		var content = $(this).val();
		console.log(content);
		$('#countResult').text(content.length); //실시간 글자수 카운팅
		}
	);
})