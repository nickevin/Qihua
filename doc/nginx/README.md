##### Commands
* sudo nginx
* sudo nginx -s stop
* sudo nginx -s quit	
* sudo nginx -s reload

##### nginx.conf
* mdfind nginx.conf
* conf-path=/usr/local/etc/nginx/nginx.conf
* http-log-path=/usr/local/var/log/nginx/access.log
* error-log-path=/usr/local/var/log/nginx/error.log 

##### 测试是否成功开启 gzip
* curl -I -H "Accept-Encoding: gzip, deflate" "http://qihua.com/Qihua/gift"
* curl -I -H "Accept-Encoding: gzip, deflate" "http://qihua.com/Qihua/resources/lib/jquery/plugins/flexslider/jquery.flexslider.css"
* curl -I -H "Accept-Encoding: gzip, deflate" "http://qihua.com/Qihua/resources/images/product/placeholder.gif"
* curl -I -H "Accept-Encoding: gzip, deflate" "http://qihua.com/Qihua/resources/lib/jquery/plugins/flexslider/jquery.flexslider.js"

##### Reference
* [http://www.ruanyifeng.com/blog/2011/09/curl.html](http://www.ruanyifeng.com/blog/2011/09/curl.html)
* [nginx 整合 Tomcat](http://cxshun.iteye.com/blog/1535188)