<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>동물 정보 등록</title>
    <link href="https://bootswatch.com/5/lumen/bootstrap.css" rel="stylesheet">
  <style>
    .container {
      display: grid;
      place-items: center;
      height: 100vh;
      grid-template-columns: auto; /* 헤더의 너비를 컨텐츠에 맞게 설정 */
    }

    .custom-heading {
      font-family: 'HU네모의꿈180', serif;
      font-size: 3.5rem;
      margin-bottom: 20px; /* 헤더와 메인 컨텐츠 간격 조정 */
      color: #6c757d; /* 폰트 색상 변경 */
    }

    .content-box {
      border: 1px solid #ccc;
      padding: 20px;
      text-align: center;
      margin: 20px 0; /* 각 컨텐츠 상단과 하단 간격 조정 */
      display: flex;
      flex-direction: column; /* 버튼을 세로로 정렬 */
      align-items: center; /* 가운데 정렬 */
    }

    .content-box button {
      font-size: 16px;
      padding: 10px 200px; /* 버튼 길이 조정 */
      margin: 10px 0; /* 버튼 간격 설정 */
    }

    /* 스크롤이 가능하도록 수정 */
    body {
      overflow-y: auto;
    }
    
    h2 {
    font-family: '한컴 말랑말랑', serif;
    margin-bottom: 20px;
    font-weight: bold;
    }
    
        /* 각 테이블 간격 조정 */
    table {
    	font-family: '세종학당', serif;
      margin-bottom: 50px;
      text-align: left; /* 테이블 내용을 왼쪽 정렬로 설정 */
    }
    

    
  </style>
</head>
<body>
  <div class="container">
    <h1 class="custom-heading">동물 정보 등록</h1>
    
    <div class="content-box">
      <form id="animalInfoForm" method="post" action="InfoRegi">
          <!-- 기본 정보 입력 폼 -->
          <h2>1. 기본 정보</h2>
          <table>
              <tr>
                  <td><label for="scientificName">학명 (Scientific Name):</label></td>
                  <td><input type="text" id="scientificName" name="sciName"></td>
              </tr>
              <tr>
                  <td><label for="commonName">일반명 (Common Name):</label></td>
                  <td><input type="text" id="commonName" name="comName"></td>
              </tr>
              <tr>
                  <td><label for="taxonomy">분류 (Taxonomy):</label></td>
                  <td><input type="text" id="taxonomy" name="taxon" placeholder="계, 문, 강, 목, 과, 속, 종"></td>
              </tr>
          </table>
          
          <h2>2. 생물학적 특성</h2>
          <table>
              <tr>
                  <td><label for="morphologicalCharacteristics">외형적 특징 (Morphological Characteristics):</label></td>
                  <td><input type="text" id="morphologicalCharacteristics" name="morphChar" placeholder="크기, 색깔, 무늬, 체형"></td>
              </tr>
              <tr>
                  <td><label for="sex">성별 (Sex):</label></td>
                  <td><input type="text" id="sex" name="sex" placeholder="수컷, 암컷, 미상"></td>
              </tr>
              <tr>
                  <td><label for="reproductiveInformation">생식 정보 (Reproductive Information):</label></td>
                  <td><input type="text" id="reproductiveInformation" name="reproInfo" placeholder="번식 시기, 번식 방식 등"></td>
              </tr>
          </table>
          
          <h2>3. 서식지 정보</h2>
          <table>
              <tr>
                  <td><label for="locationOfDiscovery">발견 위치 (Location of Discovery):</label></td>
                  <td><input type="text" id="locationOfDiscovery" name="locDiscovery" placeholder="GPS 좌표, 지리적 위치"></td>
              </tr>
              <tr>
                  <td><label for="habitatType">서식지 유형 (Habitat Type):</label></td>
                  <td><input type="text" id="habitatType" name="habType" placeholder="숲, 사막, 해양, 강"></td>
              </tr>
              <tr>
                  <td><label for="habitatConditions">서식지 조건 (Habitat Conditions):</label></td>
                  <td><input type="text" id="habitatConditions" name="habCond" placeholder="기후, 식생, 물리적 환경"></td>
              </tr>
          </table>
          
          <h2>4. 행동 및 생태 정보</h2>
          <table>
              <tr>
                  <td><label for="diet">식이 습성 (Diet):</label></td>
                  <td><input type="text" id="diet" name="diet" placeholder="초식, 육식, 잡식"></td>
              </tr>
              <tr>
                  <td><label for="activityPatterns">활동 패턴 (Activity Patterns):</label></td>
                  <td><input type="text" id="activityPatterns" name="actPatterns" placeholder="주행성, 야행성 등"></td>
              </tr>
              <tr>
                  <td><label for="socialStructure">사회적 구조 (Social Structure):</label></td>
                  <td><input type="text" id="socialStructure" name="socStruct" placeholder="단독 생활, 무리 생활"></td>
              </tr>
          </table>
          
          <h2>5. 보호 상태</h2>
          <table>
              <tr>
                  <td><label for="conservationStatus">보호 등급 (Conservation Status):</label></td>
                  <td><input type="text" id="conservationStatus" name="consStatus" placeholder="IUCN 레드 리스트 등급"></td>
              </tr>
              <tr>
                  <td><label for="threats">위협 요소 (Threats):</label></td>
                  <td><input type="text" id="threats" name="threats" placeholder="서식지 파괴 등"></td>
              </tr>
              <tr>
                  <td><label for="conservationActions">보호 조치 (Conservation Actions):</label></td>
                  <td><input type="text" id="conservationActions" name="consActions" placeholder="보호 구역 설정 등"></td>
              </tr>
          </table>

          <button type="submit" class="btn btn-outline-info">보내기</button>
      </form>
    </div>
  </div>
</body>
</html>

