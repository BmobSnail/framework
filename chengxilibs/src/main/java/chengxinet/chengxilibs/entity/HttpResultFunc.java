package chengxinet.chengxilibs.entity;

import chengxinet.chengxilibs.exception.ApiException;
import rx.functions.Func1;

/**
 * Created by quan on 16/9/13.
 */

public class HttpResultFunc<T> implements Func1<Entity<T>, T> {
    @Override
    public T call(Entity<T> entity) {
        int code = entity.getCode();
        if (code != 200) {
            throw new ApiException(code, entity.getMsg());
        }
        return entity.getResult();
    }
}
