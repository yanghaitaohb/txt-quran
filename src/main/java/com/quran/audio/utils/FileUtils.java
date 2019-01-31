package com.quran.audio.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    /**
     * 读取配置文件
     *
     * @param context 上下文
     * @return 本地文件列表
     */
    public static List<String> getFileList(Context context, String filename) {
        List<String> list = new ArrayList<>();
        try {
            InputStream in = context.getResources().getAssets().open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String str;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
