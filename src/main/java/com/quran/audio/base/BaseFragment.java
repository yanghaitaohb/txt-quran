package com.quran.audio.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG = this.getClass().getSimpleName();


    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        onViewCreateBefore();
        onSetContentView();
        onViewCreated();

        setOnListener();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return view;
    }


    /**
     * called by {@link # onCreate}
     * 在 setContentView 方法前调用
     */
    protected void onViewCreateBefore() { }

    /**
     * 初始化ContentView后调用，可以进行findViewById等操作
     */
    protected void onViewInit(View contentView) {}

    /**
     * called by {@link # onCreate}
     * 在 setContentView 方法后调用
     */
    protected void onViewCreated() {}

    /**
     *  setContentView
     */
    protected void onSetContentView() {
        view = View.inflate(getActivity(), getContentViewResource(), null);
        onViewInit(view);
    }

    /**
     * called by {@link # onCreate}
     * 进行设置监听
     */
    protected void setOnListener() {}


    /**
     * 得到 ContentView 的 Resource
     * @return eg R.layout.main_layout
     */
    public abstract int getContentViewResource();


    public void addFragment(Fragment fragment) {
        if (getView() != null && getView().getParent() != null) {
            ViewGroup parentView = (ViewGroup) getView().getParent();
            getFragmentManager().beginTransaction()
                    .add(parentView.getId(), fragment)
                    .commitAllowingStateLoss();
        }
    }

    public void replaceFragment(Fragment fragment) {
        if (getView() != null && getView().getParent() != null) {
            ViewGroup parentView = (ViewGroup) getView().getParent();
            getFragmentManager().beginTransaction()
                    .replace(parentView.getId(), fragment)
                    .commitAllowingStateLoss();
        }
    }

    public final View findViewById(int id) {
        return view.findViewById(id);
    }
}
