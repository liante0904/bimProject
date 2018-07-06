# bimProject

> http://www.bridgeimpact.com/ 의 리뉴얼 프로젝트입니다.  

## Test Bench
http://liante0904.asuscomm.com:9090/bimProject/

## Stack


### Front-end
- JSP
- jQuery 1.11.2
- CSS
- Bootstrap 3

### Server
- Java 8 & Spring Framework 3
- Linux (Synology)
- MySQL (MariaDB)
- Tomcat 8
- Docker
- ~~AWS (Expected)~~

## About

### ChangeLog
- 회원 탈퇴시 기존 패스워드 일치 여부 확인 추가(컨트롤러) 
- 레이아웃 변경(Table 태그 제거)
- Front-end 데이터 제한 및 유효성 처리(회원, 게시글, 댓글 동작시)
- 회원가입시 이메일 인증 시스템(로그인시 제한처리)
- 글 작성 및 수정요청 로직 변경(ajax to submit, 데이터 제한)
- 게시글 검색 기능추가(본문, 제목+본문, 글쓰기 검색)
- 게시글 작성시 위지윅 적용(WYSIWYG)
- 글 목록에서 댓글 갯수 표시 기능추가
- 메인 페이지 레이아웃 추가(게시판 최근 글)
- 댓글 기능개선(비동기처리, 동적구현, 데이터 제한)
- 기본 게시판 및 게시글 검색 시 페이징 처리 완료
- 에러처리 로직(비공개 게시판, 삭제된 게시글 반환처리)
- 파일 IO (DB반영)
- DB암호화 (회원가입 & 수정, 로그인 완료, 클래스 생성 필요)
- Bootstrap 적용(PC, 모바일 반응형 웹 적용)

### Testing & Working
- 회원 가입후 이메일 인증 승인 로직구현
- 관리자 인터셉터 구현(구체화), 게시판 공개여부 토글처리(메소드 통합)
- 컨트롤러, 서비스(비즈니스)로직 분리 & 정리


## TODOList
- SSL적용
- 회원가입시 이메일 중복확인, 아이디 비밀번호 찾기 구현
- 접속자의 아이피, 접속기록 저장 로직
- 글 작성, 수정시 첨부파일 로직변경
- 회원 정보 수정 페이지 재 작성
- 스프링 트랜잭션 적용하기(for 이메일 인증, 소셜회원가입) + 회원가입 로직 개선 및 기능 추가
- 온라인 신청페이지
- 에러알림처리 (사용자에게 에러문구 처리)

## TODO Bugfix
- 글 수정페이지 접근 후, 세션 만료시 NullPoint처리 
- 회원가입 후 이메일 인증처리 메일 발송 실패시 에러처리(재발송 혹은 알림) 