# EJOS-JudgeOnlineSystem

## 如何运行

需要首先在wildfly中建立数据源，参考：
http://dz.sdut.edu.cn/blog/subaochen/2016/11/wildfly%E9%85%8D%E7%BD%AEpostgresql%E6%95%B0%E6%8D%AE%E6%BA%90/
&&
http://blog.csdn.net/timo1160139211/article/details/70490210

开启wildfly,mysql服务器。
然后在命令行执行：
  mvn clean package wildfly:deploy


