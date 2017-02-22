package com.lecarlink.zframwork.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;

/**
 * 自定义控件基础类
 */
public abstract class BaseView extends View {

  /*** 资源类型-array **/
  public static final String ARRAY = "array";

  /*** 资源类型-attr **/
  public static final String ATTR = "attr";

  /*** 资源类型-anim **/
  public static final String ANIM = "anim";

  /*** 资源类型-bool **/
  public static final String BOOL = "bool";

  /*** 资源类型-color **/
  public static final String COLOR = "color";

  /*** 资源类型-dimen **/
  public static final String DIMEN = "dimen";

  /*** 资源类型-drawable **/
  public static final String DRAWABLE = "drawable";

  /*** 资源类型-id **/
  public static final String ID = "id";

  /*** 资源类型-id **/
  public static final String INTEGER = "integer";

  /*** 资源类型-layout **/
  public static final String LAYOUT = "layout";

  /*** 资源类型-drawable **/
  public static final String STRING = "string";

  /*** 资源类型-style **/
  public static final String STYLE = "style";

  /*** 资源类型-styleable **/
  public static final String STYLEABLE = "styleable";

  /** 应用的包名称 **/
  protected String PACKAGE_NAME = "";

  /** 上下文 **/
  protected Context mContext;

  /** 日志输出标志 **/
  protected final static String TAG = BaseView.class.getSimpleName();

  public BaseView(Context context) {
    this(context, null);
  }

  public BaseView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.mContext = context;
    PACKAGE_NAME = mContext.getPackageName();
  }

  /**
   * 获取屏幕宽度
   * 
   * @param mContext 上下文
   * @return
   */
  public static int gainScreenWidth(Context mContext) {
    // 获取屏幕分辨率
    DisplayMetrics displayMetrics = new DisplayMetrics();
    WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    windowManager.getDefaultDisplay().getMetrics(displayMetrics);

    return displayMetrics.widthPixels;
  }

  /**
   * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
   * 
   * @param context 环境
   * @param dipValue 需要转化的dip值
   * @return int 转化后的px值
   */
  public static int dipToPx(Context context, float dipValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dipValue * scale + 0.5f);
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dip
   * 
   * @param context 环境
   * @param pxValue 需要转换的像素值
   * @return int 转化后的dip值
   */
  public static int pxToDip(Context context, float pxValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (pxValue / scale + 0.5f);
  }

  /**
   * 将px值转换为sp值，保证文字大小不变
   * 
   * @param context
   * @param pxValue
   */
  public static int pxTosp(Context context, float pxValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (pxValue / fontScale + 0.5f);
  }

  /**
   * 将sp值转换为px值，保证文字大小不变
   * 
   * @param spValue
   * @param context （DisplayMetrics类中属性scaledDensity）
   */
  public static int spTopx(Context context, float spValue) {
    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
    return (int) (spValue * fontScale + 0.5f);
  }

  /**
   * 获取当前分辨率下指定单位对应的像素大小（根据设备信息） px,dip,sp -> px<br>
   * <br>
   * 
   * 用法:getRawSize(context,TypedValue.COMPLEX_UNIT_DIP,itemSpaceDp)<br>
   * <br>
   * 
   * 代码摘自：TextView.setTextSize()
   * 
   * @param mContext 上下文
   * @param unit TypedValue.COMPLEX_UNIT_*
   * @param size
   * @return
   */
  public static int gainRawSize(Context mContext, int unit, float size) {
    Resources r;
    if (mContext == null)
      r = Resources.getSystem();
    else
      r = mContext.getResources();

    return (int) TypedValue.applyDimension(unit, size, r.getDisplayMetrics());
  }

  /**
   * 获取资源文件id
   * 
   * @param mContext 上下文
   * @param resType 资源类型（drawable/string/layout/style/dimen/color/array等）
   * @param resName 资源文件名称
   * @return
   */
  public static int gainResId(Context mContext, String resType, String resName) {
    int result = -1;
    try {
      String packageName = mContext.getPackageName();
      result = mContext.getResources().getIdentifier(resName, resType, packageName);
    } catch (Exception e) {
      result = -1;
      Log.w(TAG, "获取资源文件失败，原因：" + e.getMessage());
    }

    return result;
  }
}
