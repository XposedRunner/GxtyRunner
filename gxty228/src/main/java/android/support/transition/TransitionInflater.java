package android.support.transition;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
    private static final ArrayMap<String, Constructor> CONSTRUCTORS = new ArrayMap();
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class};
    private final Context mContext;

    private TransitionInflater(@NonNull Context context) {
        this.mContext = context;
    }

    public static TransitionInflater from(Context context) {
        return new TransitionInflater(context);
    }

    public Transition inflateTransition(int i) {
        XmlResourceParser xml = this.mContext.getResources().getXml(i);
        try {
            Transition createTransitionFromXml = createTransitionFromXml(xml, Xml.asAttributeSet(xml), null);
            xml.close();
            return createTransitionFromXml;
        } catch (Throwable e) {
            throw new InflateException(e.getMessage(), e);
        } catch (Throwable e2) {
            throw new InflateException(xml.getPositionDescription() + ": " + e2.getMessage(), e2);
        } catch (Throwable th) {
            xml.close();
        }
    }

    public TransitionManager inflateTransitionManager(int i, ViewGroup viewGroup) {
        InflateException inflateException;
        XmlResourceParser xml = this.mContext.getResources().getXml(i);
        try {
            TransitionManager createTransitionManagerFromXml = createTransitionManagerFromXml(xml, Xml.asAttributeSet(xml), viewGroup);
            xml.close();
            return createTransitionManagerFromXml;
        } catch (Throwable e) {
            inflateException = new InflateException(e.getMessage());
            inflateException.initCause(e);
            throw inflateException;
        } catch (Throwable e2) {
            inflateException = new InflateException(xml.getPositionDescription() + ": " + e2.getMessage());
            inflateException.initCause(e2);
            throw inflateException;
        } catch (Throwable th) {
            xml.close();
        }
    }

    private Transition createTransitionFromXml(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        TransitionSet transitionSet = transition instanceof TransitionSet ? (TransitionSet) transition : null;
        Transition transition2 = null;
        while (true) {
            int next = xmlPullParser.next();
            if ((next != 3 || xmlPullParser.getDepth() > depth) && next != 1) {
                if (next == 2) {
                    Transition fade;
                    String name = xmlPullParser.getName();
                    if ("fade".equals(name)) {
                        fade = new Fade(this.mContext, attributeSet);
                    } else if ("changeBounds".equals(name)) {
                        fade = new ChangeBounds(this.mContext, attributeSet);
                    } else if ("slide".equals(name)) {
                        fade = new Slide(this.mContext, attributeSet);
                    } else if ("explode".equals(name)) {
                        fade = new Explode(this.mContext, attributeSet);
                    } else if ("changeImageTransform".equals(name)) {
                        fade = new ChangeImageTransform(this.mContext, attributeSet);
                    } else if ("changeTransform".equals(name)) {
                        fade = new ChangeTransform(this.mContext, attributeSet);
                    } else if ("changeClipBounds".equals(name)) {
                        fade = new ChangeClipBounds(this.mContext, attributeSet);
                    } else if ("autoTransition".equals(name)) {
                        fade = new AutoTransition(this.mContext, attributeSet);
                    } else if ("changeScroll".equals(name)) {
                        fade = new ChangeScroll(this.mContext, attributeSet);
                    } else if ("transitionSet".equals(name)) {
                        fade = new TransitionSet(this.mContext, attributeSet);
                    } else if ("transition".equals(name)) {
                        fade = (Transition) createCustom(attributeSet, Transition.class, "transition");
                    } else if ("targets".equals(name)) {
                        getTargetIds(xmlPullParser, attributeSet, transition);
                        fade = transition2;
                    } else if ("arcMotion".equals(name)) {
                        if (transition == null) {
                            throw new RuntimeException("Invalid use of arcMotion element");
                        }
                        transition.setPathMotion(new ArcMotion(this.mContext, attributeSet));
                        fade = transition2;
                    } else if ("pathMotion".equals(name)) {
                        if (transition == null) {
                            throw new RuntimeException("Invalid use of pathMotion element");
                        }
                        transition.setPathMotion((PathMotion) createCustom(attributeSet, PathMotion.class, "pathMotion"));
                        fade = transition2;
                    } else if (!"patternPathMotion".equals(name)) {
                        throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                    } else if (transition == null) {
                        throw new RuntimeException("Invalid use of patternPathMotion element");
                    } else {
                        transition.setPathMotion(new PatternPathMotion(this.mContext, attributeSet));
                        fade = transition2;
                    }
                    if (fade != null) {
                        if (!xmlPullParser.isEmptyElementTag()) {
                            createTransitionFromXml(xmlPullParser, attributeSet, fade);
                        }
                        if (transitionSet != null) {
                            transitionSet.addTransition(fade);
                            fade = null;
                        } else if (transition != null) {
                            throw new InflateException("Could not add transition to another transition.");
                        }
                    }
                    transition2 = fade;
                }
            }
        }
        return transition2;
    }

    private Object createCustom(AttributeSet attributeSet, Class cls, String str) {
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        if (attributeValue == null) {
            throw new InflateException(str + " tag must have a 'class' attribute");
        }
        try {
            Object newInstance;
            synchronized (CONSTRUCTORS) {
                Constructor constructor = (Constructor) CONSTRUCTORS.get(attributeValue);
                if (constructor == null) {
                    Class asSubclass = this.mContext.getClassLoader().loadClass(attributeValue).asSubclass(cls);
                    if (asSubclass != null) {
                        constructor = asSubclass.getConstructor(CONSTRUCTOR_SIGNATURE);
                        constructor.setAccessible(true);
                        CONSTRUCTORS.put(attributeValue, constructor);
                    }
                }
                newInstance = constructor.newInstance(new Object[]{this.mContext, attributeSet});
            }
            return newInstance;
        } catch (Throwable e) {
            throw new InflateException("Could not instantiate " + cls + " class " + attributeValue, e);
        }
    }

    private void getTargetIds(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                if (xmlPullParser.getName().equals("target")) {
                    TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_TARGET);
                    next = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "targetId", 1, 0);
                    if (next != 0) {
                        transition.addTarget(next);
                    } else {
                        next = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "excludeId", 2, 0);
                        if (next != 0) {
                            transition.excludeTarget(next, true);
                        } else {
                            String namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetName", 4);
                            if (namedString != null) {
                                transition.addTarget(namedString);
                            } else {
                                namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeName", 5);
                                if (namedString != null) {
                                    transition.excludeTarget(namedString, true);
                                } else {
                                    namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "excludeClass", 3);
                                    if (namedString != null) {
                                        try {
                                            transition.excludeTarget(Class.forName(namedString), true);
                                        } catch (Throwable e) {
                                            obtainStyledAttributes.recycle();
                                            throw new RuntimeException("Could not create " + namedString, e);
                                        }
                                    }
                                    namedString = TypedArrayUtils.getNamedString(obtainStyledAttributes, xmlPullParser, "targetClass", 0);
                                    if (namedString != null) {
                                        transition.addTarget(Class.forName(namedString));
                                    }
                                }
                            }
                        }
                    }
                    obtainStyledAttributes.recycle();
                } else {
                    throw new RuntimeException("Unknown scene name: " + xmlPullParser.getName());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.transition.TransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6, android.view.ViewGroup r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r4 = this;
        r1 = r5.getDepth();
        r0 = 0;
    L_0x0005:
        r2 = r5.next();
        r3 = 3;
        if (r2 != r3) goto L_0x0012;
    L_0x000c:
        r3 = r5.getDepth();
        if (r3 <= r1) goto L_0x0055;
    L_0x0012:
        r3 = 1;
        if (r2 == r3) goto L_0x0055;
    L_0x0015:
        r3 = 2;
        if (r2 != r3) goto L_0x0005;
    L_0x0018:
        r2 = r5.getName();
        r3 = "transitionManager";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x002a;
    L_0x0024:
        r0 = new android.support.transition.TransitionManager;
        r0.<init>();
        goto L_0x0005;
    L_0x002a:
        r3 = "transition";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0038;
    L_0x0032:
        if (r0 == 0) goto L_0x0038;
    L_0x0034:
        r4.loadTransition(r6, r5, r7, r0);
        goto L_0x0005;
    L_0x0038:
        r0 = new java.lang.RuntimeException;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Unknown scene name: ";
        r1 = r1.append(r2);
        r2 = r5.getName();
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0055:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionInflater.createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):android.support.transition.TransitionManager");
    }

    private void loadTransition(AttributeSet attributeSet, XmlPullParser xmlPullParser, ViewGroup viewGroup, TransitionManager transitionManager) throws NotFoundException {
        Scene scene = null;
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_MANAGER);
        int namedResourceId = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "transition", 2, -1);
        int namedResourceId2 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "fromScene", 0, -1);
        Scene sceneForLayout = namedResourceId2 < 0 ? null : Scene.getSceneForLayout(viewGroup, namedResourceId2, this.mContext);
        int namedResourceId3 = TypedArrayUtils.getNamedResourceId(obtainStyledAttributes, xmlPullParser, "toScene", 1, -1);
        if (namedResourceId3 >= 0) {
            scene = Scene.getSceneForLayout(viewGroup, namedResourceId3, this.mContext);
        }
        if (namedResourceId >= 0) {
            Transition inflateTransition = inflateTransition(namedResourceId);
            if (inflateTransition != null) {
                if (scene == null) {
                    throw new RuntimeException("No toScene for transition ID " + namedResourceId);
                } else if (sceneForLayout == null) {
                    transitionManager.setTransition(scene, inflateTransition);
                } else {
                    transitionManager.setTransition(sceneForLayout, scene, inflateTransition);
                }
            }
        }
        obtainStyledAttributes.recycle();
    }
}
