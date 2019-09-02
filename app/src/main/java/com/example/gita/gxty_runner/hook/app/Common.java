package com.example.gita.gxty_runner.hook.app;

import java.util.Arrays;
import java.util.List;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.callbacks.XCallback;

class Common {

    public static boolean isTarget(String name) {
        List<String> suPackages = Arrays.asList("com.noshufou.android.su", "com.noshufou.android.su.elite", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.thirdparty.superuser", "com.yellowes.su", "com.koushikdutta.rommanager", "com.dimonvideo.luckypatcher", "com.chelpus.lackypatch", "com.ramdroid.appquarantine", "com.devadvance.rootcloak", "de.robv.android.xposed.installer", "com.saurik.substrate", "com.devadvance.rootcloakplus", "com.zachspong.temprootremovejb", "com.amphoras.hidemyroot", "com.formyhm.hideroot");

        return name == null
                || name.toLowerCase().contains("xposed")
                || name.toLowerCase().contains("substrate")
                || name.equalsIgnoreCase("top.a1024bytes.mockloc.ca.pro")
                || name.toLowerCase().contains("daniu")
                || name.toLowerCase().contains("goldfish")
                || name.toLowerCase().contains("magisk")
                || name.toLowerCase().contains("frida")
                || name.toLowerCase().contains("root")
                || suPackages.contains(name.toLowerCase());
    }

    @SuppressWarnings("unchecked")
    public static <T extends XCallback> T instantiate(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            return (T) XC_MethodReplacement.DO_NOTHING;
        }
    }

}
