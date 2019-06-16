package REST;

public interface IRESTlogic {


    /**Handles incoming msg
     *
     * @param restMsg : RESTMsg object
     */
   void handleMsg(RESTMsg restMsg);


    /**Returns the answer
     *
     * @return RESTMsg
     */
    RESTMsg getResponse();


    /**Uses the credential parameters to verify them
     *
     * @param username : String username
     * @param pass : String password
     */
    void login(String username, String pass);


    /**Uses the credential parameters to register them
     *
     * @param username : String username
     * @param pass : String password
     */
    void register(String username, String pass);



}
