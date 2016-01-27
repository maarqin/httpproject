package thomaz.com.br.httpproject.suport;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by thomaz on 22/01/16.
 */
public class Request extends SafeAsyncTask<JSONObject> {

    private ResultRequest result;
    private Tasked task;

    public Request(Tasked task) {
        this.task = task;
    }

    @Override
    protected void onPreExecute() throws Exception {
        if( result == null )
            throw new NullPointerException("Must to be instance of AsyncTaskListener.");

        result.onPreExecute();
    }

    @Override
    public JSONObject call() throws Exception {
        Response response = (new OkHttpClient()).newCall(task.prepare()).execute();

        return new JSONObject(response.body().string());
    }

    @Override
    protected void onSuccess(JSONObject object) throws Exception {
        result.onSuccess(object, object.getBoolean("success"));
    }

    @Override
    protected void onFinally() throws RuntimeException {
        result.onFinally();
    }

    /**
     * @param result
     */
    public void setListener(ResultRequest result) {
        this.result = result;
    }

}
