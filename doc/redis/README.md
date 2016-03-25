##### Commands
* redis-server /usr/local/etc/redis.conf
* redis-cli -h 192.168.199.183 -p 6379
* info memory
* shutdown
* redis-benchmark -h 127.0.0.1 -p 6379 -c 1000 -n 10

##### API
* flushdb: remove all from current db
* flushall: remove all from all db
* keys *: list all keys
* type key: get value type of key, if show ZSET, it is a short name for Redis Sorted Set
* zrange key 0 -1: get the specified range of elements in the sorted set stored at key

##### GUI Client
* [redis-commander](https://github.com/joeferner/redis-commander)

##### Reference
* [http://www.cnblogs.com/tankaixiong/p/4048167.html](http://www.cnblogs.com/tankaixiong/p/4048167.html)
* [http://lzone.de/cheat-sheet/Redis](http://lzone.de/cheat-sheet/Redis)
* [Spring Cache 抽象详解](http://jinnianshilongnian.iteye.com/blog/2001040)

