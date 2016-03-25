##### VM Arguments
* -server 
* -Xms1024M -Xmx1024M -Xss512k 
* -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:PermSize=64M -XX:MaxPermSize=300M -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=31 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m  -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly 
* -Djava.awt.headless=true

##### APR
* brew install apr
* cd %TOMCAT_HOME%/bin/tomcat-native-1.1.33-src/jni/native
* ./configure --with-apr=/usr/local/Cellar/apr/1.5.2/bin/apr-1-config --with-java-home=/System/Library/Frameworks/JavaVM.framework/
* make && make install
* sudo ln -s /usr/local/apr/lib/libtcnative-1.* /Users/Kevin/Library/Java/Extensions

##### Reference
* [http://www.cnblogs.com/redcreen/archive/2011/05/05/2038331.html](http://www.cnblogs.com/redcreen/archive/2011/05/05/2038331.html)
* [http://unixboy.iteye.com/blog/174173](http://unixboy.iteye.com/blog/174173)
* [http://hllvm.group.iteye.com/group/wiki/2870-JVM](http://hllvm.group.iteye.com/group/wiki/2870-JVM)
* [http://blog.csdn.net/jinwanmeng/article/details/7756591](http://blog.csdn.net/jinwanmeng/article/details/7756591)
