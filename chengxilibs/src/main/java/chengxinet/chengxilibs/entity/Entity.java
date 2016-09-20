package chengxinet.chengxilibs.entity;

/**
 * Created by quan on 16/9/13.
 */

public class Entity<T> {
    private int code;
    private String msg;
    private T result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "code : " + code + " , " + "msg : " +msg;
    }
}
