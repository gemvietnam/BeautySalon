<%@page import="assets.Helpers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core"	%> 
<%@page import="java.util.List"%>
<%@page import="models.Page"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Translator - Prettier CMS</title>
<%@include file="../styles-dashboard.jsp" %>
</head>
<body>

<div id="navigation">	
<%@include file="navigation.jsp" %>
</div>

<div id="content">

	<div id="content-bar">
		<h1>Translator</h1>
	</div>

	<div class="app-data">
		<form method="post" action="translator.jsp">
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<h3>Type the text to translate</h3>
						<textarea rows="5" id="input" name="inputText" class="form-control"></textarea>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<h3>Translation</h3>
						<textarea rows="5" id="output" name="outputText" class="form-control"></textarea>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="form-group">
						<label>Input language:</label>
						<select id="inputLanguage" name="language" class="form-control">
							<option value="english" selected>English</option>
							<option value="arabic">Arabic</option>
							<option value="french">French</option>
							<option value="spanish">Spanish</option>
							<option value="portuguese">Portuguese</option>
							<option value="italian">Italian</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group">
						<label>Output language:</label>
						<select id="language" name="language" class="form-control">
							<option value="english" disabled>English</option>
							<option value="arabic" selected>Arabic</option>
							<option value="french">French</option>
							<option value="spanish">Spanish</option>
							<option value="portuguese">Portuguese</option>
							<option value="italian">Italian</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">	
					<div class="form-group">
						<input id="translate" type="submit" value="Translate" class="btn btn-primary form-control" />
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

<script>
$("#inputLanguage").change(function() {
	var chosenLanguage = $(this).val();
	console.log("Chosen language is: " + chosenLanguage);
	$("#language option[value="+chosenLanguage+"]").attr("disabled", "disabled").siblings().removeAttr("disabled");
});

$("#language").change(function() {
	var chosenLanguage = $(this).val();
	console.log("Chosen language is: " + chosenLanguage);
	$("#inputLanguage option[value="+chosenLanguage+"]").attr("disabled", "disabled").siblings().removeAttr("disabled");
});

$("#translate").click(function(e) {
	e.preventDefault();
	var input = $("#input").val();
	var language = $("#language").val();
	var inputLanguage = $("#inputLanguage").val();
	console.log("input is: " + input + ", language: " + language + ", inputLanguage: " + inputLanguage);
	
		$.ajax({
			url: 'translate',
			method: 'post', 
			data: {'input': input, 'inputLanguage': inputLanguage, 'language': language},
			success: function(responseText) {
				$('#output').val(responseText);
			}
		});
});

</script>

</body>
</html>