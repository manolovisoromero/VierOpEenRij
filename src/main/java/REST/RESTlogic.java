package REST;

import Database.DatabaseAccess;
import Database.logindataDB;
import Websocketserver.ServerLogic;

import java.util.ArrayList;

public class RESTlogic implements IRESTlogic{

    //Singleton
    private static RESTlogic RESTLlogic = new RESTlogic();
    private RESTlogic(){}
    public  static RESTlogic getInstance(){
        return RESTLlogic;
    }

    private RESTMsg restMsg;
    public DatabaseAccess dal = new DatabaseAccess();


    @Override
    public void handleMsg(RESTMsg restMsg) {
        //Database authentication answer

        switch(restMsg.getRestMsgType()){
            case LOGIN:
                login(restMsg.user, restMsg.pass);
                break;

        }

    }

    public void login(String username, String pass){
        this.restMsg = new RESTMsg(RESTMsgType.LOGINFAIL);
        ArrayList<logindataDB> users = dal.getListOfUsers();
        for(logindataDB user: users){
            if(user.username.equals(username)){
                if(user.password.equals(pass)){
                    this.restMsg.restMsgType = RESTMsgType.LOGINSUCCES;
                }
            }
        }

    }




    @Override
    public void sendMsg() {
    }

    public RESTMsg getResponse(){
        return this.restMsg;
    }
}
