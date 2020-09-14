import java.io.IOException;  // Get IO Exception libraries
import java.net.ServerSocket; // Get ServerSocket Libraries
import java.net.Socket; // Get Socket Libraries

// Server Code for InetServer Multithreading Assignment
public class InetServer {
    public static void main(String args[]) throws IOException {

        int queue_length = 6; // Queue length - for number of request to be queued by OS
        int port = 1565; // defining port number
        Socket socket;

        // Passing port number and queue length in the constructor of ServerSocket
        ServerSocket serverSocket = new ServerSocket(port, queue_length);

        // Printing out the Server startup message
        System.out.println("Riddhi Damani's Inet Server starting up. Listening to port 1565.... \n");

        while (true) {
            // connecting to the next client request
            socket = serverSocket.accept();

            // make worker handle the client request
            new InetWorker(socket).start();
        }
    }
}
