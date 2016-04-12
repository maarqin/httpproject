package thomaz.com.br.httpproject.suport;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
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

    LoaderView progress;

    private String title;
    private String message;
    private Context context;

    public ResultRequest(Context context, String title, String message) {
        this.title = title;
        this.message = message;
        this.context = context;
    }

    public ResultRequest(Context context, @StringRes int title, String message) {
        this(context, context.getString(title), message);
    }

    @Override
    public void onPreExecute() {
        progress = new LoaderView(context, message);

    }

    @Override
    public abstract void onSuccess(JSONObject object, boolean success) throws Exception;

    @Override
    public void onFinally() {
        progress.loaderViewClose();
    }

}

class LoaderView {

    Dialog dialog;
    TextView textViewMessage;

    public LoaderView (Context context, String message){

        dialog = new Dialog(context);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.fragment_dialog_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        textViewMessage = (TextView)dialog.getWindow().findViewById(R.id.fragment_dialog_tv);
        textViewMessage.setText(message);
    }

    public void loaderViewCloseWithSleep (String newMessage) {
        textViewMessage.setText(newMessage);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loaderViewClose();
            }
        }, 3000);


    }

    public void  loaderViewClose() {
        dialog.dismiss();
    }

}

