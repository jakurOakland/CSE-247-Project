package ServerEx;

import ClientEx.Menu;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;


public class ChatServer {

    private static final int PORT = 2222;

    /**
     * The set of all nameSet of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static final HashSet<String> nameSet = new HashSet<>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static final HashMap<String, DataOutputStream> writers = new HashMap<>();
    //The clients themselves
    private static final HashMap<String, Object> clients = new HashMap<>();
    //Holds the menus for the special clients, i.e. the restaurants. 
    private static final HashMap<String, Menu> restaurantMenus = new HashMap<>();
    private static final HashMap<String, Boolean> restaurants = new HashMap<>();
   
    
    /**
     * The application main method, which just listens on a port and
     * spawns handler threads.
     */
    public static void main(String[] args) throws Exception {
        restaurants.put("ChickfilA", Boolean.FALSE);
        restaurants.put("Subway", Boolean.FALSE);
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                Socket socket = listener.accept();
                new Handler(socket).start();
                System.out.println("New client connected");
            }
        } finally {
            listener.close();
            System.out.println("Client closed connection");
        }
    }
    
    private static void broadcast(String string) throws IOException{
        for (DataOutputStream writer : writers.values()) {
            writer.writeUTF(string);
                       
        }
    }

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private final Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private DataInputStream din;
        private DataOutputStream dout;

        /**
         * Constructs a handler thread.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Repeatedly requests a screen name until a unique one has been 
         * submitted, then acknowledges the name and registers the output stream
         * the client in a global set, then continually gets inputs and sends
         * them to their appropriate locations. 
         */
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                din = new DataInputStream(socket.getInputStream());
                dout = new DataOutputStream(socket.getOutputStream());

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of nameSet.
                while (true) {
                    dout.writeUTF("Please submit name");
                    name = din.readUTF();
                    System.out.println(name + " received");
                    if (name == null) {
                        return;
                    }
                    synchronized (nameSet) {
                        
                        if (!nameSet.contains(name)) {
                            nameSet.add(name);
                            clients.put(name, socket);
                            System.out.println(clients.toString());
                            for(int i = 0; i < nameSet.size(); i++){
                               System.out.println(nameSet.toString());
                            }
                            if(restaurants.containsKey(name)) {
                                restaurants.put(name, true);
                            }
                           break;
                        }
                    }
                    
                }

                // Now that a successful name has been chosen, add the
                // socket's data output to the set of all writers

                if(name.equals("ChickfilA") || name.equals("Subway")){
                    dout.writeUTF("Hello: " + name + 
                            " wait for orders to come in");
                } else {
                    dout.writeUTF("Hello " + name + " you can order now");
                }
                System.out.println("Sent hello message to " + name );
                dout.flush();
                writers.put(name, dout);
                

                // Accept messages from this client and handles them
                while (true) {
                    String input = din.readUTF();
                    System.out.println(input);
                    if (input == null) {
                        return;
                    }
                    
                    if(restaurants.containsKey(name)) {
                        if(input.contains("PICKUP")) {
                            String customerName = input.split(":")[0];
                            DataOutputStream stream = writers.get(customerName);
                            String output = (String) input;
                            stream.writeUTF(customerName + " your order is " +
                                    "ready for pickup.");
                        }
                    }
                    else {
                    String restaurantName = input.split(",")[0];
                        if(restaurants.containsKey(restaurantName)) {
                            if(restaurants.get(restaurantName)) {
                                DataOutputStream stream = writers.get(restaurantName);
                                String output = (String) input;
                                stream.writeUTF(output);
                                stream.flush();
                            }
                            else {
                                dout.writeUTF("I'm sorry, but that restaurant's"
                                        + " service is currently unavailable.");
                                dout.flush();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client has been terminated. Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    nameSet.remove(name);
                    if(restaurants.containsKey(name)) {
                        restaurants.put(name, Boolean.FALSE);
                    }
                }
                if (dout != null) {
                    writers.remove(name);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}