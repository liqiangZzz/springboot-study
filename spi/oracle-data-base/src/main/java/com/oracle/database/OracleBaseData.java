package com.oracle.database;

import com.lq.database.BaseData;

/**
 * @ClassName OracleConnection
 * @Description
 * @Author liqiang
 * @Date 2025/11/5 17:58
 */
public class OracleBaseData implements BaseData {
    @Override
    public String baseURL() {
        System.out.println("oracle ....");
        return null;
    }
}
