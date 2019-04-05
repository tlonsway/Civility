import java.net.*;
import java.io.*;
import java.util.*;
public class ServerPoolThread implements Runnable {
    ServerSocket ss;
    ServerHost sh;
    Socket connection;
    BufferedReader din;
    public void ServerPoolThread(ServerHost host, Socket s) {
        connection=s;
        sh=host;
    }
    public void run() {
        try {
            din = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while(true) {
                try {
                    din.readLine();
                    
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}