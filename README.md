# MyBatis-Kits
MyBatis 增强工具包，实现了便捷的单表 CRUD，各种自定义条件查询，以及物理分页查询，单表查询甚至可以做到无 XML，像使用 PHP 操作数据库一样简单。

--------------------------------------------------

# 仓库地址
* 码云：https://gitee.com/blackfox/mybats-kits
* GitHub: https://github.com/yangjian102621/mybatis-kits
> Note: 会优先在码云更新，GitHub 只有发布版本的时候才会更新

# 为什么使用 Mybatis-Kits?
1. 无侵入：Mybatis-Kits 在 Mybatis 的基础上进行扩展，只是内置了简单的CRUD, 并增强了查询功能，并未修改原生 API，支持所有 Mybatis 原生的特性，不会影响项目现有的 Mybatis 架构。
2. 功能精简：简单到只有你需要的功能，又强大到刚好能满足你的绝大多数业务需求。她不像其他 Mybatis 中间件那么强大，每种功能都提供了各种实现 API，她每种功能都只提供了一种（最优）实现，让你不用还去考虑使用哪个 
API 来实现当前的功能，体验不用选择的幸福。如果你刚好像我一样也有选择困难症的话，那她简直是为你量身订做。
3. 内置分页插件：基于Mybatis物理分页，开发者无需关心具体操作，配置好插件之后，写分页等同于写基本List查询
4. 接入简单：内置提供了 spring-boot-starter，让你 1 分钟接入 SpringBoot 项目。

# 最新版本

```xml
<dependency>
  <groupId>org.rockyang</groupId>
  <artifactId>mybatis-kits-core</artifactId>
  <version>1.5.2</version>
</dependency>
```

SpringBoot 项目接入方式
```xml
<dependency>
  <groupId>org.rockyang</groupId>
  <artifactId>mybatis-kits-spring-boot-starter</artifactId>
  <version>1.5.2</version>
</dependency>
```
> Note: SpringBoot 项目不需要再引入 mybatis-kits-core 了，只导入 mybatis-kits-spring-boot-starter 一个构件就 OK 了。

# 小试牛刀
假设我们已存在一张 User 表，且已有对应的实体类 User，实现 User 表的 CRUD 操作我们只需创建一个对应的 Mapper 接口就行了。

```java
public interface UserMapper extends BaseMapper<User> { }
```

#### 基本 CRUD 操作 

```java
// 影响行数
int affactRows = 0;
// 初始化 User 实体对象, 如果是非自增ID需要初始化ID
// 系统有内置的分布式 ID 生成工具
User user = new User(userMapper.getNewId());

// 插入 User (如果是自增ID的话，插入成功会自动回写ID到实体类)
user.setName("Rock");
affactRows = userMapper.add(user);

// 更新 User
user.setAge(18);
affactRows = userMapper.update(user);
// 你也可以这样
affactRows = userMapper.updateAll(user);
/*
   注意：update() 方法只更新 User 实体的非 null 字段，是增量更新
   而 updateAll() 会更新所有字段，包括值为 null 的字段
   
   应用需求：比如你知道用户ID，只想更新 Name 字段，常规方法是下面这样的 
 */
String userId = "abc";
User user = userMapper.get(userId);
user.setName("New name");
userMapper.updateAll(user);
// 或则你也可以这样实现
User user = new User(userId);
user.setName("New name");
userMapper.update(user); // 这样可以少一次查询
// 所以如果你就是要把某个字段更新成 null, 请使用 updateAll()

// 查询 User
User userItem = userMapper.get(user.getId());
// 查询ID > 100 所有用户列表
List<User> userList = userMapper.searchByConditions(
        new Conditions.gt("id", 100)
);
// 如果只想取列表中的一条
User u = userMapper.getByCondition(new Conditions.gt("id", 100));

// 删除 User
affactRows = userMapper.delete(user.getId());
// 删除 id > 100 and id < 200 的所有用户
Conditions condi = new Conditions();
condi.gt("id", 100).lt("id", 200);
affactRows = UserMapper.deleteByConditions(condi);
```

#### 分页查询（所有分页均为物理分页）

```java
// 查询所有记录并分页
Page<User> page = new Page<>();
page.setPageSize(10);
page = userMapper.search(page);

for (User user : page.getResults()) {
	logger.info("结果：{}",user);
}

// 按照条件查询分页
Conditions condi = new Conditions();
condi.eq("name", "Rock");
Page<User> page = new Page<>();
page.setPageSize(10);
page = userMapper.searchByConditions(page, condi);

for (User user : page.getResults()) {
	logger.info("结果：{}",user);
}

```

#### 条件组合

MyBatis-Kits 的 Conditions 查询异常强大，几乎可以满足你单表查询的所有需求。

先来个简单的：

```java
Conditions conditions = new Conditions();
conditions.eq("name", "Rock")
    .ge("age", 18)
    .lt("age", 30)
    .ne("address", "Addres_A");
// output: name='Rock' AND age > 18 AND age < 30 AND address != 'Addres_A'
```

再来个稍微复杂一点的：

```java
Conditions conditions = new Conditions();
conditions.add(Restrictions.and(Restrictions.eq("age",18),Restrictions.eq("name","zhangsan")));
conditions.add(Restrictions.or(Restrictions.eq("count",18),Restrictions.eq("count",29)));
// output: ((age=18 AND name='zhangsan') AND (count=18 OR count=29))
```

更多复杂条件查询请参考单元测试 `org.rockyang.mybatis.boot.demo.test.plus.ConditionTest`

上面的所有功能都是不需要创建 Mapper.xml 文档就可以轻松实现的，如果你需要增加联合查询功能，只需增加相应的 UserMapper.xml，在里面实现就好了。
完全兼容原生 MyBatis 的所有功能。

# 文档

* 文档访问地址：[http://mybatis.r9it.com/](http://mybatis.r9it.com/)
* 文档项目地址：[https://gitee.com/blackfox/mybatis-kits-doc](https://gitee.com/blackfox/mybatis-kits-doc)

另外，本项目里面有 [demo](https://gitee.com/blackfox/mybatis-kits/tree/master/demos/spring-boot-starter-demo) 项目，以及大量的单元测试，能够很快上手。

