/**
 *
 */
package com.yuliu.util;

import android.content.Context;

import java.lang.reflect.Field;

/**
 */
public class ResourceUtil {
    public static int getLayoutId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
    }

    public static int getStringId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
    }

    public static int getDrawableId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
    }

    public static int getStyleId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "style", paramContext.getPackageName());
    }

    public static int getId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
    }

    public static int getColorId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "color", paramContext.getPackageName());
    }

    public static int getDimensId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "dimen", paramContext.getPackageName());
    }

    /**
     * @param paramContext
     * @param paramString
     * @return
     * @author Hanyonglu@duoku.com
     */
    public static int getAttrId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "attr", paramContext.getPackageName());
    }

    /**
     * @param paramContext
     * @param paramString
     * @return
     */
    public static int getStyleableId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "styleable", paramContext.getPackageName());
    }

    /**
     * @param context
     * @param title
     * @return
     */
    public static final int[] getResourceDeclareStyleableIntArray(Context context, String name) {
        try {
            Field[] fields2 = Class.forName(context.getPackageName() + ".R$styleable").getFields();
            for (Field f : fields2) {
                if (f.getName().equals(name)) {
                    int[] ret = (int[]) f.get(null);
                    return ret;
                }
            }
        } catch (Throwable t) {

        }

        return null;
    }
}