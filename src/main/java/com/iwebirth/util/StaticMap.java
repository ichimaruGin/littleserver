package com.iwebirth.util;

import com.iwebirth.interact.model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YY_410 on 2015/4/17.
 */
public class StaticMap {

    /**
     * mongo
     * model.class.getSimpleName() --- collection
     * 将对象的名字与其在mongo中collection的名字对应起来，每个类对应一个collection
     * **/
    public static Map<String,String> typeMongoCollMap;

    /**
     * redis
     * model.class.getSimpleName() --- redis-key
     * 将对象的名字与其在redis中的key对应起来(实际的key还要在后面增加“-”和终端号tid)
     * **/
    public static Map<String,String> typeRediskeyMap;

    public static final String REDIS_ALIVE_TERMINAL_MAP_KEY = "TAliveId"; //在线终端数量。field对应tid，value对应time

    static {
        typeMongoCollMap = new HashMap<String, String>();
        typeMongoCollMap.put(IntimeInfo.class.getSimpleName(), IntimeInfo.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalAlarm.class.getSimpleName(), TerminalAlarm.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalError.class.getSimpleName(), TerminalError.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalLocationInfo.class.getSimpleName(), TerminalLocationInfo.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalOilInfo.class.getSimpleName(), TerminalOilInfo.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalRunInfo.class.getSimpleName(), TerminalRunInfo.class.getSimpleName().toLowerCase());
        typeMongoCollMap.put(TerminalOtherInfo.class.getSimpleName(), TerminalOtherInfo.class.getSimpleName().toLowerCase());

        typeRediskeyMap = new HashMap<String, String>();
        typeRediskeyMap.put(IntimeInfo.class.getSimpleName(), "TIntimeInfo");
        typeRediskeyMap.put(TerminalAlarm.class.getSimpleName(), "TAlarm");
        typeRediskeyMap.put(TerminalError.class.getSimpleName(), "TError");
        typeRediskeyMap.put(TerminalLocationInfo.class.getSimpleName(), "TLocation");
        typeRediskeyMap.put(TerminalOilInfo.class.getSimpleName(), "TOil");
        typeRediskeyMap.put(TerminalRunInfo.class.getSimpleName(), "TRunInfo");
        typeRediskeyMap.put(TerminalOtherInfo.class.getSimpleName(), "TOther");

    }
}
