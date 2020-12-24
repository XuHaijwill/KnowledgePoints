# 数据库专题

## ROW_NUMBER() OVER()函数用法详解 

支持Oracle,PostgreSql

语法格式：row_number() over(partition by 分组列 order by 排序列 desc)

在使用 row_number() over()函数时候，over()里头的分组以及排序的执行晚于 where 、group by、 order by 的执行。

```
表数据：
create table TEST_ROW_NUMBER_OVER(
       id varchar(10) not null,
       name varchar(10) null,
       age varchar(10) null,
       salary int null
);
select * from TEST_ROW_NUMBER_OVER t;
 
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(1,'a',10,8000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(1,'a2',11,6500);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(2,'b',12,13000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(2,'b2',13,4500);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(3,'c',14,3000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(3,'c2',15,20000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(4,'d',16,30000);
insert into TEST_ROW_NUMBER_OVER(id,name,age,salary) values(5,'d2',17,1800);
一次排序：对查询结果进行排序（无分组）
select id,name,age,salary,row_number()over(order by salary desc) rn
from TEST_ROW_NUMBER_OVER t
```

![](D:\project\KnowledgePoints\notes\数据库专题\imgs\sql01.png)

```
进一步排序：根据id分组排序
select id,name,age,salary,row_number()over(partition by id order by salary desc) rank
from TEST_ROW_NUMBER_OVER t
再一次排序：找出每一组中序号为一的数据
select * from(select id,name,age,salary,row_number()over(partition by id order by salary desc) rank
from TEST_ROW_NUMBER_OVER t)
where rank <2
```

![](D:\project\KnowledgePoints\notes\数据库专题\imgs\sql02.png)

![](D:\project\KnowledgePoints\notes\数据库专题\imgs\sql03.png)

```
参考链接：https://blog.csdn.net/qq_25221835/article/details/82762416
```

