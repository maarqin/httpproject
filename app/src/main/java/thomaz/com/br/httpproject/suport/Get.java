package thomaz.com.br.httpproject.suport;

import okhttp3.Request;

/**
 * Created by thomaz on 22/01/16.
 */
public class Get extends PrepareRequest implements Tasked {

    public Get() {
    }

    public Get(String url) {
        super(url);
    }

    public Get(Request.Builder request) {
        super(request);
    }

    @Override
    public Request prepare() {
        return request.build();
    }

}
    