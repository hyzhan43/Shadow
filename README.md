
# 什么是 Lin CMS?
Lin CMS 是一个前后端分离的 CMS 解决方案，这意味着，Lin 既提供后台的支撑，也有一套对应的前端系统。

首先，传统的网站开发更多的是采用服务端渲染的方式，需用使用一种模板语言在服务端完 成页面渲染：比如 JinJa2、Jade 等。服务端渲染的好处在于可以比较好的支持 SEO，但作 为内部使用的 CMS 管理系统，SEO 并不重要。

但一个不可忽视的事实是，服务器渲染的页面到底是由前端开发者来完成，还是由服务器开发者来完成？其实都不太合适。现在已经没有多少前端开发者是了解这些服务端模板语言的，而服务器开发者本身是不太擅长开发页面的。那还是分开吧，前端用最熟悉的 Vue 写 JS 和 CSS，而服务器只关注自己的 API 即可。

其次，单页面应用程序的体验本身就要好于传统网站。

想了解更多 Lin-cms 访问以下网址 [http://doc.cms.7yue.pro/]

# Shadow
`Shadow` 是一款基于SpringBoot2.x编写的脚手架, 整合了 Spring Data JPA + JWT + MySQL 等一系列框架, `Shadow` 在很大程度上借鉴了 Lin-cms-flask，故起名为`Shadow`  “影子” 模仿的意思。我尽量保证二者的架构和模式一致。当然由于二者属于不同语言，也有截然不同的生态，因此或多或少的存在一定的差异性，因此我编写两个版本,
如下

* `dev` 分支
    * 该分支是几乎和Lin-cms-flask版保持一致, 在url 、 参数 、 命名 、返回结果几乎无差, 也是为了方便开发者能够无缝和Lin-cms 对接。如果发现存在差异, 望告知我进行修改~
* `zhan` 分支
    * 该分支是按照我个人意愿修改的分支。由于参考Lin-cms-flask版, 语言, 架构等的差异, dev分支很难满足于我, 也发挥不了 SpringBoot, Java的魅力, 所以在这分支, 我可以随意发挥, 写出自己风格的代码~ 该分支代码仅供参考学习。

# 起步

入门一个新框架最好的方式就是将这个框架的 demo 运行起来。由于 Lin 采用的是前后端 分离的架构，所以相比于传统的网站，它的环境搭建会稍显麻烦。但 Lin 并没有采用任何 冷门的技术，相比于传统网站，只不过多出了一些对于 Vue 运行环境的支持。

Shadow 依赖 Java 环境，在使用前请确保你已经搭建好了 Java 的环境，且版本 保证在`jdk8`以上，在项目中我们运用到了大量的
jdk8新特性：`stream`、`lambda`

# Shadow 必备环境

1、安装 MySQL（version： 5.6+）

2、安装 Java环境 (version： jdk8+)

# 获取工程项目
打开你的命令行工具（terminal），在其中键入:

```
git clone https://github.com/hyzhan43/Shadow.git
```

# 分支选择

由于要配合Lin-CMS 来运行, 所以选择需要进行分支切换,切换到对应 dev分支, 输入以下命令:

```
git checkout dev
```

# 数据库配置

Shadow 需要你自己在 MySQL 中新建一个数据库，名字由你自己决定。例如，新建一个名为 lin-cms 的数据库，数据库字符编码设置为utf-8mb4。接着，我们需要在工程中进行一项 简单的配置。使用编辑器打开 Shadow 工程的`Shadow/app/src/main/resources/application.yml`, 找到如下配置项：

```java
datasource:
    url: jdbc:mysql://127.0.0.1:3306/scarecrow?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
    username: root
    password: a123456
    driver-class-name: com.mysql.cj.jdbc.Driver
```

```
url: jdbc:mysql://127.0.0.1:3306 为 [ip]:[端口号]/[数据库名]?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8
```
请在datasource这项中配置 MySQL 数据库的用户名、密码、ip、端口号与数据库名。请务必根 据自己的实际情况修改此配置项。

# 运行

一切就绪后, 点击右上角`run` (Application.java), 就可以运行项目

如果一切顺利，你将在命令行中看到项目成功运行的信息。如果你没有修改代码，Shadow 将默 认在本地启动一个端口号为 `5000` 的端口用来监听请求。此时，我们将看到控制台输出如下：

```java
Started AppApplication in 3.382 seconds (JVM running for 3.824)
```

这证明你已经成功的将 Shadow 运行起来了，Congratulations！

# 有问题反馈
在使用中有任何问题，欢迎反馈给我，可以提Issues, 也可以用以下联系方式跟我交流

* 邮件: hyzhan43@163.com
* QQ: 1063523767
