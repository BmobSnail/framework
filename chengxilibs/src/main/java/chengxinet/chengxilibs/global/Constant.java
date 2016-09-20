package chengxinet.chengxilibs.global;

/**
 * Created by Administrator on 2015/8/14.
 */
public class Constant {

    public interface LoadState {
        int START = 0;
        int LOADING = 1;
        int FINISH = 2;
        int FAIL = 3;
        int SUCCEED = 4;
        int ADD_DATA = 5;
        int REFRESH_DATA = 9;
        int INIT_DATA = 6;
        int NOT_SDCARD = 7;
        int CANNEL = 8;
        int NO_DATA = 10;
        int REFRESH_PREPARE = 11;
    }



    public interface CountDown {
        String ACTION = CountDown.class.getName() + ".countdownbroadcast";
        String COUNT_DOWN_SECONDS = CountDown.class.getName() + ".countdownsecounds";
        String COUNT_DOWN_STATE = CountDown.class.getName() + ".countdownstate";
        String COUNT_DOWN_ACION = CountDown.class.getName() + ".countdownaction";
        int GET_ABLE = 3;
        int GET_UNABLE = 4;

        int COUNT_DOWN_START = 1;
        int COUNT_DOWN_FINISH = 2;
        int MULTIPLE = 10;
        int COUNT = 60;
    }

    public interface PlatName {
        String WechatMoments = "微信朋友圈";
        String Wechat = "微信好友";
        String QQ = "QQ朋友";
        String QQZone = "QQ空间";
    }


}
