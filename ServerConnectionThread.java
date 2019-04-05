import java.net.*;
import java.io.*;
import java.util.*;
public class ServerConnectionThread implements Runnable {
    ServerSocket ss;
    ServerHost sh;
    public void ServerConnectionThread(ServerHost host) {
        sh=host;
    }
    public void run() {
        try {
            ss = new ServerSocket(1600);
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            try {
                Socket s = ss.accept();
                sh.addSocket(s);
                //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                //run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
}