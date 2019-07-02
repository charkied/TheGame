import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class InputReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String lastMessage = null;
    private boolean gotNewMessage = false;
    private boolean started = false;
    private boolean blocked = false;
    private Vector inputs;


    public InputReader() {
        inputs = new Vector();
    }

    public String getNewLine() {
        try {
            String read = reader.readLine();
            if(read != lastMessage){
                lastMessage=read;
                gotNewMessage=true;
            }
            else
                gotNewMessage=false;
            //System.out.println(getInputs().toString());
            return lastMessage;
        } catch (IOException E) {
            return "getNewLine Error";
        }
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isGotNewMessage() {
        return gotNewMessage;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public Vector getInputs() {
        inputs.clear();
        StringBuilder sb = new StringBuilder(lastMessage.length());
        char c;
        for (int i = 0; i < lastMessage.length(); i++) {
            c = lastMessage.charAt(i);
            if (c > 47 && c < 58)
                sb.append(c);
            else if (sb.length() > 0) {
                inputs.add(stringToInt(sb.toString()));
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) {
            inputs.add(stringToInt(sb.toString()));
            sb = new StringBuilder();
        }
        return inputs;
    }

    private int stringToInt(String input) {
        int result = 0;
        int number = 0;
        for (int i = 0; i < input.length(); i++) {
            number = input.charAt(i) - 48;
            result = result * 10 + number;
        }
        return result;
    }

}
