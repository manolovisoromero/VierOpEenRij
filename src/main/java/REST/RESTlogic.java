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
    private DatabaseAccess dal = new DatabaseAccess();



    public void handleMsg(RESTMsg restMsg) {
        //Database authentication answer

        switch(restMsg.getRestMsgType()){
            case LOGIN:
                login(restMsg.user, restMsg.pass);
                break;
            case REGISTER:
                register(restMsg.user, restMsg.pass);
                break;
        }

    }

    public void login(String username, String pass){
        this.restMsg = new RESTMsg(RESTMsgType.LOGINFAIL);
        for(logindataDB user: getusers()){
            if(user.username.equals(username)){
                if(user.password.equals(pass)){
                    this.restMsg.restMsgType = RESTMsgType.LOGINSUCCES;
                }
            }
        }
        restMsg.msg = "Wrong credentials";

    }

    public void register(String username, String pass){
        this.restMsg = new RESTMsg(RESTMsgType.REGSUCCES);
        for(logindataDB user: getusers()){
            if(user.username.equals(username)){
                restMsg.restMsgType = RESTMsgType.REGFAIL;
                restMsg.msg = "Username already exists";
            }
        }
        if(restMsg.restMsgType == RESTMsgType.REGSUCCES){
        dal.registerUser(username, pass);
            System.out.println("User "+username+" registered.");}


    }



    public RESTMsg getResponse(){
        return this.restMsg;
    }
    private ArrayList<logindataDB> getusers(){ return dal.getListOfUsers(); }
}
