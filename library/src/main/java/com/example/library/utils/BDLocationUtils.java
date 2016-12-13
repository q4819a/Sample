package com.example.library.utils;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.library.base.LibBaseApp;
import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2016/11/25.
 * <p/>
 * 百度定位工具类
 * 使用方法：通过getInstance()获取到实体类，直接调用locationOnce()定位（不同的参数可实现单次定位和多次定位）
 * 多次定位结束时调用stopLocation（）；
 */
public class BDLocationUtils {

    private static BDLocationUtils instance;
    public LocationClient mLocationClient = null;
    private MyLocationListener mMyLocationListener = null;
    private LocationFinishListener mLocationFinishListener;

    /**
     * Context需要时全进程有效的Context,推荐用getApplicationConext获取全进程有效的Context。
     */
    public static BDLocationUtils getInstance() {
        if (instance == null) {
            instance = new BDLocationUtils();
        }
        return instance;
    }

    private BDLocationUtils() {

        if (mLocationClient == null) {
            mLocationClient = new LocationClient(LibBaseApp.app);
        }
        if (mMyLocationListener == null) {
            mMyLocationListener = new MyLocationListener();
        }
        mLocationClient.registerLocationListener(mMyLocationListener);
    }


    /**
     * 定位一次
     *
     * @param listener 定位完成回调
     */
    public void locationOnce(LocationFinishListener listener) {
        Logger.i("开始定位");
        initLocation();
        mLocationClient.start();
        setLocationFinish(listener);
    }

    /**
     * 重复定位
     *
     * @param span     重复间隔，间隔需要大于等于1000ms才是有效的
     * @param openGps  是否使用gps定位
     * @param listener 定位完成回调
     */
    public void locationRepetition(int span, boolean openGps, LocationFinishListener listener) {
        initLocation(span, openGps);
        mLocationClient.start();
        setLocationFinish(listener);
    }

    /**
     * 停止定位,停止重复定位时候使用
     */
    public void stopLocation() {
        mLocationClient.stop();
        mLocationClient = null;
        mMyLocationListener = null;
    }


    /***
     * 默认定位参数，只定位一次
     * 高精度定位，返回百度坐标，仅定位一次，使用gps，定位停止后杀死服务
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
    }

    /**
     * 自定义定位参数
     *
     * @param span    默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
     * @param openGps 设置是否使用gps
     */
    private void initLocation(int span, boolean openGps) {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(openGps);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
        // .getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClient.setLocOption(option);
    }


    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(com.baidu.location.BDLocation bdLocation) {
            Logger.i("百度定位返回错误码：" + bdLocation.getLocType());
            LibBaseApp.city = bdLocation.getCity();
            LibBaseApp.address = bdLocation.getAddrStr();
            LibBaseApp.latitude = bdLocation.getLatitude();
            LibBaseApp.longitude = bdLocation.getLongitude();


            Logger.i("百度定位结果：" + LibBaseApp.address);
            mLocationFinishListener.locationFinish(bdLocation);
        }

    }

    private void setLocationFinish(LocationFinishListener listener) {
        this.mLocationFinishListener = listener;
    }

    public interface LocationFinishListener {
        public abstract void locationFinish(BDLocation bdLocation);
    }

}
