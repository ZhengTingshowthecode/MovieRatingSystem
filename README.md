# movieSystem
# 电影评分系统（movieSystem）

#### 介绍
一、需求分析

（1）问题描述：设计系统的简单描述

模仿某些电影影评系统，如豆瓣，设计一个电影影评系统。

该系统应该包含电影、演员、导演的如下基本信息：

①	电影 (Movies)[ 电影ID (MovieID, PK), 电影名称 (Title), 发行年份 (ReleaseYear), 电影时长 (Duration), 类型/流派 (Genre), 语言 (Language),国家/地区 (Country),简介 (Synopsis),评分]

②	演员 (Actors)[ 演员ID (ActorID, PK),姓名 (Name),性别(Gender),出生日期 (BirthDate),国籍 (Nationality)]

③	导演 (Directors)[ 导演ID (DirectorID, PK),姓名 (Name),性别(Gender),出生日期 (BirthDate),国籍 (Nationality)]

该系统还应包含：

①	 注册用户的基本信息；

②	演员参演电影的信息；

③	导演电影的信息（一部电影可能不止一个导演，一个导演会导演很多电影）；

④	用户可以对电影发表评论、给电影打分，所以还应该有评论信息、打分信息。

系统功能包含：

（1）可以维护电影、导演、演员、用户的基本信息；

（2）每部电影都应该有演员和导演；

（3）用户可以对电影打分、发表评论；

（4）用户可以维护自己的打分和评论信息；

（5）用户评分后，对应电影的总评分要自动发生变化；

（6）可以按照电影的发行年月、类型/流派、评分等信息进行组合查询和排序；

（7）可以按照演员、导演的相关信息进行查询；

（8）给出热门电影推荐信息（同学们可以自行对“热门”进行定义）；

（9）界面应该有电影、演员的图形化显示。

(2)系统功能描述，对自己所负责的功能模块，可画出所设计模块的操作流程；并分析所需要存储的数据信息。

![image](https://github.com/ZhengTingshowthecode/movieSystem/assets/163553575/aca69d3b-2011-4047-9d0c-e4d36882b36f)


(3)数据库完整性方面的要求。如是否需要显示定义原子事务、需定义具有什么功能的存储过程、需定义具有什么功能的触发器、对不同的用户有何不同的系统使用权限等。

需要定义的存储过程的功能：

（1）	以电影名为输入参数，查询该电影的所有评论信息；

（2）	用户评分；

（3）	用户评论；

（4）	用户删除自己的评分；

（5）	用户删除自己的评论。

需要定义的触发器的功能：

（1）	当用户给某电影增加了一个评分，该电影的评分自动发生变化；

（2）	当用户给某电影删除了一个评分，该电影的评分自动发生变化；

（3）	用户注册的时候，手机号规范以及密码规范。

(4)需要考虑的社会、安全、法律等因素，以及本系统中数据库安全性的设置。（这一部分内容必须有）

     用户和管理员必须通过ID和密码的验证才能进入系统。

     管理员可以删除用户的一些侮辱性、危害社会稳定等类型的言论。

     管理员可以删除一些题材、语言不合适的电影。

二、概念结构设计

画出系统整体的E-R模型；并对模型中所出现的实体及属性等信息加以说明。

![image](https://github.com/ZhengTingshowthecode/movieSystem/assets/163553575/7c018c3d-b6e5-4fe6-aa1e-c66e776afeee)


ER图中方框里的 电影、演员、导演、用户为 实体， 椭圆形中的为 属性。

一个演员可以演多部电影，一部电影可以有多个演员参演

一个导演可以导多部电影，一部电影可以有多个导演

一个用户可以评论多部电影，一部电影可以有多个用户评论

一个用户可以给多部电影打分，一部电影可以由多个用户打分

三、逻辑结构设计

（1）模式设计：按系统整体E-R模型，写出关系模式；并利用数据字典加以描述。（如每个关系模式有何属性、属性的类型、属性值的长度、是否可取空值、是否为主码、有何约束条件等信息）

关系模型：【直线为主键，波浪线为外键】

电影Movies（MovieID, Title, ReleaseYear, Duration, Genre, movieLanguage, Country, Synopsis, Score）

演员 Actors（ActorID, actorName, Gender, Birthdate, Nationality）

导演 Directors（DirectorID, directorName, Gender, Birthdate, Nationality）

用户 Users（UserID, Nickname, PWD, Phone, registerTime）

评论 Comments（UserID, MovieID, commentText, commentTime）


评分 Scores（UserID, MovieID, score, scoreTime）
【UserID和MovieID也是联合主键】

电影、演员关联表 Movie_Actor（MovieID, ActorID）
【MovieID和UserID也是联合主键】

电影、导演关联表 Movie_Director（MovieID, DirectorID）
【MovieID和UserID也是联合主键】

四、数据库的物理设计

（1）为了提高在表中搜索元组的速度，在实际实现的时候应该基于某些属性建立索引。给出所建立的索引。（包括按哪些表中哪些属性按升序还是降序来创建索引及相应的SQL的创建语句），并分析所建的索引在应用系统的查询中有无起作用。

在此电影评分系统中，可能的搜索操作通常包括按电影名称、导演、演员、类型/流派、发行年份等搜索电影，或者按用户名搜索用户，因此需要为这些属性建立索引以提高搜索速度。

除了已经建立了的主键索引之外，还应设置以下索引：

-- 设置索引

-- 为电影名称 (Title) 创建升序索引

create index idx_Title on Movies(Title asc);    -- 搜索电影详情时用到 

-- 为发行年份 (ReleaseYear) 创建降序索引

create index idx_ReleaseYear on Movies(ReleaseYear desc);

-- 为流派类型设置升序索引

create index idx_genre on Movies(Genre asc);

-- 为评分设置升序索引

create index idx_movieScore on Movies(Score asc);

-- 组合索引

create index idx_zuhe on Movies(ReleaseYear,Genre,Score);

-- 为演员、导演姓名 (Name) 创建升序索引

create index idx_actorName on actors(actorName asc);      -- 查询演员个人信息用到

create index idx_directorName on directors(directorName asc);  -- 查询导演个人信息用到

-- 为用户名 (Username) 创建升序索引

create index idx_userName on users(Nickname asc);

-- 为用户ID创建升序索引

create index idx_userID on users(userID asc);           -- 用户登录时用到

-- MovieID 和 UserID：这两个字段通常用于联结查询，以加速查找过程

create index idx_comments on Comments(movieID, userID);  -- 管理员删除用户不当评论的时候用到

create index idx_scores on Scores(movieID,userID);

新建的这些索引在查询时如果where语句里等号左边没有函数或者运算、where语句中不用in或者not in或者or这些字样、不用开头模糊查询，并且设置的两个联合索引在查询语句中把设了联合索引的属性都用上的话，是完全有效的。因此我建的索引在我设计的系统中查询时是起了作用的。

（2）从提高数据库性能的角度，分析还可以采用哪些策略提高数据库的性能。

优化查询语句：通过优化SQL查询语句，减少不必要的数据加载和计算。例如，避免使用SELECT *，而是指定需要的列，减少数据的传输量。

使用合适的连接类型：根据数据的实际情况选择合适的连接类型（如INNER JOIN、LEFT JOIN等），避免产生大量不必要的数据计算。


五、数据库设计实现及运行

（1）数据库的创建

SQL语句：

create database movie_system;


（2）数据表的创建（可同时在创建时定义相关的约束）

SQL语句：

create table Movies(

    movieID char(8) not null primary key,

    Title varchar(255) not null,

    ReleaseYear datetime,

    Duration int8 comment '单位为‘分钟’',

    Genre varchar(100),

    movieLanguage varchar(100),

    Country varchar(100),

    Synopsis varchar(1000),

    Score decimal(3,1) default 0.0

);

create table Actors(

    ActorID char(8) not null primary key,

    actorName varchar(255) not null,

    Gender char(2) default '男',

    Birthdate datetime,

    Nationality varchar(100)

);

create table Directors(

    directorID char(8) not null primary key,

    directorName varchar(255) not null,

    Gender char(2) default '男',

    Birthdate datetime,

    Nationality varchar(100)

);

create table Users(

    userID char(8) not null primary key,

    Nickname varchar(255) default '未知用户',

    PWD varchar(255) not null,

    Phone char(11) not null,

    registerTime timestamp not null default current_timestamp

);

create table Comments(

    userID char(8) not null,

    movieID char(8) not null,

    commentText varchar(1000) not null,

    commentTime timestamp not null default current_timestamp,

    foreign key(userID) references Users(userID),

    foreign key(movieID) references Movies(movieID)

);

create table Scores(

    userID char(8) not null,

    movieID char(8) not null,

    score decimal(3,1) not null,

    scoreTime timestamp not null default current_timestamp,

    foreign key(userID) references Users(userID),

    foreign key(movieID) references Movies(movieID)

);

create table movie_actor(

    movieID char(8) not null,

    ActorID char(8) not null,

    foreign key(movieID) references Movies(movieID),

    foreign key(ActorID) references Actors(ActorID)

);

create table movie_director(

    movieID char(8) not null,

    directorID char(8) not null,

    foreign key(movieID) references Movies(movieID),

    foreign key(directorID) references Directors(directorID)

);

create table manager(

    manID char(8) not null primary key,

    manPWD varchar(255) not null

);

说明：若有完整性约束的一方面可以创建时同时实现。按需求及自行设计模块中所实现的情况，在该部分对完整性约束加以实现。

-- 完整性约束

alter table Movies add constraint check_score check(Score<=10 and Score>=0);

alter table Users add constraint check_pwd check(length(PWD)>6);

alter table Users add constraint check_phone check(Phone REGEXP '^[0-9]{11}$');

alter table Scores add constraint check_setScore check(score<=10 and score>=0);

-- 联合主键  

-- （每个用户只能对一部电影评一次分，除非删了再评）

alter table Scores add constraint PK_Scores primary key(userID,movieID);

alter table movie_actor add constraint PK_MA primary key(movieID,ActorID);

alter table movie_director add constraint PK_MD primary key(movieID,directorID);


（3）视图的创建

SQL语句：

-- 该视图向用户展示某个电影的导演

create view show01 as

select Title,directorName from movies,directors,movie_director

where movies.movieID=movie_director.movieID and movie_director.directorID=directors.directorID;

SQL语句：

-- 该视图向用户展示某个电影的演员

create view show03 as

select Title,actorName from movies,actors,movie_actor

where movies.movieID=movie_actor.movieID and movie_actor.ActorID=actors.ActorID;

SQL语句：

-- 该视图向用户展示某电影的评论的人数。(用于热门)

create view show02 as

select Title,Genre,count(*)'人数' from movies,comments where movies.movieID=comments.movieID group by movies.movieID order by '人数' desc;

SQL语句：

-- 展示某演员演过的所有电影的信息

create view m_a as

select Title,ReleaseYear,Duration,Genre,movieLanguage,Country,Synopsis,Score,actorName from movies,actors,movie_actor

where movies.movieID=movie_actor.movieID and actors.ActorID=movie_actor.ActorID;

SQL语句：

-- 展示某导演导过的所有电影的信息

create view m_d as

select Title,ReleaseYear,Duration,Genre,movieLanguage,Country,Synopsis,Score,directorName from movies,directors,movie_director

where movies.movieID=movie_director.movieID and directors.directorID=movie_director.directorID;


（4）存储过程的定义实现

SQL语句：

-- 以电影名为输入参数，查询该电影的所有评论信息；

delimiter //

create procedure getComments(in movie_name varchar(255))

begin

select userID,commentText,commentTime from Comments where movieID=(select movieID from Movies where Title=movie_name);

end //

delimiter ;

SQL语句：

-- 用户评分

delimiter //

create procedure setscore(in userID char(8),in movieName varchar(255),in score decimal(3,1))

begin

declare mID char(8);

set @mID=(select movieID from movies where Title like movieName);

insert into scores(userID,movieID,score) values(userID,@mID,score);

end //

delimiter ;

SQL语句：

-- 用户评论

delimiter //

create procedure setcomment(in userID char(8),in movieName varchar(255),in commenttext varchar(1000))

begin

declare mID char(8);

set @mID=(select movieID from movies where Title like movieName);

insert into comments(userID,movieID,commentText) values(userID,@mID,commenttext);

end //

delimiter ;

SQL语句：

-- 用户删除自己的评分

delimiter //

create procedure deleteScore(in uid char(8),in movieName varchar(255))

begin

declare mID char(8);

set @mID=(select movieID from movies where Title like movieName);

delete from Scores where userID=uid and movieID=@mID;

end //

delimiter ;

SQL语句：

-- 用户删除自己的评论

delimiter //

create procedure deleteComment(in userid char(8),in movieName varchar(255),in oldText varchar(1000))

begin

declare mID char(8);

set @mID=(select movieID from movies where Title like movieName);

delete from Comments where userID=userid and movieID=@mID and commentText like oldText;

end //

delimiter ;


（5）触发器的定义实现

SQL语句：

-- 当用户给某电影增加了一个评分，该电影的评分自动发生变化；

delimiter //

create trigger update_score after insert on Scores for each row

begin

update movies set Score= (select AVG(score) from Scores where movieID=new.movieID)

where movieID=new.movieID;

end //

delimiter ;


-- 当用户给某电影删除了一个评分，该电影的评分自动发生变化；

delimiter //

create trigger update_delete_score after delete on Scores for each row

begin

update movies set Score= (select AVG(score) from Scores where movieID=old.movieID)

where movieID=old.movieID;

end //

delimiter ;

（6）自行设计各模块中所涉及的数据操纵语句



