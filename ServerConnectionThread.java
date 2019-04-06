import java.net.*;
import java.io.*;
import java.util.*;
public class ServerConnectionThread implements Runnable {
    ServerSocket ss;
    ServerHost sh;
    public ServerConnectionThread(ServerHost host) {
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
                System.out.println("Server has received a connection from " + s.getInetAddress());
                //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                //run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
}