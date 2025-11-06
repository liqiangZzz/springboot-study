package com.mysql.data;

import com.lq.database.BaseData;

/**
 * @ClassName MySQLBaseData
 * @Description
 * @Author liqiang
 * @Date 2025/11/6 09:08
 */
public class MySQLBaseData implements BaseData {
    @Override
    public String baseURL() {
        System.out.println("mysql .... ");
        return null;
    }
}
