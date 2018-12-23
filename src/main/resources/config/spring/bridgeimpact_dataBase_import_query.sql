-- phpMyAdmin SQL Dump
-- version 4.7.8
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- 생성 시간: 18-12-23 09:08
-- 서버 버전: 5.5.59-MariaDB
-- PHP 버전: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 데이터베이스: `bridgeimpact`
--
CREATE DATABASE IF NOT EXISTS `bridgeimpact` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bridgeimpact`;

-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_ARTICLE_COMMENT_TB`
--

DROP TABLE IF EXISTS `BIM_ARTICLE_COMMENT_TB`;
CREATE TABLE `BIM_ARTICLE_COMMENT_TB` (
  `IDX` int(11) NOT NULL COMMENT '댓글번호(PK)',
  `PARENT_IDX` int(11) NOT NULL COMMENT '부모글번호',
  `CONTENTS` text NOT NULL COMMENT '댓글내용',
  `DEL_GB` varchar(5) NOT NULL DEFAULT '''N''' COMMENT '삭제여부',
  `WRITE_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '댓글작성날짜',
  `WRITE_ID` varchar(11) NOT NULL COMMENT '댓글작성자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시글의 댓글을 담는 테이블';

--
-- 삽입 전에 테이블 비우기 `BIM_ARTICLE_COMMENT_TB`
--

TRUNCATE TABLE `BIM_ARTICLE_COMMENT_TB`;
--
-- 테이블의 덤프 데이터 `BIM_ARTICLE_COMMENT_TB`
--

INSERT DELAYED IGNORE INTO `BIM_ARTICLE_COMMENT_TB` (`IDX`, `PARENT_IDX`, `CONTENTS`, `DEL_GB`, `WRITE_DT`, `WRITE_ID`) VALUES
(1, 10, '댓글 작성 테스트', 'Y', '2017-09-07 04:32:07', 'admin'),
(4, 1, '댓글', 'N', '2017-09-07 04:33:25', 'admin'),
(5, 6, '6번 댓글 테스트', 'N', '2017-09-07 04:33:36', 'admin'),
(6, 6, '6번 댓글 테스트 2개 테스트', 'N', '2017-09-07 04:33:40', 'admin'),
(8, 10, '', 'Y', '2017-09-07 06:38:05', 'admin'),
(9, 10, '', 'Y', '2017-09-07 06:38:10', 'admin'),
(10, 10, '잠깐만?', 'Y', '2017-09-07 07:24:29', 'admin');

-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_ARTICLE_TB`
--

DROP TABLE IF EXISTS `BIM_ARTICLE_TB`;
CREATE TABLE `BIM_ARTICLE_TB` (
  `IDX` int(10) NOT NULL COMMENT '글번호',
  `BOARD_ID` varchar(20) NOT NULL COMMENT '작성된 게시판 구분자(ID)',
  `TITLE` varchar(100) NOT NULL COMMENT '글제목',
  `CONTENTS` varchar(4000) NOT NULL COMMENT '글내용',
  `HIT_CNT` int(11) NOT NULL DEFAULT '0' COMMENT '조회수',
  `DEL_GB` varchar(1) NOT NULL DEFAULT 'N' COMMENT '삭제구분',
  `WRITE_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
  `WRITE_ID` varchar(30) NOT NULL COMMENT '작성자ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='게시글을 담는 테이블';

--
-- 삽입 전에 테이블 비우기 `BIM_ARTICLE_TB`
--

TRUNCATE TABLE `BIM_ARTICLE_TB`;
--
-- 테이블의 덤프 데이터 `BIM_ARTICLE_TB`
--

INSERT DELAYED IGNORE INTO `BIM_ARTICLE_TB` (`IDX`, `BOARD_ID`, `TITLE`, `CONTENTS`, `HIT_CNT`, `DEL_GB`, `WRITE_DT`, `WRITE_ID`) VALUES
(1, 'freeboard', '안녕하세요. 자유게시판입니다.', '자유게시판 본문입니다.', 10, 'N', '2017-09-01 10:36:41', 'admin'),
(2, 'freeboard', '제목 복구', '', 23, 'N', '2017-09-01 13:20:57', 'admin'),
(3, 'notice', '2018 여름 청소년 빔캠프 찬양 콘티 (수정)', '<p>첫째날 저녁<br />나는 주님을 찬양 하리라<br />주님의 영광 나타나셨네<br />내 모든 삶의 행동<br />오 주님 내 맘에<br />다시 한 번&nbsp;<br />빛 되신 주&nbsp;<br />주님 약속하신 말씀 위에서<br />Glory to Jesus</p><p>&nbsp;</p><p>&nbsp;</p><p>둘째날 저녁</p><p>Glory to Jesus<br />주님 약속하신 말씀 위에서<br />주님의 영광 나타나셨네<br />오 주님 내 맘에<br />다시 한 번&nbsp;<br />빛 되신 주&nbsp;<br />낮은자의 하나님<br />춤추는 세대</p>', 69, 'N', '2017-09-17 03:06:57', 'admin'),
(5, 'question', '사역문의 드립니다!!', '사역이 뭐에요?', 30, 'N', '2017-09-18 15:33:34', 'admin'),
(6, 'counsel', '상담문의 드립니다!', '개발이 너무 재밌으면 어떡하죠?', 3, 'N', '2017-09-18 16:10:27', 'admin');

-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_BOARD_TB`
--

DROP TABLE IF EXISTS `BIM_BOARD_TB`;
CREATE TABLE `BIM_BOARD_TB` (
  `IDX` int(15) NOT NULL COMMENT '구분번호',
  `ID` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '게시판URL',
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '게시판 이름',
  `DEL_GB` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'N' COMMENT '삭제 구분'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='게시판의 정보를 담는 테이블';

--
-- 삽입 전에 테이블 비우기 `BIM_BOARD_TB`
--

TRUNCATE TABLE `BIM_BOARD_TB`;
--
-- 테이블의 덤프 데이터 `BIM_BOARD_TB`
--

INSERT DELAYED IGNORE INTO `BIM_BOARD_TB` (`IDX`, `ID`, `NAME`, `DEL_GB`) VALUES
(1, 'notice', '공지사항', 'N'),
(2, 'freeboard', '자유 게시판', 'N'),
(3, 'question', '사역 문의', 'N'),
(4, 'counsel', '상담 문의', 'N');

-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_EMAIL_AUTH`
--

DROP TABLE IF EXISTS `BIM_EMAIL_AUTH`;
CREATE TABLE `BIM_EMAIL_AUTH` (
  `IDX` int(15) NOT NULL COMMENT '인덱스',
  `USER_IDX` int(15) NOT NULL COMMENT '사용자의 인덱스',
  `USER_ID` varchar(20) NOT NULL COMMENT '사용자 아이디',
  `EMAIL_AUTH_KEY` varchar(100) NOT NULL COMMENT '이메일 인증키',
  `AUTH_FL` varchar(3) NOT NULL DEFAULT 'N' COMMENT '이메일 인증여부'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자의 인증을 담당하는 테이블';


-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_FILE_TB`
--

DROP TABLE IF EXISTS `BIM_FILE_TB`;
CREATE TABLE `BIM_FILE_TB` (
  `ARTICLE_IDX` int(11) NOT NULL COMMENT '게시글 인덱스',
  `ORIGINAL_FILE_NAME` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '원본 파일명',
  `STORED_FILE_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '변경된 파일명',
  `FILE_SIZE` int(11) NOT NULL COMMENT '파일 크기',
  `CREA_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '파일 생성일',
  `CREA_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '파일 작성자',
  `DEL_GB` varchar(3) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '''N''' COMMENT '삭제 여부'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='파일 정보 테이블';

-- --------------------------------------------------------

--
-- 테이블 구조 `BIM_MEMBER_TB`
--

DROP TABLE IF EXISTS `BIM_MEMBER_TB`;
CREATE TABLE `BIM_MEMBER_TB` (
  `IDX` int(10) NOT NULL COMMENT '인덱스(회원번호)',
  `ID` varchar(15) NOT NULL COMMENT '회원 아이디',
  `PASSWORD` varchar(80) NOT NULL COMMENT '회원 비밀번호',
  `NAME` varchar(10) NOT NULL COMMENT '회원 이름',
  `EMAIL` varchar(40) NOT NULL COMMENT '회원 이메일',
  `ADDRESS` varchar(80) NOT NULL COMMENT '회원 주소',
  `PHONE` varchar(15) NOT NULL COMMENT '회원 연락처',
  `CHURCH` varchar(20) NOT NULL COMMENT '회원 본교',
  `TYPE` varchar(4) NOT NULL DEFAULT '2' COMMENT '회원 상태 0: 탈퇴, 1: 활성, 2: 이메일 미인증(최초가입), 9: 관리자 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='회원정보를 담는 테이블';

--
-- 삽입 전에 테이블 비우기 `BIM_MEMBER_TB`
--

TRUNCATE TABLE `BIM_MEMBER_TB`;
--
-- 테이블의 덤프 데이터 `BIM_MEMBER_TB`
--

INSERT DELAYED IGNORE INTO `BIM_MEMBER_TB` (`IDX`, `ID`, `PASSWORD`, `NAME`, `EMAIL`, `ADDRESS`, `PHONE`, `CHURCH`, `TYPE`) VALUES
(1, 'admin', '$2a$10$MZO7T1VF/j9Gyv3l9YubG.vMA4ogu2QAphkcwdWl04EM.r5DO8.1S', '관리자', 'admin@gmail.com', '10', '10', '10', '9');

--
-- 덤프된 테이블의 인덱스
--

--
-- 테이블의 인덱스 `BIM_ARTICLE_COMMENT_TB`
--
ALTER TABLE `BIM_ARTICLE_COMMENT_TB`
  ADD PRIMARY KEY (`IDX`);

--
-- 테이블의 인덱스 `BIM_ARTICLE_TB`
--
ALTER TABLE `BIM_ARTICLE_TB`
  ADD PRIMARY KEY (`IDX`);

--
-- 테이블의 인덱스 `BIM_BOARD_TB`
--
ALTER TABLE `BIM_BOARD_TB`
  ADD PRIMARY KEY (`IDX`);

--
-- 테이블의 인덱스 `BIM_EMAIL_AUTH`
--
ALTER TABLE `BIM_EMAIL_AUTH`
  ADD PRIMARY KEY (`IDX`);

--
-- 테이블의 인덱스 `BIM_MEMBER_TB`
--
ALTER TABLE `BIM_MEMBER_TB`
  ADD PRIMARY KEY (`IDX`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- 덤프된 테이블의 AUTO_INCREMENT
--

--
-- 테이블의 AUTO_INCREMENT `BIM_ARTICLE_COMMENT_TB`
--
ALTER TABLE `BIM_ARTICLE_COMMENT_TB`
  MODIFY `IDX` int(11) NOT NULL AUTO_INCREMENT COMMENT '댓글번호(PK)', AUTO_INCREMENT=198;

--
-- 테이블의 AUTO_INCREMENT `BIM_ARTICLE_TB`
--
ALTER TABLE `BIM_ARTICLE_TB`
  MODIFY `IDX` int(10) NOT NULL AUTO_INCREMENT COMMENT '글번호', AUTO_INCREMENT=262;

--
-- 테이블의 AUTO_INCREMENT `BIM_BOARD_TB`
--
ALTER TABLE `BIM_BOARD_TB`
  MODIFY `IDX` int(15) NOT NULL AUTO_INCREMENT COMMENT '구분번호', AUTO_INCREMENT=26;

--
-- 테이블의 AUTO_INCREMENT `BIM_EMAIL_AUTH`
--
ALTER TABLE `BIM_EMAIL_AUTH`
  MODIFY `IDX` int(15) NOT NULL AUTO_INCREMENT COMMENT '인덱스', AUTO_INCREMENT=25;

--
-- 테이블의 AUTO_INCREMENT `BIM_MEMBER_TB`
--
ALTER TABLE `BIM_MEMBER_TB`
  MODIFY `IDX` int(10) NOT NULL AUTO_INCREMENT COMMENT '인덱스(회원번호)', AUTO_INCREMENT=68;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
