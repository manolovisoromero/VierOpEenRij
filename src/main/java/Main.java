import Socketcomm.*;
import Websocketserver.Connection;

import java.io.IOException;

public class Main {

    Communicator communicator = new Communicator();

        public static void main(String[] args) throws IOException {
            System.out.println("hello");
            Communicator.getInstance().start();


            Thread t1 = new Thread(new Runnable() {
                public void run()
                {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while(true){
                        try {
                            Thread.sleep(1000);
                            Communicator.getInstance().send("hoi",Communicator.getInstance().getSesh());
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }});
            Thread t2 = new Thread(new Runnable() {
                public void run()
                {
                    while(true){
                        try {
                            Thread.sleep(1000);
                            Communicator.getInstance().send("doei",Communicator.getInstance().getSesh());

                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }});
                t1.start();
                t2.start();
        }



}
