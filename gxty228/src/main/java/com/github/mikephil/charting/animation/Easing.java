package com.github.mikephil.charting.animation;

public class Easing {

    private static class EasingFunctions {
        public static final EasingFunction EaseInBack = new EasingFunction() {
            public float getInterpolation(float f) {
                return (f * f) * ((2.70158f * f) - 1.70158f);
            }
        };
        public static final EasingFunction EaseInBounce = new EasingFunction() {
            public float getInterpolation(float f) {
                return 1.0f - EasingFunctions.EaseOutBounce.getInterpolation(1.0f - f);
            }
        };
        public static final EasingFunction EaseInCirc = new EasingFunction() {
            public float getInterpolation(float f) {
                return -(((float) Math.sqrt((double) (1.0f - (f * f)))) - 1.0f);
            }
        };
        public static final EasingFunction EaseInCubic = new EasingFunction() {
            public float getInterpolation(float f) {
                return (f * f) * f;
            }
        };
        public static final EasingFunction EaseInElastic = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f == 0.0f) {
                    return 0.0f;
                }
                if (f == 1.0f) {
                    return 1.0f;
                }
                float f2 = f - 1.0f;
                return -(((float) Math.sin((((double) (f2 - ((0.3f / 6.2831855f) * ((float) Math.asin(1.0d))))) * 6.283185307179586d) / ((double) 1050253722))) * ((float) Math.pow(2.0d, (double) (10.0f * f2))));
            }
        };
        public static final EasingFunction EaseInExpo = new EasingFunction() {
            public float getInterpolation(float f) {
                return f == 0.0f ? 0.0f : (float) Math.pow(2.0d, (double) (10.0f * (f - 1.0f)));
            }
        };
        public static final EasingFunction EaseInOutBack = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    float f3 = 1.70158f * 1.525f;
                    return (((f2 * (1.0f + f3)) - f3) * (f2 * f2)) * 0.5f;
                }
                f2 -= 2.0f;
                f3 = 1.70158f * 1.525f;
                return (((f3 + (f2 * (1.0f + f3))) * (f2 * f2)) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutBounce = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f < 0.5f) {
                    return EasingFunctions.EaseInBounce.getInterpolation(2.0f * f) * 0.5f;
                }
                return (EasingFunctions.EaseOutBounce.getInterpolation((2.0f * f) - 1.0f) * 0.5f) + 0.5f;
            }
        };
        public static final EasingFunction EaseInOutCirc = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return (((float) Math.sqrt((double) (1.0f - (f2 * f2)))) - 1.0f) * -0.5f;
                }
                f2 -= 2.0f;
                return (((float) Math.sqrt((double) (1.0f - (f2 * f2)))) + 1.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutCubic = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return f2 * ((0.5f * f2) * f2);
                }
                f2 -= 2.0f;
                return ((f2 * (f2 * f2)) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutElastic = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f == 0.0f) {
                    return 0.0f;
                }
                float f2 = f / 0.5f;
                if (f2 == 2.0f) {
                    return 1.0f;
                }
                float asin = (0.45000002f / 6.2831855f) * ((float) Math.asin(1.0d));
                if (f2 < 1.0f) {
                    f2 -= 1.0f;
                    return (((float) Math.sin((((double) ((f2 * 1.0f) - asin)) * 6.283185307179586d) / ((double) 1055286887))) * ((float) Math.pow(2.0d, (double) (10.0f * f2)))) * -0.5f;
                }
                f2 -= 1.0f;
                return ((((float) Math.sin((((double) ((f2 * 1.0f) - asin)) * 6.283185307179586d) / ((double) 1055286887))) * ((float) Math.pow(2.0d, (double) (-10.0f * f2)))) * 0.5f) + 1.0f;
            }
        };
        public static final EasingFunction EaseInOutExpo = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f == 0.0f) {
                    return 0.0f;
                }
                if (f == 1.0f) {
                    return 1.0f;
                }
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return ((float) Math.pow(2.0d, (double) ((f2 - 1.0f) * 10.0f))) * 0.5f;
                }
                return ((-((float) Math.pow(2.0d, (double) ((f2 - 1.0f) * -10.0f)))) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInOutQuad = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return f2 * (0.5f * f2);
                }
                f2 -= 1.0f;
                return ((f2 * (f2 - 2.0f)) - 1.0f) * -0.5f;
            }
        };
        public static final EasingFunction EaseInOutQuart = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f / 0.5f;
                if (f2 < 1.0f) {
                    return f2 * (((0.5f * f2) * f2) * f2);
                }
                f2 -= 2.0f;
                return ((f2 * ((f2 * f2) * f2)) - 2.0f) * -0.5f;
            }
        };
        public static final EasingFunction EaseInOutSine = new EasingFunction() {
            public float getInterpolation(float f) {
                return -0.5f * (((float) Math.cos(3.141592653589793d * ((double) f))) - 1.0f);
            }
        };
        public static final EasingFunction EaseInQuad = new EasingFunction() {
            public float getInterpolation(float f) {
                return f * f;
            }
        };
        public static final EasingFunction EaseInQuart = new EasingFunction() {
            public float getInterpolation(float f) {
                return ((f * f) * f) * f;
            }
        };
        public static final EasingFunction EaseInSine = new EasingFunction() {
            public float getInterpolation(float f) {
                return (-((float) Math.cos(((double) f) * 1.5707963267948966d))) + 1.0f;
            }
        };
        public static final EasingFunction EaseOutBack = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (((f2 * 2.70158f) + 1.70158f) * (f2 * f2)) + 1.0f;
            }
        };
        public static final EasingFunction EaseOutBounce = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f < 0.36363637f) {
                    return (7.5625f * f) * f;
                }
                float f2;
                if (f < 0.72727275f) {
                    f2 = f - 0.54545456f;
                    return (f2 * (7.5625f * f2)) + 0.75f;
                } else if (f < 0.90909094f) {
                    f2 = f - 0.8181818f;
                    return (f2 * (7.5625f * f2)) + 0.9375f;
                } else {
                    f2 = f - 0.95454544f;
                    return (f2 * (7.5625f * f2)) + 0.984375f;
                }
            }
        };
        public static final EasingFunction EaseOutCirc = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (float) Math.sqrt((double) (1.0f - (f2 * f2)));
            }
        };
        public static final EasingFunction EaseOutCubic = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * (f2 * f2)) + 1.0f;
            }
        };
        public static final EasingFunction EaseOutElastic = new EasingFunction() {
            public float getInterpolation(float f) {
                if (f == 0.0f) {
                    return 0.0f;
                }
                if (f == 1.0f) {
                    return 1.0f;
                }
                return (((float) Math.sin((((double) (f - ((0.3f / 6.2831855f) * ((float) Math.asin(1.0d))))) * 6.283185307179586d) / ((double) 1050253722))) * ((float) Math.pow(2.0d, (double) (-10.0f * f)))) + 1.0f;
            }
        };
        public static final EasingFunction EaseOutExpo = new EasingFunction() {
            public float getInterpolation(float f) {
                return f == 1.0f ? 1.0f : -((float) Math.pow(2.0d, (double) ((1.0f + f) * -10.0f)));
            }
        };
        public static final EasingFunction EaseOutQuad = new EasingFunction() {
            public float getInterpolation(float f) {
                return (-f) * (f - 2.0f);
            }
        };
        public static final EasingFunction EaseOutQuart = new EasingFunction() {
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return -((f2 * ((f2 * f2) * f2)) - 1.0f);
            }
        };
        public static final EasingFunction EaseOutSine = new EasingFunction() {
            public float getInterpolation(float f) {
                return (float) Math.sin(((double) f) * 1.5707963267948966d);
            }
        };
        public static final EasingFunction Linear = new EasingFunction() {
            public float getInterpolation(float f) {
                return f;
            }
        };

        private EasingFunctions() {
        }
    }

    public enum EasingOption {
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce
    }

    public static EasingFunction getEasingFunctionFromOption(EasingOption easingOption) {
        switch (easingOption) {
            case EaseInQuad:
                return EasingFunctions.EaseInQuad;
            case EaseOutQuad:
                return EasingFunctions.EaseOutQuad;
            case EaseInOutQuad:
                return EasingFunctions.EaseInOutQuad;
            case EaseInCubic:
                return EasingFunctions.EaseInCubic;
            case EaseOutCubic:
                return EasingFunctions.EaseOutCubic;
            case EaseInOutCubic:
                return EasingFunctions.EaseInOutCubic;
            case EaseInQuart:
                return EasingFunctions.EaseInQuart;
            case EaseOutQuart:
                return EasingFunctions.EaseOutQuart;
            case EaseInOutQuart:
                return EasingFunctions.EaseInOutQuart;
            case EaseInSine:
                return EasingFunctions.EaseInSine;
            case EaseOutSine:
                return EasingFunctions.EaseOutSine;
            case EaseInOutSine:
                return EasingFunctions.EaseInOutSine;
            case EaseInExpo:
                return EasingFunctions.EaseInExpo;
            case EaseOutExpo:
                return EasingFunctions.EaseOutExpo;
            case EaseInOutExpo:
                return EasingFunctions.EaseInOutExpo;
            case EaseInCirc:
                return EasingFunctions.EaseInCirc;
            case EaseOutCirc:
                return EasingFunctions.EaseOutCirc;
            case EaseInOutCirc:
                return EasingFunctions.EaseInOutCirc;
            case EaseInElastic:
                return EasingFunctions.EaseInElastic;
            case EaseOutElastic:
                return EasingFunctions.EaseOutElastic;
            case EaseInOutElastic:
                return EasingFunctions.EaseInOutElastic;
            case EaseInBack:
                return EasingFunctions.EaseInBack;
            case EaseOutBack:
                return EasingFunctions.EaseOutBack;
            case EaseInOutBack:
                return EasingFunctions.EaseInOutBack;
            case EaseInBounce:
                return EasingFunctions.EaseInBounce;
            case EaseOutBounce:
                return EasingFunctions.EaseOutBounce;
            case EaseInOutBounce:
                return EasingFunctions.EaseInOutBounce;
            default:
                return EasingFunctions.Linear;
        }
    }
}
