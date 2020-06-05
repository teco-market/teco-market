## 테코마켓
- 우아한 테크코스 크루들을 위한 공구 및 중고 거래 장터입니다.

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

- [ ] 로그인 (0605-06)
    - [ ] Github : Oauth2 -> jwt -> jwt 기준 권한 관리
    - [x] Google : Oauth2 -> jwt -> jwt 기준 권한 관리
    - [x] Jwt Converter , Bearer Intercepter 구현
        - [x] Github, Google 공통 분모를 통해서 (id + organization) 을 통한 토큰 생성
        - [x] id + organization 을 통한 jwt 토큰 분해를 통한 Intercepter, MethodArgumentResolver 구현
    - [ ] 유저 권한 부여 (추후에)
        - [ ] 관리자 : 전체 사이트에 대한 통계 정보를 확인할 수 있다. (관리자용 MethodArgumentResolver를 두면 될꺼같음.)
        - [ ] 유저 : 대부분의 기능을 이용할 수 있음.
- [ ] LoginMember (토큰으로 로그인 한 사용자)
    - [x] read, update
    - [ ] read, update 테스트 추가
    - [ ] 권한 
    
- [ ] Member

- [ ] 상품 (0607-0608)
    - [ ] 멀티 이미지 업로드
    - [ ] 상품 crud
    - [ ] 페이징
    
- [ ] 댓글(0609-0610)
    - [ ] 댓글 crud (PostId, MemberId를 기준으로)
    - [ ] 사용자 Id를 기반으로 한 댓글 조회 기능

- [ ] 좋아요(0611-0613)
    - [ ] 좋아요 crud (PostId, MemberId를 기준으로)
    - [ ] 사용자 Id를 기반으로 좋아요 조회

     