package com.iwebirth.app.db;

import com.iwebirth.db.mongo.MongoDao;
import com.iwebirth.db.mongo.MongoManager;
import com.iwebirth.db.mongo.MongoService;
import com.iwebirth.db.mongo.MongoUtils;
import com.iwebirth.interact.model.IntimeInfo;
import com.iwebirth.interact.model.TerminalAlarm;
import com.iwebirth.interact.model.TerminalLocationInfo;
import com.iwebirth.interact.model.TerminalRunInfo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YY_410 on 2015/4/16.
 */
public class MongoTests {

    MongoService mongoService = new MongoService();

    @Test
    public void connectTest() {
        MongoClient mongoClient = MongoManager.getMongoClient();
        DB db = mongoClient.getDB("littleserver");
        BasicDBObject obj = new BasicDBObject()
                .append("name", "mongo")
                .append("age", 15)
                .append("parent", new BasicDBObject().append("papa", "mongopapa").append("mama", "mongomama"));
        MongoManager.insertDoc(obj, db.getCollection("test"));
        MongoManager.disConnect(mongoClient);
    }

    @Test
    public void dasd() {
        System.out.println(IntimeInfo.class.getSimpleName());
        System.out.println(TerminalAlarm.class.getSimpleName());
    }

    @Test
    public void insertObject() {
        TerminalLocationInfo location = new TerminalLocationInfo("tid01232", "123.2342", "23.1231");
        TerminalRunInfo runInfo = new TerminalRunInfo("tid124", 40, 80, 700);
        mongoService.insertObject(location);
        mongoService.insertObject(runInfo);
    }

    //MongoDao TESTS
    MongoDao mongoDao = new MongoDao();

    @Test
    public void queryAll() {
        MongoClient mongoClient = MongoManager.getMongoClient();
        DB db = mongoClient.getDB("littleserver");
        List<DBObject> res = mongoDao.queryAllDocs(db.getCollection(TerminalRunInfo.class.getSimpleName().toLowerCase()));
        for (DBObject object : res) {
            for (String key : object.keySet()) {
                System.out.println(key + " " + object.get(key));
            }
            System.out.println("next-----");
        }
        MongoManager.disConnect(mongoClient);
    }

    /**
     * 测试用例中的查询还原成mongo语句如下
     * { "speed" : { "$gt" : 50 , "$lt" : 70} , "tId" : "TID8151525"}
     * {tId:'TID8151525',$and:[{speed:{$gt:50}},{speed:{$lt:70}}]}
     * *
     */
    @Test
    public void queryCondition() {
        Map condiMap = new HashMap();
        condiMap.put("tId", "TID8151525");
        Map smap = new HashMap();
        smap.put("$gt", 50);
        smap.put("$lt", 70);
        condiMap.put("speed", smap);
        MongoClient mongoClient = MongoManager.getMongoClient();
        DB db = mongoClient.getDB("littleserver");
        List<DBObject> res = mongoDao.queryDocsByConditions(db.getCollection(TerminalRunInfo.class.getSimpleName().toLowerCase()), condiMap);
        int count = 0;
        for (DBObject object : res) {
            for (String key : object.keySet()) {
                System.out.println(key + " " + object.get(key));
            }
            System.out.println("next-----");
            count++;
        }
        System.out.println("数量为:" + count);
        MongoManager.disConnect(mongoClient);
    }

    @Test
    public void createCondiBymsql() {
        String msql = "{member:'student',age:{$gt:20}, $and:[{height:{$gt:170}},{height:{$lt:190}}]}";
        MongoUtils.getCondiByMongosql(msql);
    }
}
