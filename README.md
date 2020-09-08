## 테코마켓
> 우아한 테크코스 크루들을 위한 공구 및 중고 거래 장터입니다. 
> 개인적인 사정으로 인해 서버만 개발하고 있으며 출시 하진 않을 것 같습니다! '이런 기술을 사용해서 이런 프로젝트를 해봤구나' 정도로만 생각해주시면 좋을 것 같습니다.👨‍💻

## 주제 및 목적

> 우아한 테크코스 내에서 중고거래 및 공구를 원할하게 할 수 있는 플랫폼을 제공

## 기술 스택
> 해당 프로젝트를 수행하며 사용한 기술 스택입니다.

- Java 8
- Spring boot
- Spring Data Jpa | Jpa
- Gradle
- Spring Rest Docs 

## 고민 포인트
> 프로젝트 개발 과정에서 있었던 고민들이며 이에 대한 저의 생각들은 고민별로 링크로 연결되어 있습니다.

- 도메인 설계(객체와 테이블)
- [개발 프로세스](https://velog.io/@kyle/%EC%84%9C%EB%B2%84-API-%EB%AC%B8%EC%84%9C%ED%99%94Spring-Rest-docs)
- [문서화(Swagger | Spring Rest Docs)](https://velog.io/@kyle/Acceptance-Test-%EA%B0%9C%EB%B0%9C-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4)
- 이미지 업로드
- 인증 및 인가 
    - 구글 로그인
    - 사용자의 역할에 따라 다른 권한 부여
    - 외부 API를 어떻게 관리할 것인가?
    - 외부 API를 어떻게 테스트 할 것인가?
- 조회성 쿼리 작업 
- 슬랙 알림 기능(외부 API와 함께 정리)
- 동적 쿼리문 작성 방법 

## 세세한 기능 목록
> 세부적인 기능 목록을 나열한 것으로, 아직 완성되지 않은 기능도 포함되어 있습니다. 개발 진행 과정을 보시려면, TO-DO 부분을 확인해 주세요!
##### 상품
- 멀티 이미지 업로드
- 게시물 업로드
- 리뷰 및 평가
##### 회원가입 및 로그인
- Oauth2 구글 로그인
- 기능 사용에 권한 부여 (GUEST, ADMIN, USER)
##### 회원
- 장바구니 & 찜한 상품
- 구매한 상품 내역
- 좋아요.
- 채팅방을 가질 수 있다.(1:1이든 단톡이든)
##### 검색
- 상품 제목 검색
- 해쉬태그 관련
- 등록한 사람 이름으로 검색
##### 정렬
- 인기순
- 관련 상품
- 시간순(최신, 오래된)
- 판매완료 및 판매중....
##### 알림
- 키워드 알림 : 관심 상품에 대한 키워드 알림
    - 서비스 내에서 뜨는 알림
- 전체 알림 : 슬랙에
    - 어떠한 상품이 등록되었습니다.
    - 어떤 공구관련 글이 등록되었습니다.
    - 판매가 완료되었습니다. (공구든 판매든)
- Q & A 관련 봇이나, 채팅
##### 채팅
- 단톡방
    - 공구관련된 단톡방 → [ 판매자가 권한을 부여하거나 ]
- 1 : 1 톡방
    - 1 : 1 구매 관련
##### 관리자 페이지
- 정산이나 통계성 데이터
- 회원 권한 수정
- 상품 삭제

### TO-DO
> 개발 완료한 기능 목록들을 나열한 것으로 대부분 순서대로 진행하였습니다. 
- [x] 도메인 모델 설계

- [x] Spring rest docs 의존성 추가

- [x] 로그인 (0605-06)
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
    - [x] 인기 게시물 출력하기
   
- [x] 패키지 구조 변경

- [ ] 테스트 추가
    - [ ] Acceptance Tests
    - [ ] Layer & Domain Tests 
    - [x] S3 없이, 임의로 데이터 삽입 후 테스트
    - [x] S3 연동이후 임의로 추가한 데이터 변경
    - [ ] 테스트 코드 리팩토링

- [x] 검색 및 정렬
    - 정렬
        - [x] 카테고리를 기준으로 조회할 수 있다. -> category 
        - [x] 인기 상품을 조회할 수 있다. -> like
        - [x] 최근 작성일을 기준으로 조회할 수 있다. -> post 
        - [x] 특정 카테고리 및 최저 가격을 기준으로 조회할 수 있다. -> category & post 
 
    - 검색
        - [x] 글 제목의 키워드를 기준으로 검색할 수 있다.
        - [x] 작성자의 닉네임을 기준으로 검색할 수 있다.
         
- [x] 1 : 1 채팅 
    - [x] 객체 관계 설정
    - [x] 슬랙 연동

- [x] 알림
    - [ ] 메일 알림
    - [x] 슬랙 알림 

- [ ] 관리자
    - [ ] 통계성 데이터 화면
    - [ ] 권한 관리(Guest, User, Admin)
