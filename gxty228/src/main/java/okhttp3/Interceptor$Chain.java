package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public interface Interceptor$Chain {
    @Nullable
    Connection connection();

    Response proceed(Request request) throws IOException;

    Request request();
}
