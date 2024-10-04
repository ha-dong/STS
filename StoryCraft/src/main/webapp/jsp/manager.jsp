<%@ page import="java.time.ZonedDateTime, java.time.format.DateTimeFormatter, java.time.ZoneId" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    // 한국 시간대 설정 (Asia/Seoul)
    ZonedDateTime todayInKST = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    String currentDate = todayInKST.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StoryCraft</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/manager.css">
</head>
<body>
    <div class="sidebar">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/resources/img/Story_Craft_white-remove.png" alt="Logo">
        </div>
        <nav>
			<ul>
				<li><a href="${pageContext.request.contextPath}/admin/stories">스토리 관리</a></li>
				<li><a href="${pageContext.request.contextPath}/adminUser">유저관리</a></li>
				<li><a href="${pageContext.request.contextPath}/notice">공지관리</a></li>
				<li><a href="${pageContext.request.contextPath}/adminInquiryList">문의관리</a></li>
			</ul>
        </nav>
    </div>
    
    <div class="main-content">
        <header>
		    <div class="title">Story Craft</div>
			<div class="actions">
				<a href="${pageContext.request.contextPath}/main">
					<button>메인 페이지</button>
				</a>
			</div>
		</header>
        
        <div class="dashboard">
            <div class="overview">
                <div class="today-tasks">
                    <h2>오늘의 들어온 스토리</h2>
                    <ul>
                        <li>신규 스토리</li>
                        <li>스토리 신고</li>
                        <li>사용자 신고</li>
                        <li>문의 처리</li>
                    </ul>
                </div>
                <div class="visitor-summary">
                    <h2>입자별 요약</h2>
                    <table>
                        <thead>
                            <tr>
                                <th>일자</th>
                                <th>신규 회원 수</th>
                                <th>방문자수</th>
                                <th>가입</th>
                                <th>신고</th>
                                <th>문의</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><%= currentDate %></td>
                                <td>1명</td>
                                <td>1명</td>
                                <td>1명</td>
                                <td>0건</td>
                                <td>0건</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
            <div class="responses-row">
                <!-- 컨텐츠 반응 섹션 -->
                <div class="content-responses">
                    <h2>컨텐츠 반응</h2>
                    <div class="response-list">
                        <div class="response">
                            <p>[스토리 후기] 리뷰입니다.</p>
                            <span>2024-08-27 17:46</span>
                        </div>
                    </div>
                    <!-- 드롭다운과 조회하기 버튼 추가 -->
                    <div class="controls">
                        <select>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                            <option value="50">50</option>
                        </select>
                        <button>조회하기</button>
                    </div>
                </div>
                
                <!-- 금일 문의 섹션 -->
                <div class="today-inquiries">
                    <h2>금일 문의</h2>
                    <div class="inquiry-list">
                        <div class="inquiry">
                            <p>문의 내용입니다.</p>
                            <span>2024-09-20 10:30</span>
                        </div>
                    </div> 
                    <!-- 드롭다운과 조회하기 버튼 추가 -->
                    <div class="controls">
                        <select>
                            <option value="10">10</option>
                            <option value="20">20</option>
                            <option value="30">30</option>
                            <option value="40">40</option>
                            <option value="50">50</option>
                        </select>
                        <button>조회하기</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
