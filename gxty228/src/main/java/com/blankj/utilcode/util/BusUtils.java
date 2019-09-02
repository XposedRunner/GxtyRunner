package com.blankj.utilcode.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class BusUtils {
    private static final ConcurrentHashMap<String, IBus> BUS = new ConcurrentHashMap();

    public interface IBus {
        String getName();

        void onEvent();
    }

    public static void register(IBus iBus) {
        if (iBus != null) {
            String name = iBus.getName();
            if (name != null && name.length() != 0) {
                if (BUS.containsKey(name)) {
                    System.out.println("bus of <" + name + "> has already registered.");
                }
                BUS.put(name, iBus);
                System.out.println("bus of <" + name + "> register successfully.");
            }
        }
    }

    public static Set<String> getBus() {
        return BUS.keySet();
    }

    public static void call(String str) {
        if (str != null && str.length() != 0) {
            IBus iBus = (IBus) BUS.get(str);
            if (iBus != null) {
                iBus.onEvent();
            }
        }
    }
}
