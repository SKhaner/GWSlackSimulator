import java.io.BufferedReader;
import java.io.PrintWriter;

public class Sender{
    private PrintWriter pw;
    private ReaderT readt;
    private BufferedReader br;

    public Sender(PrintWriter pw, BufferedReader br, GWackClientGUI GUI){
        this.br = br;
        this.pw = pw;
        this.readt = new ReaderT(pw, br, GUI);
        readt.start();
    }

    public void sms(String s){
        pw.println(s);
        pw.flush();
    }
}