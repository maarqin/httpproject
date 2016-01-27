package thomaz.com.br.httpproject.suport;

import android.app.ProgressDialog;
import android.content.Context;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by thomaz on 22/01/16.
 */
public class Request extends SafeAsyncTask<JSONObject> {

    private AsyncTaskListener taskListener;
    private Tasked task;
    static private ProgressDialog progress;

    private String title;
    private String message;

    public Request(Context context, Tasked task) {
        progress = new ProgressDialog(context);
        this.task = task;
    }

    @Override
    protected void onPreExecute() throws Exception {
        if( taskListener == null )
            throw new NullPointerException("Must to be instance of AsyncTaskListener.");

        taskListener.onPreExecute();

        progress.setTitle(title);
        progress.setMessage(message);
        progress.setCancelable(false);
        progress.show();
    }

    @Override
    public JSONObject call() throws Exception {
        Response response = (new OkHttpClient()).newCall(task.prepare()).execute();

        return new JSONObject(response.body().string());
    }

    @Override
    protected void onSuccess(JSONObject object) throws Exception {
        taskListener.onSuccess(object, object.getBoolean("success"));
    }

    @Override
    protected void onFinally() throws RuntimeException {
        taskListener.onFinally();

        progress.dismiss();
    }

    /**
     * @param taskListener
     */
    public void setListener(AsyncTaskListener taskListener) {
        this.taskListener = taskListener;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}
