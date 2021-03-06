package seu.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;

import static seu.utility.ConfigUtil.getIP;
import static seu.utility.ConfigUtil.getPort;

/**
 * Sending message string to target.
 */
public class SenderThread implements Runnable {

    private char targetNode;
    private String content;

    /**
     * Construct sender thread that send message to
     * the given target node with message content.
     *
     * @param targetNode the target node.
     * @param content    the content.
     */
    public SenderThread(char targetNode, String content) {
        this.targetNode = targetNode;
        this.content = content;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(getIP(targetNode), getPort(targetNode));
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(content);
            outputStream.flush();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
