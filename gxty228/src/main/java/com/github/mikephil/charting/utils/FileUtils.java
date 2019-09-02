package com.github.mikephil.charting.utils;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String LOG = "MPChart-FileUtils";

    public static List<Entry> loadEntriesFromFile(String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        List<Entry> arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                String[] split = readLine.split("#");
                if (split.length <= 2) {
                    arrayList.add(new Entry(Float.parseFloat(split[0]), (float) Integer.parseInt(split[1])));
                } else {
                    float[] fArr = new float[(split.length - 1)];
                    for (int i = 0; i < fArr.length; i++) {
                        fArr[i] = Float.parseFloat(split[i]);
                    }
                    arrayList.add(new BarEntry((float) Integer.parseInt(split[split.length - 1]), fArr));
                }
            }
        } catch (IOException e) {
            Log.e(LOG, e.toString());
        }
        return arrayList;
    }

    public static List<Entry> loadEntriesFromAssets(AssetManager assetManager, String str) {
        IOException e;
        Throwable th;
        List<Entry> arrayList = new ArrayList();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    String[] split = readLine.split("#");
                    if (split.length <= 2) {
                        arrayList.add(new Entry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                    } else {
                        float[] fArr = new float[(split.length - 1)];
                        for (int i = 0; i < fArr.length; i++) {
                            fArr[i] = Float.parseFloat(split[i]);
                        }
                        arrayList.add(new BarEntry((float) Integer.parseInt(split[split.length - 1]), fArr));
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        Log.e(LOG, e2.toString());
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Log.e(LOG, e2.toString());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            Log.e(LOG, e22.toString());
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            Log.e(LOG, e4.toString());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedReader = null;
            Log.e(LOG, e22.toString());
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return arrayList;
    }

    public static void saveToSdCard(List<Entry> list, String str) {
        File file = new File(Environment.getExternalStorageDirectory(), str);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(LOG, e.toString());
            }
        }
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (Entry entry : list) {
                bufferedWriter.append(entry.getY() + "#" + entry.getX());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e2) {
            Log.e(LOG, e2.toString());
        }
    }

    public static List<BarEntry> loadBarEntriesFromAssets(AssetManager assetManager, String str) {
        IOException e;
        Throwable th;
        List<BarEntry> arrayList = new ArrayList();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(str), "UTF-8"));
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    String[] split = readLine.split("#");
                    arrayList.add(new BarEntry(Float.parseFloat(split[1]), Float.parseFloat(split[0])));
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        Log.e(LOG, e2.toString());
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Log.e(LOG, e2.toString());
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            Log.e(LOG, e22.toString());
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            Log.e(LOG, e4.toString());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedReader = null;
            Log.e(LOG, e22.toString());
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            throw th;
        }
        return arrayList;
    }
}
