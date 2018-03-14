package com.cheese.radio.ui.media.anchors;

import com.cheese.radio.util.CheeseApiParams;
import com.cheese.radio.util.MD5;
import com.cheese.radio.util.MyBaseUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by 29283 on 2018/3/11.
 */

public class AnchorsParams extends CheeseApiParams{
    private String uuid;
    private String timestamp;
    private String sign;

    public String getUuid() {
        return uuid= MyBaseUtil.getMacAddress();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTimestamp() {
            if (timestamp==null)
                timestamp=MyBaseUtil.getTimestamp();
        return timestamp;
    }

    public void setTimest(String timestamp) {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }

//    public String getSign() {
////        String head="timestamp"+getTimestamp()+"uuid"+getUuid()+MyBaseUtil.getRandoms(8);
//        String head="timestamp"+"="+getTimestamp()+MyBaseUtil.getRandoms(8);
//        String tail= MD5.getInstance().getMD5(head);
//        return sign=head+tail;
//    }

    public String getSign(){
        if(sign!=null) return sign;
       HashMap<String,String> argParams=new HashMap<>() ;
        argParams.put("uuid",getUuid());
        argParams.put("timestamp",getTimestamp());
        argParams.put("phone","17857025659");
        String randomString =MyBaseUtil.getRandoms(8);
        StringBuilder sb=new StringBuilder();
        int index =0;
        String[] paramArray=new String[argParams.size()];
        Iterator<HashMap.Entry<String,String>> iterator=argParams.entrySet().iterator();
        while (iterator.hasNext()){
            sb.setLength(0);
            HashMap.Entry<String,String> en =iterator.next();
            paramArray[index++] =sb.append(en.getKey()).append("=").append(en.getValue()).toString();
        }

        Arrays.sort(paramArray);
        sb.setLength(0);
        for(int i=0;i<paramArray.length;++i){
            sb.append(paramArray[i]).append("&");
        }
        sb.setLength(sb.length()-1);
        sb.append(randomString);
        return sign=randomString+MD5.getInstance().getMD5(sb.toString()).toUpperCase();
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return getSign();
    }



}
