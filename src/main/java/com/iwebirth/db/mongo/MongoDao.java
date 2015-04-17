package com.iwebirth.db.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import java.util.*;

/**
 * Created by YY_410 on 2015/4/16.
 */
public class MongoDao {
    /**
     * @param coll collection
     * @param maps 传入map
     * 单层结构的doc
     * **/
    public void insertMap(DBCollection coll, Map... maps){
        List<DBObject> list = new ArrayList<DBObject>();
        for(Map map : maps){
            list.add(new BasicDBObject(map));
        }
        coll.insert(list);
    }

    /**
     * 返回coll中所有doc
     * **/
    public List<DBObject> queryAllDocs(DBCollection coll){
        DBCursor cursor = coll.find();
        List<DBObject> list = new ArrayList<DBObject>();
        try{
            while(cursor.hasNext()){
                list.add(cursor.next());
            }
        }finally{
            cursor.close();
        }
        return list;
    }

    /**
     * 条件查询
     * @param conditions 按照key-value的形式将查询条件传入
     e.g. db.getCollection('test').find({member:'student',age:{$gt:20}, $and:[{height:{$gt:170}},{height:{$lt:190}}]})
     * **/
    public List<DBObject> queryDocsByConditions(DBCollection coll, Map<String, Object> conditions){
        BasicDBObject query = new BasicDBObject();
        for(String key : conditions.keySet()){
            Object value = conditions.get(key);
            if(value instanceof Map.Entry){
                //conditions: time > 10000   find({time:{$gt:10000}})
                //可以直接用Map代替
                Map.Entry entry = (Map.Entry)value;
                BasicDBObject squery = new BasicDBObject((String)entry.getKey(),entry.getValue());
                query.append(key,squery);
            } else if(value instanceof Map){
                //conditions: time > 10000 && time <110000  find({time:{$gt:10000},time:{$lt:12000}})
                Map map = (Map)value;
                BasicDBObject squery = new BasicDBObject();
                for(Object skey : map.keySet()){
                    squery.append((String)skey,map.get(skey));
                }
                query.append(key,squery);
            } else{
                query.append(key,conditions.get(key));
            }
        }
        System.out.println(JSON.serialize(query));
        DBCursor cursor = coll.find(query);
        List<DBObject> list = new ArrayList<DBObject>();
        try{
            while(cursor.hasNext()){
                list.add(cursor.next());
            }
        }finally {
            cursor.close();
        }
        return  list;
    }


 }
