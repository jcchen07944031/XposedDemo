package com.wrbug.xposeddemo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * XposedInit
 *
 * @author wrbug
 * @since 2017/4/20
 */
public class XposedInit implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals(BuildConfig.APPLICATION_ID)){
            XposedHelpers.findAndHookMethod(MainActivity.class.getName(), lpparam.classLoader, "isActive", XC_MethodReplacement.returnConstant(true));
        }

        Log.i("PackageName", lpparam.packageName);

        if (lpparam.packageName.equals("com.habby.archero")) {
            XposedHelpers.findAndHookMethod("android.app.ApplicationPackageManager", lpparam.classLoader, "getInstalledApplications", int.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                    Log.i("PackageName", "Hook Success");
                    List<ApplicationInfo> applicationInfos = (List<ApplicationInfo>) param.getResult();

                    Log.i("PackageName", "Begin Print PackageName ********");
                    for (ApplicationInfo applicationInfo:applicationInfos){
                        Log.i("PackageName", "----" + applicationInfo.packageName + "----");
                    }
                    Log.i("PackageName", "End Print PackageName **********");

                    param.setResult(applicationInfos);
                }
            });


        }
    }
}
