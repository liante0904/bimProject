# bimProject

> http://www.bridgeimpact.com/ 의 리뉴얼을 목적으로 시작한 프로젝트입니다.  ~~무기한 보류로 연습장이 되어버렸..~~


## Stack


### Front-end
- JSP
- jQuery 1.11.2
- CSS
- Bootstrap 3 (Applying)

### Server
- Java 8 & Spring Framework
- Linux (Synology)
- MySQL (MariaDB)
- Tomcat 8
- Docker
- ~~AWS (Expected)~~

## About

### ChangeLog
- 댓글 개선(비동기처리, 동적구현)
- 게시글 검색 & 페이지 처리(검색된 게시글 페이징 처리)
- 페이징 클래스 객체화(로직 개선,코드 정리중)
- 에러처리 로직(비공개 게시판, 삭제된 게시글 반환처리)
- 파일 IO (DB반영)
- DB암호화 (회원가입 & 수정, 로그인 완료, 클래스 생성 필요) 
- Bootstrap 적용(메인 헤더, 게시글 조회 한정)

### Testing
- 위지윅 적용(WYSIWYG) 
- Front-end 데이터 제한처리(회원가입 페이지 처리 완료, 게시판 영역 적용중)
- 관리자 인터셉터 구현(구체화), 게시판 공개여부 토글처리(메소드 통합)
- 컨트롤러, 서비스(비즈니스)로직 분리 & 정리
 

## TODOList
- table 태그 비율 or 레이아웃 변경
- SSL적용
- 온라인 신청페이지
- 메인 페이지 레이아웃 추가 (게시판 최근글)
- 로그인 로직개선(소셜 로그인, 이메일 인증 추가)
- 에러알림처리 (사용자에게 에러문구 처리)
- Back-end SQL Injection AOP처리
- 대대적인 리펙토링 ~~사실상 끝이 안날수도...~~