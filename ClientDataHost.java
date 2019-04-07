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
    boolean connected;
    public static void main(String[] args) throws Exception {
        new ClientDataHost();
    }
    public ClientDataHost() {
        chat = new ArrayList<String>();
        connected=false;
    }
    public void run() {
        Socket s = null;
        try {
            s = new Socket("71.115.226.213",1600);
            ps = new PrintStream(s.getOutputStream());
            connected=true;
            //InetAddress IP=InetAddress.getLocalHost();
            //String IPaddr = IP.getHostAddress();
            //sps.println("IPaddr");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (s==null) {
            System.out.println("An error occured establishing a connection with the server");
            connected=false;
            //System.exit(1);
        }
        /*try {
            ss = new ServerSocket(1601);
            //din = new BufferedReader(new InputStreamReader(ss.getInputStream()));
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        try {
            //s = ss.accept();
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
        int amount=22;
        if (chat.size()-amount>=0) {
            start=chat.size()-amount;
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
    public String encodeChat(String name, String message) {
        return ("c:n:"+name+":"+message+":n:n:n");
    }
    public void sendMessage(String name,String message) {
        //chat.add(name+": "+message);
        if (connected) {
            ps.println(encodeChat(name,message));
            ps.flush();
        } else {
            chat.add(name+": "+message);
        }
    }
}