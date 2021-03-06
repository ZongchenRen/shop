
- `@NotNull` 不能为空
- `@NotEmpty` 用于集合
- `@NotBlank(message = "用户名不能为空")` 用于String 判断空格
- `@Valid` 表单验证  必备注解 ,统一处理后，方法参数不需要`BindingResult`
- `ResponseVo` 请求响应VO˚统一处理
- `ResponseEnum` 响应状态码统一处理
- 单元测试时class添加 `@Transactional`注解，数据只做测试，不修改数据库
- 打包 -> `mvn clean package `
- 打包跳过单元测试部分 ->  `mvn clean package -Dmaven.test.skip=true`
- 耗时：http请求 > 磁盘IO > 内存，所以`redis`很快
- 单元测试一定要写
- `@JsonInclude(value = Include.NON_NULL)`   当返回数据为空的时候不进行序列化
- `@Param` 用在dao，与xml中的参数映射
- `@RequsetParam` 用在controller,表示传参
- `@PathVariable` restful接口,/xxx/{***}

- `useGeneratedKeys="true" keyProperty="id"`  返回当前id

- 推荐使用`BigDecimal.valueOf()`
- Redis 有事物（打包命令），不能回滚

- `docker run -d  -p 5672:5672 -p 15672:15672 rabbitmq:3.8.2-management`