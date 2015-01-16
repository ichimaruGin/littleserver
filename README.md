##毕设server端

#Mina

* 写了一个简单的server端,有ip过滤(目前保持对所有ip的通行allowed;可以在spring-base.xml中配置legalListFilter的regex参数值来限制)
* server端的协议解码器自定义，目前还没有加入与业务逻辑相关的处理
* 写了一个mina客户端原型，在test目录下面

#Redis

*使用spring的redis对部分内容进行缓存
*这边有个神坑要注意
在用redisTemplate.opsForValue().set(key, value)存入String类型的数据时，在代码中直接redisTemplate.opsForValue().get(key)
是可以正常取出value值的;但是在client中(Redis Desktop Manager or linux redis-cli)会发现get key 的值为 nil ，
原因: redisTemplate在处理key和value时，会用keySerializer和valueSerializer进行序列化和反序列化，而默认的序列化的类是jdk自带的那个东东，
所以序列化后的key值和value值就让client无法识别;(具体看RedisTemplate源码)
解决方法1：采用org.springframework.data.redis.serializer.StringRedisSerializer这个类来序列化就OK了，具体做法是在配置文件中为redisTemplate
这个bean配置对应的成员变量(具体参看spring-redis.xml)  http://qatang.com/2014/03/18/spring-data-redis%E4%BD%BF%E7%94%A8/
解决方法2：先将key和value序列化成byte[]，再用redisConnection.setNX(...)，参考CommonRedisClient.add();
