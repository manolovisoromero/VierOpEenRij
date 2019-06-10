package REST;

public class RESTMsg {


    public String user;
    public String pass;
    public String msg;


    public RESTMsg() {
    }

    public RESTMsgType getRestMsgType() {
        return restMsgType;
    }

    RESTMsgType restMsgType;

    public void setLogin(String user, String pass){
        this.pass = pass;
        this.user = user;
    }

    public RESTMsg(RESTMsgType restMsgType){
        this.restMsgType = restMsgType;
    }
}
