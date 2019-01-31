package com.quran.audio.worship;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.quran.audio.R;
import com.quran.audio.base.control.ControlFragment;
import com.quran.audio.bean.IslamicDateBean;
import com.quran.audio.customView.CustomCalendar;
import java.util.List;

public class WorshipFragment extends ControlFragment<WorshipPresenter> implements IWorship {
    private CustomCalendar cal;
    private Button btSign;

    @Override
    protected void onViewInit(View contentView) {
        super.onViewInit(contentView);
        cal = (CustomCalendar) findViewById(R.id.cal);
        btSign = (Button) findViewById(R.id.bt_sign);
    }

    @Override
    protected void setOnListener() {
        btSign.setOnClickListener(mPresenter);
        cal.setOnCalendarClickListener(mPresenter);
    }

    @Override
    public int getContentViewResource() {
        return R.layout.fragment_worship;
    }
    @Override
    public WorshipPresenter createPresenter() {
        return new WorshipPresenter(this);
    }


    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void setData(List<IslamicDateBean> list, List<String> signDates) {
        cal.setIslamicDateAndSignDate(list, signDates);
    }

    @Override
    public void monthChange(int change, List<IslamicDateBean> list) {
        cal.monthChange(change, list);
    }

    @Override
    public void setSignDates(List<String> list) {
        cal.setSignDates(list);
    }

    @Override
    public void setSignEnable(boolean enable) {
        if (enable)
            btSign.setVisibility(View.VISIBLE);
        else
            btSign.setVisibility(View.INVISIBLE);
    }
}

//************指南针与gps (未完成 暂停)************
//
//<com.quran.audio.customView.DirectionView
//        android:id="@+id/direction"
//        android:background="#808080"
//        android:layout_below="@+id/bt_sign"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        />
//
//
//private final class SensorListener implements SensorEventListener {
//    private float predegree = 0;
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        /**
//         *  values[0]: x-axis 方向加速度
//         　  values[1]: y-axis 方向加速度
//         　　values[2]: z-axis 方向加速度
//         */
//        float degree = event.values[0];// 存放了方向值
//        predegree = -degree;
//        btSign.setText(((int) degree) + "°");
////         mCompassDegreeTxt.setText(""+((int)degree)+"°");
////         mCompassDirectionTxt.setText(formatPredegree(degree));
//        directionView.rotate = predegree;
////            if (location != null) {
////                directionView.isMecca = true;
////                directionView.meccaRotate = meccaRotate;
////            }
//        directionView.postInvalidate();
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//    }
//}
//
//    SensorManager manager;
//    SensorListener listener;
//
//    @Override
//    protected void onResume() {
//        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        listener = new SensorListener();
//        /**
//         *  获取方向传感器
//         *  通过SensorManager对象获取相应的Sensor类型的对象
//         */
//        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION); //应用在前台时候注册监听器
//        Sensor sensor1 = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //应用在前台时候注册监听器
//        Sensor sensor2 = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD); //应用在前台时候注册监听器
//        Log.d(TAG, "onResume:  ==== " + (sensor == null) + (sensor1 == null)+(sensor2 == null));
//        manager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME);
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() { //应用不在前台时候销毁掉监听器
//        manager.unregisterListener(listener);
//        super.onPause();
//    }
//
//
//    LocationManager locationManager;
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
//                    Log.d(TAG, "onRequestPermissionsResult: 同意");
//                    getLngAndLat();
//                }
//                break;
//        }
//
//    }
//    Location location;
//    float meccaRotate;
//    public String getLngAndLat() {
//        double latitude = 0.0;
//        double longitude = 0.0;
//
////        mOnLocationListener = onLocationResultListener;
//
//        String locationProvider = null;
//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //获取所有可用的位置提供器
//        List<String> providers = locationManager.getProviders(true);
//
//        if (providers.contains(LocationManager.GPS_PROVIDER)) {
//            Log.d(TAG, "getLngAndLat: gps");
//            //如果是GPS
//            locationProvider = LocationManager.GPS_PROVIDER;
//        }else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//            Log.d(TAG, "getLngAndLat: network");
//            //如果是Network
//            locationProvider = LocationManager.NETWORK_PROVIDER;
//        } else {
//            Intent i = new Intent();
//            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//            startActivity(i);
//            return null;
//        }
//
//        //获取Location
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            Log.d(TAG, "getLngAndLat:   " + (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED));
//            Log.d(TAG, "getLngAndLat:   " + (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED));
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            //请求权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//            return null;
//        }
//        location = locationManager.getLastKnownLocation(locationProvider);
//        if (location != null) {
//            //不为空,显示地理位置经纬度
////            if (mOnLocationListener != null) {
////                mOnLocationListener.onLocationResult(location);
////            }
//            // 麦加 (Mecca) ，经纬度坐标北纬 21°26' (21.433度), 东经 39°49'(39.816度)
//            Log.d(TAG, "onLocationChanged:  === 位置  " + location.getLongitude() + "   " + location.getLatitude());
//            Toast.makeText(this,"位置 ==  "+ location.getLongitude() + "   " + location.getLatitude(),Toast.LENGTH_LONG).show();
//            meccaRotate = getAngle(location.getLongitude(), location.getLatitude());
////            meccaRotate = getAngle(17.805770,4.853298);
//            Log.d(TAG, "getLngAndLat:  === 位置 M  " + meccaRotate);
//        }
//        //监视地理位置变化
////        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
//        return null;
//    }
//
//    public float getAngle(double longitude,double latitude){
//        double MECCA_LONGITUDE = 39.816, MECCA_LATITUDE = 21.433;
//        double angle = Math.toDegrees(Math.atan2(MECCA_LATITUDE -latitude,MECCA_LONGITUDE -longitude)); //纬度差，经度差
//        if (angle <= 90) {
//            angle = 90 - angle;
//        } else {
//            angle = 360 - (angle - 90);
//        }
//        return  (float) angle;
//    }
//    public LocationListener locationListener = new LocationListener() {
//
//        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Log.d(TAG, "onStatusChanged: ");
//        }
//
//        // Provider被enable时触发此函数，比如GPS被打开
//        @Override
//        public void onProviderEnabled(String provider) {
//            Log.d(TAG, "onProviderEnabled: ");
//        }
//
//        // Provider被disable时触发此函数，比如GPS被关闭
//        @Override
//        public void onProviderDisabled(String provider) {
//            Log.d(TAG, "onProviderDisabled: ");
//        }
//
//        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
//        @Override
//        public void onLocationChanged(Location location) {
////            if (mOnLocationListener != null) {
////                mOnLocationListener.OnLocationChange(location);
////            }
//            Log.d(TAG, "onLocationChanged:  === 位置变化  " + location.getLongitude() + "   " + location.getLatitude());
//            Toast.makeText(WorshipActivity.this,"位置变化 ==  "+ location.getLongitude() + "   " + location.getLatitude(),Toast.LENGTH_LONG).show();
//        }
//    };