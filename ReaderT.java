import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ReaderT extends Thread{
    private PrintWriter pw;
    private BufferedReader br;
    private GWackClientGUI GUI;
    private volatile boolean connection = false;

    public ReaderT(PrintWriter pw, BufferedReader br, GWackClientGUI GUI){
        this.br = br;
        this.pw = pw;
        this.GUI = GUI;
        connection = true;
    }

    public void run(){
        String m;
        try{
            while(connection){
                m = br.readLine();
                if(m == null){
                    GUI.getSocket().close();
                    connection = false;
                    return;
                }
                if(m.equals("START_CLIENT_LIST")){
                    GUI.clearmembers();
                    m = br.readLine();
                    while(!m.equals("END_CLIENT_LIST")){
                        GUI.addmember(m);
                        m = br.readLine();
                    }
                } else{
                    GUI.addmsg(m);
                }
            }
            if(GUI.getSocket().isClosed()){
                return;
            }
        } catch(IOException e){
            connection = false;
            return;
        }
    }

    public void close(){
        pw.flush();
        try{
            br.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}

