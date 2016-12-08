# SpringBoot_Demo
SpringBoot+Mybatis+MySQL

---

前段时间去了解了SpringBoot，感觉这东西太方便，不去要部署到Tomcat，也不需要配置文件，直接写一句代码就跑起来一个web项目，直接打包成jar运行就ok了。大型项目会不会使用SpringBoot我不太清除，但对于我这种入门程序员想做一些小项目简直太方便。

入门SpringBoot非常简单，Spring官网有教程，如果学过Spring和SpringMVC，那么简单5分钟即可入门。当然，如果没有学习过Spring和SpringMVC也不推荐去学习SpringBoot，建议先将前边两个至少入了门，毕竟这俩才是核心。

此项目是Maven项目，IDE为Intellij IDEA

## 依赖

国际惯例，先上依赖。

这是SpringBoot文档给出的依赖，只需要以下一个jar包，SpringBoot就能跑起来。

	<parent>
	    <groupId>org.springframework.boot</groupId>
	    <artifactId>spring-boot-starter-parent</artifactId>
	    <version>1.4.1.RELEASE</version>
  	</parent>

	<!-- SpringBoot核心jar包 -->
  	<dependencies>
	    <dependency>
	      <groupId>org.springframework.boot</groupId>
	      <artifactId>spring-boot-starter-web</artifactId>
	    </dependency>
  	</dependencies>

	<!-- 打包可执行的jar包 -->
	<build>
		<plugins>
		  <plugin>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-maven-plugin</artifactId>
		  </plugin>
		</plugins>
	</build>

其余jar包

加入Junit依赖

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>

开发工具，自动检测classpath变化并重新部署

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
    </dependency>

SpringBoot测试框架

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
    </dependency>

Mybatis-SpringBoot集成,SpringBoot官方只集成了JPA，并没有集成Mybatis，但Mybatis出了与SpringBoot的集成jar包

	<dependency>
      <groupId>org.mybatis.spring.boot</groupId>
      <artifactId>mybatis-spring-boot-starter</artifactId>
      <version>1.1.1</version>
    </dependency>

MySQL驱动jar包

	<dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.38</version>
    </dependency>

druid,alibaba开源的数据库连接池，可以抛弃dbcp和c3p0了，毕竟好久没更新了，而且alibaba在大数据和高并发上有丰富的经验，性能是非常不错的。如fastjson也有越来越多的开发者使用。

	<dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>druid</artifactId>
      <version>1.0.26</version>
    </dependency>

---

## 配置

配置数据库信息和连接池信息，我是使用yml文件来配置，更加简洁

在resources下新建application.yml文件，写入以下配置。

**注意：**具体配置项，冒号后面要有空格！如'name: mydb',但如'spring:'则不需要。

	#数据库相关配置
	spring:
	  datasource:
	    name: mydb
	    type: com.alibaba.druid.pool.DruidDataSource
	    url: jdbc:mysql://127.0.0.1:3306/phycholee_blog?useUnicode=true&characterEncoding=UTF-8
	    username: root
	    password: root
	    driver-class-name: com.mysql.jdbc.Driver
	    minIdle: 1
	    maxActive: 20
	    initialSize: 1
	    timeBetweenEvictionRunsMillis: 3000
	    minEvictableIdleTimeMillis: 300000

	#Mabatis的Mapper的XML目录，（可选，详情下面讲）
	mybatis:
	  mapperLocations: classpath*:mapper/*.xml
	  typeAliasesPackage: com.llf.springboot.model

还可以用properties文件配置，如果yml和properties都存在，SpringBoot默认会**选择properties**。

	spring.datasource.url=jdbc:MySQL://127.0.0.1:3306/phycholee_blog
	spring.datasource.username=root
	spring.datasource.password=root
	spring.datasource.driver-class-name=com.mysql.jdbc.Driver
	spring.datasource.max-idle=5
	spring.datasource.max-wait=10000
	spring.datasource.min-idle=1
	spring.datasource.initial-size=3

	server.port=8088
	server.session.timeout=10
	server.tomcat.max-threads=800
	server.tomcat.uri-encoding=UTF-8

	mybatis.mapperLocations=classpath*:mapper/*.xml
	mybatis.typeAliasesPackage=com.llf.springboot.model

---

## SpringBoot入口类

在项目目录下，创建Application.java类，代码如下

	@SpringBootApplication
	@EnableTransactionManagement    //开启事务注解
	@MapperScan("com.llf.springboot.dao")	//扫描Mybatis Mapper接口
	public class Application {
	    public static void main(String[] args) throws Exception {
	        SpringApplication.run(Application.class, args);
	    }
	}

其中@SpringBootApplication为最主要的注解，它等价于以下三个注解

	@Configuration		//代替XML配置文件
	@EnableAutoConfiguration	//自动配置其他bean，比如Mybatis的SQLSessionFactoryBean
	@ComponentScan	//扫描注解

---

## Mybatis Mapper

### Mapper XML

首先Mapper.xml文件，这是操作数据库的关键配置。mapper放在resources文件夹下，在上面yml配置的'mapperLocations'就是配置此路径。

**注意:**此文件不要放在java文件目录如'controller','service'的父目录下，不然Spring会扫描不到。

### Mapper接口

	//@Mapper
	public interface UserDao {

	//    @Select("select * from user where id = #{id}")
	    User findById(Integer id) throws SQLException;
	}

上面'@Mapper'注掉是因为在入口类Application.java中有注解@MapperScan("com.llf.springboot.dao")，Spring会自动在此文件夹下扫描。(这里使用dao是因为个人习惯，官方是使用mapper)

如果不想使用xml文件写sql，可以使用 @Select("")直接在mapper接口里写sql。

	@Select("")
	@Insert("")
    @Delete("")
    @Update("")

## Junit

SpringBoot中使用Junit，只需在Test类的类名上添加注解，需要加入'spring-boot-starter-test' jar包

	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringApplicationConfiguration(classes = Application.class)
	@WebAppConfiguration
