package thomaz.com.br.httpproject.suport;

import okhttp3.Request;

/**
 * Created by thomaz on 22/01/16.
 */
public class Post extends PrepareRequest implements Tasked {

    @Override
    public Request prepare() {
        request.url(String.format("%s%s", url, parameters));

        return request.build();
    }

}
