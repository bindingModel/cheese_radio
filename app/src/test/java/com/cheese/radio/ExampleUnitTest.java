package com.cheese.radio;

import com.cheese.radio.util.MyBaseUtil;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void test(){
        HashMap<String,String> map = new HashMap<>();

        map.put("method","classPlace");
        map.put("uuid","02:00:00:00:00:00");
        map.put("timestamp","1524372023341");
        map.put("token","eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMSJ9.8x7Q4ItmsfcRwZ21iHvAKVnGPi594bqL0KCqF7C10FOTaPsKwQkhHPdbtXY-OQtCOpb7XmRmAZpWfK7AiW0YMw&uuid=02%3A00%3A00%3A00%3A00%3A00");

        System.out.println(MyBaseUtil.getSign(map));
    }
}