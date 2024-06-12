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
            grid-template-columns: 1fr; /* 컨텐츠의 너비를 균등하게 설정 */
        }

        .custom-heading {
            margin-top: 50px;
            font-family: 'HU네모의꿈180', serif;
            font-size: 3.5rem;
            margin-bottom: 20px; /* 헤더와 메인 컨텐츠 간격 조정 */
            color: #6f42c1;
        }

        .content-tlqkf {
            display: flex;
            justify-content: center; /* 수평 정렬을 위한 설정 */
            align-items: center; /* 세로 정렬을 위한 설정 */
            margin: 20px 0; /* 각 컨텐츠 상단과 하단 간격 조정 */
        }

        .content-tlqkf button {
            font-family: '세종학당', serif;
            font-size: 20px;
            padding: 10px 400px; /* 버튼 길이 조정 */
            margin: 10px 0; /* 버튼 간격 설정 */
            width: 100%; /* content-box의 너비 조정 */
        }

        /* 스크롤이 가능하도록 수정 */
        body {
            overflow-y: auto;
        }

        /* 각 테이블 간격 조정 */
        table {
            font-family: '세종학당', serif;
            font-size: 17px;
            margin-bottom: 50px;
            text-align: left; /* 테이블 내용을 왼쪽 정렬로 설정 */
            width: 100%;
        }

        th {
            font-family: "한컴 말랑말랑";
            font-size: 25px;
            color: #6f42c1;;
        }

        /* input 요소 너비 조정 */
        input[type="text"] {
            width: 100%; /* input 요소의 너비를 조정하세요 */
            max-width: 600px; /* 최대 너비 설정 */
            padding: 10px; /* 내부 여백 설정 */
            margin: 5px 0; /* 간격 설정 */
            box-sizing: border-box; /* padding, border를 포함한 크기 설정 */
            font-size: 17px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="custom-heading">동물 정보 등록</h1>
    <div class="content-tlqkf">
        <form id="animalInfoForm" method="post" action="InfoRegi">
            <!-- 기본 정보 입력 폼 -->
            <table>
                <tr>
                    <th colspan="2">1. 기본 정보</th>
                </tr>
                <tr>
                    <td><label for="scientificName">학명 :</label></td>
                    <td><input type="text" id="scientificName" name="sciName"></td>
                </tr>
                <tr>
                    <td><label for="commonName">일반명 :</label></td>
                    <td><input type="text" id="commonName" name="comName"></td>
                </tr>
                <tr>
                    <td><label for="taxonomy">분류 :</label></td>
                    <td><input type="text" id="taxonomy" name="taxon" placeholder="계, 문, 강, 목, 과, 속, 종"></td>
                </tr>

                <tr>
                    <th colspan="2">2. 생물학적 특성</th>
                </tr>
                <tr>
                    <td><label for="morphologicalCharacteristics">외형적 특징 :</label></td>
                    <td><input type="text" id="morphologicalCharacteristics" name="morphChar" placeholder="크기, 색깔, 무늬, 체형"></td>
                </tr>
                <tr>
                    <td><label for="sex">성별 :</label></td>
                    <td><input type="text" id="sex" name="sex" placeholder="수컷, 암컷, 미상"></td>
                </tr>
                <tr>
                    <td><label for="reproductiveInformation">생식 정보 :</label></td>
                    <td><input type="text" id="reproductiveInformation" name="reproInfo" placeholder="번식 시기, 번식 방식 등"></td>
                </tr>

                <tr>
                    <th colspan="2">3. 서식지 정보</th>
                </tr>
                <tr>
                    <td><label for="locationOfDiscovery">발견 위치 :</label></td>
                    <td><input type="text" id="locationOfDiscovery" name="locDiscovery" placeholder="GPS 좌표, 지리적 위치"></td>
                </tr>
                <tr>
                    <td><label for="habitatType">서식지 유형 :</label></td>
                    <td><input type="text" id="habitatType" name="habType" placeholder="숲, 사막, 해양, 강"></td>
                </tr>
                <tr>
                    <td><label for="habitatConditions">서식지 조건 :</label></td>
                    <td><input type="text" id="habitatConditions" name="habCond" placeholder="기후, 식생, 물리적 환경"></td>
                </tr>

                <tr>
                    <th colspan="2">4. 행동 및 생태 정보</th>
                </tr>
                <tr>
                    <td><label for="diet">식이 습성 :</label></td>
                    <td><input type="text" id="diet" name="diet" placeholder="초식, 육식, 잡식"></td>
                </tr>
                <tr>
                    <td><label for="activityPatterns">활동 패턴 :</label></td>
                    <td><input type="text" id="activityPatterns" name="actPatterns" placeholder="주행성, 야행성 등"></td>
                </tr>
                <tr>
                    <td><label for="socialStructure">사회적 구조 :</label></td>
                    <td><input type="text" id="socialStructure" name="socStruct" placeholder="단독 생활, 무리 생활"></td>
                </tr>

                <tr>
                    <th colspan="2">5. 보호 상태</th>
                </tr>
                <tr>
                    <td><label for="conservationStatus">보호 등급 :</label></td>
                    <td><input type="text" id="conservationStatus" name="consStatus" placeholder="IUCN 레드 리스트 등급"></td>
                </tr>
                <tr>
                    <td><label for="threats">위협 요소 :</label></td>
                    <td><input type="text" id="threats" name="threats" placeholder="서식지 파괴 등"></td>
                </tr>
                <tr>
                    <td><label for="conservationActions">보호 조치 :</label></td>
                    <td><input type="text" id="conservationActions" name="consActions" placeholder="보호 구역 설정 등"></td>
                </tr>
            </table>
            <button type="submit" class="btn btn-outline-info">보내기 </button>
		</form>
	</div>
</div>
</body>
</html>


