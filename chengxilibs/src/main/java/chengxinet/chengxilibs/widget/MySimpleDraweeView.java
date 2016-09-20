package chengxinet.chengxilibs.widget;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import chengxinet.chengxilibs.R;
import chengxinet.chengxilibs.utils.SharedPreferencesHelper;
import chengxinet.chengxilibs.utils.ThumbnailUtil;

/**
 * Created by Jun on 2016/4/15.
 */
public class MySimpleDraweeView extends SimpleDraweeView {

    private int fail;

    private int unImg;

    private SharedPreferencesHelper mSPHelper;

    private OnSuccess onSuccess;

    private String url;

    private int loadCount = 0;

    public MySimpleDraweeView(Context context, GenericDraweeHierarchy hierarchy) {
        super(context, hierarchy);
        init();
    }

    public MySimpleDraweeView(Context context) {
        super(context);
        init();
    }

    public MySimpleDraweeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySimpleDraweeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MySimpleDraweeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {

        GenericDraweeHierarchy hierarchy = getHierarchy();
        hierarchy.setPlaceholderImage(ContextCompat.getDrawable(getContext(), R.drawable.icon_image_fail));

        hierarchy.setFailureImage(ContextCompat.getDrawable(getContext(),  R.drawable.icon_image_fail));

        mSPHelper = SharedPreferencesHelper.getInstance(getContext());

    }

    @Override
    public void setImageURI(final Uri uri) {
//        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
//                .setBackgroundColor(Color.WHITE)
//                .setDecodePreviewFrame(true)
//                .build();

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
//                .setImageDecodeOptions(decodeOptions)
//                .setAutoRotateEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
//                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(false)
                .setResizeOptions(new ResizeOptions(360, 360))
                .build();


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(true)
//                .setUri(uri)
                .setControllerListener(new BaseControllerListener<ImageInfo>() {

                    @Override
                    public void onFailure(String id, Throwable throwable) {
                        super.onFailure(id, throwable);
                        if(loadCount != 10) {
                            loadCount++;
                            String temp = appendHead(MySimpleDraweeView.this.url);

                            if (TextUtils.isEmpty(temp) || !(temp.endsWith(".jpg") || temp.endsWith(".png"))) return;

                            setImageURI(Uri.parse(temp));
                        }
                    }

                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        super.onFinalImageSet(id, imageInfo, animatable);
                        if (!isClickable()) {
                            setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (onSuccess != null) {
                                        onSuccess.callBack(getTag(), url);
                                    }
                                }
                            });
                        }
                    }
                })
                .setOldController(getController())
                .build();
        setController(controller);
    }

    public void setImageUrl(String url) {
        setImageUrl(url, null);
    }

    public void setImageUrl(String url, String quality) {
        this.url = url;
        String temp = appendHead(url);

        if (temp == null) return;

        Uri uri;
        if (TextUtils.isEmpty(quality)) {
            uri = Uri.parse(temp);
        } else {
            //        System.out.println(url);
//        System.out.println(quality);
//            System.out.println(ThumbnailUtil.getThum(temp, quality));
            uri = Uri.parse(ThumbnailUtil.getThum(temp, quality));
        }




        setImageURI(uri);
    }

    public interface OnSuccess {
        void callBack(Object tag, String uri);
    }

    public void setOnSuccess(OnSuccess onSuccess) {
        this.onSuccess = onSuccess;
    }


    private String appendHead(String url) {
        String temp = null;
        if (!TextUtils.isEmpty(url)) {
            if (url.startsWith("http")) {
                temp = url;
            } else {
                String head = mSPHelper.getString("ImageHeader");
                temp = head + url;
            }
        }

        return temp;
    }

}
