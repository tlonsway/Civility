import java.net.*;
import java.io.*;
import java.util.*;
public class ClientDataHost implements Runnable {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    InputStream is;
    ArrayList<String> chat;
    PrintStream ps;
    public static void main(String[] args) throws Exception {
        new ClientDataHost();
    }
    public ClientDataHost() {
        chat = new ArrayList<String>();
    }
    public void run() {
        try {
            ss = new ServerSocket(1558);
            //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s = ss.accept();
            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println("server connection achieved " + s.getInetAddress());
            //(new Thread(new serverRunabble(s))).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            String line = "";
            try {
                line = din.readLine();
                process(line);
                //chat.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("server input received: " + line);
        }
    }
    public ArrayList<String> getChat() {
        return chat;
    }
    public ArrayList<String> getRecent() {
        ArrayList<String> temp = new ArrayList<String>();
        int start=0;
        if (chat.size()-15>=0) {
            start=chat.size()-15;
        } else {
            start=0;
        }
        for(int i=start;i<chat.size();i++) {
            temp.add(chat.get(i));
        }
        return temp;
    }
    public void process(String input) {
        /*Structure of a server command:
         * t1:t2:s1:s2:s3:s4:s5
         * t1 is type 1(chat,location,mapdata), t2 is type 2(more unique commands)
         * s1,s2,s3,s4,s5, are strings that can be sent optionally
        */
        String[] components = new String[7];
        for(int i=0;i<7;i++) {
            if (i<6) {
                components[i]=input.substring(0,input.indexOf(":"));
                input=input.substring(input.indexOf(":")+1);
            } else {
                components[i]=input;
            }
        }
        /*Structure of a chat message:
         * c:n:name:message:n:n:n
         * c=chat
         * n=null
         * name=name of sender
         * message=the text content
        */
        for(String s : components) {
            System.out.println(s);
        }
        if (components[0].equals("c")) {
            System.out.println("chat message received!");
            chat.add(components[2]+": "+components[3]);
        }
    }
    public void sendMessage(String name,String message) {
        chat.add(name+": "+message);
    }
}