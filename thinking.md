## post - comment
post 는 comment 를 모르고, comment만 자신이 속한 post를 알고 있음.

comment 저장
 - member 찾아오기 -> id / object
 - 해당 post 찾아오기 -> id
 - 다 등록되면, post 를 조회할 때도 필요한 경우 추가해주기.
 
 Post post = postRepository.findById(id) 
             .orElseThrow(NotFoundPostException::new);
         Long count = likeRepository.findCountByPostId(post.getId());
         
쿼리 : 1 + 1 + 1

한번에 조인하면 ? -> post, comment, like, member, member
post.title

post - member - like/ -> 1

comment-member -> 2

내가 쓴 댓글보기에서 나와야하는 항목

post title, comment content;

select * from category;
select * from comment;
select * from member;
select * from photo;
select * from post;
select * from post_like;
select * from thumbnail;

검색에 있어서 조건이 다양해 질 수 있다. 정렬도 마찬가지
하나의 검색 리소스에 다양한 파라미터의 형태 <- 파라미터로 다양한 검색 조건이 넘어온다. 이 부분을 담는 객체를 만들고
이 객체에 따라서, 각기 다른 처리를 통해 결과를 반환한다.   