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

##### Homebrew
* brew search xxx
* brew info xxx: 主要看具体的信息，比如目前的版本，依赖，安装后注意事项等
* brew update: 更新 Homebrew 自己，并且使得接下来的两个操作有意义
* brew outdated: 列出所有安装的软件里可以升级的那些
* brew upgrade: 升级所有可以升级的软件们
* brew cleanup: 清理不需要的版本极其安装包缓存

##### Homebrew 推荐流程
* brew update: 更新 Homebrew 的信息
* brew outdated: 看一下哪些软件可以升级
* brew upgrade xxx: 如果不是所有的都要升级，那就这样升级指定的
* brew upgrade; brew cleanup: 如果都要升级，直接升级完然后清理干净