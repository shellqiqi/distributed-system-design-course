package seu;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static seu.App.*;
import static seu.MessageUtil.*;

public class TransmissionEventCreator implements Runnable {

    private String IP1;
    private int port1;
    private String IP2;
    private int port2;
    private Random random;

    private Lock lock = new ReentrantLock();

    /**
     * Constructor of transmission event creator.
     * @param IP1 first target IP.
     * @param port1 first target port.
     * @param IP2 second target IP.
     * @param port2 second target port.
     * @param seed random seed.
     */
    public TransmissionEventCreator(String IP1, int port1, String IP2, int port2, int seed) {
        this.IP1 = IP1;
        this.port1 = port1;
        this.IP2 = IP2;
        this.port2 = port2;
        this.random = new Random(seed);
    }

    @Override
    public void run() {
        try {
            int counter1 = 0;
            int counter2 = 0;
            for (int i = 0; i < 100; i++) {
                Thread.sleep(getRandomInterval(1));
                if (counter1 >= 50) {
                    transmit(2); counter2++;
                } else if (counter2 >= 50) {
                    transmit(1); counter1++;
                } else {
                    if (random.nextBoolean()) {
                        transmit(1); counter1++;
                    } else {
                        transmit(2); counter2++;
                    }
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transmit some resource to the target IP.
     * @param i which IP to send.
     * @throws IOException IOException.
     */
    private void transmit(int i) throws IOException {
        Socket socket;
        switch (i) {
            case 1:
                socket = new Socket(IP1, port1);
                break;
            case 2:
                socket = new Socket(IP2, port2);
                break;
            default:
                throw new IOException("Transmit argument illegal.");
        }
        lock.lock();
        int transmission = random.nextInt(App.resource / 4) + 1;
        App.resource -= transmission;
        App.logger.log(0, (InetSocketAddress) socket.getRemoteSocketAddress(), 0b00, transmission, App.resource, new Date());
        lock.unlock();
        Thread thread = new Thread(new MessageSender(socket, getMessage(0b00, transmission), 1000));
        thread.start();
    }
}
