package thomaz.com.br.httpproject.suport;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by thomaz on 22/01/16.
 */
public class PrepareRequest {

    protected Request.Builder request;
    protected StringBuilder parameters = new StringBuilder();
    protected String url;

    public PrepareRequest() {
        this.request = new Request.Builder();
    }

    /**
     * @param url
     */
    public PrepareRequest(String url) {
        this.url = url;
    }

    /**
     * @param request
     */
    public PrepareRequest(Request.Builder request) {
        this.request = request;
    }

    /**
     * Address to consult
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Example: http://www.domain.com/page/other-page
     * Must receive an array of string
     *
     * @param parameters
     */
    public void setParameters(String... parameters) {
        StringBuilder params = new StringBuilder();
        for (String parameter : parameters) {
            params.append('/').append(parameter);
        }
        this.parameters.append(params);
    }

    /**
     * Example: http://www.domain.com?id=1&user=4
     * Must receive a list of key-value
     *
     * @param parameters
     */
    public void setParameters(HashMap<String, String> parameters) {
        StringBuilder params = new StringBuilder();
        params.append('?');

        for (String key : parameters.keySet()) {
            params.append(key).append('=').append(parameters.get(key)).append('&');
        }
        params.deleteCharAt(params.length() - 1);

        this.parameters.append(params);
    }

    /**
     * When post, user have to inform a JSONObject(key-value)
     *
     * @param body
     */
    public void setBody(JSONObject body) {
        request.post(RequestBody
                .create(MediaType.parse("application/json; charset=utf-8"), body.toString()));
    }

}