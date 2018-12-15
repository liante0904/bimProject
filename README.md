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
- Java 8 & Spring Framework (MVC)
- Linux (Synology)
- MySQL (MariaDB)
- Tomcat 8
- Docker
- ~~AWS (Expected)~~

### Library & Open Source

- [Bootstrap 3](https://getbootstrap.com/)
- [Ckeditor 4](https://ckeditor.com/)
- [DownloadView BoilerPlate Code](http://jwgye.tistory.com/4)


## [How to use this Project](https://github.com/liante0904/bimProject/blob/master/HOWTOUSE.md)
## [Reference Documents](https://github.com/liante0904/bimProject/blob/master/Reference.md)

## About
### Complete Function
- [x] 모바일 대응(Bootstrap 반응형 웹, Table 태그 제거)
- [x] 회원 주요기능(CRUD)
    - [x] 이메일 인증과 인증에 기반한 아이디, 비밀번호 찾기 지원
- [x] 게시판 게시글, 댓글 주요기능(CRUD) 
- [x] 검색 및 페이징 구현
- [x] 게시글 작성 및 수정시 첨부 파일 기능지원
- [x] Front-end 데이터 제한 및 유효성 처리(회원, 게시글, 댓글 동작시)
- [x] 회원가입시 이메일 인증 시스템(미 인증 로그인시도시 제한)
- [x] 게시글 작성시 위지윅 적용(WYSIWYG)
- [x] 글 목록에서 댓글 갯수 표시 기능추가
- [x] 메인 페이지 레이아웃 추가(게시판 최근 글)
- [x] 댓글 기능 구현 및 개선(비동기 및 동적)
- [x] 게시판, 게시글 제한처리(비공개, 삭제 처리된 게시굴 및 게시판)
- [x] 첨부 파일 지원 (DB를 이용한 기록)
- [x] 개인 정보 DB암호화(패스워드)
- [x] 지속적 리팩토링
    - [x] 컨트롤러, 서비스(비즈니스)로직 분리 & 정리
- [x]디자인 색깔톤 대폭 변경

## TODOList
### Testing
- SSL 적용 테스트 중
    - [자체 서명 가이드1](http://gdtbgl93.tistory.com/74)
    - [자체 서명 가이드2](https://dzone.com/articles/setting-ssl-tomcat-5-minutes)
    - [TODO Let's Encrypt 인증서 적용](http://www.kwangsiklee.com/2016/12/%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8%EB%A1%9C-letsencrypt%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EB%AC%B4%EB%A3%8C-ssl-%EC%98%AC%EB%A6%AC%EA%B8%B0/)
- 게시글 수정
    - 수정 페이지 진입시 Validation 로직 변경(수정할 게시글 작성자 <-> 로그인 사용자 비교)
    - 수정 페이지 진입시 기존에 작성된 글 제목과 내용 표시
- 특정 상황에서 세션이 만료 되었을때 NullPointException 처리

### Layout and content 
- 컨텐츠 채우기
    - 현재 위지윅 작성시 본문에 사진 및 태그 기능 미작동 수정예정

### File
- 글 작성, 수정시 첨부파일 저장 위치 로직변경(Test or Live)
    - Docker 배포시 첨부파일 영역 구분 확인 필요
- Intercepter, Controller dataValidation 처리 
### Function & minor bug
- 회원 탈퇴시 기존 패스워드 일치 여부 확인 추가(컨트롤러) 
- 이메일 발신내역 관리 및 재발신 기능 추가 
    - 인증 만료시간 추가 고려
- 사용자에게 에러 페이지 및 알림처리
    - 인터셉터단에서 일반 회원 로그인시 modal 알림 추가 고려
- 게시글 작성
    - 위지윅 기능 추가(이미지 추가 및 태그기능)
### etc
- 개발 환경 openJDK 전환
- Todo get start jenkins and AWS

## holdingList
- ~공동구매 기능(온라인 신청)~
- ~접속자의 아이피, 접속기록, 저장 및 로그인 횟수 차단~
- ~스프링 트랜잭션 적용하기(for 이메일 인증, 소셜회원가입) + 회원가입 로직 개선 및 기능 추가~
- ~테스트 중인 사이드바 적용~
- (보류) 글 수정페이지 접근 후, 세션 만료시 NullPoint처리 
    - [톰캣 재시작시 세션유지](http://intro0517.tistory.com/147)
        - 실제 적용 해보았으나 세션 유지가 되지 않았음 
