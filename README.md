<img src="https://user-images.githubusercontent.com/63503102/90855056-88b15780-e3b9-11ea-8e8e-b18523c162c2.png"> 

# <영화 예매 사이트 SGV 제작>
- 학교 졸업 프로젝트용 제작
- 3인 팀 프로젝트
- 개발 기간 : 2020/04/21 ~ 2020/07/07



@ 역할
-------------------------------------
- 팀장으로서 '전반적인 프로젝트 계획 및 설계'
- 요구 사항 분석 단계에서의 '유사 시스템 조사 및 분석'
- 설계 단계에서의 '주요 구현 기능 파악 및 설계'
- 구현 단계에서의 '주요 기능 코딩(Java/JavaScript/JSP)과 데이터베이스 설계 및 운영(Oracle->MySQL->MariaDB)'
- 제출할 프로젝트 제안서, 관련 연구 발표, 중간 발표, 최종 발표, 그리고 최종 보고서에 필요한 문서들을 word 파일로 정리
- 나머지 팀원1, 팀원2가 CSS와 HTML, 그리고 JavaScript까지도 활용하여 Front-end 파트를 맡음 
- 시간이 조금 지연되더라도 정확성을 높이기 위해 팀원 전체가 작업 단계가 끝날 때마다 다같이 단위 테스트를 진행



@ 개발 환경 및 언어 
-------------------------------------
- 운영체제 : Windows 10 / 64bits
- 개발 IDE : Eclipse JAVA EE IDE 
- 프레임워크 : Spring Framework (Eclipse 내 STS 설치)
- 서버 : Apache Tomcat v8.5 Server
- 데이터베이스 : Oracle (sqldeveloper 사용)
- 개발 언어 : Java / JSP / JavaScript / HTML / CSS 
- SQL Mapping : MyBatis



@ 주요 기능
-------------------------------------
~ 회원 페이지와 관리자 페이지를 분리 ~
1. 회원 페이지
- 회원 가입, 로그인&로그아웃, 마이페이지(회원 정보 확인 및 수정)
- 영화 - 무비 차트, 영화 소개(줄거리, 등장인물, 배우 상세...), 리뷰 작성 및 평점 부여
- 예매/티켓 관련 - 영화 예매(영화관별&영화별&시간대별 -> 인원 선택 -> 좌석 선택 -> 결제), 예매 정보 확인 및 예매 취소)
- 영화관 - 영화관 정보 
- 스토어 - 스낵바(단순 리스트만, 구매까지는 구현X)
- 고객 센터 - 1대1 문의 및 공지사항
- 비회원도 예약 가능 - 간단한 정보 입력 후 예매, 이후 입력 정보 바탕으로 예매 내역 조회 및 취소 가능

2. 관리자 페이지 (회원 페이지로 이동 가능)
- 영화 관리 - 상영 영화 등록,수정 및 삭제
- 영화관 관리 - 영화관 등록, 영화관 내 상영관 생성, 영화관 별 관리자 생성(등록) ex) SGV 강남점, SGV 도곡점 -> 해당 지점 관리자만이 해당 지점 영화 등록 가능(기타 관리자 접근X)
- 상영관 관리 - 영화관 별, 상영관 별 영화 등록(시간대 별 편성)
- 회원 관리 - 회원 예매 내역 조회, 회원 검색 및 회원 삭제
- 상품 관리 - 스낵바에 상품 등록, 수정, 삭제
- 게시판 관리 - 공지사항 등록, 수정, 삭제 및 1대1 문의 답변



@ 스크린샷
-------------------------------------
- 회원 페이지 메인 
<img src="https://user-images.githubusercontent.com/63503102/90853882-5d793900-e3b6-11ea-88a3-b25e014a965c.png">
<img src="https://user-images.githubusercontent.com/63503102/90854145-2a837500-e3b7-11ea-9098-430a9d07cf98.png">


- 영화 소개 및 리뷰 작성
<img src="https://user-images.githubusercontent.com/63503102/90854319-9ebe1880-e3b7-11ea-9274-3863b5571dc9.png">


- 영화 예매 페이지
<img src="https://user-images.githubusercontent.com/63503102/90854378-c44b2200-e3b7-11ea-9b73-4088246df525.png">
<img src="https://user-images.githubusercontent.com/63503102/90854457-efce0c80-e3b7-11ea-9112-ceafb221462f.png">
<img src="https://user-images.githubusercontent.com/63503102/90854504-12f8bc00-e3b8-11ea-814f-2e2ae9fead19.png">


- 관리자 페이지 메인
<img src="https://user-images.githubusercontent.com/63503102/90854647-7f73bb00-e3b8-11ea-8506-923f5b71591f.png">


- 영화 등록 페이지
<img src="https://user-images.githubusercontent.com/63503102/90854760-ceb9eb80-e3b8-11ea-9f23-998cd8aa54fe.png">


- 영화관/ 영화 등록(시간대 별 편성)
<img src="https://user-images.githubusercontent.com/63503102/90854876-0fb20000-e3b9-11ea-8aa9-c7ff56e0df09.png">



@ 추가 에피소드
-------------------------------------
- 웹 페이지를 완성하고 웹 호스팅을 할 때 사설 업체를 통한 웹 호스팅을 계획했었다.
- 그러나 해당 업체(대부분의 업체)가 Oracle DB를 지원하지 않고 MariaDB만을 지원하여 결국 DB 관련 코드를 MySQL 문법에 맞게 수정했다.
- 바로 MariaDB로 바꾸지 않고 MySQL로 바꾼 이유 : MySQL만 사용해봤고 MySQL workbench가 설치되어 있었으며, MariaDB와 MySQL은 호환이 된다고 들었기 때문이었다.
- 그러나 최신 버전에 있어서 예약어 사용 등 약간의 차이를 보인다 하여 MariaDB 전용 툴인 HeidiSQL을 설치하여 데이터를 새로 넣고 소스 코드도 약간 수정하려 했으나 
소스 코드는 사실상 차이가 없었다.
- 여기 올린 코드는 Oracle 기반의 소스 코드이고 추후 MySQL/MariaDB 버전 소스 코드도 올릴 예정이다.
- 이러한 해프닝으로 Oracle DB 부터 MySQL, 그리고 MariaDB 까지 3가지 DB를 모두 경험하게 되었다. (전용 관리 툴인 sqldeveloper, mysqlworkbench, heidisql 까지)
- 또한 프로젝트를 시작하기 전 Spring Framework를 사용할 계획이 없었고 어느 정도 진행이 되서야 Spring을 활용하기로 결정한 것인데 이것이 신의 한수였다.
- Spring의 여러 자동화 기능 덕도 봤고 DB 관련 xml 파일에서만 수정하면 되었기 때문에 일일이 복잡한 파일을 뒤지지 않고도 생각보다 간편하게 DB를 옮겨갈 수 있었다.







