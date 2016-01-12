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
* find /Users/Kevin/.m2 -name "*.lastUpdated" -exec rm -r "{}" \;
