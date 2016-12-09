package thomaz.com.br.httpproject.suport;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import thomaz.com.br.httpproject.R;

/**
 * Created by thomaz on 27/01/16.
 */
public abstract class ResultRequest implements AsyncTaskListener {

    private Dialog dialogLoading;

    public ResultRequest(Activity activity, String message, int color) {
        this(new LoaderViewDefault(activity, message, color));
    }

    public ResultRequest(Activity activity, String message) {
        this(new LoaderViewDefault(activity, message));
    }

    public ResultRequest(Dialog dialogLoading) {
        this.dialogLoading = dialogLoading;
    }

    @Override
    public void onPreExecute() {
        if( dialogLoading != null ) dialogLoading.show();
    }

    @Override
    public abstract void onSuccess(JSONObject object, boolean success) throws Exception;

    @Override
    public void onFinally() {
        if( dialogLoading != null ) dialogLoading.cancel();
    }

    private static class LoaderViewDefault extends Dialog {

        public LoaderViewDefault(Context context, String message, int color) {
            this(context, message);

            AVLoadingIndicatorView indicatorView = (AVLoadingIndicatorView)
                    getWindow().findViewById(R.id.avloadingIndicatorView);
            indicatorView.setIndicatorColor(color);
        }

        public LoaderViewDefault(Context context, String message) {
            super(context);

            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.fragment_dialog_loader);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            TextView textViewMessage = (TextView) getWindow().findViewById(R.id.fragment_dialog_tv);
            textViewMessage.setText(message);
        }

    }

}

