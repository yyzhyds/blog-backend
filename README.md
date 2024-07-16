# blog-backend

## 实现思路

### 如何知道是哪个用户是否登录了？

> 我这里使用最最简单的方法

1. 连接服务器端后，得到一个 session 状态（匿名会话），返回给前端

2. 登录成功后，得到了登录成功的 session，并且给该session设置一些值（比如用户信息），返回给前端一个设置 cookie 的 ”命令“

   **session => cookie**

3. 前端接收到后端的命令后，设置 cookie，保存到浏览器内

4. 前端再次请求后端的时候（相同的域名），在请求头中带上cookie去请求

5. 后端拿到前端传来的 cookie，找到对应的 session

6. 后端从 session 中可以取出基于该 session 存储的变量（用户的登录信息、登录名）

### 权限校验

> 对于用户信息表进行扩展，添加一个字段，也是最最简单的办法

### 密码的保护

1. 对于数据库的密码使用druid自带的密码加解密工具
2. 对于用户信息的脱敏处理

## 注意事项

1. 对于登录注册整个流程的校验，后端也必须有自己的一套，不能全部依赖于前端，有的人不通过前端页面对接口进行访问
2. 使用Jwt生成token

