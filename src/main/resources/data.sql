insert into Category(id, name, created_at, updated_at) values (1, '전자기기', null, null);
insert into Category(id, name, created_at, updated_at) values (2, '자전거', null, null);
insert into Category(id, name, created_at, updated_at) values (3, '사무용품', null, null);
insert into Category(id, name, created_at, updated_at) values (4, '의류', null, null);


insert into generation(id,created_at,updated_at,alias) values (1,null,null,'utecruise');
insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (1,null,null,'1','GOOGLE','pin','pin@pin','GUEST','jungjum',1);
insert into member(id, created_at, updated_at, platform_id, platform_type, name, email, role, nickname, generation_id)
values (2,null,null,'2','GOOGLE','kim','si@young','USER','jungjum',1);