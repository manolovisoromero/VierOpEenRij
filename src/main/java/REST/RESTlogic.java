package REST;

import Websocketserver.ServerLogic;

public class RESTlogic implements IRESTlogic{

    //Singleton
    private static RESTlogic RESTLlogic = new RESTlogic();
    private RESTlogic(){}
    public  static RESTlogic getInstance(){
        return RESTLlogic;
    }

    private RESTMsg restMsg;


    @Override
    public void handleMsg(RESTMsg restMsg) {
        //Database authentication answer
        this.restMsg = new RESTMsg(RESTMsgType.LOGINSUCCES);

    }

    @Override
    public void sendMsg() {
    }

    public RESTMsg getResponse(){
        return this.restMsg;
    }
}
