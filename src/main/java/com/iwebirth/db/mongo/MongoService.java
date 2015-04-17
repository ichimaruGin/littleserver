package com.iwebirth.db.mongo;

import com.iwebirth.util.StaticMap;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YY_410 on 2015/4/16.
 */
public class MongoService {

    MongoClient mongoClient = MongoManager.getMongoClient();
    DB db = mongoClient.getDB("littleserver");
    MongoDao dao = new MongoDao();

    public void insertObject(Object object){
        String className = object.getClass().getSimpleName();
        //String collName = StaticMap.typeMongoCollMap.get(className);
        String collName = className.toLowerCase();
        System.out.println(className+"--->"+collName);
        Map<String,Object> map = new HashMap();
        Field[] fields = object.getClass().getFields();
        for(Field field : fields){
            field.setAccessible(true);
            String name = field.getName();
            try {
                map.put(name,field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        dao.insertMap(db.getCollection(collName),map);
    }

}
