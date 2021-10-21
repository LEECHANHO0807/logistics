<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <script src="https://unpkg.com/ag-grid-community/dist/ag-grid-community.min.noStyle.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-grid.css">
    <link rel="stylesheet" href="https://unpkg.com/ag-grid-community/dist/styles/ag-theme-balham.css">
<title>HOME</title>
    <style>
        * {
            margin: 0px;
        }

        h5 {
            margin-top: 3px;
            margin-bottom: 3px;
        }
    </style>
</head>
<body>
	<h5> 미세먼지 경보 </h5>
	
	<article class="apiGrid">
    <div align="center">
        <div id="apiGrid" class="ag-theme-balham" style="height:500px; width:auto; text-align: center;"></div>
    </div>
</article>

	<script>
	
	var apiData = [];
	
	$(document).ready(function(){
		   dustAPIList();
		});
	
	function dustAPIList(){
		   $.ajax({
		      url : "${pageContext.request.contextPath}/DustAPI.do",
		      dataType : "json",
		      type : 'POST',
		      data : {
		         method : "DustTornado"
		      },
		      success : function(data) {
		         apiData = data.openDust;
		         console.log(apiData);
		         
		         startAPI();
		      }
		   });
		}
	
	
	function startAPI(){
		
		console.log(apiData);

		let apiCoulumn = [
			{headerName: "지역", field: "districtName"},
			{headerName: "발령일", field: "dataDate"},
			{headerName: "해제일", field: "clearDate"},
			{headerName: "경보단계", field: "issueGbn" }
		];
		
		
		let apigirdOption = {
				columnDefs : apiCoulumn,
				rowSelection : 'single',
				rowData : apiData
		};
	
		new agGrid.Grid(apiGrid, apigirdOption);
	}
	</script>
	
	
</body>
</html>