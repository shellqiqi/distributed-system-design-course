package seu;

import static seu.ConfigUtil.*;

public class Message {
    public int command;
    public char node;
    public int resource;
    public int snapshotId;

    public Message(String message) {
        String[] splits = message.split("\\|");
        command = Integer.parseInt(splits[0]);
        switch (command) {
            case 1:
                node = splits[1].charAt(0);
                resource = Integer.parseInt(splits[2]);
                break;
            case 2:
                snapshotId = Integer.parseInt(splits[1]);
                break;
            case 3:
                node = splits[1].charAt(0);
                resource = Integer.parseInt(splits[2]);
                break;
            case 4:
                node = splits[1].charAt(0);
                snapshotId = Integer.parseInt(splits[2]);
                break;
            case 5:
            default:
                break;
        }
    }

    public static Message getInstanceOfResourceTransmit(int resource) {
        return new Message(3 + "|" + NODE + "|" + resource);
    }

    public static Message getInstanceOfSnapshot(int snapshotId) {
        return new Message(4 + "|" + NODE + "|" + snapshotId);
    }

    @Override
    public String toString() {
        switch (command) {
            case 1:
                return command + "|" + node + "|" + resource;
            case 2:
                return command + "|" + snapshotId;
            case 3:
                return command + "|" + node + "|" + resource;
            case 4:
                return command + "|" + node + "|" + snapshotId;
            case 5:
            default:
                return String.valueOf(command);
        }
    }
}
