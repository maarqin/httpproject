package thomaz.com.br.httpproject.suport;

import org.json.JSONObject;

/**
 * Created by thomaz on 23/01/16.
 */
public interface AsyncTaskListener extends HalfAsync {

    /**
     * @param object
     * @param success
     * @throws Exception
     */
    void onSuccess(JSONObject object, boolean success) throws Exception;

}
