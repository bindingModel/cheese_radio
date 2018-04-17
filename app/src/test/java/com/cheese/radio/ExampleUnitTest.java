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
        map.put("startIndex","0");
        map.put("method","search");
        map.put("title","1");
        map.put("uuid","02%3A00%3A00%3A00%3A00%3A00");
        map.put("maxCount","8");
        map.put("timestamp","1523966881780");



        System.out.println(MyBaseUtil.getSign(map));
        System.out.println("kGZRx0fM65E5EA3D679D89E7B927440FB248B2AF");
    }
}