package thomaz.com.br.httpproject.suport;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.StringRes;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.json.JSONObject;

import thomaz.com.br.httpproject.R;

/**
 * Created by thomaz on 27/01/16.
 */
public abstract class ResultRequest implements AsyncTaskListener {

    private LoaderView progress;
    private String title;
    private String message;
    private Activity activity;

    public ResultRequest(Activity activity, String title, String message) {
        this.title = title;
        this.message = message;
        this.activity = activity;
    }

    public ResultRequest(Activity activity, @StringRes int title, String message) {
        this(activity, activity.getString(title), message);
    }

    @Override
    public void onPreExecute() {
        progress = new LoaderView(activity, message);
    }

    @Override
    public abstract void onSuccess(JSONObject object, boolean success) throws Exception;

    @Override
    public void onFinally() {
        progress.loaderViewClose();
    }

    private class LoaderView {

        private Dialog dialog;
        private TextView textViewMessage;

        public LoaderView(Activity activity, String message){

            dialog = new Dialog(activity);
            dialog.setTitle(title);
            dialog.setCancelable(false);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow()
                    .setFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
            dialog.setContentView(R.layout.fragment_dialog_loader);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            textViewMessage = (TextView) dialog.getWindow().findViewById(R.id.fragment_dialog_tv);
            textViewMessage.setText(message);
        }

        public void loaderViewClose() {
            dialog.dismiss();
        }

    }

}

