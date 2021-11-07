package com.example.sunday;

import android.provider.BaseColumns;

public class TableData {
    public TableData(){}

    public static abstract class TableInfo implements BaseColumns{
        public static final String PRODUCT_ID = "product_id";
        public static final String PRODUCT_NUMBER = "product_number";
        public  static final String COUNT = "count";
        public static final String DATABASE_NAME = "BEST_PRICE_CART255.db";
        public static final String TABLE_NAME= "PRO_INFO";
    }

}
