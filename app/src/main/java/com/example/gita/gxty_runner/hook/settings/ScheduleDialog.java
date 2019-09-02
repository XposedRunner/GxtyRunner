package com.example.gita.gxty_runner.hook.settings;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class ScheduleDialog extends DialogFragment {

    public static final int ACTION_OK = 0;
    public static final int ACTION_CANCEL = 1;
    public static final int ACTION_STORE = 2;
    public static final int ACTION_DELETE = 3;
    private Calendar mCalendar = Calendar.getInstance();
    private boolean mRepeat;

    private OnActionListener mOnActionListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(false);
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP;
            }
        });
        Window window = this.getDialog().getWindow();
        Objects.requireNonNull(window).getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable());
        LinearLayout view = new LinearLayout(getActivity());
        view.setOrientation(LinearLayout.VERTICAL);
        view.setBackgroundColor(Color.WHITE);
        long l = Long.parseLong(getTag());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(l == -1 ? System.currentTimeMillis() : Math.abs(l));
        mCalendar.setTimeInMillis(calendar.getTimeInMillis());
        mRepeat = l < 0;
        final HashMap<String, WeakReference<View>> viewHashMap = new HashMap<>();
        {

            RelativeLayout relativeLayout = new RelativeLayout(getActivity());
            relativeLayout.setId(View.generateViewId());
            TextView textView = new TextView(getActivity());
            textView.setText(l == -1 ? "取消" : "删除");
            textView.setTextSize(14.5f);
            textView.setTextColor(Color.RED);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(dip2px(getActivity(), 20), 0, dip2px(getActivity(), 20), 0);
            if (l == -1) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnActionListener != null) {
                            mOnActionListener.onAction(ACTION_CANCEL, Long.parseLong(getTag()), 0);
                        }
                        dismiss();
                    }
                });
            } else {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "长按删除", Toast.LENGTH_SHORT).show();
                    }
                });
                textView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mOnActionListener != null) {
                            mOnActionListener.onAction(ACTION_DELETE, Long.parseLong(getTag()), Long.parseLong(getTag()));
                        }
                        Toast.makeText(getActivity(), "已删除", Toast.LENGTH_LONG).show();
                        dismiss();
                        return true;
                    }
                });
            }

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
            relativeLayout.addView(textView, layoutParams);

            textView = new TextView(getActivity());
            textView.setText(l == -1 ? "添加" : "编辑");
            textView.setTextSize(16.5f);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(dip2px(getActivity(), 20), 0, dip2px(getActivity(), 20), 0);
            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            relativeLayout.addView(textView, layoutParams);

            textView = new TextView(getActivity());
            textView.setText(l == -1 ? "确定" : "保存");
            textView.setTextSize(14.5f);
            textView.setTextColor(Color.parseColor("#4bd9ba"));
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(dip2px(getActivity(), 20), 0, dip2px(getActivity(), 20), 0);
            if (l == -1) {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mRepeat && mCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                            Toast.makeText(getActivity(), "时间无效：该时间已经过", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        while (mCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                            mCalendar.add(Calendar.DATE, 1);
                        }
                        if (mOnActionListener != null) {
                            mOnActionListener.onAction(ACTION_OK, Long.parseLong(getTag()), mRepeat ? -mCalendar.getTimeInMillis() : mCalendar.getTimeInMillis());
                        }
                        dismiss();
                    }
                });
            } else {
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!mRepeat && mCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                            Toast.makeText(getActivity(), "时间无效：该时间已经过", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        while (mCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                            mCalendar.add(Calendar.DATE, 1);
                        }
                        if (mOnActionListener != null) {
                            mOnActionListener.onAction(ACTION_STORE, Long.parseLong(getTag()), mRepeat ? -mCalendar.getTimeInMillis() : mCalendar.getTimeInMillis());
                        }
                        dismiss();
                    }
                });
            }

            layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
            relativeLayout.addView(textView, layoutParams);

            view.addView(relativeLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(getActivity(), 48)));
        }
        {
            ImageView divider = new ImageView(getActivity());
            divider.setImageDrawable(new ColorDrawable(Color.parseColor("#20000000")));
            view.addView(divider, view.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(getActivity(), 2)));
        }
        {
            LinearLayout linearLayout = new LinearLayout(getActivity());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setPadding(dip2px(getActivity(), 20), dip2px(getActivity(), 10), dip2px(getActivity(), 20), dip2px(getActivity(), 10));
            {
                RelativeLayout relativeLayout = new RelativeLayout(getActivity());
                {
                    LinearLayout linearLayout1 = new LinearLayout(getActivity());
                    linearLayout1.setOrientation(LinearLayout.VERTICAL);
                    {
                        TextView textView = new TextView(getActivity());
                        textView.setText(DateFormat.format("yyyy", mCalendar.getTimeInMillis()));
                        linearLayout1.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        viewHashMap.put("yearTextView", new WeakReference<View>(textView));
                    }
                    {
                        TextView textView = new TextView(getActivity());
                        textView.setText(DateFormat.format("MM月dd日", mCalendar.getTimeInMillis()));
                        textView.setTextSize(18);
                        linearLayout1.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        viewHashMap.put("dateTextView", new WeakReference<View>(textView));
                    }
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_START);
                    linearLayout1.setId(View.generateViewId());
                    linearLayout1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View calendarView = viewHashMap.get("calendarView").get();
                            ViewGroup.LayoutParams layoutParamsA = calendarView.getLayoutParams();
                            layoutParamsA.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            calendarView.setLayoutParams(layoutParamsA);
                            View timePickerView = viewHashMap.get("timePickerView").get();
                            ViewGroup.LayoutParams layoutParamsB = timePickerView.getLayoutParams();
                            layoutParamsB.width = 0;
                            timePickerView.setLayoutParams(layoutParamsB);
                        }
                    });
                    relativeLayout.addView(linearLayout1, layoutParams);
                    viewHashMap.put("dateLinearLayout", new WeakReference<View>(linearLayout1));
                }
                {
                    TextView textView = new TextView(getActivity());
                    textView.setText(mRepeat ? "每日" : "单次");
                    textView.setTextSize(18);
                    textView.setGravity(Gravity.BOTTOM);
                    textView.setPadding(dip2px(getActivity(), 20), 0, dip2px(getActivity(), 20), 0);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mRepeat = !mRepeat;
                            ((TextView) v).setText(mRepeat ? "每日" : "单次");
                        }
                    });
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, relativeLayout.getChildAt(relativeLayout.getChildCount() - 1).getId());
                    relativeLayout.addView(textView, layoutParams);

                }
                {
                    TextView textView = new TextView(getActivity());
                    textView.setText(DateFormat.format("HH:mm", mCalendar.getTimeInMillis()));
                    textView.setTextSize(32);
                    textView.setGravity(Gravity.BOTTOM | Gravity.END);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View timePickerView = viewHashMap.get("timePickerView").get();
                            ViewGroup.LayoutParams layoutParamsA = timePickerView.getLayoutParams();
                            layoutParamsA.width = ViewGroup.LayoutParams.MATCH_PARENT;
                            timePickerView.setLayoutParams(layoutParamsA);
                            View calendarView = viewHashMap.get("calendarView").get();
                            ViewGroup.LayoutParams layoutParamsB = calendarView.getLayoutParams();
                            layoutParamsB.width = 0;
                            calendarView.setLayoutParams(layoutParamsB);

                        }
                    });
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
                    relativeLayout.addView(textView, layoutParams);
                    viewHashMap.put("timeTextView", new WeakReference<View>(textView));
                }
                linearLayout.addView(relativeLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            {
                LinearLayout linearLayout1 = new LinearLayout(getActivity());
                linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                {
                    CalendarView calendarView = new CalendarView(getActivity());
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTimeInMillis(calendar1.getTimeInMillis());
                    calendarView.setMinDate(calendar1.getTimeInMillis());
                    calendarView.setDate(System.currentTimeMillis());
                    calendar1.add(Calendar.YEAR, 1);
                    calendarView.setMaxDate(calendar1.getTimeInMillis());
                    calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                            mCalendar.set(year, month, dayOfMonth);
                            ((TextView) viewHashMap.get("yearTextView").get()).setText(DateFormat.format("yyyy", mCalendar.getTimeInMillis()));
                            ((TextView) viewHashMap.get("dateTextView").get()).setText(DateFormat.format("MM月dd日", mCalendar.getTimeInMillis()));
                        }
                    });
                    linearLayout1.addView(calendarView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    viewHashMap.put("calendarView", new WeakReference<View>(calendarView));
                }
                {
                    TimePicker timePicker = new TimePicker(getActivity());
                    timePicker.setIs24HourView(true);
                    timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
                    timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

                    timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                        @Override
                        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            mCalendar.set(Calendar.MINUTE, minute);
                            ((TextView) viewHashMap.get("timeTextView").get()).setText(DateFormat.format("HH:mm", mCalendar.getTimeInMillis()));
                        }
                    });
                    viewHashMap.put("timePickerView", new WeakReference<View>(timePicker));
                    linearLayout1.addView(timePicker, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, dip2px(getActivity(), 10), 0, 0);
                linearLayout.addView(linearLayout1, layoutParams);
            }
            view.addView(linearLayout, view.getChildCount(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        return view;
    }

    private int dip2px(Context context, float dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    public void setOnActionListener(OnActionListener actionListener) {
        this.mOnActionListener = actionListener;
    }


    public static ScheduleDialog show(FragmentManager manager, long tag) {
        ScheduleDialog scheduleDialog = new ScheduleDialog();
        scheduleDialog.show(manager, String.valueOf(tag));
        return scheduleDialog;
    }

    public static ScheduleDialog show(FragmentManager manager, long tag, OnActionListener actionListener) {
        ScheduleDialog scheduleDialog = new ScheduleDialog();
        scheduleDialog.show(manager, String.valueOf(tag));
        scheduleDialog.setOnActionListener(actionListener);
        return scheduleDialog;
    }

    public interface OnActionListener {
        void onAction(int action, long tag, long value);
    }
}
