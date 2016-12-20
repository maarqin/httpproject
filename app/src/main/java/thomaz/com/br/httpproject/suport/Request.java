package thomaz.com.br.httpproject.suport;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by thomaz on 22/01/16.
 */
public class Request extends SafeAsyncTask<JSONObject> {

    private ResultRequest result;
    private Tasked task;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Response response;

    public Request(Tasked task) {
        this.task = task;
    }

    public Request(Tasked task, @NonNull OkHttpClient okHttpClient) {
        this(task);
        this.okHttpClient = okHttpClient;
    }

    @Override
    protected void onPreExecute() throws Exception {
        if( result == null )
            throw new NullPointerException("Must to be instance of AsyncTaskListener.");

        result.onPreExecute();
    }

    @Override
    public JSONObject call() throws Exception {
        response = okHttpClient.newCall(task.prepare()).execute();

        return new JSONObject(response.body().string());
    }

    @Override
    protected void onSuccess(JSONObject object) throws Exception {
        result.onSuccess(object, object.has("success") && object.getBoolean("success"));
    }

    @Override
    protected void onFinally() throws RuntimeException {
        result.onFinally();
    }

    /**
     * @param result {@link ResultRequest}
     */
    public void setListener(ResultRequest result) {
        this.result = result;
    }

    /**
     * @return Response
     */
    public Response getResponse() {
        return response;
    }

}
