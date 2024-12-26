# CRUD 게시판 프로젝트

## 소개

이 프로젝트는 **Spring Boot**를 사용하여 개발된 간단한 CRUD 게시판 애플리케이션입니다.  
사용자는 게시글을 작성(Create), 조회(Read), 수정(Update), 삭제(Delete)할 수 있습니다.

## 주요 기능

- 게시글 목록 조회
- 게시글 상세 조회
- 게시글 작성
- 게시글 수정
- 게시글 삭제

## 기술 스택

- **Back-end**
  - Java 17
  - Spring Boot
- **Front-end**
  - Bootstrap (CSS 프레임워크)
- **빌드 도구**
  - Gradle 또는 Maven

## 실행 방법

1. **소스 코드 클론**

   ```bash
   git clone https://github.com/your-repository/crud-board.git
   cd crud-board
   ```

2. **빌드 및 실행**

- BoardSystemApplication.java 파일을 실행합니다.

3. **웹 브라우저에서 실행**

- 기본 포트: [http://localhost:8080](http://localhost:8080)

## 데이터베이스 설정

- `application.yml` 또는 `application.properties` 파일에서 설정을 확인 및 수정할 수 있습니다.

## 엔드포인트

| HTTP Method | URL                    | 설명             |
| ----------- | ---------------------- | ---------------- |
| GET         | /board/list/           | 게시글 목록 조회 |
| GET         | /board/detail?bno={id} | 게시글 상세 조회 |
| POST        | /board/insert          | 게시글 작성      |
| PUT         | /board/update/{id}     | 게시글 수정      |
| DELETE      | /board/delete/{id}     | 게시글 삭제      |

## 기여

버그 신고 및 기능 제안은 언제든 환영합니다. Pull Request를 통해 기여해주세요!
