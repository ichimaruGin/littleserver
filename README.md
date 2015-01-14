##毕设server端

#Mina

* 写了一个简单的server端,有ip过滤(目前保持对所有ip的通行allowed;可以在spring-base.xml中配置legalListFilter的regex参数值来限制)
* server端的协议解码器自定义，目前还没有加入与业务逻辑相关的处理
* 写了一个mina客户端原型，在test目录下面