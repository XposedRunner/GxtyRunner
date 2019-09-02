package com.autonavi.amap.mapcore.animation;

import com.amap.api.maps.model.animation.Animation;
import java.util.ArrayList;
import java.util.List;

public class GLAnimationSet extends GLAnimation {
    private static final int PROPERTY_CHANGE_BOUNDS_MASK = 128;
    private static final int PROPERTY_DURATION_MASK = 32;
    private static final int PROPERTY_FILL_AFTER_MASK = 1;
    private static final int PROPERTY_FILL_BEFORE_MASK = 2;
    private static final int PROPERTY_MORPH_MATRIX_MASK = 64;
    private static final int PROPERTY_REPEAT_MODE_MASK = 4;
    private static final int PROPERTY_SHARE_INTERPOLATOR_MASK = 16;
    private static final int PROPERTY_START_OFFSET_MASK = 8;
    private ArrayList<GLAnimation> mAnimations = new ArrayList();
    private boolean mDirty;
    private int mFlags = 0;
    private boolean mHasAlpha;
    private long mLastEnd;
    private GLTransformation mTempTransformation = new GLTransformation();

    public GLAnimationSet(boolean z) {
        setFlag(16, z);
        init();
    }

    public GLAnimationSet clone() throws CloneNotSupportedException {
        GLAnimationSet gLAnimationSet = (GLAnimationSet) super.clone();
        gLAnimationSet.mTempTransformation = new GLTransformation();
        gLAnimationSet.mAnimations = new ArrayList();
        int size = this.mAnimations.size();
        ArrayList arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            gLAnimationSet.mAnimations.add(((GLAnimation) arrayList.get(i)).clone());
        }
        return gLAnimationSet;
    }

    private void setFlag(int i, boolean z) {
        if (z) {
            this.mFlags |= i;
        } else {
            this.mFlags &= i ^ -1;
        }
    }

    private void init() {
        this.mStartTime = 0;
    }

    public void setFillAfter(boolean z) {
        this.mFlags |= 1;
        super.setFillAfter(z);
    }

    public void setFillBefore(boolean z) {
        this.mFlags |= 2;
        super.setFillBefore(z);
    }

    public void setRepeatMode(int i) {
        this.mFlags |= 4;
        super.setRepeatMode(i);
    }

    public void setStartOffset(long j) {
        this.mFlags |= 8;
        super.setStartOffset(j);
    }

    public boolean hasAlpha() {
        if (this.mDirty) {
            this.mHasAlpha = false;
            this.mDirty = false;
            int size = this.mAnimations.size();
            ArrayList arrayList = this.mAnimations;
            for (int i = 0; i < size; i++) {
                if (((GLAnimation) arrayList.get(i)).hasAlpha()) {
                    this.mHasAlpha = true;
                    break;
                }
            }
        }
        return this.mHasAlpha;
    }

    public void setDuration(long j) {
        this.mFlags |= 32;
        super.setDuration(j);
        this.mLastEnd = this.mStartOffset + this.mDuration;
    }

    public void addAnimation(Animation animation) {
        boolean z = false;
        this.mAnimations.add(animation.glAnimation);
        if (((this.mFlags & 64) == 0) && animation.glAnimation.willChangeTransformationMatrix()) {
            this.mFlags |= 64;
        }
        if ((this.mFlags & 128) == 0) {
            z = true;
        }
        if (z && animation.glAnimation.willChangeBounds()) {
            this.mFlags |= 128;
        }
        if ((this.mFlags & 32) == 32) {
            this.mLastEnd = this.mStartOffset + this.mDuration;
        } else if (this.mAnimations.size() == 1) {
            this.mDuration = animation.glAnimation.getStartOffset() + animation.glAnimation.getDuration();
            this.mLastEnd = this.mStartOffset + this.mDuration;
        } else {
            this.mLastEnd = Math.max(this.mLastEnd, animation.glAnimation.getStartOffset() + animation.glAnimation.getDuration());
            this.mDuration = this.mLastEnd - this.mStartOffset;
        }
        this.mDirty = true;
    }

    public void setStartTime(long j) {
        super.setStartTime(j);
        int size = this.mAnimations.size();
        ArrayList arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            ((GLAnimation) arrayList.get(i)).setStartTime(j);
        }
    }

    public long getStartTime() {
        long j = Long.MAX_VALUE;
        int size = this.mAnimations.size();
        ArrayList arrayList = this.mAnimations;
        for (int i = 0; i < size; i++) {
            j = Math.min(j, ((GLAnimation) arrayList.get(i)).getStartTime());
        }
        return j;
    }

    public void restrictDuration(long j) {
        super.restrictDuration(j);
        ArrayList arrayList = this.mAnimations;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((GLAnimation) arrayList.get(i)).restrictDuration(j);
        }
    }

    public long getDuration() {
        ArrayList arrayList = this.mAnimations;
        int size = arrayList.size();
        long j = 0;
        if (((this.mFlags & 32) == 32 ? 1 : null) != null) {
            return this.mDuration;
        }
        for (int i = 0; i < size; i++) {
            j = Math.max(j, ((GLAnimation) arrayList.get(i)).getDuration());
        }
        return j;
    }

    public long computeDurationHint() {
        long j = 0;
        int size = this.mAnimations.size();
        ArrayList arrayList = this.mAnimations;
        int i = size - 1;
        while (i >= 0) {
            long computeDurationHint = ((GLAnimation) arrayList.get(i)).computeDurationHint();
            if (computeDurationHint <= j) {
                computeDurationHint = j;
            }
            i--;
            j = computeDurationHint;
        }
        return j;
    }

    public boolean getTransformation(long j, GLTransformation gLTransformation) {
        int size = this.mAnimations.size();
        ArrayList arrayList = this.mAnimations;
        GLTransformation gLTransformation2 = this.mTempTransformation;
        gLTransformation.clear();
        int i = size - 1;
        boolean z = true;
        Object obj = null;
        boolean z2 = false;
        while (i >= 0) {
            boolean z3;
            GLAnimation gLAnimation = (GLAnimation) arrayList.get(i);
            gLTransformation2.clear();
            if (gLAnimation.getTransformation(j, gLTransformation, getScaleFactor()) || z2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (obj != null || gLAnimation.hasStarted()) {
                obj = 1;
            } else {
                obj = null;
            }
            if (gLAnimation.hasEnded() && z) {
                z3 = true;
            } else {
                z3 = false;
            }
            i--;
            z = z3;
        }
        if (obj != null) {
            try {
                if (!this.mStarted) {
                    if (this.mListener != null) {
                        this.mListener.onAnimationStart();
                    }
                    this.mStarted = true;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        if (z != this.mEnded) {
            if (this.mListener != null) {
                this.mListener.onAnimationEnd();
            }
            this.mEnded = z;
        }
        return z2;
    }

    public void scaleCurrentDuration(float f) {
        ArrayList arrayList = this.mAnimations;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((GLAnimation) arrayList.get(i)).scaleCurrentDuration(f);
        }
    }

    public void reset() {
        super.reset();
    }

    public List<GLAnimation> getAnimations() {
        return this.mAnimations;
    }

    public boolean willChangeTransformationMatrix() {
        return (this.mFlags & 64) == 64;
    }

    public boolean willChangeBounds() {
        return (this.mFlags & 128) == 128;
    }

    public void cleanAnimation() {
        this.mAnimations.clear();
    }
}
