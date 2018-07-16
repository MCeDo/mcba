# mcba
JavaWeb的大作业，完成的基本功能有：

1.用户管理模块：
a)划分角色和权限：超级管理员（所有用户的管理：增加、删除、查询用户）；版主（可以删除本版的帖子）；普通用户（可以增删改自己发布的帖子，可以查看并评论所有帖子）；游客（可以查看所有帖子，不能评论）；
b)经验值：根据登录次数、登录时长、发布帖子数量、评论帖子数量，给予经验值，并且根据经验值给出对应的职位或者称呼；
2.发布帖子模块
a)规定帖子的阅读权限（比如游客不可看），规定评论权限（比如不可评论）；
b)为版主提供帖子置顶功能；
c)论坛包括多个版面，比如体育版，生活版，学习版等等，每个版有版主。
3.查询功能模块
a)模糊查询用户；
b)根据标题或者内容，模糊查询帖子；
4.实时功能（使用HttpSessionBindingListener实现）
a)实时在线用户数（包括登录用户和guest游客（没有登录的访问用户））
b)实时登录用户数
c)记录网站的用户登录日志（登录，退出信息等）
5.账号安全
a)用户登录密码错了一次之后，下一次登录需要验证码校验码；
b)同个账号登录，密码错误3次后，锁死账号。（可以是1小时后解锁，或者通过注册邮箱发送链接解锁）
6.站内邮件功能模块
a)发送邮件通知到用户注册邮箱

采用的技术：Servlet，JDBC
特点，封装简易的orm框架，对servlet进行封装，采用一个servlet接收所有请求，做好约定，应用反射根据URL调用相应的控制层类及执行方法，并实现简单的依赖注入
 