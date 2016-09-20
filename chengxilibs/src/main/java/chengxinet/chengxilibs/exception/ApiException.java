package chengxinet.chengxilibs.exception;

/**
 * Created by quan on 16/9/13.
 */

public class ApiException extends RuntimeException {

    private int code;
    private String msg;

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ApiException(int code, String msg) {
        super(code + "," + msg);
        this.code = code;
        this.msg = msg;
    }

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
}
