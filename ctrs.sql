CREATE DATABASE if not exists ctrs DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
use ctrs;
create table user(user_id int primary key auto_increment not null,
				user_name varchar(20) not null,
                password varchar(50) not null,
                user_type tinyint not null);

create table course(course_id int primary key auto_increment not null,
					course_name varchar(64) not null,
                    user_id int not null,
                    remark varchar(256) null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE);
create table homework(homework_id int primary key auto_increment not null,
					user_id int not null,
                    course_id int not null,
                    state smallint not null,
                    commit_time datetime not null,
                    save_path varchar(256) not null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE);
                    
create table student_course(id int primary key auto_increment not null,
						course_id int not null,
                        user_id int not null,
                        FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                        FOREIGN KEY(course_id) references course(course_id) ON DELETE CASCADE ON UPDATE CASCADE);
                        
create table resource(resource_id int primary key auto_increment not null,
					resource_type smallint null,
                    user_id int not null,
                    upload_time datetime not null,
                    save_path varchar(256) not null,
                    download_times int null,
                    remark varchar(512) null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE);
create table question(question_id int primary key auto_increment not null,
					user_id int not null,
                    theme varchar(64) null,
                    content varchar(512) not null,
                    publish_time datetime null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE);
create table answer(answer_id int primary key auto_increment not null,
					user_id int not null,
                    question_id int not null,
                    content varchar(512) not null,
                    answer_time datetime null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE,
                    FOREIGN KEY(question_id) references question(question_id) ON DELETE CASCADE ON UPDATE CASCADE);
                    
create table notice(notice_id int primary key auto_increment not null,
					user_id int not null,
                    title varchar(128) null,
                    content varchar(512) not null,
                    publish_time datetime null,
                    FOREIGN KEY(user_id) references user(user_id) ON DELETE CASCADE ON UPDATE CASCADE);
 

create view courseInfo
	as
		select 
			course.course_id,course.course_name,course.remark,user.user_name,course.user_id
		from
			course,user
		where 
			course.user_id=user.user_id;


--新建2个用户
insert into user(user_name,password,user_type) values('zqb1','123456',0);--普通用户（学生）
insert into user(user_name,password,user_type) values('zqb','123456',1);--管理员用户（教师）