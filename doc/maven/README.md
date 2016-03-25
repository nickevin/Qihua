##### Commands
* mvn -version
* mvn help:system
* mvn archetype:generate -DarchetypeCatalog=internal
* mvn eclipse:eclipse, mvn eclipse:eclipse -DdownloadSources=true
* mvn install
* mvn site
* mvn dependency:sources
* mvn javadoc:javadoc
* mvn -X package

##### Plugins
###### yuicompressor-maven-plugin
* mvn yuicompressor:compress

###### tomcat7-maven-plugin
* run tomcat instance: sh catalina.sh run
* deploy: mvn tomcat7:deploy
* redeploy: mvn tomcat7:redeploy
* undeploy: mvn tomcat7:undeploy

##### Reference
* http://chembo.iteye.com/blog/2085173
* http://blog.csdn.net/kimsoft/article/details/8724362
* http://www.oracle.com/technetwork/cn/community/java/apache-maven-getting-started-2-405568-zhs.html
* http://wang-jia-sina-com.iteye.com/blog/1538000
* http://davidb.github.io/yuicompressor-maven-plugin/
* http://www.mkyong.com/maven/how-to-deploy-maven-based-war-file-to-tomcat/
* http://tomcat.apache.org/maven-plugin-2.2/index.html

##### Misc
* del D:\User\.m2\repository\*lastUpdate* /a/s


