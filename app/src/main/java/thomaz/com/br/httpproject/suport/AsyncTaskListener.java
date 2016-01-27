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
     * @param success
     * @throws Exception
     */
    void onSuccess(JSONObject object, boolean success) throws Exception;

    /**
     *
     */
    void onFinally();
}
