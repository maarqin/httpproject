package thomaz.com.br.httpproject.suport;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.StringRes;

import org.json.JSONObject;

/**
 * Created by thomaz on 27/01/16.
 */
public abstract class ResultRequest implements AsyncTaskListener {

    static private ProgressDialog progress;

    private String title;
    private String message;

    public ResultRequest(Context context, String title, String message) {
        progress = new ProgressDialog(context);
        this.title = title;
        this.message = message;
    }

    public ResultRequest(Context context, @StringRes int title, String message) {
        this(context, context.getString(title), message);
    }

    @Override
    public void onPreExecute() {
        progress.setTitle(title);
        progress.setMessage(message);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public abstract void onSuccess(JSONObject object, boolean success) throws Exception;

    @Override
    public void onFinally() {
        progress.dismiss();
    }

}
