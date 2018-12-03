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

### Library & Open Source

- [Bootstrap 3](https://getbootstrap.com/)
- [Ckeditor 4](https://ckeditor.com/)
- [DownloadView BoilerPlate Code](http://jwgye.tistory.com/4)
- [~~Startbootstrap's simple-sidebar~~](https://startbootstrap.com/template-overviews/simple-sidebar/)

## [How to use this Project](https://github.com/liante0904/bimProject/blob/master/HOWTOUSE.md)
## [Reference Documents](https://github.com/liante0904/bimProject/blob/master/Reference.md)

## About
### Complete Function
- 모바일 대응(반응형 웹)
- 회원 주요기능(CRUD)
- 게시판 게시글, 댓글 주요기능(CRUD) 
- 검색 및 페이징
- 게시글 작성시 파일 기능(첨부 및 삭제)

- 회원 탈퇴시 기존 패스워드 일치 여부 확인 추가(컨트롤러) 
### ChangeLog
- [x] 레이아웃 변경(Table 태그 제거)
- [x] Front-end 데이터 제한 및 유효성 처리(회원, 게시글, 댓글 동작시)
- [x] 회원가입시 이메일 인증 시스템(미 인증 로그인시도시 제한)
- [x] 글 작성 및 수정요청 로직 변경(ajax to submit, 데이터 제한)
- [x] 게시글 작성시 위지윅 적용(WYSIWYG)
- [x] 글 목록에서 댓글 갯수 표시 기능추가
- [x] 메인 페이지 레이아웃 추가(게시판 최근 글)
- [x] 댓글 기능 구현 및 개선(비동기 및 동적)
- [x] 검색, 페이징 기능 구현
- [x] 게시판, 게시글 제한처리(비공개, 삭제 처리된 게시굴 및 게시판)
- [x] 파일 IO (DB반영)
- [x] DB암호화 (회원가입 & 수정, 로그인 완료, 클래스 생성 필요)
- [x] Bootstrap 적용(PC, 모바일 반응형 웹 적용)
- [x] 이메일 인증메일 수신하여 인증 승인처리 구체화
### Testing & Working
- [x] 관리자 인터셉터 구현(구체화)
- [x] 컨트롤러, 서비스(비즈니스)로직 분리 & 정리
- SSL 적용 테스트 중
    - [자체 서명 가이드1](http://gdtbgl93.tistory.com/74)
    - [자체 서명 가이드2](https://dzone.com/articles/setting-ssl-tomcat-5-minutes)
    - TODO Let's Encrypt 인증서 적용

## TODOList
### Layout and content 
- 컨텐츠 채우기
    - 현재 위지윅 작성시 본문에 사진 및 태그 기능 미작동 수정예정
	- 게시판 변경(기존의 사이트)
- 디자인 색깔톤 대폭 변경
### File
- 게시글 수정시 첨부파일 추가 & 수정 & 삭제 구현(삭제 구현완료, 추가 및 수정 구현중)
   - [x] writeArticle Mapper에서 insertArticle재구현
        - 현행: Article과 File 객체를 각각 insert 처리
        - 수정안: Service Method명을 writeArticle로 변경하고 Service에서 각각의 DAO를 호출하는 방향
        - 혹은 insert를 1번의 db Access로 처리 
	
- 글 작성, 수정시 첨부파일 저장 위치 로직변경(Test or Live)
    - Docker 배포시 첨부파일 영역 구분 확인 필요
- 데이터를 반영시 무결성 처리(부분부분 진행중)
    - 필요한 controller, URL mapper 확인 후 추가 예정
### Function & minor bug
- 아이디 비밀번호 찾기 구현
    - 아이디 찾기 => 이름, 이메일 일치 여부 확인 후 모자이크 아이디 반환
    - 비밀번호 찾기 => 가입시 이메일 정보로 비밀번호 변경 페이지 반환 
- 사용자에게 에러 페이지 및 알림처리
    - 인터셉터단에서 일반 회원 로그인시 modal 알림 추가 고려
- [x] 회원 이메일 주소 유니크 처리, 가입시 submit전 데이터 유효성 체크
- 회원가입 후 이메일 인증처리 메일 발송 실패시 에러처리(재발송 혹은 알림) 
    - 재발송 자체는 어렵지 않으나.. 일정 시간이 지났을 때 인증 만료(트랜잭션) 추가 고려
### etc
- 개발 환경 openJDK 전환
- Todo get start jenkins and AWS

## TODO Bugfix(긴급)
### 구조 변경(DB, 혹은 IDE 변경)에 따른 동작 버그 확인 및 재구현
- 게시글
	- 게시글 조회
		- 작성되지 않은 게시글 번호로 접근시 Exception처리
	- 게시글 작성
		- 위지윅 기능 추가(이미지 추가 및 태그기능)
		- 업로드 된 파일 구조화(현재 live배포시 첨부 파일 삭제되는 구조 문제)
	- 게시글 수정
		- [x] 수정 페이지 진입시 Validation 로직 변경(수정할 게시글 작성자 <-> 로그인 사용자 비교)
		- [x] 수정 페이지 진입시 기존에 작성된 글 제목과 내용 표시
- 파일
	-  [x] File테이블 index 컬럼삭제로 인한 로직변경

- 회원정보
	- 회원정보 수정 페이지 대폭수정
	- 회원가입 페이지 이용

## holdingList
- ~공동구매 기능(온라인 신청)~
- ~접속자의 아이피, 접속기록, 저장 및 로그인 횟수 차단~
- ~스프링 트랜잭션 적용하기(for 이메일 인증, 소셜회원가입) + 회원가입 로직 개선 및 기능 추가~
- ~테스트 중인 사이드바 적용~
- (보류) 글 수정페이지 접근 후, 세션 만료시 NullPoint처리 
    - [톰캣 재시작시 세션유지](http://intro0517.tistory.com/147)
        - 실제 적용 해보았으나 세션 유지가 되지 않았음 
