##### hosts-path
* /private/etc/hosts

#### 用户组
* dscl . list /users
* dscl . list /groups

##### 端口占用
* lsof -i tcp:8080

##### 终止进程
* kill PID

##### 在/路径下查找文件名java结尾的文件
* find / -name "*.java"

##### 在/Users/Kevin/.m2路径下查找文件名lastUpdated结尾的文件并删除
* find /Users/Kevin/.m2 -name \*.lastUpdated -exec rm -r {} +

##### Homebrew
* [官方 FAQ](https://github.com/Homebrew/brew/blob/master/docs/FAQ.md)
* brew search xxx
* brew info xxx: 主要看具体的信息，比如**版本，安装路径，注意事项**等
* brew update: 更新 Homebrew 自己，并且使得接下来的两个操作有意义
* brew outdated: 列出所有安装的软件里可以升级的那些
* brew upgrade: 升级所有可以升级的软件们
* brew cleanup: 清理不需要的版本以及安装包缓存
* brew ls --versions | grep jenkins：查看已安装软件的所有版本

##### Homebrew Workflow
* brew update: 更新 Homebrew 的信息
* brew outdated: 查看可以升级的软件包
* brew upgrade xxx: 更新指定的软件包
* brew upgrade; brew cleanup: 升级后清理缓存

#### Shell Profile
* sudo sysctl -w kern.maxfiles=1048600
* sudo sysctl -w kern.maxfilesperproc=1048576
* ulimit -S -n 1048576
* ulimit -n 100000 unlimited

##### Homebrew 软件包
* nginx
	* install: /usr/local/Cellar/nginx
	* conf: /usr/local/etc/nginx/nginx.conf
	* log: /usr/local/var/log/nginx

* redis
	* install: /usr/local/Cellar/redis
	* conf: /usr/local/etc/redis.conf
	* log: /usr/local/var/log/mongodb	

* mongodb
	* install: /usr/local/Cellar/mongodb
	* conf: /usr/local/etc/mongod.conf
	* log: /usr/local/var/log/mongodb	

##### Jenkins
* insall: brew install jenkins
* run: 
	* cd /usr/local/Cellar/jenkins/VERSION/libexec
	* java -jar jenkins.war --httpPort=7001
