/*
package com.cheese.radio.base.rxjava;

import com.cheese.radio.base.esp.IEsptouchResult;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
*/

/**
 * Created by arvin on 2018/1/9.
 */

/*public class ConfigEspTransform  implements ObservableTransformer<IEsptouchResult,Integer> {


    @Override
    public ObservableSource<Integer> apply(Observable<IEsptouchResult> upstream) {
        return upstream.concatMap(esp ->
            Observable.create(e -> {
                int i = 0;
                if(!esp.isSuc())e.onNext(-1);
                while (esp.isSuc()&&i<7){
                    e.onNext(++i);
                    Thread.sleep(2000);
                }

            })
        );
    }*/

//    public void onNext(IEsptouchResult result) {
//        if (!result.isSuc()) {
//            BaseUtil.toast("网关发现失败");
//            dialog.dismiss();
//        } else {
//            infoAgree.setLogic_id(WifiUtil.getIpAddress());
//            SocketUtil.sendSocketData(this, infoAgree, (code, agree, t) -> {
//                TimeUtil.getInstance().remove(this);
//                byte[] data = agree.getAgree().getLight_data();
//                if (code == SocketRespond.timeout) {
//                    if (entities.isEmpty()) {
//
//                    } else
//                        builder.items(entities)
//                                .itemsCallback((dialog, itemView, position, text) -> {
//                                    GateWayEntity gateWayEntity = entities.get(position);
//                                    if (IkeApplication.getDaoUtils().queryGateWay(IkeApplication.getUser().getId()).contains(gateWayEntity)) {
//                                        BaseUtil.toast("网关已存在");
//                                    } else {
//                                        infoAgree.getAgree().setCmd(0x01);
//                                        infoAgree.getAgree().setWayData(b(IkeApplication.getUser().getId()));
//                                        SocketUtil.sendSocketData(this, infoAgree, (code1, agree1, t1) -> {
//                                            if (code1 != success) return 0;
//                                            IkeApplication.getDaoUtils().insertEntity(gateWayEntity);
//                                            return 1;
//                                        }, 4);
//                                    }
//                                }).show();
//                } else if (AgreeUtil.tIntLow(data) == 1) {
//                    GateWayEntity entity = new GateWayEntity();
//                    entity.setMac_address(AgreeUtil.intToString(infoAgree.getMac()));
//                    entity.setUser_id(IkeApplication.getUser().getId());
//                    entity.setSsid_name(wifiEntity.getSsid());
//                    entities.add(entity);
//                }
//                return 1;
//            });
//        }
//    }

//}
/**
 * .compose(new ErrorTransform<>(skipToLogin))
 .flatMap(entity -> Observable.create(
 subscriber -> {
 try {
 Timber.i("code:%1d", entity.getCode());
 switch (entity.getCode()) {
 case 0:if (entity.getData() != null) subscriber.onNext(entity.getData());
 break;
 default:
 throw new ApiException(entity.getMsg());
 }
 } catch (Exception e) {
 subscriber.onError(e);
 } finally {
 subscriber.onComplete();
 }
 })
 );*/