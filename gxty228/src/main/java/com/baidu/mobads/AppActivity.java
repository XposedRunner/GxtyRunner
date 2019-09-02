package com.baidu.mobads;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import com.baidu.mobads.utils.b;
import com.baidu.mobads.utils.l;
import java.io.Serializable;

public class AppActivity extends Activity {
    public static String a = null;
    private static boolean b;
    private static ActionBarColorTheme d = ActionBarColorTheme.ACTION_BAR_WHITE_THEME;
    private b c = new b();

    public static class ActionBarColorTheme implements Serializable {
        public static final ActionBarColorTheme ACTION_BAR_BLACK_THEME = new ActionBarColorTheme(-1, -1, -12510, -13749450);
        public static final ActionBarColorTheme ACTION_BAR_BLUE_THEME = new ActionBarColorTheme(-1, -1, -12510, -13870424);
        public static final ActionBarColorTheme ACTION_BAR_COFFEE_THEME = new ActionBarColorTheme(-1, -1, -12510, -11255230);
        public static final ActionBarColorTheme ACTION_BAR_GREEN_THEME = new ActionBarColorTheme(-1, -1, -11113262, -14303071);
        public static final ActionBarColorTheme ACTION_BAR_NAVYBLUE_THEME = new ActionBarColorTheme(-1, -1, 16764706, -14210226);
        public static final ActionBarColorTheme ACTION_BAR_RED_THEME = new ActionBarColorTheme(-1, -1, -12510, -1294276);
        public static final ActionBarColorTheme ACTION_BAR_WHITE_THEME = new ActionBarColorTheme(-5987164, -6842473, -11113262, -328966);
        public int backgroundColor;
        public int closeColor;
        public int progressColor;
        public int titleColor;

        public ActionBarColorTheme(int i, int i2, int i3, int i4) {
            this.closeColor = i;
            this.titleColor = i2;
            this.progressColor = i3;
            this.backgroundColor = i4;
        }

        public ActionBarColorTheme(ActionBarColorTheme actionBarColorTheme) {
            this.closeColor = actionBarColorTheme.closeColor;
            this.titleColor = actionBarColorTheme.titleColor;
            this.progressColor = actionBarColorTheme.progressColor;
            this.backgroundColor = actionBarColorTheme.backgroundColor;
        }

        public int getCloseColor() {
            return this.closeColor;
        }

        public void setCloseColor(int i) {
            this.closeColor = i;
        }

        public int getTitleColor() {
            return this.titleColor;
        }

        public void setTitleColor(int i) {
            this.titleColor = i;
        }

        public int getProgressColor() {
            return this.progressColor;
        }

        public void setProgressColor(int i) {
            this.progressColor = i;
        }

        public int getBackgroundColor() {
            return this.backgroundColor;
        }

        public void setBackgroundColor(int i) {
            this.backgroundColor = i;
        }

        public boolean equals(Object obj) {
            ActionBarColorTheme actionBarColorTheme = (ActionBarColorTheme) obj;
            return this.backgroundColor == actionBarColorTheme.backgroundColor && this.titleColor == actionBarColorTheme.titleColor && this.closeColor == actionBarColorTheme.closeColor && this.progressColor == actionBarColorTheme.progressColor;
        }
    }

    public static boolean a() {
        return !TextUtils.isEmpty(a);
    }

    public static Class<?> b() {
        Class<?> cls = AppActivity.class;
        if (!TextUtils.isEmpty(a)) {
            try {
                cls = Class.forName(a);
            } catch (Throwable e) {
                l.a().e(e);
            }
        }
        return cls;
    }

    public static ActionBarColorTheme c() {
        return d;
    }

    public static void a(ActionBarColorTheme actionBarColorTheme) {
        if (actionBarColorTheme != null) {
            d = new ActionBarColorTheme(actionBarColorTheme);
        }
    }

    public static boolean d() {
        return b;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.c.a(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.c.a(motionEvent)) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (this.c.b(motionEvent)) {
            return true;
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.c.a(i, i2, intent);
        super.onActivityResult(i, i2, intent);
    }

    protected void onApplyThemeResource(Theme theme, int i, boolean z) {
        this.c.a(theme, i, z);
        super.onApplyThemeResource(theme, i, z);
    }

    protected void onChildTitleChanged(Activity activity, CharSequence charSequence) {
        this.c.a(activity, charSequence);
        super.onChildTitleChanged(activity, charSequence);
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.c.a(configuration);
        super.onConfigurationChanged(configuration);
    }

    public void onContentChanged() {
        this.c.b();
        super.onContentChanged();
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        if (this.c.a(menuItem)) {
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }

    public void onContextMenuClosed(Menu menu) {
        this.c.a(menu);
        super.onContextMenuClosed(menu);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            this.c.a((Activity) this);
            this.c.a(bundle);
        } catch (Throwable e) {
            l.a().e(e);
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        this.c.a(contextMenu, view, contextMenuInfo);
    }

    public CharSequence onCreateDescription() {
        CharSequence c = this.c.c();
        return c != null ? c : super.onCreateDescription();
    }

    protected Dialog onCreateDialog(int i) {
        Dialog a = this.c.a(i);
        return a != null ? a : super.onCreateDialog(i);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.c.b(menu)) {
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (this.c.a(i, menu)) {
            return true;
        }
        return super.onCreatePanelMenu(i, menu);
    }

    public View onCreatePanelView(int i) {
        View b = this.c.b(i);
        return b != null ? b : super.onCreatePanelView(i);
    }

    public boolean onCreateThumbnail(Bitmap bitmap, Canvas canvas) {
        if (this.c.a(bitmap, canvas)) {
            return true;
        }
        return super.onCreateThumbnail(bitmap, canvas);
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View a = this.c.a(str, context, attributeSet);
        return a != null ? a : super.onCreateView(str, context, attributeSet);
    }

    protected void onDestroy() {
        if (new b().webviewMultiProcess(this)) {
            Intent intent = new Intent();
            intent.setAction("AppActivity_onDestroy");
            sendBroadcast(intent);
        }
        this.c.d();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.c.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        if (this.c.a(i, i2, keyEvent)) {
            return true;
        }
        return super.onKeyMultiple(i, i2, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.c.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void onLowMemory() {
        this.c.e();
        super.onLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (this.c.a(i, menuItem)) {
            return true;
        }
        return super.onMenuItemSelected(i, menuItem);
    }

    public boolean onMenuOpened(int i, Menu menu) {
        if (this.c.b(i, menu)) {
            return true;
        }
        return super.onMenuOpened(i, menu);
    }

    protected void onNewIntent(Intent intent) {
        this.c.a(intent);
        super.onNewIntent(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.c.b(menuItem)) {
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onOptionsMenuClosed(Menu menu) {
        this.c.c(menu);
        super.onOptionsMenuClosed(menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        this.c.c(i, menu);
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        this.c.f();
        super.onPause();
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        this.c.b(bundle);
    }

    protected void onPostResume() {
        super.onPostResume();
        this.c.g();
    }

    protected void onPrepareDialog(int i, Dialog dialog) {
        super.onPrepareDialog(i, dialog);
        this.c.a(i, dialog);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (this.c.d(menu)) {
            return true;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (this.c.a(i, view, menu)) {
            return true;
        }
        return super.onPreparePanel(i, view, menu);
    }

    protected void onRestart() {
        super.onRestart();
        this.c.h();
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.c.c(bundle);
    }

    protected void onResume() {
        super.onResume();
        this.c.i();
    }

    public Object onRetainNonConfigurationInstance() {
        Object j = this.c.j();
        return j != null ? j : super.onRetainNonConfigurationInstance();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.c.d(bundle);
    }

    public boolean onSearchRequested() {
        if (this.c.k()) {
            return true;
        }
        return super.onSearchRequested();
    }

    protected void onStart() {
        super.onStart();
        this.c.l();
    }

    protected void onStop() {
        this.c.m();
        super.onStop();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.c.a(charSequence, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.c.c(motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (this.c.d(motionEvent)) {
            return true;
        }
        return super.onTrackballEvent(motionEvent);
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        this.c.n();
    }

    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.c.o();
    }

    public void onWindowAttributesChanged(LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
        this.c.a(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        this.c.a(z);
    }
}
