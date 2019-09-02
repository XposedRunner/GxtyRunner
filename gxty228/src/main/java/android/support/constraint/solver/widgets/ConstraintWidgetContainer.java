package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintWidgetContainer extends WidgetContainer {
    static boolean ALLOW_ROOT_GROUP = true;
    private static final boolean DEBUG = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_CHAIN_OPTIMIZE = 0;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_SNAPSHOT = true;
    private static final boolean USE_THREAD = false;
    private boolean[] flags;
    protected LinearSystem mBackgroundSystem;
    private boolean mHeightMeasuredTooSmall;
    private ConstraintWidget[] mHorizontalChainsArray;
    private int mHorizontalChainsSize;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    private ConstraintWidget[] mVerticalChainsArray;
    private int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;
    int mWrapHeight;
    int mWrapWidth;

    public ConstraintWidgetContainer() {
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2, int i3, int i4) {
        super(i, i2, i3, i4);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int i, int i2) {
        super(i, i2);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
    }

    public String getType() {
        return "ConstraintLayout";
    }

    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer constraintWidgetContainer, String str, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2 = 0;
        Rectangle bounds = WidgetContainer.getBounds(arrayList);
        if (bounds.width == 0 || bounds.height == 0) {
            return null;
        }
        if (i > 0) {
            int min = Math.min(bounds.x, bounds.y);
            if (i > min) {
                i = min;
            }
            bounds.grow(i, i);
        }
        constraintWidgetContainer.setOrigin(bounds.x, bounds.y);
        constraintWidgetContainer.setDimension(bounds.width, bounds.height);
        constraintWidgetContainer.setDebugName(str);
        ConstraintWidget parent = ((ConstraintWidget) arrayList.get(0)).getParent();
        int size = arrayList.size();
        while (i2 < size) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i2);
            if (constraintWidget.getParent() == parent) {
                constraintWidgetContainer.add(constraintWidget);
                constraintWidget.setX(constraintWidget.getX() - bounds.x);
                constraintWidget.setY(constraintWidget.getY() - bounds.y);
            }
            i2++;
        }
        return constraintWidgetContainer;
    }

    public boolean addChildrenToSolver(LinearSystem linearSystem, int i) {
        boolean z;
        addToSolver(linearSystem, i);
        int size = this.mChildren.size();
        if (this.mOptimizationLevel != 2 && this.mOptimizationLevel != 4) {
            z = true;
        } else if (optimize(linearSystem)) {
            return false;
        } else {
            z = false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            if (constraintWidget instanceof ConstraintWidgetContainer) {
                DimensionBehaviour dimensionBehaviour = constraintWidget.mHorizontalDimensionBehaviour;
                DimensionBehaviour dimensionBehaviour2 = constraintWidget.mVerticalDimensionBehaviour;
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
                }
                constraintWidget.addToSolver(linearSystem, i);
                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
                }
                if (dimensionBehaviour2 == DimensionBehaviour.WRAP_CONTENT) {
                    constraintWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
                }
            } else {
                if (z) {
                    Optimizer.checkMatchParent(this, linearSystem, constraintWidget);
                }
                constraintWidget.addToSolver(linearSystem, i);
            }
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(linearSystem);
        }
        if (this.mVerticalChainsSize > 0) {
            applyVerticalChain(linearSystem);
        }
        return true;
    }

    private boolean optimize(LinearSystem linearSystem) {
        int i;
        int i2;
        int size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mHorizontalResolution = -1;
            constraintWidget.mVerticalResolution = -1;
            if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                constraintWidget.mHorizontalResolution = 1;
                constraintWidget.mVerticalResolution = 1;
            }
        }
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        while (!z) {
            boolean z2;
            int i6 = i3 + 1;
            int i7 = 0;
            i2 = 0;
            i = 0;
            while (i7 < size) {
                constraintWidget = (ConstraintWidget) this.mChildren.get(i7);
                if (constraintWidget.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, linearSystem, constraintWidget);
                    }
                }
                if (constraintWidget.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                        constraintWidget.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, linearSystem, constraintWidget);
                    }
                }
                if (constraintWidget.mVerticalResolution == -1) {
                    i++;
                }
                if (constraintWidget.mHorizontalResolution == -1) {
                    i3 = i2 + 1;
                } else {
                    i3 = i2;
                }
                i7++;
                i2 = i3;
            }
            if (i == 0 && i2 == 0) {
                z2 = true;
            } else if (i5 == i && r8 == i2) {
                z2 = true;
            } else {
                z2 = z;
            }
            i4 = i2;
            i5 = i;
            z = z2;
            i3 = i6;
        }
        int i8 = 0;
        i2 = 0;
        i = 0;
        while (i8 < size) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i8);
            if (constraintWidget.mHorizontalResolution == 1 || constraintWidget.mHorizontalResolution == -1) {
                i++;
            }
            if (constraintWidget.mVerticalResolution == 1 || constraintWidget.mVerticalResolution == -1) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
            }
            i8++;
            i2 = i3;
        }
        if (i == 0 && i2 == 0) {
            return true;
        }
        return false;
    }

    private void applyHorizontalChain(LinearSystem linearSystem) {
        for (int i = 0; i < this.mHorizontalChainsSize; i++) {
            ConstraintWidget constraintWidget = this.mHorizontalChainsArray[i];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(this.mHorizontalChainsArray[i], 0, this.flags);
            int drawX;
            ConstraintWidget constraintWidget2;
            if (this.flags[1]) {
                drawX = constraintWidget.getDrawX();
                while (constraintWidget != null) {
                    linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, drawX);
                    constraintWidget2 = constraintWidget.mRight.mTarget != null ? constraintWidget.mRight.mTarget.mOwner : null;
                    if (constraintWidget2 == null || constraintWidget2.mLeft.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner != constraintWidget) {
                        constraintWidget2 = null;
                    }
                    drawX += (constraintWidget.mLeft.getMargin() + constraintWidget.getWidth()) + constraintWidget.mRight.getMargin();
                    constraintWidget = constraintWidget2;
                }
            } else {
                Object obj = constraintWidget.mHorizontalChainStyle == 0 ? 1 : null;
                Object obj2 = constraintWidget.mHorizontalChainStyle == 2 ? 1 : null;
                Object obj3 = this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                if ((this.mOptimizationLevel == 2 || this.mOptimizationLevel == 8) && this.flags[0] && constraintWidget.mHorizontalChainFixedPosition && obj2 == null && obj3 == null && constraintWidget.mHorizontalChainStyle == 0) {
                    Optimizer.applyDirectResolutionHorizontalChain(this, linearSystem, countMatchConstraintsChainedWidgets, constraintWidget);
                } else if (countMatchConstraintsChainedWidgets == 0 || obj2 != null) {
                    r5 = null;
                    ConstraintWidget constraintWidget3 = constraintWidget;
                    while (constraintWidget3 != null && constraintWidget3.getVisibility() == 8) {
                        linearSystem.addEquality(constraintWidget3.mLeft.mSolverVariable, constraintWidget3.mLeft.mTarget.mSolverVariable, 0, 5);
                        linearSystem.addEquality(constraintWidget3.mRight.mSolverVariable, constraintWidget3.mLeft.mTarget.mSolverVariable, 0, 5);
                        constraintWidget2 = constraintWidget3.mRight.mTarget != null ? constraintWidget3.mRight.mTarget.mOwner : null;
                        if (constraintWidget2 != null && ((constraintWidget2 instanceof Guideline) || constraintWidget2.mLeft.mTarget == null || constraintWidget2.mLeft.mTarget.mOwner != constraintWidget3)) {
                            constraintWidget2 = null;
                        }
                        constraintWidget3 = constraintWidget2;
                    }
                    if (constraintWidget3 != null) {
                        ConstraintAnchor constraintAnchor;
                        ConstraintAnchor constraintAnchor2;
                        int margin;
                        int margin2;
                        SolverVariable solverVariable;
                        SolverVariable solverVariable2;
                        obj3 = null;
                        r15 = null;
                        ConstraintWidget constraintWidget4 = null;
                        ConstraintWidget constraintWidget5 = constraintWidget3;
                        while (constraintWidget5 != null) {
                            Object obj4;
                            ConstraintWidget constraintWidget6;
                            r6 = constraintWidget5.mRight.mTarget != null ? constraintWidget5.mRight.mTarget.mOwner : null;
                            if (r6 == null || r6.mLeft.mTarget == null || r6.mLeft.mTarget.mOwner != constraintWidget5) {
                                r15 = r6;
                                obj4 = 1;
                                constraintWidget6 = constraintWidget5;
                            } else {
                                while (obj3 == null && r6 != null && r6.getVisibility() == 8) {
                                    ConstraintWidget constraintWidget7;
                                    linearSystem.addEquality(r6.mLeft.mSolverVariable, r6.mLeft.mTarget.mSolverVariable, 0, 5);
                                    linearSystem.addEquality(r6.mRight.mSolverVariable, r6.mLeft.mTarget.mSolverVariable, 0, 5);
                                    if (r6.mRight.mTarget != null) {
                                        constraintWidget7 = r6.mRight.mTarget.mOwner;
                                    } else {
                                        constraintWidget7 = null;
                                    }
                                    if (constraintWidget7 == null || constraintWidget7.mLeft.mTarget == null || constraintWidget7.mLeft.mTarget.mOwner != r6) {
                                        obj3 = 1;
                                        r5 = r6;
                                    }
                                    r6 = constraintWidget7;
                                }
                                r15 = r6;
                                obj4 = obj3;
                                constraintWidget6 = r5;
                            }
                            if (obj2 != null) {
                                ConstraintAnchor constraintAnchor3 = constraintWidget5.mLeft;
                                r4 = constraintAnchor3.getMargin();
                                if (constraintWidget4 != null) {
                                    drawX = r4 + constraintWidget4.mRight.getMargin();
                                } else {
                                    drawX = r4;
                                }
                                r4 = 1;
                                if (constraintWidget3 != constraintWidget5) {
                                    r4 = 3;
                                }
                                linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, drawX, r4);
                                if (constraintWidget5.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    constraintAnchor = constraintWidget5.mRight;
                                    if (constraintWidget5.mMatchConstraintDefaultWidth == 1) {
                                        linearSystem.addEquality(constraintAnchor.mSolverVariable, constraintAnchor3.mSolverVariable, Math.max(constraintWidget5.mMatchConstraintMinWidth, constraintWidget5.getWidth()), 3);
                                    } else {
                                        linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, constraintAnchor3.mMargin, 3);
                                        linearSystem.addLowerThan(constraintAnchor.mSolverVariable, constraintAnchor3.mSolverVariable, constraintWidget5.mMatchConstraintMinWidth, 3);
                                    }
                                }
                            } else if (obj != null || obj4 == null || constraintWidget4 == null) {
                                if (obj != null || obj4 != null || constraintWidget4 != null) {
                                    ConstraintAnchor constraintAnchor4 = constraintWidget5.mLeft;
                                    constraintAnchor2 = constraintWidget5.mRight;
                                    margin = constraintAnchor4.getMargin();
                                    margin2 = constraintAnchor2.getMargin();
                                    linearSystem.addGreaterThan(constraintAnchor4.mSolverVariable, constraintAnchor4.mTarget.mSolverVariable, margin, 1);
                                    linearSystem.addLowerThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, -margin2, 1);
                                    solverVariable = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                    if (constraintWidget4 == null) {
                                        solverVariable = constraintWidget.mLeft.mTarget != null ? constraintWidget.mLeft.mTarget.mSolverVariable : null;
                                    }
                                    solverVariable2 = constraintWidget5.mParent == r15 ? r15.mRight.mSolverVariable : r15.mLeft.mSolverVariable;
                                    if (!(solverVariable == null || solverVariable2 == null)) {
                                        linearSystem.addCentering(constraintAnchor4.mSolverVariable, solverVariable, margin, 0.5f, solverVariable2, constraintAnchor2.mSolverVariable, margin2, 4);
                                    }
                                } else if (constraintWidget5.mLeft.mTarget == null) {
                                    linearSystem.addEquality(constraintWidget5.mLeft.mSolverVariable, constraintWidget5.getDrawX());
                                } else {
                                    linearSystem.addEquality(constraintWidget5.mLeft.mSolverVariable, constraintWidget.mLeft.mTarget.mSolverVariable, constraintWidget5.mLeft.getMargin(), 5);
                                }
                            } else if (constraintWidget5.mRight.mTarget == null) {
                                linearSystem.addEquality(constraintWidget5.mRight.mSolverVariable, constraintWidget5.getDrawRight());
                            } else {
                                linearSystem.addEquality(constraintWidget5.mRight.mSolverVariable, constraintWidget6.mRight.mTarget.mSolverVariable, -constraintWidget5.mRight.getMargin(), 5);
                            }
                            if (obj4 != null) {
                                constraintWidget2 = null;
                            } else {
                                constraintWidget2 = r15;
                            }
                            r5 = constraintWidget6;
                            constraintWidget4 = constraintWidget5;
                            constraintWidget5 = constraintWidget2;
                            obj3 = obj4;
                        }
                        if (obj2 != null) {
                            constraintAnchor = constraintWidget3.mLeft;
                            constraintAnchor2 = r5.mRight;
                            margin = constraintAnchor.getMargin();
                            margin2 = constraintAnchor2.getMargin();
                            solverVariable = constraintWidget.mLeft.mTarget != null ? constraintWidget.mLeft.mTarget.mSolverVariable : null;
                            solverVariable2 = r5.mParent == r15 ? r15.mRight.mSolverVariable : r15.mLeft.mSolverVariable;
                            if (!(solverVariable == null || solverVariable2 == null)) {
                                linearSystem.addLowerThan(constraintAnchor2.mSolverVariable, solverVariable2, -margin2, 1);
                                linearSystem.addCentering(constraintAnchor.mSolverVariable, solverVariable, margin, constraintWidget.mHorizontalBiasPercent, solverVariable2, constraintAnchor2.mSolverVariable, margin2, 4);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    float f = 0.0f;
                    r5 = null;
                    while (true) {
                        if (r5 == null || (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner == r5)) {
                            if (constraintWidget.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                                r4 = constraintWidget.mLeft.getMargin();
                                if (r5 != null) {
                                    r4 += r5.mRight.getMargin();
                                }
                                drawX = 3;
                                if (constraintWidget.mLeft.mTarget.mOwner.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    drawX = 2;
                                }
                                linearSystem.addGreaterThan(constraintWidget.mLeft.mSolverVariable, constraintWidget.mLeft.mTarget.mSolverVariable, r4, drawX);
                                r4 = constraintWidget.mRight.getMargin();
                                if (constraintWidget.mRight.mTarget.mOwner.mLeft.mTarget != null && constraintWidget.mRight.mTarget.mOwner.mLeft.mTarget.mOwner == constraintWidget) {
                                    r4 += constraintWidget.mRight.mTarget.mOwner.mLeft.getMargin();
                                }
                                drawX = 3;
                                if (constraintWidget.mRight.mTarget.mOwner.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    drawX = 2;
                                }
                                linearSystem.addLowerThan(constraintWidget.mRight.mSolverVariable, constraintWidget.mRight.mTarget.mSolverVariable, -r4, drawX);
                            } else {
                                f += constraintWidget.mHorizontalWeight;
                                linearSystem.addGreaterThan(constraintWidget.mRight.mSolverVariable, constraintWidget.mLeft.mSolverVariable, 0, 1);
                                linearSystem.addLowerThan(constraintWidget.mRight.mSolverVariable, constraintWidget.mRight.mTarget.mSolverVariable, 0, 1);
                            }
                            r5 = constraintWidget;
                            constraintWidget = constraintWidget.mRight.mTarget.mOwner;
                        }
                    }
                    if (countMatchConstraintsChainedWidgets == 1) {
                        r6 = this.mMatchConstraintsChainedWidgets[0];
                        r4 = r6.mLeft.getMargin();
                        if (r6.mLeft.mTarget != null) {
                            r4 += r6.mLeft.mTarget.getMargin();
                        }
                        drawX = r6.mRight.getMargin();
                        if (r6.mRight.mTarget != null) {
                            drawX += r6.mRight.mTarget.getMargin();
                        }
                        if (r6.mMatchConstraintDefaultWidth == 1) {
                            linearSystem.addGreaterThan(constraintWidget.mLeft.mSolverVariable, constraintWidget.mLeft.mTarget.mSolverVariable, r4, 1);
                            linearSystem.addLowerThan(constraintWidget.mRight.mSolverVariable, constraintWidget.mRight.mTarget.mSolverVariable, -drawX, 1);
                            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, constraintWidget.mLeft.mSolverVariable, constraintWidget.getWidth(), 2);
                        } else {
                            linearSystem.addEquality(r6.mLeft.mSolverVariable, r6.mLeft.mTarget.mSolverVariable, r4, 1);
                            linearSystem.addEquality(r6.mRight.mSolverVariable, r6.mRight.mTarget.mSolverVariable, -drawX, 1);
                        }
                    } else {
                        for (int i2 = 0; i2 < countMatchConstraintsChainedWidgets - 1; i2++) {
                            ConstraintWidget constraintWidget8 = this.mMatchConstraintsChainedWidgets[i2];
                            r15 = this.mMatchConstraintsChainedWidgets[i2 + 1];
                            SolverVariable solverVariable3 = constraintWidget8.mLeft.mSolverVariable;
                            SolverVariable solverVariable4 = constraintWidget8.mRight.mSolverVariable;
                            SolverVariable solverVariable5 = r15.mLeft.mSolverVariable;
                            SolverVariable solverVariable6 = r15.mRight.mSolverVariable;
                            r4 = constraintWidget8.mLeft.getMargin();
                            if (!(constraintWidget8.mLeft.mTarget == null || constraintWidget8.mLeft.mTarget.mOwner.mRight.mTarget == null || constraintWidget8.mLeft.mTarget.mOwner.mRight.mTarget.mOwner != constraintWidget8)) {
                                r4 += constraintWidget8.mLeft.mTarget.mOwner.mRight.getMargin();
                            }
                            linearSystem.addGreaterThan(solverVariable3, constraintWidget8.mLeft.mTarget.mSolverVariable, r4, 2);
                            r4 = constraintWidget8.mRight.getMargin();
                            if (!(constraintWidget8.mRight.mTarget == null || constraintWidget8.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget8.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget8)) {
                                r4 += constraintWidget8.mRight.mTarget.mOwner.mLeft.getMargin();
                            }
                            linearSystem.addLowerThan(solverVariable4, constraintWidget8.mRight.mTarget.mSolverVariable, -r4, 2);
                            if (i2 + 1 == countMatchConstraintsChainedWidgets - 1) {
                                r4 = r15.mLeft.getMargin();
                                if (!(r15.mLeft.mTarget == null || r15.mLeft.mTarget.mOwner.mRight.mTarget == null || r15.mLeft.mTarget.mOwner.mRight.mTarget.mOwner != r15)) {
                                    r4 += r15.mLeft.mTarget.mOwner.mRight.getMargin();
                                }
                                linearSystem.addGreaterThan(solverVariable5, r15.mLeft.mTarget.mSolverVariable, r4, 2);
                                r4 = r15.mRight.getMargin();
                                if (!(r15.mRight.mTarget == null || r15.mRight.mTarget.mOwner.mLeft.mTarget == null || r15.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != r15)) {
                                    r4 += r15.mRight.mTarget.mOwner.mLeft.getMargin();
                                }
                                linearSystem.addLowerThan(solverVariable6, r15.mRight.mTarget.mSolverVariable, -r4, 2);
                            }
                            if (constraintWidget.mMatchConstraintMaxWidth > 0) {
                                linearSystem.addLowerThan(solverVariable4, solverVariable3, constraintWidget.mMatchConstraintMaxWidth, 2);
                            }
                            ArrayRow createRow = linearSystem.createRow();
                            createRow.createRowEqualDimension(constraintWidget8.mHorizontalWeight, f, r15.mHorizontalWeight, solverVariable3, constraintWidget8.mLeft.getMargin(), solverVariable4, constraintWidget8.mRight.getMargin(), solverVariable5, r15.mLeft.getMargin(), solverVariable6, r15.mRight.getMargin());
                            linearSystem.addConstraint(createRow);
                        }
                    }
                }
            }
        }
    }

    private void applyVerticalChain(LinearSystem linearSystem) {
        for (int i = 0; i < this.mVerticalChainsSize; i++) {
            ConstraintWidget constraintWidget = this.mVerticalChainsArray[i];
            int countMatchConstraintsChainedWidgets = countMatchConstraintsChainedWidgets(this.mVerticalChainsArray[i], 1, this.flags);
            int drawY;
            ConstraintWidget constraintWidget2;
            if (this.flags[1]) {
                drawY = constraintWidget.getDrawY();
                while (constraintWidget != null) {
                    linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, drawY);
                    constraintWidget2 = constraintWidget.mBottom.mTarget != null ? constraintWidget.mBottom.mTarget.mOwner : null;
                    if (constraintWidget2 == null || constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner != constraintWidget) {
                        constraintWidget2 = null;
                    }
                    drawY += (constraintWidget.mTop.getMargin() + constraintWidget.getHeight()) + constraintWidget.mBottom.getMargin();
                    constraintWidget = constraintWidget2;
                }
            } else {
                Object obj = constraintWidget.mVerticalChainStyle == 0 ? 1 : null;
                Object obj2 = constraintWidget.mVerticalChainStyle == 2 ? 1 : null;
                Object obj3 = this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT ? 1 : null;
                if ((this.mOptimizationLevel == 2 || this.mOptimizationLevel == 8) && this.flags[0] && constraintWidget.mVerticalChainFixedPosition && obj2 == null && obj3 == null && constraintWidget.mVerticalChainStyle == 0) {
                    Optimizer.applyDirectResolutionVerticalChain(this, linearSystem, countMatchConstraintsChainedWidgets, constraintWidget);
                } else if (countMatchConstraintsChainedWidgets == 0 || obj2 != null) {
                    r5 = null;
                    ConstraintWidget constraintWidget3 = constraintWidget;
                    while (constraintWidget3 != null && constraintWidget3.getVisibility() == 8) {
                        linearSystem.addEquality(constraintWidget3.mTop.mSolverVariable, constraintWidget3.mTop.mTarget.mSolverVariable, 0, 5);
                        linearSystem.addEquality(constraintWidget3.mBottom.mSolverVariable, constraintWidget3.mTop.mTarget.mSolverVariable, 0, 5);
                        constraintWidget2 = constraintWidget3.mBottom.mTarget != null ? constraintWidget3.mBottom.mTarget.mOwner : null;
                        if (constraintWidget2 != null && ((constraintWidget2 instanceof Guideline) || constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner != constraintWidget3)) {
                            constraintWidget2 = null;
                        }
                        constraintWidget3 = constraintWidget2;
                    }
                    if (constraintWidget3 != null) {
                        int margin;
                        SolverVariable solverVariable;
                        ConstraintAnchor constraintAnchor;
                        ConstraintAnchor constraintAnchor2;
                        int margin2;
                        SolverVariable solverVariable2;
                        obj3 = null;
                        r15 = null;
                        ConstraintWidget constraintWidget4 = null;
                        ConstraintWidget constraintWidget5 = constraintWidget3;
                        while (constraintWidget5 != null) {
                            Object obj4;
                            ConstraintWidget constraintWidget6;
                            ConstraintWidget constraintWidget7 = constraintWidget5.mBottom.mTarget != null ? constraintWidget5.mBottom.mTarget.mOwner : null;
                            if (constraintWidget7 == null || constraintWidget7.mTop.mTarget == null || constraintWidget7.mTop.mTarget.mOwner != constraintWidget5) {
                                r15 = constraintWidget7;
                                obj4 = 1;
                                constraintWidget6 = constraintWidget5;
                            } else {
                                while (obj3 == null && constraintWidget7 != null && constraintWidget7.getVisibility() == 8) {
                                    linearSystem.addEquality(constraintWidget7.mTop.mSolverVariable, constraintWidget7.mTop.mTarget.mSolverVariable, 0, 5);
                                    linearSystem.addEquality(constraintWidget7.mBottom.mSolverVariable, constraintWidget7.mTop.mTarget.mSolverVariable, 0, 5);
                                    if (constraintWidget7.mBottom.mTarget != null) {
                                        r7 = constraintWidget7.mBottom.mTarget.mOwner;
                                    } else {
                                        r7 = null;
                                    }
                                    if (r7 == null || r7.mTop.mTarget == null || r7.mTop.mTarget.mOwner != constraintWidget7) {
                                        obj3 = 1;
                                        r5 = constraintWidget7;
                                    }
                                    constraintWidget7 = r7;
                                }
                                r15 = constraintWidget7;
                                obj4 = obj3;
                                constraintWidget6 = r5;
                            }
                            if (obj2 != null) {
                                ConstraintAnchor constraintAnchor3 = constraintWidget5.mTop;
                                margin = constraintAnchor3.getMargin();
                                if (constraintWidget4 != null) {
                                    margin += constraintWidget4.mBottom.getMargin();
                                }
                                r4 = 1;
                                if (constraintWidget3 != constraintWidget5) {
                                    r4 = 3;
                                }
                                solverVariable = null;
                                SolverVariable solverVariable3 = null;
                                if (constraintAnchor3.mTarget != null) {
                                    solverVariable = constraintAnchor3.mSolverVariable;
                                    solverVariable3 = constraintAnchor3.mTarget.mSolverVariable;
                                } else if (constraintWidget5.mBaseline.mTarget != null) {
                                    solverVariable = constraintWidget5.mBaseline.mSolverVariable;
                                    solverVariable3 = constraintWidget5.mBaseline.mTarget.mSolverVariable;
                                    margin -= constraintAnchor3.getMargin();
                                }
                                if (!(solverVariable == null || solverVariable3 == null)) {
                                    linearSystem.addGreaterThan(solverVariable, solverVariable3, margin, r4);
                                }
                                if (constraintWidget5.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    constraintAnchor = constraintWidget5.mBottom;
                                    if (constraintWidget5.mMatchConstraintDefaultHeight == 1) {
                                        linearSystem.addEquality(constraintAnchor.mSolverVariable, constraintAnchor3.mSolverVariable, Math.max(constraintWidget5.mMatchConstraintMinHeight, constraintWidget5.getHeight()), 3);
                                    } else {
                                        linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, constraintAnchor3.mMargin, 3);
                                        linearSystem.addLowerThan(constraintAnchor.mSolverVariable, constraintAnchor3.mSolverVariable, constraintWidget5.mMatchConstraintMinHeight, 3);
                                    }
                                }
                            } else if (obj != null || obj4 == null || constraintWidget4 == null) {
                                if (obj != null || obj4 != null || constraintWidget4 != null) {
                                    ConstraintAnchor constraintAnchor4 = constraintWidget5.mTop;
                                    constraintAnchor2 = constraintWidget5.mBottom;
                                    margin = constraintAnchor4.getMargin();
                                    margin2 = constraintAnchor2.getMargin();
                                    linearSystem.addGreaterThan(constraintAnchor4.mSolverVariable, constraintAnchor4.mTarget.mSolverVariable, margin, 1);
                                    linearSystem.addLowerThan(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, -margin2, 1);
                                    solverVariable = constraintAnchor4.mTarget != null ? constraintAnchor4.mTarget.mSolverVariable : null;
                                    if (constraintWidget4 == null) {
                                        solverVariable = constraintWidget.mTop.mTarget != null ? constraintWidget.mTop.mTarget.mSolverVariable : null;
                                    }
                                    solverVariable2 = constraintWidget5.mParent == r15 ? r15.mBottom.mSolverVariable : r15.mTop.mSolverVariable;
                                    if (!(solverVariable == null || solverVariable2 == null)) {
                                        linearSystem.addCentering(constraintAnchor4.mSolverVariable, solverVariable, margin, 0.5f, solverVariable2, constraintAnchor2.mSolverVariable, margin2, 3);
                                    }
                                } else if (constraintWidget5.mTop.mTarget == null) {
                                    linearSystem.addEquality(constraintWidget5.mTop.mSolverVariable, constraintWidget5.getDrawY());
                                } else {
                                    linearSystem.addEquality(constraintWidget5.mTop.mSolverVariable, constraintWidget.mTop.mTarget.mSolverVariable, constraintWidget5.mTop.getMargin(), 5);
                                }
                            } else if (constraintWidget5.mBottom.mTarget == null) {
                                linearSystem.addEquality(constraintWidget5.mBottom.mSolverVariable, constraintWidget5.getDrawBottom());
                            } else {
                                linearSystem.addEquality(constraintWidget5.mBottom.mSolverVariable, constraintWidget6.mBottom.mTarget.mSolverVariable, -constraintWidget5.mBottom.getMargin(), 5);
                            }
                            if (obj4 != null) {
                                constraintWidget2 = null;
                            } else {
                                constraintWidget2 = r15;
                            }
                            r5 = constraintWidget6;
                            constraintWidget4 = constraintWidget5;
                            constraintWidget5 = constraintWidget2;
                            obj3 = obj4;
                        }
                        if (obj2 != null) {
                            constraintAnchor = constraintWidget3.mTop;
                            constraintAnchor2 = r5.mBottom;
                            margin = constraintAnchor.getMargin();
                            margin2 = constraintAnchor2.getMargin();
                            solverVariable = constraintWidget.mTop.mTarget != null ? constraintWidget.mTop.mTarget.mSolverVariable : null;
                            solverVariable2 = r5.mParent == r15 ? r15.mBottom.mSolverVariable : r15.mTop.mSolverVariable;
                            if (!(solverVariable == null || solverVariable2 == null)) {
                                linearSystem.addLowerThan(constraintAnchor2.mSolverVariable, solverVariable2, -margin2, 1);
                                linearSystem.addCentering(constraintAnchor.mSolverVariable, solverVariable, margin, constraintWidget.mVerticalBiasPercent, solverVariable2, constraintAnchor2.mSolverVariable, margin2, 4);
                            }
                        }
                    } else {
                        return;
                    }
                } else {
                    float f = 0.0f;
                    r5 = null;
                    r7 = constraintWidget;
                    while (true) {
                        if (r5 == null || (r7.mTop.mTarget != null && r7.mTop.mTarget.mOwner == r5)) {
                            if (r7.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                                r4 = r7.mTop.getMargin();
                                if (r5 != null) {
                                    r4 += r5.mBottom.getMargin();
                                }
                                drawY = 3;
                                if (r7.mTop.mTarget.mOwner.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    drawY = 2;
                                }
                                linearSystem.addGreaterThan(r7.mTop.mSolverVariable, r7.mTop.mTarget.mSolverVariable, r4, drawY);
                                r4 = r7.mBottom.getMargin();
                                if (r7.mBottom.mTarget.mOwner.mTop.mTarget != null && r7.mBottom.mTarget.mOwner.mTop.mTarget.mOwner == r7) {
                                    r4 += r7.mBottom.mTarget.mOwner.mTop.getMargin();
                                }
                                drawY = 3;
                                if (r7.mBottom.mTarget.mOwner.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                                    drawY = 2;
                                }
                                linearSystem.addLowerThan(r7.mBottom.mSolverVariable, r7.mBottom.mTarget.mSolverVariable, -r4, drawY);
                            } else {
                                f += r7.mVerticalWeight;
                                linearSystem.addGreaterThan(r7.mBottom.mSolverVariable, r7.mTop.mSolverVariable, 0, 1);
                                linearSystem.addLowerThan(r7.mBottom.mSolverVariable, r7.mBottom.mTarget.mSolverVariable, 0, 1);
                            }
                            r5 = r7;
                            r7 = r7.mBottom.mTarget.mOwner;
                        }
                    }
                    if (countMatchConstraintsChainedWidgets == 1) {
                        r5 = this.mMatchConstraintsChainedWidgets[0];
                        r4 = r5.mTop.getMargin();
                        if (r5.mTop.mTarget != null) {
                            r4 += r5.mTop.mTarget.getMargin();
                        }
                        linearSystem.addEquality(r5.mTop.mSolverVariable, r5.mTop.mTarget.mSolverVariable, r4, 1);
                        r4 = r5.mBottom.getMargin();
                        if (r5.mBottom.mTarget != null) {
                            r4 += r5.mBottom.mTarget.getMargin();
                        }
                        linearSystem.addEquality(r5.mBottom.mSolverVariable, r5.mBottom.mTarget.mSolverVariable, -r4, 1);
                    } else {
                        for (int i2 = 0; i2 < countMatchConstraintsChainedWidgets - 1; i2++) {
                            ConstraintWidget constraintWidget8 = this.mMatchConstraintsChainedWidgets[i2];
                            r15 = this.mMatchConstraintsChainedWidgets[i2 + 1];
                            SolverVariable solverVariable4 = constraintWidget8.mTop.mSolverVariable;
                            SolverVariable solverVariable5 = constraintWidget8.mBottom.mSolverVariable;
                            SolverVariable solverVariable6 = r15.mTop.mSolverVariable;
                            SolverVariable solverVariable7 = r15.mBottom.mSolverVariable;
                            r4 = constraintWidget8.mTop.getMargin();
                            if (!(constraintWidget8.mTop.mTarget == null || constraintWidget8.mTop.mTarget.mOwner.mBottom.mTarget == null || constraintWidget8.mTop.mTarget.mOwner.mBottom.mTarget.mOwner != constraintWidget8)) {
                                r4 += constraintWidget8.mTop.mTarget.mOwner.mBottom.getMargin();
                            }
                            linearSystem.addGreaterThan(solverVariable4, constraintWidget8.mTop.mTarget.mSolverVariable, r4, 2);
                            r4 = constraintWidget8.mBottom.getMargin();
                            if (!(constraintWidget8.mBottom.mTarget == null || constraintWidget8.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget8.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget8)) {
                                r4 += constraintWidget8.mBottom.mTarget.mOwner.mTop.getMargin();
                            }
                            linearSystem.addLowerThan(solverVariable5, constraintWidget8.mBottom.mTarget.mSolverVariable, -r4, 2);
                            if (i2 + 1 == countMatchConstraintsChainedWidgets - 1) {
                                r4 = r15.mTop.getMargin();
                                if (!(r15.mTop.mTarget == null || r15.mTop.mTarget.mOwner.mBottom.mTarget == null || r15.mTop.mTarget.mOwner.mBottom.mTarget.mOwner != r15)) {
                                    r4 += r15.mTop.mTarget.mOwner.mBottom.getMargin();
                                }
                                linearSystem.addGreaterThan(solverVariable6, r15.mTop.mTarget.mSolverVariable, r4, 2);
                                r4 = r15.mBottom.getMargin();
                                if (!(r15.mBottom.mTarget == null || r15.mBottom.mTarget.mOwner.mTop.mTarget == null || r15.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != r15)) {
                                    r4 += r15.mBottom.mTarget.mOwner.mTop.getMargin();
                                }
                                linearSystem.addLowerThan(solverVariable7, r15.mBottom.mTarget.mSolverVariable, -r4, 2);
                            }
                            ArrayRow createRow = linearSystem.createRow();
                            createRow.createRowEqualDimension(constraintWidget8.mVerticalWeight, f, r15.mVerticalWeight, solverVariable4, constraintWidget8.mTop.getMargin(), solverVariable5, constraintWidget8.mBottom.getMargin(), solverVariable6, r15.mTop.getMargin(), solverVariable7, r15.mBottom.getMargin());
                            linearSystem.addConstraint(createRow);
                        }
                    }
                }
            }
        }
    }

    public void updateChildrenFromSolver(LinearSystem linearSystem, int i, boolean[] zArr) {
        zArr[2] = false;
        updateFromSolver(linearSystem, i);
        int size = this.mChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
            constraintWidget.updateFromSolver(linearSystem, i);
            if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getWidth() < constraintWidget.getWrapWidth()) {
                zArr[2] = true;
            }
            if (constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                zArr[2] = true;
            }
        }
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public void layout() {
        boolean z;
        int size;
        int i;
        ConstraintWidget constraintWidget;
        int i2;
        boolean z2;
        int i3;
        int height;
        int i4 = this.mX;
        int i5 = this.mY;
        int width = getWidth();
        int height2 = getHeight();
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.mPaddingLeft);
            setY(this.mPaddingTop);
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        boolean z3 = false;
        DimensionBehaviour dimensionBehaviour = this.mVerticalDimensionBehaviour;
        DimensionBehaviour dimensionBehaviour2 = this.mHorizontalDimensionBehaviour;
        if (this.mOptimizationLevel == 2 && (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT || this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT)) {
            findWrapSize(this.mChildren, this.flags);
            z3 = this.flags[0];
            if (width > 0 && height2 > 0 && (this.mWrapWidth > width || this.mWrapHeight > height2)) {
                z3 = false;
            }
            if (z3) {
                if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                    if (width <= 0 || width >= this.mWrapWidth) {
                        setWidth(this.mWrapWidth);
                    } else {
                        this.mWidthMeasuredTooSmall = true;
                        setWidth(width);
                    }
                }
                if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT) {
                    this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                    if (height2 <= 0 || height2 >= this.mWrapHeight) {
                        setHeight(this.mWrapHeight);
                    } else {
                        this.mHeightMeasuredTooSmall = true;
                        setHeight(height2);
                        z = z3;
                        resetChains();
                        size = this.mChildren.size();
                        for (i = 0; i < size; i++) {
                            constraintWidget = (ConstraintWidget) this.mChildren.get(i);
                            if (constraintWidget instanceof WidgetContainer) {
                                ((WidgetContainer) constraintWidget).layout();
                            }
                        }
                        i2 = 0;
                        z2 = z;
                        z = true;
                        while (z) {
                            i3 = i2 + 1;
                            try {
                                this.mSystem.reset();
                                z = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
                                if (z) {
                                    this.mSystem.minimize();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (z) {
                                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                                while (height < size) {
                                    constraintWidget = (ConstraintWidget) this.mChildren.get(height);
                                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getWidth() >= constraintWidget.getWrapWidth()) {
                                        if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.getHeight() < constraintWidget.getWrapHeight()) {
                                            this.flags[2] = true;
                                            break;
                                        }
                                    } else {
                                        this.flags[2] = true;
                                        break;
                                    }
                                }
                            }
                            updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
                            if (i3 < 8 || !this.flags[2]) {
                                z3 = false;
                                z = z2;
                            } else {
                                int i6 = 0;
                                int i7 = 0;
                                for (int i8 = 0; i8 < size; i8++) {
                                    constraintWidget = (ConstraintWidget) this.mChildren.get(i8);
                                    i6 = Math.max(i6, constraintWidget.mX + constraintWidget.getWidth());
                                    i7 = Math.max(i7, constraintWidget.getHeight() + constraintWidget.mY);
                                }
                                if (dimensionBehaviour2 != DimensionBehaviour.WRAP_CONTENT || getWidth() >= i6) {
                                    z3 = false;
                                    z = z2;
                                } else {
                                    setWidth(i6);
                                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                                    z = true;
                                    z3 = true;
                                }
                                if (dimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && getHeight() < i7) {
                                    setHeight(i7);
                                    this.mVerticalDimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
                                    z = true;
                                    z3 = true;
                                }
                            }
                            if (!z) {
                                if (this.mHorizontalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && width > 0 && getWidth() > width) {
                                    this.mWidthMeasuredTooSmall = true;
                                    z = true;
                                    this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                                    setWidth(width);
                                    z3 = true;
                                }
                                if (this.mVerticalDimensionBehaviour == DimensionBehaviour.WRAP_CONTENT && height2 > 0 && getHeight() > height2) {
                                    this.mHeightMeasuredTooSmall = true;
                                    this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                                    setHeight(height2);
                                    z2 = true;
                                    z = true;
                                    i2 = i3;
                                }
                            }
                            z2 = z;
                            z = z3;
                            i2 = i3;
                        }
                        if (this.mParent == null) {
                            i2 = getWidth();
                            height = getHeight();
                            this.mSnapshot.applyTo(this);
                            setWidth((i2 + this.mPaddingLeft) + this.mPaddingRight);
                            setHeight((this.mPaddingTop + height) + this.mPaddingBottom);
                        } else {
                            this.mX = i4;
                            this.mY = i5;
                        }
                        if (z2) {
                            this.mHorizontalDimensionBehaviour = dimensionBehaviour2;
                            this.mVerticalDimensionBehaviour = dimensionBehaviour;
                        }
                        resetSolverVariables(this.mSystem.getCache());
                        if (this == getRootConstraintContainer()) {
                            updateDrawPosition();
                        }
                    }
                }
            }
        }
        z = z3;
        resetChains();
        size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        i2 = 0;
        z2 = z;
        z = true;
        while (z) {
            i3 = i2 + 1;
            this.mSystem.reset();
            z = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
            if (z) {
                this.mSystem.minimize();
            }
            if (z) {
                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                for (height = 0; height < size; height++) {
                    constraintWidget = (ConstraintWidget) this.mChildren.get(height);
                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    }
                    if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT) {
                    }
                }
            } else {
                updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
            }
            if (i3 < 8) {
            }
            z3 = false;
            z = z2;
            if (z) {
                this.mWidthMeasuredTooSmall = true;
                z = true;
                this.mHorizontalDimensionBehaviour = DimensionBehaviour.FIXED;
                setWidth(width);
                z3 = true;
                this.mHeightMeasuredTooSmall = true;
                this.mVerticalDimensionBehaviour = DimensionBehaviour.FIXED;
                setHeight(height2);
                z2 = true;
                z = true;
                i2 = i3;
            }
            z2 = z;
            z = z3;
            i2 = i3;
        }
        if (this.mParent == null) {
            this.mX = i4;
            this.mY = i5;
        } else {
            i2 = getWidth();
            height = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth((i2 + this.mPaddingLeft) + this.mPaddingRight);
            setHeight((this.mPaddingTop + height) + this.mPaddingBottom);
        }
        if (z2) {
            this.mHorizontalDimensionBehaviour = dimensionBehaviour2;
            this.mVerticalDimensionBehaviour = dimensionBehaviour;
        }
        resetSolverVariables(this.mSystem.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    static int setGroup(ConstraintAnchor constraintAnchor, int i) {
        int i2 = constraintAnchor.mGroup;
        if (constraintAnchor.mOwner.getParent() == null) {
            return i;
        }
        if (i2 <= i) {
            return i2;
        }
        constraintAnchor.mGroup = i;
        ConstraintAnchor opposite = constraintAnchor.getOpposite();
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        i2 = opposite != null ? setGroup(opposite, i) : i;
        if (constraintAnchor2 != null) {
            i2 = setGroup(constraintAnchor2, i2);
        }
        if (opposite != null) {
            i2 = setGroup(opposite, i2);
        }
        constraintAnchor.mGroup = i2;
        return i2;
    }

    public int layoutFindGroupsSimple() {
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            constraintWidget.mLeft.mGroup = 0;
            constraintWidget.mRight.mGroup = 0;
            constraintWidget.mTop.mGroup = 1;
            constraintWidget.mBottom.mGroup = 1;
            constraintWidget.mBaseline.mGroup = 1;
        }
        return 2;
    }

    public void findHorizontalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        ConstraintWidget constraintWidget2 = null;
        boolean z = false;
        if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        boolean optimizerWrapWidth = constraintWidget.getOptimizerWrapWidth();
        if (constraintWidget.mHorizontalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mDimensionRatio <= 0.0f) {
            int i;
            int i2;
            constraintWidget.mHorizontalWrapVisited = true;
            if (constraintWidget instanceof Guideline) {
                int i3;
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() != 1) {
                    i3 = optimizerWrapWidth;
                    z = optimizerWrapWidth;
                } else if (guideline.getRelativeBegin() != -1) {
                    i3 = guideline.getRelativeBegin();
                } else if (guideline.getRelativeEnd() != -1) {
                    optimizerWrapWidth = guideline.getRelativeEnd();
                    i3 = 0;
                    z = optimizerWrapWidth;
                } else {
                    i3 = 0;
                }
                i = i3;
                i2 = z;
            } else if (constraintWidget.mRight.isConnected() || constraintWidget.mLeft.isConnected()) {
                ConstraintWidget constraintWidget3;
                if (constraintWidget.mRight.mTarget != null) {
                    constraintWidget3 = constraintWidget.mRight.mTarget.mOwner;
                    i2 = constraintWidget.mRight.getMargin() + optimizerWrapWidth;
                    if (!(constraintWidget3.isRoot() || constraintWidget3.mHorizontalWrapVisited)) {
                        findHorizontalWrapRecursive(constraintWidget3, zArr);
                    }
                } else {
                    constraintWidget3 = null;
                    i2 = optimizerWrapWidth;
                }
                if (constraintWidget.mLeft.mTarget != null) {
                    constraintWidget2 = constraintWidget.mLeft.mTarget.mOwner;
                    i = optimizerWrapWidth + constraintWidget.mLeft.getMargin();
                    if (!(constraintWidget2.isRoot() || constraintWidget2.mHorizontalWrapVisited)) {
                        findHorizontalWrapRecursive(constraintWidget2, zArr);
                    }
                }
                if (!(constraintWidget.mRight.mTarget == null || constraintWidget3.isRoot())) {
                    boolean z2;
                    if (constraintWidget.mRight.mTarget.mType == Type.RIGHT) {
                        i2 += constraintWidget3.mDistToRight - constraintWidget3.getOptimizerWrapWidth();
                    } else if (constraintWidget.mRight.mTarget.getType() == Type.LEFT) {
                        i2 += constraintWidget3.mDistToRight;
                    }
                    if (constraintWidget3.mRightHasCentered || !(constraintWidget3.mLeft.mTarget == null || constraintWidget3.mRight.mTarget == null || constraintWidget3.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    constraintWidget.mRightHasCentered = z2;
                    if (constraintWidget.mRightHasCentered) {
                        if (constraintWidget3.mLeft.mTarget != null) {
                            if (constraintWidget3.mLeft.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i2 += i2 - constraintWidget3.mDistToRight;
                    }
                }
                if (!(constraintWidget.mLeft.mTarget == null || constraintWidget2.isRoot())) {
                    if (constraintWidget.mLeft.mTarget.getType() == Type.LEFT) {
                        i += constraintWidget2.mDistToLeft - constraintWidget2.getOptimizerWrapWidth();
                    } else if (constraintWidget.mLeft.mTarget.getType() == Type.RIGHT) {
                        i += constraintWidget2.mDistToLeft;
                    }
                    if (constraintWidget2.mLeftHasCentered || !(constraintWidget2.mLeft.mTarget == null || constraintWidget2.mRight.mTarget == null || constraintWidget2.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z = true;
                    }
                    constraintWidget.mLeftHasCentered = z;
                    if (constraintWidget.mLeftHasCentered) {
                        if (constraintWidget2.mRight.mTarget != null) {
                            if (constraintWidget2.mRight.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i += i - constraintWidget2.mDistToLeft;
                    }
                }
            } else {
                boolean z3 = optimizerWrapWidth;
                i = constraintWidget.getX() + optimizerWrapWidth;
            }
            if (constraintWidget.getVisibility() == 8) {
                i -= constraintWidget.mWidth;
                i2 -= constraintWidget.mWidth;
            }
            constraintWidget.mDistToLeft = i;
            constraintWidget.mDistToRight = i2;
            return;
        }
        zArr[0] = false;
    }

    public void findVerticalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        ConstraintWidget constraintWidget2 = null;
        boolean z = false;
        if (constraintWidget.mVerticalDimensionBehaviour != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.mDimensionRatio <= 0.0f) {
            int i;
            int i2;
            boolean optimizerWrapHeight = constraintWidget.getOptimizerWrapHeight();
            constraintWidget.mVerticalWrapVisited = true;
            int i3;
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() != 0) {
                    i3 = optimizerWrapHeight;
                    z = optimizerWrapHeight;
                } else if (guideline.getRelativeBegin() != -1) {
                    optimizerWrapHeight = guideline.getRelativeBegin();
                    i3 = 0;
                    z = optimizerWrapHeight;
                } else {
                    i3 = guideline.getRelativeEnd() != -1 ? guideline.getRelativeEnd() : 0;
                }
                i = i3;
                i2 = z;
            } else if (constraintWidget.mBaseline.mTarget == null && constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                i2 = optimizerWrapHeight + constraintWidget.getY();
            } else if (constraintWidget.mBaseline.isConnected()) {
                r0 = constraintWidget.mBaseline.mTarget.getOwner();
                if (!r0.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(r0, zArr);
                }
                int max = Math.max((r0.mDistToTop - r0.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                i3 = Math.max((r0.mDistToBottom - r0.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                if (constraintWidget.getVisibility() == 8) {
                    max -= constraintWidget.mHeight;
                    i3 -= constraintWidget.mHeight;
                }
                constraintWidget.mDistToTop = max;
                constraintWidget.mDistToBottom = i3;
                return;
            } else {
                if (constraintWidget.mTop.isConnected()) {
                    r0 = constraintWidget.mTop.mTarget.getOwner();
                    i2 = constraintWidget.mTop.getMargin() + optimizerWrapHeight;
                    if (!(r0.isRoot() || r0.mVerticalWrapVisited)) {
                        findVerticalWrapRecursive(r0, zArr);
                    }
                } else {
                    r0 = null;
                    i2 = optimizerWrapHeight;
                }
                if (constraintWidget.mBottom.isConnected()) {
                    constraintWidget2 = constraintWidget.mBottom.mTarget.getOwner();
                    i = optimizerWrapHeight + constraintWidget.mBottom.getMargin();
                    if (!(constraintWidget2.isRoot() || constraintWidget2.mVerticalWrapVisited)) {
                        findVerticalWrapRecursive(constraintWidget2, zArr);
                    }
                }
                if (!(constraintWidget.mTop.mTarget == null || r0.isRoot())) {
                    boolean z2;
                    if (constraintWidget.mTop.mTarget.getType() == Type.TOP) {
                        i2 += r0.mDistToTop - r0.getOptimizerWrapHeight();
                    } else if (constraintWidget.mTop.mTarget.getType() == Type.BOTTOM) {
                        i2 += r0.mDistToTop;
                    }
                    if (r0.mTopHasCentered || !(r0.mTop.mTarget == null || r0.mTop.mTarget.mOwner == constraintWidget || r0.mBottom.mTarget == null || r0.mBottom.mTarget.mOwner == constraintWidget || r0.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    constraintWidget.mTopHasCentered = z2;
                    if (constraintWidget.mTopHasCentered) {
                        if (r0.mBottom.mTarget != null) {
                            if (r0.mBottom.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i2 += i2 - r0.mDistToTop;
                    }
                }
                if (!(constraintWidget.mBottom.mTarget == null || constraintWidget2.isRoot())) {
                    if (constraintWidget.mBottom.mTarget.getType() == Type.BOTTOM) {
                        i += constraintWidget2.mDistToBottom - constraintWidget2.getOptimizerWrapHeight();
                    } else if (constraintWidget.mBottom.mTarget.getType() == Type.TOP) {
                        i += constraintWidget2.mDistToBottom;
                    }
                    if (constraintWidget2.mBottomHasCentered || !(constraintWidget2.mTop.mTarget == null || constraintWidget2.mTop.mTarget.mOwner == constraintWidget || constraintWidget2.mBottom.mTarget == null || constraintWidget2.mBottom.mTarget.mOwner == constraintWidget || constraintWidget2.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT)) {
                        z = true;
                    }
                    constraintWidget.mBottomHasCentered = z;
                    if (constraintWidget.mBottomHasCentered) {
                        if (constraintWidget2.mTop.mTarget != null) {
                            if (constraintWidget2.mTop.mTarget.mOwner != constraintWidget) {
                            }
                        }
                        i += i - constraintWidget2.mDistToBottom;
                    }
                }
            }
            if (constraintWidget.getVisibility() == 8) {
                i2 -= constraintWidget.mHeight;
                i -= constraintWidget.mHeight;
            }
            constraintWidget.mDistToTop = i2;
            constraintWidget.mDistToBottom = i;
            return;
        }
        zArr[0] = false;
    }

    public void findWrapSize(ArrayList<ConstraintWidget> arrayList, boolean[] zArr) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int size = arrayList.size();
        zArr[0] = true;
        int i7 = 0;
        while (i7 < size) {
            int i8;
            int i9;
            int i10;
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i7);
            if (constraintWidget.isRoot()) {
                i8 = i6;
                i9 = i5;
                i10 = i4;
                i6 = i3;
                i5 = i2;
                i4 = i;
            } else {
                if (!constraintWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(constraintWidget, zArr);
                }
                if (!constraintWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(constraintWidget, zArr);
                }
                if (zArr[0]) {
                    i9 = (constraintWidget.mDistToLeft + constraintWidget.mDistToRight) - constraintWidget.getWidth();
                    i10 = (constraintWidget.mDistToTop + constraintWidget.mDistToBottom) - constraintWidget.getHeight();
                    if (constraintWidget.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
                        i9 = (constraintWidget.getWidth() + constraintWidget.mLeft.mMargin) + constraintWidget.mRight.mMargin;
                    }
                    if (constraintWidget.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_PARENT) {
                        i10 = (constraintWidget.getHeight() + constraintWidget.mTop.mMargin) + constraintWidget.mBottom.mMargin;
                    }
                    if (constraintWidget.getVisibility() == 8) {
                        i9 = 0;
                        i10 = 0;
                    }
                    i2 = Math.max(i2, constraintWidget.mDistToLeft);
                    i3 = Math.max(i3, constraintWidget.mDistToRight);
                    i4 = Math.max(i4, constraintWidget.mDistToBottom);
                    i = Math.max(i, constraintWidget.mDistToTop);
                    i9 = Math.max(i5, i9);
                    i8 = Math.max(i6, i10);
                    i10 = i4;
                    i6 = i3;
                    i5 = i2;
                    i4 = i;
                } else {
                    return;
                }
            }
            i7++;
            i2 = i5;
            i = i4;
            i4 = i10;
            i3 = i6;
            i5 = i9;
            i6 = i8;
        }
        this.mWrapWidth = Math.max(Math.max(i2, i3), i5);
        this.mWrapHeight = Math.max(Math.max(i, i4), i6);
        for (i9 = 0; i9 < size; i9++) {
            constraintWidget = (ConstraintWidget) arrayList.get(i9);
            constraintWidget.mHorizontalWrapVisited = false;
            constraintWidget.mVerticalWrapVisited = false;
            constraintWidget.mLeftHasCentered = false;
            constraintWidget.mRightHasCentered = false;
            constraintWidget.mTopHasCentered = false;
            constraintWidget.mBottomHasCentered = false;
        }
    }

    public int layoutFindGroups() {
        int i;
        int i2;
        int i3;
        Type[] typeArr = new Type[]{Type.LEFT, Type.RIGHT, Type.TOP, Type.BASELINE, Type.BOTTOM};
        int i4 = 1;
        int size = this.mChildren.size();
        for (i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i4) == i4) {
                i4++;
            }
            constraintAnchor = constraintWidget.mTop;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i4) == i4) {
                i4++;
            }
            constraintAnchor = constraintWidget.mRight;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i4) == i4) {
                i4++;
            }
            constraintAnchor = constraintWidget.mBottom;
            if (constraintAnchor.mTarget == null) {
                constraintAnchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor, i4) == i4) {
                i4++;
            }
            ConstraintAnchor constraintAnchor2 = constraintWidget.mBaseline;
            if (constraintAnchor2.mTarget == null) {
                constraintAnchor2.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(constraintAnchor2, i4) == i4) {
                i4++;
            }
        }
        Object obj = 1;
        int i5 = 0;
        i = 0;
        while (obj != null) {
            obj = null;
            int i6 = i5 + 1;
            for (i2 = 0; i2 < size; i2++) {
                constraintWidget = (ConstraintWidget) this.mChildren.get(i2);
                for (Type type : typeArr) {
                    ConstraintAnchor constraintAnchor3 = null;
                    switch (type) {
                        case LEFT:
                            constraintAnchor3 = constraintWidget.mLeft;
                            break;
                        case TOP:
                            constraintAnchor3 = constraintWidget.mTop;
                            break;
                        case RIGHT:
                            constraintAnchor3 = constraintWidget.mRight;
                            break;
                        case BOTTOM:
                            constraintAnchor3 = constraintWidget.mBottom;
                            break;
                        case BASELINE:
                            constraintAnchor3 = constraintWidget.mBaseline;
                            break;
                    }
                    ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
                    if (constraintAnchor4 != null) {
                        if (!(constraintAnchor4.mOwner.getParent() == null || constraintAnchor4.mGroup == constraintAnchor3.mGroup)) {
                            i3 = constraintAnchor3.mGroup > constraintAnchor4.mGroup ? constraintAnchor4.mGroup : constraintAnchor3.mGroup;
                            constraintAnchor3.mGroup = i3;
                            constraintAnchor4.mGroup = i3;
                            i++;
                            obj = 1;
                        }
                        constraintAnchor4 = constraintAnchor4.getOpposite();
                        if (!(constraintAnchor4 == null || constraintAnchor4.mGroup == constraintAnchor3.mGroup)) {
                            i3 = constraintAnchor3.mGroup > constraintAnchor4.mGroup ? constraintAnchor4.mGroup : constraintAnchor3.mGroup;
                            constraintAnchor3.mGroup = i3;
                            constraintAnchor4.mGroup = i3;
                            i++;
                            obj = 1;
                        }
                    }
                }
            }
            i5 = i6;
        }
        i = 0;
        int[] iArr = new int[((this.mChildren.size() * typeArr.length) + 1)];
        Arrays.fill(iArr, -1);
        i3 = 0;
        while (i3 < size) {
            constraintWidget = (ConstraintWidget) this.mChildren.get(i3);
            ConstraintAnchor constraintAnchor5 = constraintWidget.mLeft;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i4 = i + 1;
                    iArr[i6] = i;
                } else {
                    i4 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            } else {
                i4 = i;
            }
            constraintAnchor5 = constraintWidget.mTop;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i4 + 1;
                    iArr[i6] = i4;
                    i4 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            constraintAnchor5 = constraintWidget.mRight;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i4 + 1;
                    iArr[i6] = i4;
                    i4 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            constraintAnchor5 = constraintWidget.mBottom;
            if (constraintAnchor5.mGroup != Integer.MAX_VALUE) {
                i6 = constraintAnchor5.mGroup;
                if (iArr[i6] == -1) {
                    i = i4 + 1;
                    iArr[i6] = i4;
                    i4 = i;
                }
                constraintAnchor5.mGroup = iArr[i6];
            }
            ConstraintAnchor constraintAnchor6 = constraintWidget.mBaseline;
            if (constraintAnchor6.mGroup != Integer.MAX_VALUE) {
                i2 = constraintAnchor6.mGroup;
                if (iArr[i2] == -1) {
                    i5 = i4 + 1;
                    iArr[i2] = i4;
                    i4 = i5;
                }
                constraintAnchor6.mGroup = iArr[i2];
            }
            i3++;
            i = i4;
        }
        return i;
    }

    public void layoutWithGroup(int i) {
        int i2 = 0;
        int i3 = this.mX;
        int i4 = this.mY;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            this.mX = 0;
            this.mY = 0;
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        int size = this.mChildren.size();
        for (int i5 = 0; i5 < size; i5++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i5);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).layout();
            }
        }
        this.mLeft.mGroup = 0;
        this.mRight.mGroup = 0;
        this.mTop.mGroup = 1;
        this.mBottom.mGroup = 1;
        this.mSystem.reset();
        while (i2 < i) {
            try {
                addToSolver(this.mSystem, i2);
                this.mSystem.minimize();
                updateFromSolver(this.mSystem, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateFromSolver(this.mSystem, -2);
            i2++;
        }
        if (this.mParent != null) {
            int width = getWidth();
            i2 = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth(width);
            setHeight(i2);
        } else {
            this.mX = i3;
            this.mY = i4;
        }
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 1) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> arrayList = new ArrayList();
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                if (guideline.getOrientation() == 0) {
                    arrayList.add(guideline);
                }
            }
        }
        return arrayList;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            while (constraintWidget.mLeft.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget != null && constraintWidget.mLeft.mTarget.mOwner.mRight.mTarget == constraintWidget.mLeft && constraintWidget.mLeft.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(constraintWidget);
        } else if (i == 1) {
            while (constraintWidget.mTop.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget != null && constraintWidget.mTop.mTarget.mOwner.mBottom.mTarget == constraintWidget.mTop && constraintWidget.mTop.mTarget.mOwner != constraintWidget) {
                constraintWidget = constraintWidget.mTop.mTarget.mOwner;
            }
            addVerticalChain(constraintWidget);
        }
    }

    private void addHorizontalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mHorizontalChainsSize) {
            if (this.mHorizontalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = constraintWidget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget constraintWidget) {
        int i = 0;
        while (i < this.mVerticalChainsSize) {
            if (this.mVerticalChainsArray[i] != constraintWidget) {
                i++;
            } else {
                return;
            }
        }
        if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = constraintWidget;
        this.mVerticalChainsSize++;
    }

    private int countMatchConstraintsChainedWidgets(ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        int i2;
        zArr[0] = true;
        zArr[1] = false;
        ConstraintWidget constraintWidget2;
        boolean z;
        ConstraintWidget constraintWidget3;
        int i3;
        ConstraintWidget constraintWidget4;
        if (i == 0) {
            if (constraintWidget.mLeft.mTarget == null || constraintWidget.mLeft.mTarget.mOwner == this) {
                constraintWidget2 = null;
                z = true;
                i2 = 0;
                constraintWidget3 = constraintWidget;
            } else {
                constraintWidget2 = null;
                z = false;
                i2 = 0;
                constraintWidget3 = constraintWidget;
            }
            while (constraintWidget3.mRight.mTarget != null) {
                if (constraintWidget3.getVisibility() != 8 && constraintWidget3.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget3.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget3.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        if (i2 + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        i3 = i2 + 1;
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget3;
                        i2 = i3;
                    }
                }
                if (constraintWidget3.mRight.mTarget.mOwner.mLeft.mTarget == null || constraintWidget3.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != constraintWidget3 || constraintWidget3.mRight.mTarget.mOwner == constraintWidget3) {
                    break;
                }
                constraintWidget4 = constraintWidget3.mRight.mTarget.mOwner;
                constraintWidget2 = constraintWidget4;
                constraintWidget3 = constraintWidget4;
            }
            if (!(constraintWidget3.mRight.mTarget == null || constraintWidget3.mRight.mTarget.mOwner == this)) {
                z = false;
            }
            if (constraintWidget.mLeft.mTarget == null || r1.mRight.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mHorizontalChainFixedPosition = z;
        } else {
            if (constraintWidget.mTop.mTarget == null || constraintWidget.mTop.mTarget.mOwner == this) {
                constraintWidget2 = null;
                z = true;
                i2 = 0;
                constraintWidget3 = constraintWidget;
            } else {
                constraintWidget2 = null;
                z = false;
                i2 = 0;
                constraintWidget3 = constraintWidget;
            }
            while (constraintWidget3.mBottom.mTarget != null) {
                if (constraintWidget3.getVisibility() != 8 && constraintWidget3.mVerticalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (constraintWidget3.mHorizontalDimensionBehaviour == DimensionBehaviour.MATCH_CONSTRAINT) {
                        zArr[0] = false;
                    }
                    if (constraintWidget3.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        if (i2 + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        i3 = i2 + 1;
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget3;
                        i2 = i3;
                    }
                }
                if (constraintWidget3.mBottom.mTarget.mOwner.mTop.mTarget == null || constraintWidget3.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != constraintWidget3 || constraintWidget3.mBottom.mTarget.mOwner == constraintWidget3) {
                    break;
                }
                constraintWidget4 = constraintWidget3.mBottom.mTarget.mOwner;
                constraintWidget2 = constraintWidget4;
                constraintWidget3 = constraintWidget4;
            }
            if (!(constraintWidget3.mBottom.mTarget == null || constraintWidget3.mBottom.mTarget.mOwner == this)) {
                z = false;
            }
            if (constraintWidget.mTop.mTarget == null || r1.mBottom.mTarget == null) {
                zArr[1] = true;
            }
            constraintWidget.mVerticalChainFixedPosition = z;
        }
        return i2;
    }
}
