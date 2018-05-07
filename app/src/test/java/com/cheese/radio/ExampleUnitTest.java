package com.cheese.radio;

import com.cheese.radio.util.MyBaseUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void test() {
        HashMap<String, String> map = new HashMap<>();
        map.put("startIndex", "0");
        map.put("method", "search");
        map.put("title", "一");
        map.put("uuid", "123");
        map.put("maxCount", "8");
        map.put("timestamp", "111111111111");


        System.out.println(MyBaseUtil.getSign(map));
        System.out.println("Qyw6tX4f73D8881DFEA62DF3A8C921ADFE3B441A");
    }

    @Test
    public void test2() {
        Gson gson = new Gson();
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("openid", "openid");
        infoMap.put("unionid", "openid");
        infoMap.put("scope", "openid");
        infoMap.put(" expires_in", "openid");
        infoMap.put("refresh_token", "openid");
        infoMap.put("access_token", "openid");
        System.out.println(gson.toJson(infoMap));
    }

    @Test
    public void test3() throws Exception {
//        String jsonString = "    {  \n" +
//                "     \"persons\":[  \n" +
//                "            {  \n" +
//                "                \"name\":\"Bob\",  \n" +
//                "                \"age\":14,  \n" +
//                "                \"married\":false,  \n" +
//                "                \"salary\":null  \n" +
//                "            },  \n" +
//                "            {  \n" +
//                "                \"name\":\"Kate\",  \n" +
//                "                \"age\":26,  \n" +
//                "                \"married\":true,  \n" +
//                "                \"salary\":8888.88  \n" +
//                "            }  \n" +
//                "        ],  \n" +
//                "     \"team\":\"Android\"  \n" +
//                "    }  ";
        String jsonString = "{array:[1,2,3,4,5]}";
        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
        JsonEntity jsonEntity = new JsonEntity();
        getNode(jsonEntity, jsonObject);

    }

    //遍历获得每个成员
    private void getNode(Object entity, JsonObject json) throws Exception {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class fieldClazz = field.getType();
            String name = field.getName();
            String nameMethodTail = name.substring(0, 1).toUpperCase() + name.substring(1);//对象名
            Method getFieldEntity = entity.getClass().getMethod("get" + nameMethodTail);//取值
//            System.out.println(nameMethodTail);

            if (fieldClazz.getName().equals("java.util.List")) {
                System.out.println(fieldClazz.getGenericSuperclass());
                //list

            }
            if (fieldClazz.getComponentType() != null && fieldClazz.getComponentType().getName() != null) {
                //数组
                JsonArray ja = json.getAsJsonArray(name);
                int jaLength = ja.size();
                System.out.println(fieldClazz.getComponentType().getName());
                int[] obj = new int[jaLength];
                for (int i = 0; i <jaLength;i++){
                    obj[i]=ja.get(i).getAsInt();
                    System.out.println(obj[i]);
                }

                if (fieldClazz.isArray()){
                    Method setFieldEntity = entity.getClass().getMethod("set" + nameMethodTail,  fieldClazz);//赋值
                    setFieldEntity.invoke(entity,new Object[]{obj});
                    System.out.println(((JsonEntity) entity).getArray());
                }

            }

//            if (fieldClazz.getComponentType()!=null) {
//                Object o=getObjcet(field,entity);
//                setArray(o, json.getAsJsonArray("array"));
//            }

        }
    }
    private Class<?> changeType(Class clazz){
        if(clazz.getName().equals("int")) return Integer.class;
        return null;
    }
    private Object getObjcet(Field field, Object o) throws Exception {
        String name = field.getName();
        String nameMethodTail = name.substring(0, 1).toUpperCase() + name.substring(1);
        Method method = o.getClass().getMethod("get" + nameMethodTail);
        return method.invoke(o);
    }

    private boolean setArray(Object array, JsonArray jsonArray) throws Exception {
        int jsonArraySize = jsonArray.size();
        int count = 0;

//        Method method = JsonEntity.class.getMethod("setArray", field.getType());
//        Object objce = Array.newInstance(array.getType(), jsonArraySize);
//        for (JsonElement element : jsonArray) {
//            Array.set(objce, count++, element);
//        }
//        method.invoke(array, objce);
        return true;
    }

    private void listFields(List list, JsonArray array) {
    }

    @Test
    public void test4() throws Exception {


        int[] names =new int[]{1,2,3};
        Method sayHello = A.class.getDeclaredMethod("sayHello", names.getClass());
//        sayHello.setAcess(true);
        sayHello.invoke(new A(),new Object[]{ names});
    }
    class A{
        public void sayHello(int[] names){
            //...
            System.out.println("sayHello invoked");
        }
    }
}