-- schema
show tables;

desc user;
desc blog;
desc category;
desc post;

select * from user;
select * from blog;
select * from category;
select * from post;
select * from category where blog_id ='ddochi';
delete from blog where blog_id = 'test';
select * from post order by no desc;

delete from user where id ='city';

insert into user values('dooly','둘리','1234');
select name from user where id='dooly' and password='1234';
select name from user where id = 'dooly';

select blog_id, title, logo from blog where blog_id = 'city';
insert into blog values('test','Hello Spring','/assets/gallery/default.jpg');
update blog set title='CCC', logo ='11' where blog_id ='city';

insert into category values(null,'Java','JavaStudy','city');

select * from category a, post b where a.no = b.category_no and a.blog_id='ddochi' group by category_no;  
select no from category where blog_id ='city';

select * from post a, (select no from user a, category b where a.id = b.blog_id and a.id = 'city' order by no desc)as b where category_no = b.no group by b.no order by a.no desc;
select * from user a, category b where a.id = b.blog_id and a.id = 'city';
select * from post where category_no = 6 order by no = 3 desc;