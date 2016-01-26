package thomaz.com.br.httpproject.suport;

import okhttp3.Request;

/**
 * Created by thomaz on 22/01/16.
 */
public class Get extends PrepareRequest implements Tasked {

    @Override
    public Request prepare() {
        return request.build();
    }

}
    