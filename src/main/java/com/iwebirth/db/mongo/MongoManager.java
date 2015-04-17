package com.iwebirth.db.mongo;

import com.iwebirth.interact.model.*;
import com.mongodb.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YY_410 on 2015/4/16.
 */
public class MongoManager {

    static ServerAddress severAddress;
    static Config config;
    static MongoCredential credential;
    /**
     * 通过类与colletction名字对应
     * key为类的名字，value为coll的名字(类的名字的小写)
     * **/

    static{
        config = ConfigFactory.load("mongodb");
        String user = config.getString("mongo.user");
        String dbName = config.getString("mongo.dbName");
        String password = config.getString("mongo.password");
        String host = config.getString("mongo.host");
        int port = config.getInt("mongo.port");
        credential = MongoCredential.createCredential(user, dbName, password.toCharArray());
        try {
            severAddress = new ServerAddress(host,port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    /**
     * @return 返回一个MongoClinet对象
     * **/
    public static MongoClient getMongoClient(){
        return new  MongoClient(severAddress, Arrays.asList(credential));
    }
    /**
     * 关闭一个连接
     * **/
    public static void disConnect(MongoClient mongoClient){
        if(mongoClient != null){
            mongoClient.close();
        }
    }


    public static WriteResult insertDoc(DBObject obj, DBCollection coll){
        return coll.insert(obj);
    }
}
