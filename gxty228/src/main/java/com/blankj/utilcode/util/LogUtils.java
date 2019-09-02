package com.blankj.utilcode.util;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IntRange;
import android.support.annotation.RequiresApi;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import cn.jiguang.net.HttpUtils;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class LogUtils {
    public static final int A = 7;
    private static final String ARGS = "args";
    private static final String BOTTOM_BORDER = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String BOTTOM_CORNER = "└";
    private static final Config CONFIG = new Config();
    public static final int D = 3;
    public static final int E = 6;
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static final int FILE = 16;
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final Format FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS ", Locale.getDefault());
    public static final int I = 4;
    private static final SimpleArrayMap<Class, IFormatter> I_FORMATTER_MAP = new SimpleArrayMap();
    private static final int JSON = 32;
    private static final String LEFT_BORDER = "│ ";
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final int MAX_LEN = 3000;
    private static final String MIDDLE_BORDER = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MIDDLE_CORNER = "├";
    private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String NOTHING = "log nothing";
    private static final String NULL = "null";
    private static final String PLACEHOLDER = " ";
    private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final char[] T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};
    private static final String TOP_BORDER = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String TOP_CORNER = "┌";
    public static final int V = 2;
    public static final int W = 5;
    private static final int XML = 48;

    public static class Config {
        private int mConsoleFilter;
        private String mDefaultDir;
        private String mDir;
        private int mFileFilter;
        private String mFilePrefix;
        private String mGlobalTag;
        private boolean mLog2ConsoleSwitch;
        private boolean mLog2FileSwitch;
        private boolean mLogBorderSwitch;
        private boolean mLogHeadSwitch;
        private boolean mLogSwitch;
        private int mSaveDays;
        private boolean mSingleTagSwitch;
        private int mStackDeep;
        private int mStackOffset;
        private boolean mTagIsSpace;

        private Config() {
            this.mFilePrefix = "util";
            this.mLogSwitch = true;
            this.mLog2ConsoleSwitch = true;
            this.mGlobalTag = null;
            this.mTagIsSpace = true;
            this.mLogHeadSwitch = true;
            this.mLog2FileSwitch = false;
            this.mLogBorderSwitch = true;
            this.mSingleTagSwitch = true;
            this.mConsoleFilter = 2;
            this.mFileFilter = 2;
            this.mStackDeep = 1;
            this.mStackOffset = 0;
            this.mSaveDays = -1;
            if (this.mDefaultDir == null) {
                if (!"mounted".equals(Environment.getExternalStorageState()) || Utils.getApp().getExternalCacheDir() == null) {
                    this.mDefaultDir = Utils.getApp().getCacheDir() + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
                } else {
                    this.mDefaultDir = Utils.getApp().getExternalCacheDir() + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
                }
            }
        }

        public Config setLogSwitch(boolean z) {
            this.mLogSwitch = z;
            return this;
        }

        public Config setConsoleSwitch(boolean z) {
            this.mLog2ConsoleSwitch = z;
            return this;
        }

        public Config setGlobalTag(String str) {
            if (LogUtils.isSpace(str)) {
                this.mGlobalTag = "";
                this.mTagIsSpace = true;
            } else {
                this.mGlobalTag = str;
                this.mTagIsSpace = false;
            }
            return this;
        }

        public Config setLogHeadSwitch(boolean z) {
            this.mLogHeadSwitch = z;
            return this;
        }

        public Config setLog2FileSwitch(boolean z) {
            this.mLog2FileSwitch = z;
            return this;
        }

        public Config setDir(String str) {
            if (LogUtils.isSpace(str)) {
                this.mDir = null;
            } else {
                if (!str.endsWith(LogUtils.FILE_SEP)) {
                    str = str + LogUtils.FILE_SEP;
                }
                this.mDir = str;
            }
            return this;
        }

        public Config setDir(File file) {
            this.mDir = file == null ? null : file.getAbsolutePath() + LogUtils.FILE_SEP;
            return this;
        }

        public Config setFilePrefix(String str) {
            if (LogUtils.isSpace(str)) {
                this.mFilePrefix = "util";
            } else {
                this.mFilePrefix = str;
            }
            return this;
        }

        public Config setBorderSwitch(boolean z) {
            this.mLogBorderSwitch = z;
            return this;
        }

        public Config setSingleTagSwitch(boolean z) {
            this.mSingleTagSwitch = z;
            return this;
        }

        public Config setConsoleFilter(int i) {
            this.mConsoleFilter = i;
            return this;
        }

        public Config setFileFilter(int i) {
            this.mFileFilter = i;
            return this;
        }

        public Config setStackDeep(@IntRange(from = 1) int i) {
            this.mStackDeep = i;
            return this;
        }

        public Config setStackOffset(@IntRange(from = 0) int i) {
            this.mStackOffset = i;
            return this;
        }

        public Config setSaveDays(@IntRange(from = 1) int i) {
            this.mSaveDays = i;
            return this;
        }

        public final <T> Config addFormatter(IFormatter<T> iFormatter) {
            if (iFormatter != null) {
                LogUtils.I_FORMATTER_MAP.put(LogUtils.getTypeClassFromParadigm(iFormatter), iFormatter);
            }
            return this;
        }

        public String toString() {
            return "switch: " + this.mLogSwitch + LogUtils.LINE_SEP + "console: " + this.mLog2ConsoleSwitch + LogUtils.LINE_SEP + "tag: " + (this.mTagIsSpace ? LogUtils.NULL : this.mGlobalTag) + LogUtils.LINE_SEP + "head: " + this.mLogHeadSwitch + LogUtils.LINE_SEP + "file: " + this.mLog2FileSwitch + LogUtils.LINE_SEP + "dir: " + (this.mDir == null ? this.mDefaultDir : this.mDir) + LogUtils.LINE_SEP + "filePrefix: " + this.mFilePrefix + LogUtils.LINE_SEP + "border: " + this.mLogBorderSwitch + LogUtils.LINE_SEP + "singleTag: " + this.mSingleTagSwitch + LogUtils.LINE_SEP + "consoleFilter: " + LogUtils.T[this.mConsoleFilter - 2] + LogUtils.LINE_SEP + "fileFilter: " + LogUtils.T[this.mFileFilter - 2] + LogUtils.LINE_SEP + "stackDeep: " + this.mStackDeep + LogUtils.LINE_SEP + "stackOffset: " + this.mStackOffset + LogUtils.LINE_SEP + "saveDays: " + this.mSaveDays + LogUtils.LINE_SEP + "formatter: " + LogUtils.I_FORMATTER_MAP;
        }
    }

    public static abstract class IFormatter<T> {
        public abstract String format(T t);
    }

    private static class LogFormatter {
        private LogFormatter() {
        }

        static String formatJson(String str) {
            try {
                if (str.startsWith("{")) {
                    return new JSONObject(str).toString(4);
                }
                if (str.startsWith("[")) {
                    return new JSONArray(str).toString(4);
                }
                return str;
            } catch (JSONException e) {
                e.printStackTrace();
                return str;
            }
        }

        static String formatXml(String str) {
            try {
                Source streamSource = new StreamSource(new StringReader(str));
                Object streamResult = new StreamResult(new StringWriter());
                Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
                newTransformer.setOutputProperty("indent", "yes");
                newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                newTransformer.transform(streamSource, streamResult);
                str = streamResult.getWriter().toString().replaceFirst(">", ">" + LogUtils.LINE_SEP);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

        static String array2String(Object obj) {
            if (obj instanceof Object[]) {
                return Arrays.deepToString((Object[]) obj);
            }
            if (obj instanceof boolean[]) {
                return Arrays.toString((boolean[]) obj);
            }
            if (obj instanceof byte[]) {
                return Arrays.toString((byte[]) obj);
            }
            if (obj instanceof char[]) {
                return Arrays.toString((char[]) obj);
            }
            if (obj instanceof double[]) {
                return Arrays.toString((double[]) obj);
            }
            if (obj instanceof float[]) {
                return Arrays.toString((float[]) obj);
            }
            if (obj instanceof int[]) {
                return Arrays.toString((int[]) obj);
            }
            if (obj instanceof long[]) {
                return Arrays.toString((long[]) obj);
            }
            if (obj instanceof short[]) {
                return Arrays.toString((short[]) obj);
            }
            throw new IllegalArgumentException("Array has incompatible type: " + obj.getClass());
        }

        static String throwable2String(Throwable th) {
            Throwable th2;
            for (th2 = th; th2 != null; th2 = th2.getCause()) {
                if (th2 instanceof UnknownHostException) {
                    return "";
                }
            }
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            for (th2 = th.getCause(); th2 != null; th2 = th2.getCause()) {
                th2.printStackTrace(printWriter);
            }
            printWriter.flush();
            return stringWriter.toString();
        }

        static String bundle2String(Bundle bundle) {
            Iterator it = bundle.keySet().iterator();
            if (!it.hasNext()) {
                return "Bundle {}";
            }
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("Bundle { ");
            while (true) {
                String str = (String) it.next();
                Bundle bundle2 = bundle.get(str);
                stringBuilder.append(str).append('=');
                if (bundle2 == null || !(bundle2 instanceof Bundle)) {
                    stringBuilder.append(LogUtils.formatObject(bundle2));
                } else {
                    stringBuilder.append(bundle2 == bundle ? "(this Bundle)" : bundle2String(bundle2));
                }
                if (!it.hasNext()) {
                    return stringBuilder.append(" }").toString();
                }
                stringBuilder.append(',').append(' ');
            }
        }

        static String intent2String(Intent intent) {
            Object obj = 1;
            Object obj2 = null;
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("Intent { ");
            String action = intent.getAction();
            if (action != null) {
                stringBuilder.append("act=").append(action);
                Object obj3 = null;
            } else {
                int i = 1;
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("cat=[");
                for (String action2 : categories) {
                    if (obj == null) {
                        stringBuilder.append(',');
                    }
                    stringBuilder.append(action2);
                    obj = null;
                }
                stringBuilder.append("]");
                obj3 = null;
            }
            Uri data = intent.getData();
            if (data != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("dat=").append(data);
                obj3 = null;
            }
            String type = intent.getType();
            if (type != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("typ=").append(type);
                obj3 = null;
            }
            int flags = intent.getFlags();
            if (flags != 0) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("flg=0x").append(Integer.toHexString(flags));
                obj3 = null;
            }
            type = intent.getPackage();
            if (type != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("pkg=").append(type);
                obj3 = null;
            }
            ComponentName component = intent.getComponent();
            if (component != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("cmp=").append(component.flattenToShortString());
                obj3 = null;
            }
            Rect sourceBounds = intent.getSourceBounds();
            if (sourceBounds != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("bnds=").append(sourceBounds.toShortString());
                obj3 = null;
            }
            if (VERSION.SDK_INT >= 16) {
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    if (obj3 == null) {
                        stringBuilder.append(' ');
                    }
                    clipData2String(clipData, stringBuilder);
                    obj3 = null;
                }
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (obj3 == null) {
                    stringBuilder.append(' ');
                }
                stringBuilder.append("extras={");
                stringBuilder.append(bundle2String(extras));
                stringBuilder.append('}');
            } else {
                obj2 = obj3;
            }
            if (VERSION.SDK_INT >= 15) {
                Intent selector = intent.getSelector();
                if (selector != null) {
                    if (obj2 == null) {
                        stringBuilder.append(' ');
                    }
                    stringBuilder.append("sel={");
                    stringBuilder.append(selector == intent ? "(this Intent)" : intent2String(selector));
                    stringBuilder.append("}");
                }
            }
            stringBuilder.append(" }");
            return stringBuilder.toString();
        }

        @RequiresApi(api = 16)
        private static void clipData2String(ClipData clipData, StringBuilder stringBuilder) {
            Item itemAt = clipData.getItemAt(0);
            if (itemAt == null) {
                stringBuilder.append("ClipData.Item {}");
                return;
            }
            stringBuilder.append("ClipData.Item { ");
            String htmlText = itemAt.getHtmlText();
            if (htmlText != null) {
                stringBuilder.append("H:");
                stringBuilder.append(htmlText);
                stringBuilder.append("}");
                return;
            }
            CharSequence text = itemAt.getText();
            if (text != null) {
                stringBuilder.append("T:");
                stringBuilder.append(text);
                stringBuilder.append("}");
                return;
            }
            Uri uri = itemAt.getUri();
            if (uri != null) {
                stringBuilder.append("U:").append(uri);
                stringBuilder.append("}");
                return;
            }
            Intent intent = itemAt.getIntent();
            if (intent != null) {
                stringBuilder.append("I:");
                stringBuilder.append(intent2String(intent));
                stringBuilder.append("}");
                return;
            }
            stringBuilder.append("NULL");
            stringBuilder.append("}");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    private static class TagHead {
        String[] consoleHead;
        String fileHead;
        String tag;

        TagHead(String str, String[] strArr, String str2) {
            this.tag = str;
            this.consoleHead = strArr;
            this.fileHead = str2;
        }
    }

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Config getConfig() {
        return CONFIG;
    }

    public static void v(Object... objArr) {
        log(2, CONFIG.mGlobalTag, objArr);
    }

    public static void vTag(String str, Object... objArr) {
        log(2, str, objArr);
    }

    public static void d(Object... objArr) {
        log(3, CONFIG.mGlobalTag, objArr);
    }

    public static void dTag(String str, Object... objArr) {
        log(3, str, objArr);
    }

    public static void i(Object... objArr) {
        log(4, CONFIG.mGlobalTag, objArr);
    }

    public static void iTag(String str, Object... objArr) {
        log(4, str, objArr);
    }

    public static void w(Object... objArr) {
        log(5, CONFIG.mGlobalTag, objArr);
    }

    public static void wTag(String str, Object... objArr) {
        log(5, str, objArr);
    }

    public static void e(Object... objArr) {
        log(6, CONFIG.mGlobalTag, objArr);
    }

    public static void eTag(String str, Object... objArr) {
        log(6, str, objArr);
    }

    public static void a(Object... objArr) {
        log(7, CONFIG.mGlobalTag, objArr);
    }

    public static void aTag(String str, Object... objArr) {
        log(7, str, objArr);
    }

    public static void file(Object obj) {
        log(19, CONFIG.mGlobalTag, obj);
    }

    public static void file(int i, Object obj) {
        log(i | 16, CONFIG.mGlobalTag, obj);
    }

    public static void file(String str, Object obj) {
        log(19, str, obj);
    }

    public static void file(int i, String str, Object obj) {
        log(i | 16, str, obj);
    }

    public static void json(String str) {
        log(35, CONFIG.mGlobalTag, str);
    }

    public static void json(int i, String str) {
        log(i | 32, CONFIG.mGlobalTag, str);
    }

    public static void json(String str, String str2) {
        log(35, str, str2);
    }

    public static void json(int i, String str, String str2) {
        log(i | 32, str, str2);
    }

    public static void xml(String str) {
        log(51, CONFIG.mGlobalTag, str);
    }

    public static void xml(int i, String str) {
        log(i | 48, CONFIG.mGlobalTag, str);
    }

    public static void xml(String str, String str2) {
        log(51, str, str2);
    }

    public static void xml(int i, String str, String str2) {
        log(i | 48, str, str2);
    }

    public static void log(int i, String str, Object... objArr) {
        if (!CONFIG.mLogSwitch) {
            return;
        }
        if (CONFIG.mLog2ConsoleSwitch || CONFIG.mLog2FileSwitch) {
            int i2 = i & 15;
            int i3 = i & GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN;
            if (i2 >= CONFIG.mConsoleFilter || i2 >= CONFIG.mFileFilter) {
                TagHead processTagAndHead = processTagAndHead(str);
                String processBody = processBody(i3, objArr);
                if (CONFIG.mLog2ConsoleSwitch && i2 >= CONFIG.mConsoleFilter && i3 != 16) {
                    print2Console(i2, processTagAndHead.tag, processTagAndHead.consoleHead, processBody);
                }
                if ((CONFIG.mLog2FileSwitch || i3 == 16) && i2 >= CONFIG.mFileFilter) {
                    print2File(i2, processTagAndHead.tag, processTagAndHead.fileHead + processBody);
                }
            }
        }
    }

    private static TagHead processTagAndHead(String str) {
        if (CONFIG.mTagIsSpace || CONFIG.mLogHeadSwitch) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int access$900 = CONFIG.mStackOffset + 3;
            String fileName;
            if (access$900 >= stackTrace.length) {
                fileName = getFileName(stackTrace[3]);
                if (CONFIG.mTagIsSpace && isSpace(str)) {
                    int indexOf = fileName.indexOf(46);
                    if (indexOf != -1) {
                        fileName = fileName.substring(0, indexOf);
                    }
                    str = fileName;
                }
                return new TagHead(str, null, ": ");
            }
            int indexOf2;
            String fileName2 = getFileName(stackTrace[access$900]);
            if (CONFIG.mTagIsSpace && isSpace(str)) {
                indexOf2 = fileName2.indexOf(46);
                if (indexOf2 == -1) {
                    fileName = fileName2;
                } else {
                    fileName = fileName2.substring(0, indexOf2);
                }
                str = fileName;
            }
            if (CONFIG.mLogHeadSwitch) {
                fileName2 = new Formatter().format("%s, %s.%s(%s:%d)", new Object[]{Thread.currentThread().getName(), r4.getClassName(), r4.getMethodName(), fileName2, Integer.valueOf(r4.getLineNumber())}).toString();
                String str2 = " [" + fileName2 + "]: ";
                if (CONFIG.mStackDeep <= 1) {
                    return new TagHead(str, new String[]{fileName2}, str2);
                }
                String[] strArr = new String[Math.min(CONFIG.mStackDeep, stackTrace.length - access$900)];
                strArr[0] = fileName2;
                indexOf2 = fileName.length() + 2;
                fileName2 = new Formatter().format("%" + indexOf2 + "s", new Object[]{""}).toString();
                int length = strArr.length;
                for (indexOf2 = 1; indexOf2 < length; indexOf2++) {
                    StackTraceElement stackTraceElement = stackTrace[indexOf2 + access$900];
                    strArr[indexOf2] = new Formatter().format("%s%s.%s(%s:%d)", new Object[]{fileName2, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), getFileName(stackTraceElement), Integer.valueOf(stackTraceElement.getLineNumber())}).toString();
                }
                return new TagHead(str, strArr, str2);
            }
        }
        str = CONFIG.mGlobalTag;
        return new TagHead(str, null, ": ");
    }

    private static String getFileName(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        if (fileName != null) {
            return fileName;
        }
        fileName = stackTraceElement.getClassName();
        String[] split = fileName.split("\\.");
        if (split.length > 0) {
            fileName = split[split.length - 1];
        }
        int indexOf = fileName.indexOf(36);
        if (indexOf != -1) {
            fileName = fileName.substring(0, indexOf);
        }
        return fileName + ".java";
    }

    private static String processBody(int i, Object... objArr) {
        String str = NULL;
        if (objArr != null) {
            if (objArr.length == 1) {
                str = formatObject(i, objArr[0]);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    stringBuilder.append(ARGS).append("[").append(i2).append("]").append(" = ").append(formatObject(objArr[i2])).append(LINE_SEP);
                }
                str = stringBuilder.toString();
            }
        }
        return str.length() == 0 ? NOTHING : str;
    }

    private static String formatObject(int i, Object obj) {
        if (obj == null) {
            return NULL;
        }
        if (i == 32) {
            return LogFormatter.formatJson(obj.toString());
        }
        if (i == 48) {
            return LogFormatter.formatXml(obj.toString());
        }
        return formatObject(obj);
    }

    private static String formatObject(Object obj) {
        if (obj == null) {
            return NULL;
        }
        if (!I_FORMATTER_MAP.isEmpty()) {
            IFormatter iFormatter = (IFormatter) I_FORMATTER_MAP.get(getClassFromObject(obj));
            if (iFormatter != null) {
                return iFormatter.format(obj);
            }
        }
        if (obj.getClass().isArray()) {
            return LogFormatter.array2String(obj);
        }
        if (obj instanceof Throwable) {
            return LogFormatter.throwable2String((Throwable) obj);
        }
        if (obj instanceof Bundle) {
            return LogFormatter.bundle2String((Bundle) obj);
        }
        if (obj instanceof Intent) {
            return LogFormatter.intent2String((Intent) obj);
        }
        return obj.toString();
    }

    private static void print2Console(int i, String str, String[] strArr, String str2) {
        if (CONFIG.mSingleTagSwitch) {
            printSingleTagMsg(i, str, processSingleTagMsg(i, str, strArr, str2));
            return;
        }
        printBorder(i, str, true);
        printHead(i, str, strArr);
        printMsg(i, str, str2);
        printBorder(i, str, false);
    }

    private static void printBorder(int i, String str, boolean z) {
        if (CONFIG.mLogBorderSwitch) {
            Log.println(i, str, z ? TOP_BORDER : BOTTOM_BORDER);
        }
    }

    private static void printHead(int i, String str, String[] strArr) {
        if (strArr != null) {
            for (String str2 : strArr) {
                String str22;
                if (CONFIG.mLogBorderSwitch) {
                    str22 = LEFT_BORDER + str22;
                }
                Log.println(i, str, str22);
            }
            if (CONFIG.mLogBorderSwitch) {
                Log.println(i, str, MIDDLE_BORDER);
            }
        }
    }

    private static void printMsg(int i, String str, String str2) {
        int i2 = 0;
        int length = str2.length();
        int i3 = length / 3000;
        if (i3 > 0) {
            int i4 = 0;
            while (i2 < i3) {
                printSubMsg(i, str, str2.substring(i4, i4 + 3000));
                i4 += 3000;
                i2++;
            }
            if (i4 != length) {
                printSubMsg(i, str, str2.substring(i4, length));
                return;
            }
            return;
        }
        printSubMsg(i, str, str2);
    }

    private static void printSubMsg(int i, String str, String str2) {
        if (CONFIG.mLogBorderSwitch) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str3 : str2.split(LINE_SEP)) {
                Log.println(i, str, LEFT_BORDER + str3);
            }
            return;
        }
        Log.println(i, str, str2);
    }

    private static String processSingleTagMsg(int i, String str, String[] strArr, String str2) {
        int i2 = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PLACEHOLDER).append(LINE_SEP);
        int length;
        if (CONFIG.mLogBorderSwitch) {
            stringBuilder.append(TOP_BORDER).append(LINE_SEP);
            if (strArr != null) {
                for (String append : strArr) {
                    stringBuilder.append(LEFT_BORDER).append(append).append(LINE_SEP);
                }
                stringBuilder.append(MIDDLE_BORDER).append(LINE_SEP);
            }
            String[] split = str2.split(LINE_SEP);
            int length2 = split.length;
            while (i2 < length2) {
                stringBuilder.append(LEFT_BORDER).append(split[i2]).append(LINE_SEP);
                i2++;
            }
            stringBuilder.append(BOTTOM_BORDER);
        } else {
            if (strArr != null) {
                length = strArr.length;
                while (i2 < length) {
                    stringBuilder.append(strArr[i2]).append(LINE_SEP);
                    i2++;
                }
            }
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }

    private static void printSingleTagMsg(int i, String str, String str2) {
        int i2 = 1;
        int i3 = 3000;
        int length = str2.length();
        int i4 = length / 3000;
        if (i4 <= 0) {
            Log.println(i, str, str2);
        } else if (CONFIG.mLogBorderSwitch) {
            Log.println(i, str, str2.substring(0, 3000) + LINE_SEP + BOTTOM_BORDER);
            while (i2 < i4) {
                Log.println(i, str, PLACEHOLDER + LINE_SEP + TOP_BORDER + LINE_SEP + LEFT_BORDER + str2.substring(i3, i3 + 3000) + LINE_SEP + BOTTOM_BORDER);
                i3 += 3000;
                i2++;
            }
            if (i3 != length) {
                Log.println(i, str, PLACEHOLDER + LINE_SEP + TOP_BORDER + LINE_SEP + LEFT_BORDER + str2.substring(i3, length));
            }
        } else {
            Log.println(i, str, str2.substring(0, 3000));
            while (i2 < i4) {
                Log.println(i, str, PLACEHOLDER + LINE_SEP + str2.substring(i3, i3 + 3000));
                i3 += 3000;
                i2++;
            }
            if (i3 != length) {
                Log.println(i, str, PLACEHOLDER + LINE_SEP + str2.substring(i3, length));
            }
        }
    }

    private static void print2File(int i, String str, String str2) {
        String format = FORMAT.format(new Date(System.currentTimeMillis()));
        String substring = format.substring(0, 10);
        String substring2 = format.substring(11);
        format = (CONFIG.mDir == null ? CONFIG.mDefaultDir : CONFIG.mDir) + CONFIG.mFilePrefix + "-" + substring + ".txt";
        if (createOrExistsFile(format)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(substring2).append(T[i - 2]).append(HttpUtils.PATHS_SEPARATOR).append(str).append(str2).append(LINE_SEP);
            input2File(stringBuilder.toString(), format);
            return;
        }
        Log.e("LogUtils", "create " + format + " failed!");
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            deleteDueLogs(str);
            boolean createNewFile = file.createNewFile();
            if (createNewFile) {
                printDeviceInfo(str);
            }
            return createNewFile;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void deleteDueLogs(String str) {
        File[] listFiles = new File(str).getParentFile().listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.matches("^" + LogUtils.CONFIG.mFilePrefix + "-[0-9]{4}-[0-9]{2}-[0-9]{2}.txt$");
            }
        });
        if (listFiles.length > 0) {
            int length = str.length();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                long time = simpleDateFormat.parse(str.substring(length - 14, length - 4)).getTime() - (((long) CONFIG.mSaveDays) * LogBuilder.MAX_INTERVAL);
                for (final File file : listFiles) {
                    String name = file.getName();
                    int length2 = name.length();
                    if (simpleDateFormat.parse(name.substring(length2 - 14, length2 - 4)).getTime() <= time) {
                        EXECUTOR.execute(new Runnable() {
                            public void run() {
                                if (!file.delete()) {
                                    Log.e("LogUtils", "delete " + file + " failed!");
                                }
                            }
                        });
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printDeviceInfo(String str) {
        int i = 0;
        String str2 = "";
        try {
            PackageInfo packageInfo = Utils.getApp().getPackageManager().getPackageInfo(Utils.getApp().getPackageName(), 0);
            if (packageInfo != null) {
                str2 = packageInfo.versionName;
                i = packageInfo.versionCode;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        input2File("************* Log Head ****************\nDate of Log        : " + str.substring(str.length() - 14, str.length() - 4) + "\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + VERSION.RELEASE + "\nAndroid SDK        : " + VERSION.SDK_INT + "\nApp VersionName    : " + str2 + "\nApp VersionCode    : " + i + "\n************* Log Head ****************\n\n", str);
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static void input2File(final String str, final String str2) {
        EXECUTOR.execute(new Runnable() {
            public void run() {
                IOException e;
                Throwable th;
                BufferedWriter bufferedWriter;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(str2, true));
                    try {
                        bufferedWriter.write(str);
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        try {
                            e2.printStackTrace();
                            Log.e("LogUtils", "log to " + str2 + " failed!");
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    bufferedWriter = null;
                    e22.printStackTrace();
                    Log.e("LogUtils", "log to " + str2 + " failed!");
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter = null;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    throw th;
                }
            }
        });
    }

    static <T> Class getTypeClassFromParadigm(IFormatter<T> iFormatter) {
        Type type;
        Type[] genericInterfaces = iFormatter.getClass().getGenericInterfaces();
        if (genericInterfaces.length == 1) {
            type = genericInterfaces[0];
        } else {
            type = iFormatter.getClass().getGenericSuperclass();
        }
        Object obj = ((ParameterizedType) type).getActualTypeArguments()[0];
        while (obj instanceof ParameterizedType) {
            obj = ((ParameterizedType) obj).getRawType();
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("class ")) {
            obj2 = obj2.substring(6);
        } else if (obj2.startsWith("interface ")) {
            obj2 = obj2.substring(10);
        }
        try {
            return Class.forName(obj2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Class getClassFromObject(Object obj) {
        Class cls = obj.getClass();
        if (cls.isAnonymousClass() || cls.isSynthetic()) {
            String obj2;
            Type[] genericInterfaces = cls.getGenericInterfaces();
            Object obj3;
            if (genericInterfaces.length == 1) {
                obj3 = genericInterfaces[0];
                while (obj3 instanceof ParameterizedType) {
                    obj3 = ((ParameterizedType) obj3).getRawType();
                }
                obj2 = obj3.toString();
            } else {
                obj3 = cls.getGenericSuperclass();
                while (obj3 instanceof ParameterizedType) {
                    obj3 = ((ParameterizedType) obj3).getRawType();
                }
                obj2 = obj3.toString();
            }
            if (obj2.startsWith("class ")) {
                obj2 = obj2.substring(6);
            } else if (obj2.startsWith("interface ")) {
                obj2 = obj2.substring(10);
            }
            try {
                return Class.forName(obj2);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cls;
    }
}
