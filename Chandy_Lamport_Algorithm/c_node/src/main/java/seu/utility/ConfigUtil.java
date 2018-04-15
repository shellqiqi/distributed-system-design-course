package seu.utility;

import seu.pojo.Snapshot;

import java.util.Random;
import java.util.TreeMap;

public class ConfigUtil {
    // Channel delay from x to y
    private final static int DELAY_IJ = 1000;
    private final static int DELAY_JI = 1300;
    private final static int DELAY_IK = 1600;
    private final static int DELAY_KI = 1900;
    private final static int DELAY_JK = 2100;
    private final static int DELAY_KJ = 2400;
    // Port of node x
    private final static int PORT_I = 777;
    private final static int PORT_J = 888;
    private final static int PORT_K = 999;
    private final static int PORT_C = 666;
    // IP of opposite nodes
    public static String OPPOSITE_IP;
    // Node info
    public final static char NODE = 'c';
    public static int TRANSMIT_TIMES;
    public static int SNAPSHOT_TIMES;
    public static Random RANDOM;
    public static TreeMap<Integer, Snapshot> SNAPSHOT_TABLE = new TreeMap<>();

    public static char getOtherNode(char node1, char node2) {
        if (node1 == 'i' && node2 == 'j') return 'k';
        else if (node1 == 'j' && node2 == 'i') return 'k';
        else if (node1 == 'i' && node2 == 'k') return 'j';
        else if (node1 == 'k' && node2 == 'i') return 'j';
        else if (node1 == 'j' && node2 == 'k') return 'i';
        else if (node1 == 'k' && node2 == 'j') return 'i';
        else return 0;
    }

    public static char[] getOtherNodes(char node) throws Exception {
        switch (node) {
            case 'i': return new char[] {'j', 'k'};
            case 'j': return new char[] {'i', 'k'};
            case 'k': return new char[] {'i', 'j'};
            default: throw new Exception("Unsupported node name");
        }
    }

    public static String getIP() {
        return "127.0.0.1";
    }

    public static String getIP(char node) throws Exception {
        switch (node) {
            case 'i':
            case 'j':
                return OPPOSITE_IP;
            case 'k':
            case 'c':
                return "127.0.0.1";
            default: throw new Exception("Unsupported node name");
        }
    }

    public static int getPort() throws Exception {
        return getPort(NODE);
    }

    public static int getPort(char node) throws Exception {
        switch (node) {
            case 'i': return PORT_I;
            case 'j': return PORT_J;
            case 'k': return PORT_K;
            case 'c': return PORT_C;
            default: throw new Exception("Unsupported node name");
        }
    }

    public static int getDelay(char to) {
        return getDelay(NODE, to);
    }

    public static int getDelay(char from, char to) {
        if (from == 'i' && to == 'j') return DELAY_IJ;
        else if (from == 'i' && to == 'k') return DELAY_IK;
        else if (from == 'j' && to == 'i') return DELAY_JI;
        else if (from == 'j' && to == 'k') return DELAY_JK;
        else if (from == 'k' && to == 'i') return DELAY_KI;
        else if (from == 'k' && to == 'j') return DELAY_KJ;
        else return 0;
    }

    public static long getRandomInterval(int second) {
        return Math.round(second * -1000 * Math.log(RANDOM.nextDouble()));
    }
}