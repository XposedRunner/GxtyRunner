package okhttp3.internal.cache;

import com.lzy.okgo.model.HttpHeaders;
import com.qq.e.comm.constants.ErrorCode.AdError;
import com.qq.e.comm.constants.ErrorCode.NetWorkError;
import com.tencent.bugly.beta.tinker.TinkerReport;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;

public final class CacheStrategy {
    @Nullable
    public final Response cacheResponse;
    @Nullable
    public final Request networkRequest;

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    public static boolean isCacheable(Response response, Request request) {
        switch (response.code()) {
            case 200:
            case TinkerReport.KEY_APPLIED_SUCC_COST_60S_LESS /*203*/:
            case TinkerReport.KEY_APPLIED_SUCC_COST_OTHER /*204*/:
            case 300:
            case 301:
            case TinkerReport.KEY_LOADED_MISSING_RES /*308*/:
            case 404:
            case NetWorkError.RESOURCE_LOAD_FAIL_ERROR /*405*/:
            case 410:
            case 414:
            case AdError.NO_FILL_ERROR /*501*/:
                break;
            case 302:
            case TinkerReport.KEY_LOADED_MISSING_DEX_OPT /*307*/:
                if (response.header(HttpHeaders.HEAD_KEY_EXPIRES) == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            default:
                return false;
        }
        return (response.cacheControl().noStore() || request.cacheControl().noStore()) ? false : true;
    }
}
