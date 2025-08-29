package seekUnderstanding;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

public interface connectionMethods {

    public static void connect(int port, AtomicReference<BufferedWriter> outRef) {

        while (true) {
      	  
             try {
                  Socket socket = new Socket("127.0.0.1", port);
                  BufferedWriter out = new BufferedWriter(
                       new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)
                  );
                  outRef.set(out);
//                  conn.setText("Connected to Program B.");

                  // Keep the socket open until UI closes (write-only client)
                  // If the server closes, writes will fail; reconnect loop could be added.
                  return;

             } catch (IOException e) {
//                  conn.setText("Retrying connection to Program B…");
                  sleep(1000);
                  
             } //try {...} catch (IOException e)
             
        } //while (true)

   } //private void connect()

   public static void sendJson(BufferedWriter out, String value) {

        if (out == null) return;

        try {
      	  
             String json = "{\"type\":\"text\",\"value\":\"" + escape(value) + "\"}\n";
             out.write(json);
             out.flush();
             
        } catch (IOException ignored) {
      	  
             // Optionally trigger a reconnect on failure
      	  
        }

   } //private static void sendJson(BufferedWriter out, String value)

   public static String escape(String s) {

        return s.replace("\\", "\\\\").replace("\"", "\\\"");

   } //private static String escape(String s)

   public static void closeQuietly(Closeable c) {

        try {
      	  
             if (c != null) c.close();
             
        } catch (IOException ignored) {}

   } //private static void closeQuietly(Closeable c)

   public static void sleep(long ms) {

        try {
      	  
             Thread.sleep(ms);
             
        } catch (InterruptedException ignored) {}

   } //private static void sleep(long ms)
   
   // Minimal JSON "parser" for {"type":"text","value":"..."}
   public static String extractValue(String json) {

       int i = json.indexOf("\"value\"");
       if (i < 0) return null;

       int colon = json.indexOf(':', i);
       if (colon < 0) return null;

       int firstQuote = json.indexOf('"', colon + 1);
       int secondQuote = json.indexOf('"', firstQuote + 1);
       if (firstQuote < 0 || secondQuote < 0) return null;

       return json.substring(firstQuote + 1, secondQuote);

  } //private static String extractValue(String json)
	
} //public interface connectionMethods
