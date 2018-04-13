# SutentCreatProject
这是我的大学生创业项目代码，我负责服务器部分，最后实现所需接口的编写。

服务器通过LAMP方式搭建，扩展tomcat，用Java实现业务逻辑。

数据库存储浴室某时刻的人数
这是我的mysql表结构：
```
+----------+-------------+------+-----+---------+----------------+
| Field    | Type        | Null | Key | Default | Extra          |
+----------+-------------+------+-----+---------+----------------+
| id       | int(11)     | NO   | PRI | NULL    | auto_increment |
| day      | date        | YES  |     | NULL    |                |
| clock    | time        | YES  |     | NULL    |                |
| count    | int(11)     | YES  |     | NULL    |                |
| position | varchar(25) | YES  |     | NULL    |                |
+----------+-------------+------+-----+---------+----------------+
```

业务负责对数据库进行操作：
1、查询
2、删除
3、插入
