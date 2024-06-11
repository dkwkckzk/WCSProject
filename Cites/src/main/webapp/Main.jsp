<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>메인 페이지</title>
  <!-- 부트스트랩 CSS 파일 포함 -->
  <link href="https://bootswatch.com/5/lumen/bootstrap.css" rel="stylesheet">
  <style>
    .container {
      display: grid;
      place-items: center;
      height: 100vh;
      overflow: hidden;
      grid-template-columns: auto; /* 헤더의 너비를 컨텐츠에 맞게 설정 */
    }

	.custom-heading {
	  font-family: 'HU네모의꿈180', serif;
	  font-size: 3.5rem;
	  margin-bottom: 0;

	}


    .content-box {
      border: 1px solid #ccc;
      padding: 20px;
      text-align: center;
      margin: 10px 0;
      display: flex;
      flex-direction: column; /* 버튼을 세로로 정렬 */
      align-items: center; /* 가운데 정렬 */
      margin-bottom: 200px; /* content-box 아래쪽 여백 추가 */
    }

    .content-box button {
      font-size: 16px;
      padding: 10px 200px; /* 버튼 길이 조정 */
      margin: 10px 0; /* 버튼 간격 설정 */
    }
  </style>
</head>
<body>
  <div class="container">
    <h1 class="custom-heading">메인페이지</h1>
    <div class="content-box">
      <form action="AnimalRegi.jsp" method="GET">
        <button type="submit" class="btn btn-outline-info">정보 추가</button>
      </form>
      <form action="InfoShow" method="GET">
        <button type="submit" class="btn btn-outline-info">정보 조회</button>
      </form>
    </div>
  </div>
</body>
</html>