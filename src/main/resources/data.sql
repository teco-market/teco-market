insert into Category(id, name, created_at, updated_at) values (1, '전자기기', null, null);
insert into Category(id, name, created_at, updated_at) values (2, '자전거', null, null);
insert into Category(id, name, created_at, updated_at) values (3, '사무용품', null, null);
insert into Category(id, name, created_at, updated_at) values (4, '의류', null, null);

insert into generation(id,created_at,updated_at,alias) values (1,null,null,'utecruise');
insert into generation(id,created_at,updated_at,alias) values (2,null,null,'반란군들');

insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (1,null,null,'1','GOOGLE','pin','pin@pin','GUEST','jungjum',1);
insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (2,null,null,'2','GOOGLE','kim','si@young','USER','jungjum',1);
insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (3,null,null,'3','GOOGLE','kimsiyoung','si@young','USER','카일',2);
insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (4,null,null,'4','GOOGLE','kimhodol','ho@dol','USER','호돌',2);

insert into post(id, created_at, updated_at, content, price, title, category_id, member_id)
values (1, '2020-01-01',null, 'first post', 20000, 'It is first post', 1, 2);
insert into post(id, created_at, updated_at, content, price, title, category_id, member_id)
values (2, '2020-05-05',null, 'second post', 10000, 'It is second post', 2, 2);
insert into post(id, created_at, updated_at, content, price, title, category_id, member_id)
values (3, '2020-05-05',null, '호돌이의 애플워치', 150000, '호돌 : 애플워치 팝니다', 1, 4);


insert into photo(post_id, url) values (1, 'post1-image1-url');
insert into photo(post_id, url) values (1, 'post2-image2-url');
insert into photo(post_id, url) values (1, 'post2-image3-url');
insert into photo(post_id, url) values (2, 'post2-image1-url');
insert into photo(post_id, url) values (2, 'post2-image2-url');
insert into photo(post_id, url) values (2, 'post2-image3-url');
insert into photo(post_id, url) values (3, 'https://market-photos.s3.ap-northeast-2.amazonaws.com/git_commit1.pnghttps://market-photos.s3.ap-northeast-2.amazonaws.com/git_commit1.png');

insert into thumbnail(post_id, url) values (1, 'post1-thumbnail');
insert into thumbnail(post_id, url) values (2, 'post1-thumbnail');
insert into thumbnail(post_id, url) values (3, 'https://market-photos.s3.ap-northeast-2.amazonaws.com/thumbnail-git_commit1.png.jpeg');

