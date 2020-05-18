package com.moonlight035.udfsbackend.utils;

import com.moonlight035.udfsbackend.constants.FileTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

public class FileUtils {

    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    private final static int LENGTH = 6;

    private final static char BASE = '0';

    private static int nameSuffix = 0;


    public static String buildName() {
        long l = System.currentTimeMillis();
        int suffix;
        synchronized (FileUtils.class) {
            suffix = nameSuffix++;
        }
        if (suffix >= 10) {
            synchronized (FileUtils.class) {
                if (System.currentTimeMillis() > l) {
                    return buildName();
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                    }
                    l = System.currentTimeMillis();
                    nameSuffix=0;
                    suffix = nameSuffix++;
                }
            }
        }
        return String.valueOf(l) + suffix;
    }

    public static String buildVideoNo() {
        int num = atomicInteger.getAndIncrement();
        String val = String.valueOf(num);
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < LENGTH - val.length(); i++) {
            prefix.append(BASE);
        }
        return prefix + val;
    }

    public static FileTypeEnum getFileType(String suffix){

        for (FileTypeEnum e : FileTypeEnum.values()) {
            if(e==FileTypeEnum.OTHER)
                return e;
            for(String suf : e.getSuffixs())
                if(suf.equals(suffix))
                    return e;
        }
        return null;
    }
}
