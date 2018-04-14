package seu;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import static seu.ConfigUtil.*;

public class Receiver implements Runnable {

    public static boolean enableServer = true;

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(getPort());
            while (enableServer) {
                try {
                    serverSocket.setSoTimeout(15000);
                    Socket socket = serverSocket.accept();
                    Thread thread = new Thread(new ReceiverThread(socket));
                    thread.start();
                } catch (SocketTimeoutException e) {
                    // Retry
                }
            }
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
