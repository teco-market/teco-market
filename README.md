## 테코마켓
⭐️ 우아한 테크코스 크루들을 위한 공구 및 중고 거래 장터입니다. ⭐️

## 주제 및 목적

→ 우아한 테크코스 내에서 중고거래 및 공구를 원할하게 할 수 있는 플랫폼을 제공

## 기술 스택

- Java 8
- Spring boot
- Spring Data Jpa | Jpa
- Spring security
- Gradle
- Intellij
- React
- [ 문서화 ]

## 기능

### 상품

- 멀티 이미지 업로드
- 게시물 업로드
- 리뷰 및 평가

### 회원가입 및 로그인

- 깃허브 및 구글 연동
- [ 자체 회원가입 및 로그인 서비스 ]
- 기능 사용에 권한 부여 (GUEST, ADMIN, USER)

### 회원

- 장바구니 & 찜한 상품
- 구매한 상품 내역
- 좋아요.
- 채팅방을 가질 수 있다.(1:1이든 단톡이든)

### 검색

- 상품 제목 검색
- 해쉬태그 관련
- 등록한 사람 이름으로 검색

### 정렬

- 인기순
- 관련 상품
- 시간순(최신, 오래된)
- 판매완료 및 판매중....

### 알림

- 키워드 알림 : 관심 상품에 대한 키워드 알림
    - 서비스 내에서 뜨는 알림
- 전체 알림 : 슬랙에
    - 어떠한 상품이 등록되었습니다.
    - 어떤 공구관련 글이 등록되었습니다.
    - 판매가 완료되었습니다. (공구든 판매든)
- Q & A 관련 봇이나, 채팅

### 채팅

- 단톡방
    - 공구관련된 단톡방 → [ 판매자가 권한을 부여하거나 ]
- 1 : 1 톡방
    - 1 : 1 구매 관련

### 관리자 페이지

- 정산이나 통계성 데이터
- 회원 권한 수정
- 상품 삭제

### 우선순위

1차 : 로그인 및 회원가입 → 상품 → 검색 & 정렬 → 채팅(1 : 1) → 알림 → 회원 → 관리자(가볍게)

2차 : → 공구 → 채팅(멀티)  → 회원 → 관리자(조금 더 깊게)

### 진행방식

- 페어 ← 같이 있는 시간
- 따로 공부나 더 깊게 구현할 수 있는 부분들

### TO-DO

- [x] 도메인 모델 설계

- [x] Spring rest docs 의존성 추가

- [x] 로그인 (0605-06)
    - [ ] Github : Oauth2 -> jwt -> jwt 기준 권한 관리
    - [x] Google : Oauth2 -> jwt -> jwt 기준 권한 관리
    - [x] Jwt Converter , Bearer Intercepter 구현
        - [x] Github, Google 공통 분모를 통해서 (id + organization) 을 통한 토큰 생성
        - [x] id + organization 을 통한 jwt 토큰 분해를 통한 Intercepter, MethodArgumentResolver 구현
    - [x] 유저 권한 부여 
        - [x] 관리자 : 전체 사이트에 대한 통계 정보를 확인할 수 있다. (관리자용 MethodArgumentResolver를 두면 될꺼같음.)
        - [x] 유저 : 대부분의 기능을 이용할 수 있음.
        - [x] 손님 : 가입 승인이 되기전의 사용자
        
- [x] LoginMember (토큰으로 로그인 한 사용자)
    - [x] read, update
    - [x] read, update 테스트 추가
    - [x] 권한 -> AllowRole 사용
    
- [x] 상품 (0607-0608)   
    - [x] 멀티 이미지 업로드
    - [x] 상품 crud
    - [x] 페이징
    
- [x] 댓글(0609-0610)
    - [x] 댓글 crud (PostId, Member를 기준으로)
    - [x] 사용자 Id를 기반으로 한 댓글 조회 기능
    - [x] Post 상세화면에서, 관련 댓글 함께 보여주기

- [x] 좋아요(0611-0613)
    - [x] 좋아요 crud (PostId, MemberId를 기준으로)
    - [x] 사용자 Id를 기반으로 좋아요 조회
    - [ ] 인기 게시물 출력하기
   
- [x] 패키지 구조 변경

- [ ] 테스트 추가
    - Acceptance - Layer - Unit
        - [ ] Member
            - [x] Acceptance
            - [ ] Unit 
        - [ ] Post
            - [x] Acceptance
            - [ ] Unit
        - [ ] Comment
            - [x] Acceptance
            - [ ] Unit
        - [ ] Like
            - [x] Acceptance
            - [ ] Unit
        - [ ] Layer test
    - [ ] S3 없이, 임의로 데이터 삽입 후 테스트
    - [ ] S3 연동이후 임의로 추가한 데이터 변경
    - [ ] 테스트 코드 리팩토링

- [ ] 검색 및 정렬
    - 정렬
        - [ ] 카테고리를 기준으로 조회할 수 있다. -> category 
        - [ ] 인기 상품을 조회할 수 있다. -> like
        - [ ] 최근 작성일을 기준으로 조회할 수 있다. -> post 
        - [ ] 특정 카테고리 및 최저 가격을 기준으로 조회할 수 있다. -> category & post 
 
    - 검색
        - [ ] 글 제목의 키워드를 기준으로 검색할 수 있다.
        - [ ] 작성자의 닉네임을 기준으로 검색할 수 있다.
         
- [ ] 패키지 구조 리팩토링 및 DTO 변환 부분 생각해보기.
    
- [ ] 1 : 1 채팅 
    - [ ] 객체 관계 설정
    - [ ] 자체 대화 서비스?
    - [ ] 슬랙 연동

- [ ] 알림
    - [ ] 객체 관계 설정
    - [ ] 사이트 내 알림
    - [ ] 메일 알림
    - [ ] 슬랙 알림 

- [ ] 관리자
    - [ ] 통계성 데이터 화면
    - [ ] 권한 관리(Guest, User, Admin)

### 리팩토링

-[ ] 상품 상세화면을 쿼리 한번으로 할 수 있을거 같은데..