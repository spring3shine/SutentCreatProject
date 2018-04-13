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

业务负责对数据库进行操作，获取数据后以json形式输出：

　
1. 查询最新信息　     http://localhost:8080/test4/Query?userName=root&userPass=123456
2. 按日期查询单日信息  http://localhost:8080/test4/QueryDay?userName=root&userPass=123456&searchDay=2018-04-06
3. 查询所有信息       http://localhost:8080/test4/QueryLatest?userName=root&userPass=123456
4. 按id删除单条信息   http://localhost:8080/test4/DeleteId?userName=root&userPass=123456&deleteId=6
5. 按日期删除单日信息  http://localhost:8080/test4/DeleteDay?userName=root&userPass=123456&deleteDay=2018-4-6
6. 插入单条信息       http://localhost:8080/test4/Listen?userName=root&userPass=123456&Day=2018-4-6&Time=18:08:00&Count=4&Position=NorthGirl
