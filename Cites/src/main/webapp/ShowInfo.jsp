<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%
    // 서블릿에서 설정한 속성을 가져오기
    String sciName = (String) request.getAttribute("sciName");
    String comName = (String) request.getAttribute("comName");
    String taxon = (String) request.getAttribute("taxon");
    String morphChar = (String) request.getAttribute("morphChar");
    String sex = (String) request.getAttribute("sex");
    String reproInfo = (String) request.getAttribute("reproInfo");
    String locDiscovery = (String) request.getAttribute("locDiscovery");
    String habType = (String) request.getAttribute("habType");
    String habCond = (String) request.getAttribute("habCond");
    String diet = (String) request.getAttribute("diet");
    String actPatterns = (String) request.getAttribute("actPatterns");
    String socStruct = (String) request.getAttribute("socStruct");
    String consStatus = (String) request.getAttribute("consStatus");
    String threats = (String) request.getAttribute("threats");
    String consActions = (String) request.getAttribute("consActions");
%>
<!DOCTYPE html>
<html>
<head>
    <title>등록 정보 확인</title>
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
            flex-direction: column;
            align-items: center; /* 세로 정렬을 위한 설정 */
            margin: 20px 0; /* 각 컨텐츠 상단과 하단 간격 조정 */
        }

        .content-tlqkf button {
            font-family: '세종학당', serif;
            font-size: 20px;
            padding: 10px 400px; /* 버튼 세로 간격 조정 */
            margin: 10px 0; /* 버튼 간격 설정 */
            width: 100%; /* 버튼 너비 조정 */
            text-align: center; /* 텍스트 가운데 정렬 */
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
            border-collapse: collapse; /* 테이블 경계를 겹치도록 설정 */
        }

        th {
            font-family: "한컴 말랑말랑";
            font-size: 25px;
            color: #6f42c1;
        }

        /* 등록 정보 확인 테이블 스타일 */
        .info-data {
            padding: 10px; /* 셀 안의 내용과 경계 사이 간격 설정 */
            margin: 5px 0; /* 셀 바깥 간격 설정 */
            box-sizing: border-box; /* padding, border를 포함한 크기 설정 */
            width: 70%; /* 셀의 너비 조정 */
            max-width: 600px; /* 최대 너비 설정 */
            border-left: 2px solid #6f42c1; /* 왼쪽 테두리 선 추가 */
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="custom-heading">동물 정보 확인</h1>

    <div class="content-tlqkf">
        <table>
            <tr>
                <th colspan="2">1. 기본 정보</th>
            </tr>
            <tr>
                <td>학명 :</td>
                <td class="info-data"><%= sciName %></td>
            </tr>
            <tr>
                <td>일반명 :</td>
                <td class="info-data"><%= comName %></td>
            </tr>
            <tr>
                <td>분류 :</td>
                <td class="info-data"><%= taxon %></td>
            </tr>

            <tr>
                <th colspan="2">2. 생물학적 특성</th>
            </tr>
            <tr>
                <td>외형적 특징 :</td>
                <td class="info-data"><%= morphChar %></td>
            </tr>
            <tr>
                <td>성별 :</td>
                <td class="info-data"><%= sex %></td>
            </tr>
            <tr>
                <td>생식 정보 :</td>
                <td class="info-data"><%= reproInfo %></td>
            </tr>

            <tr>
                <th colspan="2">3. 서식지 정보</th>
            </tr>
            <tr>
                <td>발견 위치 :</td>
                <td class="info-data"><%= locDiscovery %></td>
            </tr>
            <tr>
                <td>서식지 유형 :</td>
                <td class="info-data"><%= habType %></td>
            </tr>
            <tr>
                <td>서식지 조건 :</td>
                <td class="info-data"><%= habCond %></td>
            </tr>

            <tr>
                <th colspan="2">4. 행동 및 생태 정보</th>
            </tr>
            <tr>
                <td>식이 습성 :</td>
                <td class="info-data"><%= diet %></td>
            </tr>
            <tr>
                <td>활동 패턴 :</td>
                <td class="info-data"><%= actPatterns %></td>
            </tr>
            <tr>
                <td>사회적 구조 :</td>
                <td class="info-data"><%= socStruct %></td>
            </tr>

            <tr>
                <th colspan="2">5. 보호 상태</th>
            </tr>
            <tr>
                <td>보호 등급 :</td>
                <td class="info-data"><%= consStatus %></td>
            </tr>
            <tr>
                <td>위협 요소 :</td>
                <td class="info-data"><%= threats %></td>
            </tr>
            <tr>
                <td>보호 조치 :</td>
                <td class="info-data"><%= consActions %></td>
            </tr>
        </table>

        <form action="Main.jsp">
            <button type="submit" class="btn btn-outline-info">확인</button>
        </form>
    </div>
</div>
</body>
</html>
               


