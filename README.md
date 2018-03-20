# bimProject

> http://www.bridgeimpact.com/ 의 리뉴얼을 목적으로 시작한 프로젝트입니다.  ~~무기한 보류로 연습장이 되어버렸..~~


## Stack


### Front-end
- JSP
- jQuery 1.11.2
- CSS
- Bootstrap 3 (Testing)

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
- 에러처리 로직(비공개 게시판, 삭제된 게시글)

### Testing
- 파일 IO (다중 파일 업로드 처리 구현완료, DB반영 구현중)
- 관리자 인터셉터 구현(구체화), 게시판 공개여부 토글처리(메소드 통합)
- DB암호화 (회원가입, 로그인 완료, 클래스 생성 필요) 

## TODOList
- 온라인 신청페이지
- 로그인 로직개선(소셜 로그인, 이메일 인증 추가)
- 컨트롤러, 서비스(비즈니스)로직 분리 & 정리
- 에러알림처리 (사용자에게 에러문구 처리)
- 예외처리 (비공개 게시판 처리완료 , 삭제된 게시글 )
- Front-end 데이터 제한처리(전체)
