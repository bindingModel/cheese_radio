package com.cheese.radio.ui;

/**
 * Created by pc on 2017/8/12.
 */

public interface Constant {

    String socket_address = "106.15.34.29";
    //  String socket_address = "106.15.34.29";
//    String socket_address ="192.168.6.125";
//    String socket_address ="183.136.193.218";
    int socket_port = 8088;


    String room = "room";
    String socketRespond = "socketRespond";
    String id = "id";
    String wifi_change = "wifi_change";
    String logout = "logout";
    String login = "login";
//    String entity = "entity";
    String sight = "sight";

    String roomAdd = "roomAdd";
    String position = "position";
    String key = "key";
    String type = "type";
    long Interval = 150;
    int photograph = 20;
    int imageAlbum = 10;
    String user = "user";
    String gateWay = "gateWay";
    String device = "device";
    String path = "path";
    String bundle = "bundle";
    String authorId="authorId";
    String CONTENT_LIST="CONTENT_LIST";
    String CATEGORY_LIST="CATEGORY_LIST";
    String AUTHOR_LIST="AUTHOR_LIST";
    String AUTHOR_INFO="AUTHOR_INFO";
    String GROUP_INFO="GROUP_INFO";
    String PLAY="PLAY";
    String location="location";
}

//    static Drawable getMipmap(String name,int defMipmap){
//        Integer mipmap = Image.nameImage.get(name);
//        if(mipmap== null)mipmap = defMipmap;
//        return App.getDrawable(mipmap);
//    }

//    static HashMap<String, Integer> getNameImage() {
//        return Image.nameImage;
//    }

//    private int getNavigationHeight() {
//        int height = -1;
//        try {
//            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
//            Object object = clazz.newInstance();
//            String heightStr = clazz.getField("navigation_bar_height").get(object).toString();
//            height = Integer.parseInt(heightStr);
//            height = getResources().getDimensionPixelSize(height);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//        return height;
//    }
//        private static final HashMap<String,Integer> nameImage = new HashMap<>();

//            nameImage.put("sight_icon_comfortably", R.mipmap.sight_icon_comfortably);
//            nameImage.put("sight_icon_bath", R.mipmap.sight_icon_bath);
//            nameImage.put("sight_icon_dinner", R.mipmap.sight_icon_dinner);
//            nameImage.put("sight_icon_fitness", R.mipmap.sight_icon_fitness);
//            nameImage.put("sight_icon_fun", R.mipmap.sight_icon_fun);
//            nameImage.put("sight_icon_home", R.mipmap.sight_icon_home);
//            nameImage.put("sight_icon_leavehome", R.mipmap.sight_icon_leavehome);
//            nameImage.put("sight_icon_light", R.mipmap.sight_icon_light);
//            nameImage.put("sight_icon_meeting", R.mipmap.sight_icon_meeting);
//            nameImage.put("sight_icon_meet", R.mipmap.sight_icon_meet);
//            nameImage.put("sight_icon_movie", R.mipmap.sight_icon_movie);
//            nameImage.put("sight_icon_music", R.mipmap.sight_icon_music);
//            nameImage.put("sight_icon_off", R.mipmap.sight_icon_off);
//            nameImage.put("sight_icon_read", R.mipmap.sight_icon_read);
//            nameImage.put("sight_icon_romantic", R.mipmap.sight_icon_romantic);
//            nameImage.put("sight_icon_sleep", R.mipmap.sight_icon_sleep);
//            nameImage.put("sight_icon_soft", R.mipmap.sight_icon_soft);
//            nameImage.put("sight_icon_wake", R.mipmap.sight_icon_wake);
//            nameImage.put("sight_icon_warm", R.mipmap.sight_icon_warm);
//            nameImage.put("sight_icon_wash", R.mipmap.sight_icon_wash);
//            nameImage.put("sight_icon_yelight", R.mipmap.sight_icon_yelight);
//
//
//            nameImage.put("device_400", R.mipmap.device_400);
//            nameImage.put("device_401", R.mipmap.device_401);
//            nameImage.put("device_410", R.mipmap.device_410);
//            nameImage.put("device_411", R.mipmap.device_411);
//            nameImage.put("device_412", R.mipmap.device_412);
//            nameImage.put("device_413", R.mipmap.device_413);
//            nameImage.put("device_414", R.mipmap.device_414);
//            nameImage.put("device_415", R.mipmap.device_415);
//            nameImage.put("device_416", R.mipmap.device_416);
//            nameImage.put("device_417", R.mipmap.device_417);
//            nameImage.put("device_icon_camera",R.mipmap.device_icon_camera);
//            nameImage.put("device_icon_curtain",R.mipmap.device_icon_curtain);
//            nameImage.put("device_icon_envrevies",R.mipmap.device_icon_envrevies);
//            nameImage.put("device_icon_light",R.mipmap.device_icon_light);
//            nameImage.put("device_icon_lock",R.mipmap.device_icon_lock);
//            nameImage.put("device_icon_music",R.mipmap.device_icon_music);
//            nameImage.put("device_icon_outlet",R.mipmap.device_icon_outlet);
//            nameImage.put("device_icon_relay",R.mipmap.device_icon_relay);
//            nameImage.put("device_icon_security",R.mipmap.device_icon_security);
//            nameImage.put("device_icon_sensor",R.mipmap.device_icon_sensor);
//            nameImage.put("device_icon_socket",R.mipmap.device_icon_socket);
//            nameImage.put("device_icon_xcoretag",R.mipmap.device_icon_xcoretag);
//            nameImage.put("device_icon_gateway",R.mipmap.device_icon_gateway);
