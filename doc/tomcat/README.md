##### VM Arguments
* -server 
* -Xms1024M -Xmx1024M -Xss512k 
* -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:PermSize=64M -XX:MaxPermSize=300M -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=31 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m  -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly 
* -Djava.awt.headless=true

##### Reference
* [http://www.cnblogs.com/redcreen/archive/2011/05/05/2038331.html](http://www.cnblogs.com/redcreen/archive/2011/05/05/2038331.html)
* [http://unixboy.iteye.com/blog/174173](http://unixboy.iteye.com/blog/174173)
* [http://hllvm.group.iteye.com/group/wiki/2870-JVM](http://hllvm.group.iteye.com/group/wiki/2870-JVM)
* [http://blog.csdn.net/jinwanmeng/article/details/7756591](http://blog.csdn.net/jinwanmeng/article/details/7756591)
