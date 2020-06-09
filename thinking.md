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