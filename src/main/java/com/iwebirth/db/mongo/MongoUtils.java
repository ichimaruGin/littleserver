package com.iwebirth.db.mongo;

import com.mongodb.DBObject;
import com.mongodb.util.JSON;

/**
 * Created by YY_410 on 2015/4/17.
 */
public class MongoUtils {

    /**
     * 原生Mongo语句生成查询条件
     * **/
    public static DBObject getCondiByMongosql(String msql){
        return (DBObject) JSON.parse(msql);
    }

    /**
     * DBObject条件转换成原生语句
     * **/
    public String getMsqlByCondi(DBObject query){
        return JSON.serialize(query);
    }

}
