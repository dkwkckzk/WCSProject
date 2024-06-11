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
</head>
<body>
<h1>동물 정보 확인</h1>

<h2>1. 기본 정보</h2>
<table>
    <tr>
        <td>학명 (Scientific Name):</td>
        <td><%= sciName %></td>
    </tr>
    <tr>
        <td>일반명 (Common Name):</td>
        <td><%= comName %></td>
    </tr>
    <tr>
        <td>분류 (Taxonomy):</td>
        <td><%= taxon %></td>
    </tr>
</table>

<h2>2. 생물학적 특성</h2>
<table>
    <tr>
        <td>외형적 특징 (Morphological Characteristics):</td>
        <td><%= morphChar %></td>
    </tr>
    <tr>
        <td>성별 (Sex):</td>
        <td><%= sex %></td>
    </tr>
    <tr>
        <td>생식 정보 (Reproductive Information):</td>
        <td><%= reproInfo %></td>
    </tr>
</table>

<h2>3. 서식지 정보</h2>
<table>
    <tr>
        <td>발견 위치 (Location of Discovery):</td>
        <td><%= locDiscovery %></td>
    </tr>
    <tr>
        <td>서식지 유형 (Habitat Type):</td>
        <td><%= habType %></td>
    </tr>
    <tr>
        <td>서식지 조건 (Habitat Conditions):</td>
        <td><%= habCond %></td>
    </tr>
</table>

<h2>4. 행동 및 생태 정보</h2>
<table>
    <tr>
        <td>식이 습성 (Diet):</td>
        <td><%= diet %></td>
    </tr>
    <tr>
        <td>활동 패턴 (Activity Patterns):</td>
        <td><%= actPatterns %></td>
    </tr>
    <tr>
        <td>사회적 구조 (Social Structure):</td>
        <td><%= socStruct %></td>
    </tr>
</table>

<h2>5. 보호 상태</h2>
<table>
    <tr>
        <td>보호 등급 (Conservation Status):</td>
        <td><%= consStatus %></td>
    </tr>
    <tr>
        <td>위협 요소 (Threats):</td>
        <td><%= threats %></td>
    </tr>
    <tr>
        <td>보호 조치 (Conservation Actions):</td>
        <td><%= consActions %></td>
    </tr>
</table>

<form action="Main.jsp">
    <button type="submit" class="btn btn-outline-info">확인</button>
</form>

</body>
</html>

