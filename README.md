# Qihua [![Build Status](https://travis-ci.org/nickevin/Qihua.svg?branch=master)](https://travis-ci.org/nickevin/Qihua)

### System Architecture
* Servelt Container: Tomcat 7.*
* Cache Layer: Redis
* Web Server: nginx
* Database Server: MySQL 5.6 

### Installation and Getting Started
* git clone https://github.com/nickevin/Qihua.git
* mvn eclipse:eclipse
* java -jar lombok.jar
* import to eclipse
* just right click on the project and click [Configure] and you should see “Convert to Maven Project” option

### Dependency Libraries
* [Spring Framework](https://github.com/spring-projects/spring-framework)
*	[Lombok](https://github.com/rzwitserloot/lombok)
*	[Apache Commons](http://commons.apache.org/)
*	[Apache Log4j 2](http://logging.apache.org/log4j/2.x/)
*	[Joda-Time](http://www.joda.org/joda-time/)
*	[Jasypt](http://www.jasypt.org/)
*	[Druid](https://github.com/alibaba/druid)
* [Jedis](https://github.com/xetorthio/jedis)
*	[Tomcat Session Manager](https://github.com/jcoleman/tomcat-redis-session-manager)

### Configuartion
##### MySQL Dump
* cd database
* cat dump.sql

##### ~~Tomcat Session Manager~~
~~Modify context.xml~~
```xml
<Valve className="com.radiadesign.catalina.session.RedisSessionHandlerValve" />
<Manager className="com.radiadesign.catalina.session.RedisSessionManager" host="192.168.199.183" port="6379" database="0" maxInactiveInterval="60" />

```
~~Put these jar into the %TOMCAT%\lib folder~~
* ~~tomcat-redis-session-manager-1.2-tomcat-7.jar~~
* ~~jedis-2.7.3.jar~~
* ~~commons-pool2-2.0.jar~~

##### nginx  
* cd nginx
* cat readme.txt
* cat nginx.conf

  ```
  upstream qihua.com {  // Please replace YOUR HOST
        #ip_hash;
        server 192.168.199.108:8080 weight=5 max_fails=1 fail_timeout=2s; // Please replace YOUR IP
        server 192.168.199.183:8080 weight=5 max_fails=1 fail_timeout=2s; // Please replace YOUR IP
    }
  ```
  
  ```
  user  Kevin staff; // Please replace YOUR USERNAME AND GROUP(I use OS X, so you have to check yours)
  ```
  
  ```
  server {
        listen       80;
        server_name  www.qihua.com qihua.com; // Please replace YOUR HOST
        ...
  }
  ```

##### Redis
* cd redis
* cat readme.txt

#### System
##### Mac
* cd /private/etc
* vim hosts
  * add 127.0.0.1	www.qihua.com // Please replace YOUR HOST
  * add 127.0.0.1	qihua.com     // Please replace YOUR HOST

##### Win
* cd %Windows%\System32\drivers\etc
* edit hosts file
  * add 127.0.0.1	www.qihua.com // Please replace YOUR HOST
  * add 127.0.0.1	qihua.com     // Please replace YOUR HOST
  
### CI
Travis CI: https://travis-ci.org/nickevin/Qihua

### Load Testing
```
ab -kc 1024 -n 1024 http://qihua.com/Qihua/gift
```
### Issues and Feature Requests
Please report issues via GitHub's issue tracker.

### License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)
