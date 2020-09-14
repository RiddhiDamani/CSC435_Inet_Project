import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// Worker Code for Inet Server
public class InetWorker extends Thread {
    Socket socket;
    InetWorker (Socket s) {
        socket = s;
    }

    public void run() {
        PrintStream output = null;
        BufferedReader input = null;

        try {
           input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           output = new PrintStream(socket.getOutputStream());

           try {
               String name;
               name = input.readLine();
               System.out.println("Looking up " + name);
               printRemoteAddress(name, output);
           }
           catch (IOException ioException) {
               System.out.println("Server read error");
               ioException.printStackTrace();
           }
           socket.close();
        }
        catch (IOException ioException) {
            System.out.println(ioException);
        }
    }

    static String toText(byte ipAddress[]) {
        StringBuffer result = new StringBuffer();
        for(int i=0; i < ipAddress.length; i++) {
            if(i >  0) result.append(".");
            result.append(0xff & ipAddress[i]);
        }
        //System.out.println("Inside IP Address: Worker" + result);
        return result.toString();
    }

    static void printRemoteAddress(String name, PrintStream output) {
        try {
            output.println("Looking up " + name + "...");
            InetAddress machine = InetAddress.getByName(name);
            output.println("Host Name : " + machine.getHostName());
            output.println("Host IP : " + toText(machine.getAddress()));
        }
        catch (UnknownHostException exception) {
            output.println("Failed in attempt to look up " + name);
        }
    }

}
