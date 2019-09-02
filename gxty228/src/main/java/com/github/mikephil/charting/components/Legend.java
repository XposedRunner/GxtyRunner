package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;

public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    @Deprecated
    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.mTextSize = Utils.convertDpToPixel(10.0f);
        this.mXOffset = Utils.convertDpToPixel(5.0f);
        this.mYOffset = Utils.convertDpToPixel(3.0f);
    }

    public Legend(LegendEntry[] legendEntryArr) {
        this();
        if (legendEntryArr == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = legendEntryArr;
    }

    @Deprecated
    public Legend(int[] iArr, String[] strArr) {
        this();
        if (iArr == null || strArr == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        } else if (iArr.length != strArr.length) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        } else {
            List arrayList = new ArrayList();
            for (int i = 0; i < Math.min(iArr.length, strArr.length); i++) {
                LegendEntry legendEntry = new LegendEntry();
                legendEntry.formColor = iArr[i];
                legendEntry.label = strArr[i];
                if (legendEntry.formColor == ColorTemplate.COLOR_SKIP) {
                    legendEntry.form = LegendForm.NONE;
                } else if (legendEntry.formColor == ColorTemplate.COLOR_NONE || legendEntry.formColor == 0) {
                    legendEntry.form = LegendForm.EMPTY;
                }
                arrayList.add(legendEntry);
            }
            this.mEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
        }
    }

    @Deprecated
    public Legend(List<Integer> list, List<String> list2) {
        this(Utils.convertIntegers(list), Utils.convertStrings(list2));
    }

    public void setEntries(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    public float getMaximumEntryWidth(Paint paint) {
        float f = 0.0f;
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormToTextSpace);
        LegendEntry[] legendEntryArr = this.mEntries;
        int length = legendEntryArr.length;
        int i = 0;
        float f2 = 0.0f;
        while (i < length) {
            LegendEntry legendEntry = legendEntryArr[i];
            float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? this.mFormSize : legendEntry.formSize);
            if (convertDpToPixel2 <= f) {
                convertDpToPixel2 = f;
            }
            String str = legendEntry.label;
            if (str == null) {
                f = f2;
            } else {
                f = (float) Utils.calcTextWidth(paint, str);
                if (f <= f2) {
                    f = f2;
                }
            }
            i++;
            f2 = f;
            f = convertDpToPixel2;
        }
        return (f2 + f) + convertDpToPixel;
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            String str = legendEntry.label;
            if (str != null) {
                float calcTextHeight = (float) Utils.calcTextHeight(paint, str);
                if (calcTextHeight > f) {
                    f = calcTextHeight;
                }
            }
        }
        return f;
    }

    @Deprecated
    public int[] getColors() {
        int[] iArr = new int[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            int i2 = this.mEntries[i].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : this.mEntries[i].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : this.mEntries[i].formColor;
            iArr[i] = i2;
        }
        return iArr;
    }

    @Deprecated
    public String[] getLabels() {
        String[] strArr = new String[this.mEntries.length];
        for (int i = 0; i < this.mEntries.length; i++) {
            strArr[i] = this.mEntries[i].label;
        }
        return strArr;
    }

    @Deprecated
    public int[] getExtraColors() {
        int[] iArr = new int[this.mExtraEntries.length];
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            int i2 = this.mExtraEntries[i].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : this.mExtraEntries[i].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : this.mExtraEntries[i].formColor;
            iArr[i] = i2;
        }
        return iArr;
    }

    @Deprecated
    public String[] getExtraLabels() {
        String[] strArr = new String[this.mExtraEntries.length];
        for (int i = 0; i < this.mExtraEntries.length; i++) {
            strArr[i] = this.mExtraEntries[i].label;
        }
        return strArr;
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    public void setExtra(List<LegendEntry> list) {
        this.mExtraEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public void setExtra(LegendEntry[] legendEntryArr) {
        if (legendEntryArr == null) {
            legendEntryArr = new LegendEntry[0];
        }
        this.mExtraEntries = legendEntryArr;
    }

    @Deprecated
    public void setExtra(List<Integer> list, List<String> list2) {
        setExtra(Utils.convertIntegers(list), Utils.convertStrings(list2));
    }

    public void setExtra(int[] iArr, String[] strArr) {
        List arrayList = new ArrayList();
        for (int i = 0; i < Math.min(iArr.length, strArr.length); i++) {
            LegendEntry legendEntry = new LegendEntry();
            legendEntry.formColor = iArr[i];
            legendEntry.label = strArr[i];
            if (legendEntry.formColor == ColorTemplate.COLOR_SKIP || legendEntry.formColor == 0) {
                legendEntry.form = LegendForm.NONE;
            } else if (legendEntry.formColor == ColorTemplate.COLOR_NONE) {
                legendEntry.form = LegendForm.EMPTY;
            }
            arrayList.add(legendEntry);
        }
        this.mExtraEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public void setCustom(LegendEntry[] legendEntryArr) {
        this.mEntries = legendEntryArr;
        this.mIsLegendCustom = true;
    }

    public void setCustom(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
        this.mIsLegendCustom = true;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    @Deprecated
    public LegendPosition getPosition() {
        if (this.mOrientation == LegendOrientation.VERTICAL && this.mHorizontalAlignment == LegendHorizontalAlignment.CENTER && this.mVerticalAlignment == LegendVerticalAlignment.CENTER) {
            return LegendPosition.PIECHART_CENTER;
        }
        if (this.mOrientation == LegendOrientation.HORIZONTAL) {
            if (this.mVerticalAlignment == LegendVerticalAlignment.TOP) {
                if (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) {
                    return LegendPosition.ABOVE_CHART_LEFT;
                }
                return this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER;
            } else if (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) {
                return LegendPosition.BELOW_CHART_LEFT;
            } else {
                return this.mHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER;
            }
        } else if (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) {
            if (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) {
                return LegendPosition.LEFT_OF_CHART_INSIDE;
            }
            return this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART;
        } else if (this.mVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) {
            return LegendPosition.RIGHT_OF_CHART_INSIDE;
        } else {
            return this.mVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART;
        }
    }

    @Deprecated
    public void setPosition(LegendPosition legendPosition) {
        LegendHorizontalAlignment legendHorizontalAlignment;
        switch (legendPosition) {
            case LEFT_OF_CHART:
            case LEFT_OF_CHART_INSIDE:
            case LEFT_OF_CHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
                this.mVerticalAlignment = legendPosition == LegendPosition.LEFT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case RIGHT_OF_CHART:
            case RIGHT_OF_CHART_INSIDE:
            case RIGHT_OF_CHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
                this.mVerticalAlignment = legendPosition == LegendPosition.RIGHT_OF_CHART_CENTER ? LegendVerticalAlignment.CENTER : LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
            case ABOVE_CHART_LEFT:
            case ABOVE_CHART_CENTER:
            case ABOVE_CHART_RIGHT:
                legendHorizontalAlignment = legendPosition == LegendPosition.ABOVE_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.ABOVE_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mHorizontalAlignment = legendHorizontalAlignment;
                this.mVerticalAlignment = LegendVerticalAlignment.TOP;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case BELOW_CHART_LEFT:
            case BELOW_CHART_CENTER:
            case BELOW_CHART_RIGHT:
                legendHorizontalAlignment = legendPosition == LegendPosition.BELOW_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.BELOW_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                this.mHorizontalAlignment = legendHorizontalAlignment;
                this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
                this.mOrientation = LegendOrientation.HORIZONTAL;
                break;
            case PIECHART_CENTER:
                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
                this.mVerticalAlignment = LegendVerticalAlignment.CENTER;
                this.mOrientation = LegendOrientation.VERTICAL;
                break;
        }
        boolean z = legendPosition == LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
        this.mDrawInside = z;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public void setFormSize(float f) {
        this.mFormSize = f;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public void setFormLineWidth(float f) {
        this.mFormLineWidth = f;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public void setXEntrySpace(float f) {
        this.mXEntrySpace = f;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public void setYEntrySpace(float f) {
        this.mYEntrySpace = f;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public void setFormToTextSpace(float f) {
        this.mFormToTextSpace = f;
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public void setStackSpace(float f) {
        this.mStackSpace = f;
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public void setMaxSizePercent(float f) {
        this.mMaxSizePercent = f;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    public void calculateDimensions(Paint paint, ViewPortHandler viewPortHandler) {
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormSize);
        float convertDpToPixel2 = Utils.convertDpToPixel(this.mStackSpace);
        float convertDpToPixel3 = Utils.convertDpToPixel(this.mFormToTextSpace);
        float convertDpToPixel4 = Utils.convertDpToPixel(this.mXEntrySpace);
        float convertDpToPixel5 = Utils.convertDpToPixel(this.mYEntrySpace);
        boolean z = this.mWordWrapEnabled;
        LegendEntry[] legendEntryArr = this.mEntries;
        int length = legendEntryArr.length;
        this.mTextWidthMax = getMaximumEntryWidth(paint);
        this.mTextHeightMax = getMaximumEntryHeight(paint);
        float f;
        float f2;
        Object obj;
        float f3;
        float f4;
        switch (this.mOrientation) {
            case VERTICAL:
                f = 0.0f;
                f2 = 0.0f;
                float f5 = 0.0f;
                float lineHeight = Utils.getLineHeight(paint);
                Object obj2 = null;
                int i = 0;
                while (i < length) {
                    LegendEntry legendEntry = legendEntryArr[i];
                    obj = legendEntry.form != LegendForm.NONE ? 1 : null;
                    if (Float.isNaN(legendEntry.formSize)) {
                        f3 = convertDpToPixel;
                    } else {
                        f3 = Utils.convertDpToPixel(legendEntry.formSize);
                    }
                    String str = legendEntry.label;
                    if (obj2 == null) {
                        f5 = 0.0f;
                    }
                    if (obj != null) {
                        if (obj2 != null) {
                            f5 += convertDpToPixel2;
                        }
                        f5 += f3;
                    }
                    if (str != null) {
                        if (obj != null && obj2 == null) {
                            f3 = f5 + convertDpToPixel3;
                            f5 = f2;
                            obj = obj2;
                            f4 = f;
                        } else if (obj2 != null) {
                            f4 = Math.max(f, f5);
                            f5 = f2 + (lineHeight + convertDpToPixel5);
                            f3 = 0.0f;
                            obj = null;
                        } else {
                            obj = obj2;
                            f3 = f5;
                            f5 = f2;
                            f4 = f;
                        }
                        f3 += (float) Utils.calcTextWidth(paint, str);
                        if (i < length - 1) {
                            f2 = (lineHeight + convertDpToPixel5) + f5;
                        } else {
                            f2 = f5;
                        }
                    } else {
                        obj = 1;
                        f3 += f5;
                        if (i < length - 1) {
                            f3 += convertDpToPixel2;
                            f4 = f;
                        } else {
                            f4 = f;
                        }
                    }
                    f = Math.max(f4, f3);
                    i++;
                    obj2 = obj;
                    f5 = f3;
                }
                this.mNeededWidth = f;
                this.mNeededHeight = f2;
                break;
            case HORIZONTAL:
                int i2;
                float lineHeight2 = Utils.getLineHeight(paint);
                float lineSpacing = Utils.getLineSpacing(paint) + convertDpToPixel5;
                float contentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;
                f2 = 0.0f;
                f4 = 0.0f;
                int i3 = -1;
                this.mCalculatedLabelBreakPoints.clear();
                this.mCalculatedLabelSizes.clear();
                this.mCalculatedLineSizes.clear();
                int i4 = 0;
                convertDpToPixel5 = 0.0f;
                while (i4 < length) {
                    float f6;
                    int i5;
                    LegendEntry legendEntry2 = legendEntryArr[i4];
                    obj = legendEntry2.form != LegendForm.NONE ? 1 : null;
                    if (Float.isNaN(legendEntry2.formSize)) {
                        f3 = convertDpToPixel;
                    } else {
                        f3 = Utils.convertDpToPixel(legendEntry2.formSize);
                    }
                    String str2 = legendEntry2.label;
                    this.mCalculatedLabelBreakPoints.add(Boolean.valueOf(false));
                    if (i3 == -1) {
                        convertDpToPixel5 = 0.0f;
                    } else {
                        convertDpToPixel5 += convertDpToPixel2;
                    }
                    if (str2 != null) {
                        this.mCalculatedLabelSizes.add(Utils.calcTextSize(paint, str2));
                        f6 = obj != null ? convertDpToPixel3 + f3 : 0.0f;
                        i5 = i3;
                        f = ((FSize) this.mCalculatedLabelSizes.get(i4)).width + (convertDpToPixel5 + f6);
                    } else {
                        this.mCalculatedLabelSizes.add(FSize.getInstance(0.0f, 0.0f));
                        if (obj == null) {
                            f3 = 0.0f;
                        }
                        f6 = convertDpToPixel5 + f3;
                        if (i3 == -1) {
                            i5 = i4;
                            f = f6;
                        } else {
                            i5 = i3;
                            f = f6;
                        }
                    }
                    if (str2 != null || i4 == length - 1) {
                        if (f4 == 0.0f) {
                            f6 = 0.0f;
                        } else {
                            f6 = convertDpToPixel4;
                        }
                        if (!z || f4 == 0.0f || contentWidth - f4 >= f6 + f) {
                            f6 = (f6 + f) + f4;
                            f4 = f2;
                        } else {
                            this.mCalculatedLineSizes.add(FSize.getInstance(f4, lineHeight2));
                            f4 = Math.max(f2, f4);
                            List list = this.mCalculatedLabelBreakPoints;
                            if (i5 > -1) {
                                i2 = i5;
                            } else {
                                i2 = i4;
                            }
                            list.set(i2, Boolean.valueOf(true));
                            f6 = f;
                        }
                        if (i4 == length - 1) {
                            this.mCalculatedLineSizes.add(FSize.getInstance(f6, lineHeight2));
                            f4 = Math.max(f4, f6);
                        }
                    } else {
                        f6 = f4;
                        f4 = f2;
                    }
                    if (str2 != null) {
                        i5 = -1;
                    }
                    i4++;
                    convertDpToPixel5 = f;
                    f2 = f4;
                    f4 = f6;
                    i3 = i5;
                }
                this.mNeededWidth = f2;
                f3 = lineHeight2 * ((float) this.mCalculatedLineSizes.size());
                if (this.mCalculatedLineSizes.size() == 0) {
                    i2 = 0;
                } else {
                    i2 = this.mCalculatedLineSizes.size() - 1;
                }
                this.mNeededHeight = (((float) i2) * lineSpacing) + f3;
                break;
        }
        this.mNeededHeight += this.mYOffset;
        this.mNeededWidth += this.mXOffset;
    }
}
