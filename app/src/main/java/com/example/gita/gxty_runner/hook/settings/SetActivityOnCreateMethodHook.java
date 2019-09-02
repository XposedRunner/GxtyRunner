package com.example.gita.gxty_runner.hook.settings;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class SetActivityOnCreateMethodHook extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(final MethodHookParam param) {
        final SharedPreferences sharedPreferences = ((Activity) param.thisObject).getSharedPreferences("module", Context.MODE_PRIVATE);

        final Activity activity = (Activity) param.thisObject;
        final Class startActivityClazz = XposedHelpers.findClass("com.example.gita.gxty.ram.StartActivity", ((Activity) param.thisObject).getClassLoader());
        Resources resources = activity.getApplicationContext().getResources();
        final String packageName = activity.getApplicationContext().getPackageName();

        if (activity.getIntent().hasExtra("module")
                && activity.findViewById(resources.getIdentifier("setwentiBtn", "id", packageName)) != null) {

            activity.findViewById(resources.getIdentifier("logoutBtn", "id", packageName)).setVisibility(View.GONE);
            ViewGroup titleBar = activity.findViewById(resources.getIdentifier("title_bar", "id", packageName));
            String title = activity.getIntent().getStringExtra("title");
            XposedHelpers.callMethod(titleBar, "setTitle", new Class[]{CharSequence.class}, title);

            TextView exampleSummaryTextView = activity.findViewById(resources.getIdentifier("huancunSize", "id", packageName));
            TextView exampleTitleTextView = (TextView) ((LinearLayout) exampleSummaryTextView.getParent()).getChildAt(0);
            Drawable backgroundDrawable = ((LinearLayout) exampleTitleTextView.getParent().getParent()).getBackground();

            LinearLayout mainLinearLayout = new LinearLayout(activity);
            mainLinearLayout.setOrientation(LinearLayout.VERTICAL);
            mainLinearLayout.addView(new TextView(activity), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Common.dip2px(activity, 10)));

            switch (activity.getIntent().getIntExtra("module", 0)) {
                case 0: {
                    final HashMap<String, WeakReference<View>> viewReferenceHashMap = new HashMap<>();

                    {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                        linearLayout.setTag(sharedPreferences.getBoolean("module_on", false));
                        viewReferenceHashMap.put("mainSwitchLinearLayout", new WeakReference<View>(linearLayout));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText("模块开关");
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText((boolean) linearLayout.getTag() ? "已开启" : "已关闭");
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean b = !(boolean) linearLayout.getTag();
                                linearLayout.setTag(b);
                                textViewB.setText(b ? "已开启" : "已关闭");
                                viewReferenceHashMap.get("subSettingsLinearLayout").get().setVisibility(b ? View.VISIBLE : View.GONE);
                                sharedPreferences.edit().putBoolean("module_on", b).apply();

                                String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                                Set<String> stringSet = new HashSet<>();
                                for (String string : strings) {
                                    long l = Long.parseLong(string);
                                    if (Math.abs(l) < System.currentTimeMillis()) {
                                        if (l < 0) {
                                            do {
                                                l -= AlarmManager.INTERVAL_DAY;
                                            }
                                            while (Math.abs(l) < System.currentTimeMillis());
                                        } else {
                                            continue;
                                        }
                                    }

                                    stringSet.add(String.valueOf(l));

                                    Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + l));
                                    PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                                    if (b) {
                                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(l), pendingIntent);
                                    } else {
                                        alarmManager.cancel(pendingIntent);
                                    }

                                }
                                sharedPreferences.edit().putStringSet("autorun_schedules", stringSet).apply();
                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }

                    mainLinearLayout.addView(new TextView(activity), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Common.dip2px(activity, 10)));

                    {
                        final String strings[] = new String[]{"小地图设置", "跑步设置", "自动跑步设置", "其他模块设置"};

                        LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setBackground(backgroundDrawable);

                        for (int i = 0; i < strings.length; i++) {
                            TextView textView = new TextView(activity);
                            textView.setText(strings[i]);
                            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                            textView.setTextColor(exampleTitleTextView.getTextColors());
                            textView.setGravity(exampleTitleTextView.getGravity());
                            textView.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);

                            final int finalI = i;
                            textView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(activity, XposedHelpers.findClass("com.example.gita.gxty.activity.SetActivity", param.thisObject.getClass().getClassLoader()));
                                    intent.putExtra("module", finalI + 1);
                                    intent.putExtra("title", strings[finalI]);
                                    activity.startActivity(intent);
                                }
                            });

                            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(exampleTitleTextView.getLayoutParams());
                            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            linearLayout.addView(textView, layoutParams);

                        }
                        viewReferenceHashMap.put("subSettingsLinearLayout", new WeakReference<View>(linearLayout));
                        linearLayout.setVisibility((boolean) viewReferenceHashMap.get("mainSwitchLinearLayout").get().getTag() ? View.VISIBLE : View.GONE);
                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());

                    }
                }
                break;
                case 1: {
                    final HashMap<String, WeakReference<View>> viewReferenceHashMap = new HashMap<>();

                    {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                        linearLayout.setTag(sharedPreferences.getBoolean("map_shown", false));
//                                viewReferenceHashMap.put("mapShownSwitchLinearLayout", new WeakReference<View>(linearLayout));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText("显示小地图");
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText((boolean) linearLayout.getTag() ? "已开启" : "已关闭");
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean b = !(boolean) linearLayout.getTag();
                                linearLayout.setTag(b);
                                textViewB.setText(b ? "已开启" : "已关闭");
                                sharedPreferences.edit().putBoolean("map_shown", b).apply();
                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }

                    mainLinearLayout.addView(new TextView(activity), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Common.dip2px(activity, 10)));

                    {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
//                                linearLayout.setTag(sharedPreferences.getBoolean("map_shown", false));
//                                viewReferenceHashMap.put("mapShownSwitchLinearLayout", new WeakReference<View>(linearLayout));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText("小地图轨迹颜色");
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText(String.format("#%s", Integer.toHexString(Color.GREEN).toUpperCase()));
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(Color.GREEN);
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                        boolean b = !(boolean) linearLayout.getTag();
//                                        linearLayout.setTag(b);
//                                        textViewB.setText(b ? "已开启" : "已关闭");
//                                        sharedPreferences.edit().putBoolean("map_shown", b).apply();
                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }

                    {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                        linearLayout.setTag(sharedPreferences.getBoolean("map_2d", true));
//                                viewReferenceHashMap.put("map3DLinearLayout", new WeakReference<View>(linearLayout));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText("小地图视角");
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText((boolean) linearLayout.getTag() ? "2D自由视角" : "3D跟随视角");
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean b = !(boolean) linearLayout.getTag();
                                linearLayout.setTag(b);
                                textViewB.setText((boolean) linearLayout.getTag() ? "2D自由视角" : "3D跟随视角");
                                sharedPreferences.edit().putBoolean("map_2d", b).apply();
                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }

                }
                break;
                case 2: {

                }
                break;
                case 3: {
                    final HashMap<String, WeakReference<View>> viewReferenceHashMap = new HashMap<>();
                    final ScheduleDialog.OnActionListener onActionListener = new ScheduleDialog.OnActionListener() {
                        @Override
                        public void onAction(int action, long tag, long value) {
                            LinearLayout subSettingsLinearLayout = (LinearLayout) viewReferenceHashMap.get("subSettingsLinearLayout").get();
                            LinearLayout mainSwitchLinearLayout = (LinearLayout) viewReferenceHashMap.get("mainSwitchLinearLayout").get();
                            TextView exampleTitleTextView = (TextView) mainSwitchLinearLayout.getChildAt(0);
                            TextView exampleSummaryTextView = (TextView) mainSwitchLinearLayout.getChildAt(1);
                            final ScheduleDialog.OnActionListener actionListener = this;

                            switch (action) {
                                case ScheduleDialog.ACTION_OK: {
                                    HashSet<String> hashSet = new HashSet<>();
                                    String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                                    for (String string : strings) {
                                        long ll = Long.parseLong(string);
                                        int int1 = Integer.parseInt(DateFormat.format("HHmm", Math.abs(ll)).toString());
                                        int int2 = Integer.parseInt(DateFormat.format("HHmm", Math.abs(value)).toString());
                                        if (int1 == int2) {
                                            Toast.makeText((Context) param.thisObject, "相同时间的计划已存在,不做处理", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                        hashSet.add(String.valueOf(ll));
                                    }
                                    hashSet.add(String.valueOf(value));
                                    sharedPreferences.edit().putStringSet("autorun_schedules", hashSet).apply();

                                    Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + value));
                                    PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(value), pendingIntent);

                                    final LinearLayout layout = new LinearLayout(activity);
                                    layout.setOrientation(LinearLayout.HORIZONTAL);
                                    layout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                                    layout.setTag(value);

                                    TextView textViewA = new TextView(activity);
                                    textViewA.setText(String.format("%s已计划", DateFormat.format("HH:mm", Math.abs(value))));
                                    textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                                    textViewA.setTextColor(exampleTitleTextView.getTextColors());
                                    textViewA.setGravity(exampleTitleTextView.getGravity());
                                    layout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                                    final TextView textViewB = new TextView(activity);
                                    textViewB.setText(String.format("%s触发,%s", DateFormat.format("yyyy/MM/dd", Math.abs(value)), (value < 0 ? "每日" : "单次")));
                                    textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                                    textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                                    textViewB.setGravity(exampleSummaryTextView.getGravity());

                                    layout.addView(textViewB, exampleSummaryTextView.getLayoutParams());
                                    layout.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ScheduleDialog.show(((Activity) param.thisObject).getFragmentManager(), (Long) layout.getTag(), actionListener);
                                        }
                                    });
                                    subSettingsLinearLayout.addView(layout, mainSwitchLinearLayout.getLayoutParams());
                                }
                                break;
                                case ScheduleDialog.ACTION_STORE: {

                                    for (String string : sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{})) {
                                        if (Math.abs(tag) == Math.abs(value)) {
                                            continue;
                                        }
                                        long ll = Long.parseLong(string);
                                        int int1 = Integer.parseInt(DateFormat.format("HHmm", Math.abs(ll)).toString());
                                        int int2 = Integer.parseInt(DateFormat.format("HHmm", Math.abs(value)).toString());
                                        if (int1 == int2) {
                                            Toast.makeText((Context) param.thisObject, "相同时间的计划已存在,不做处理", Toast.LENGTH_LONG).show();
                                            return;
                                        }
                                    }
                                    LinearLayout linearLayout = subSettingsLinearLayout.findViewWithTag(tag);
                                    linearLayout.setTag(value);
                                    TextView textViewA = (TextView) linearLayout.getChildAt(0);
                                    TextView textViewB = (TextView) linearLayout.getChildAt(1);
                                    textViewA.setText(String.format("%s已计划", DateFormat.format("HH:mm", Math.abs(value))));
                                    textViewB.setText(String.format("%s触发,%s", DateFormat.format("yyyy/MM/dd", Math.abs(value)), (value < 0 ? "每日" : "单次")));

                                    HashSet<String> hashSet = new HashSet<>();
                                    String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                                    for (String string : strings) {
                                        long ll = Long.parseLong(string);
                                        if (tag == ll) {
                                            continue;
                                        }
                                        hashSet.add(String.valueOf(ll));
                                    }
                                    hashSet.add(String.valueOf(value));
                                    sharedPreferences.edit().putStringSet("autorun_schedules", hashSet).apply();

                                    Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + tag));
                                    PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.cancel(pendingIntent);

                                    intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + tag));
                                    pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(value), pendingIntent);
                                }
                                break;
                                case ScheduleDialog.ACTION_DELETE: {
                                    subSettingsLinearLayout.removeView(subSettingsLinearLayout.findViewWithTag(tag));
                                    Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + tag));
                                    PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.cancel(pendingIntent);
                                    HashSet<String> hashSet = new HashSet<>();
                                    String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                                    for (String string : strings) {
                                        long l = Long.parseLong(string);
                                        if (tag == l) {
                                            continue;
                                        }
                                        hashSet.add(String.valueOf(l));
                                    }
                                    sharedPreferences.edit().putStringSet("autorun_schedules", hashSet).apply();
                                }
                                break;
                            }
                        }
                    };

                    {
                        TextView textView = new TextView(activity);
                        textView.setText("添加计划");
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextColor(Color.parseColor("#4bd9ba"));
                        textView.setPadding(Common.dip2px(activity, 5), 0, Common.dip2px(activity, 5), 0);
                        textView.setVisibility(sharedPreferences.getBoolean("autorun_on", false) ? View.VISIBLE : View.GONE);
                        viewReferenceHashMap.put("addScheduleTextView", new WeakReference<View>(textView));
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        LinearLayout linearLayout = (LinearLayout) titleBar.getChildAt(2);
                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ScheduleDialog.show(((Activity) param.thisObject).getFragmentManager(), -1, onActionListener);
                            }
                        });
                        linearLayout.addView(textView, layoutParams);
                    }

                    {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                        linearLayout.setTag(sharedPreferences.getBoolean("autorun_on", false));
                        viewReferenceHashMap.put("mainSwitchLinearLayout", new WeakReference<View>(linearLayout));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText("自动跑步");
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText((boolean) linearLayout.getTag() ? "已开启" : "已关闭");
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean b = !(boolean) linearLayout.getTag();
                                linearLayout.setTag(b);
                                textViewB.setText(b ? "已开启" : "已关闭");
                                viewReferenceHashMap.get("addScheduleTextView").get().setVisibility(b ? View.VISIBLE : View.GONE);
                                viewReferenceHashMap.get("subSettingsLinearLayout").get().setVisibility(b ? View.VISIBLE : View.GONE);
                                sharedPreferences.edit().putBoolean("autorun_on", b).apply();

                                String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                                Set<String> stringSet = new HashSet<>();
                                for (String string : strings) {
                                    long l = Long.parseLong(string);
                                    if (Math.abs(l) < System.currentTimeMillis()) {
                                        if (l < 0) {
                                            do {
                                                l -= AlarmManager.INTERVAL_DAY;
                                            }
                                            while (Math.abs(l) < System.currentTimeMillis());
                                        } else {
                                            continue;
                                        }
                                    }

                                    stringSet.add(String.valueOf(l));

                                    Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                                    intent.setAction(Intent.ACTION_MAIN);
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                                    intent.setData(Uri.parse("autorun://scheduled?time=" + l));
                                    PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                                    AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                                    if (b) {
                                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(l), pendingIntent);
                                    } else {
                                        alarmManager.cancel(pendingIntent);
                                    }

                                }
                                sharedPreferences.edit().putStringSet("autorun_schedules", stringSet).apply();

                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }

                    mainLinearLayout.addView(new TextView(activity), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Common.dip2px(activity, 10)));

                    {
                        LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        linearLayout.setBackground(backgroundDrawable);

                        String[] strings = sharedPreferences.getStringSet("autorun_schedules", new HashSet<String>()).toArray(new String[]{});
                        Set<String> stringSet = new HashSet<>();
                        for (String string : strings) {
                            long l = Long.parseLong(string);
                            if (Math.abs(l) < System.currentTimeMillis()) {
                                if (l < 0) {
                                    do {
                                        l -= AlarmManager.INTERVAL_DAY;
                                    } while (Math.abs(l) < System.currentTimeMillis());
                                } else {
                                    continue;
                                }
                            }

                            stringSet.add(String.valueOf(l));

                            Intent intent = new Intent((Context) param.thisObject, startActivityClazz);
                            intent.setAction(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            intent.addFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                            intent.setData(Uri.parse("autorun://scheduled?time=" + l));
                            PendingIntent pendingIntent = PendingIntent.getActivity((Context) param.thisObject, 0, intent, 0);
                            AlarmManager alarmManager = (AlarmManager) ((Context) param.thisObject).getSystemService(Context.ALARM_SERVICE);
                            if (sharedPreferences.getBoolean("autorun_on", false)) {
                                alarmManager.setExact(AlarmManager.RTC_WAKEUP, Math.abs(l), pendingIntent);
                            } else {
                                alarmManager.cancel(pendingIntent);
                            }

                            final LinearLayout layout = new LinearLayout(activity);
                            layout.setOrientation(LinearLayout.HORIZONTAL);
                            layout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                            layout.setTag(l);

                            TextView textViewA = new TextView(activity);
                            textViewA.setText(String.format("%s已计划", DateFormat.format("HH:mm", Math.abs(l))));
                            textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                            textViewA.setTextColor(exampleTitleTextView.getTextColors());
                            textViewA.setGravity(exampleTitleTextView.getGravity());
                            layout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                            final TextView textViewB = new TextView(activity);
                            textViewB.setText(String.format("%s触发,%s", DateFormat.format("yyyy/MM/dd", Math.abs(l)), (l < 0 ? "每日" : "单次")));
                            textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                            textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                            textViewB.setGravity(exampleSummaryTextView.getGravity());

                            layout.addView(textViewB, exampleSummaryTextView.getLayoutParams());
                            layout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ScheduleDialog.show(((Activity) param.thisObject).getFragmentManager(), (Long) layout.getTag(), onActionListener);
                                }
                            });

                            linearLayout.addView(layout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                        }
                        sharedPreferences.edit().putStringSet("autorun_schedules", stringSet).apply();
                        viewReferenceHashMap.put("subSettingsLinearLayout", new WeakReference<View>(linearLayout));
                        linearLayout.setVisibility((boolean) viewReferenceHashMap.get("mainSwitchLinearLayout").get().getTag() ? View.VISIBLE : View.GONE);
                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());

                    }
                }
                break;
                case 4: {
                    final String strings[][] = new String[][]{
                            {"Root防检测", "root_hide"},
                            {"Xposed防检测", "xposed_hide"},
                            {"广告屏蔽", "ads_free"}};

                    for (final String[] string : strings) {
                        final LinearLayout linearLayout = new LinearLayout(activity);
                        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                        linearLayout.setBackground(backgroundDrawable);
                        linearLayout.setPadding(Common.dip2px(activity, 20), 0, Common.dip2px(activity, 20), 0);
                        linearLayout.setTag(sharedPreferences.getBoolean(string[1], false));

                        TextView textViewA = new TextView(activity);
                        textViewA.setText(string[0]);
                        textViewA.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleTitleTextView.getTextSize());
                        textViewA.setTextColor(exampleTitleTextView.getTextColors());
                        textViewA.setGravity(exampleTitleTextView.getGravity());
                        linearLayout.addView(textViewA, exampleTitleTextView.getLayoutParams());

                        final TextView textViewB = new TextView(activity);
                        textViewB.setText((boolean) linearLayout.getTag() ? "已开启" : "已关闭");
                        textViewB.setTextSize(TypedValue.COMPLEX_UNIT_PX, exampleSummaryTextView.getTextSize());
                        textViewB.setTextColor(exampleSummaryTextView.getTextColors());
                        textViewB.setGravity(exampleSummaryTextView.getGravity());

                        linearLayout.addView(textViewB, exampleSummaryTextView.getLayoutParams());

                        linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                boolean b = !(boolean) linearLayout.getTag();
                                linearLayout.setTag(b);
                                textViewB.setText(b ? "已开启" : "已关闭");
                                sharedPreferences.edit().putBoolean(string[1], b).apply();
                            }
                        });

                        mainLinearLayout.addView(linearLayout, ((LinearLayout) exampleSummaryTextView.getParent()).getLayoutParams());
                    }
                }
                break;
                default: {
                    activity.finish();
                }
                break;
            }

            ScrollView scrollView = (ScrollView) exampleTitleTextView.getParent().getParent().getParent();
            scrollView.removeAllViews();
            scrollView.addView(mainLinearLayout, new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));

        }

    }
}
