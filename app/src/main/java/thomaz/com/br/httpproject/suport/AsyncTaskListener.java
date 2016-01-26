package thomaz.com.br.httpproject.suport;

import org.json.JSONObject;

/**
 * Created by thomaz on 23/01/16.
 */
public interface AsyncTaskListener {

    /**
     *
     */
    void onPreExecute();

    /**
     * @param object
     */
    void onSuccess(JSONObject object);

    /**
     *
     */
    void onFinally();
}
