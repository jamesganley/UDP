import java.io.*;
import java.net.*;
public class UDPEchoClient
{
private static InetAddress host;
private static final int PORT = 1234;
private static DatagramSocket dgramSocket;
private static DatagramPacket inPacket, outPacket;
private static byte[] buffer;
public static void main(String[] args) {
try {
 host = InetAddress.getLocalHost();
} catch(UnknownHostException e) {
 System.out.println("Host ID not found!");
 System.exit(1);
 }
run();
}
private static void run() {
 try {
 dgramSocket = new DatagramSocket(); //Step 1.
 //Set up stream for keyboard entry...
 BufferedReader userEntry = new BufferedReader(
 new InputStreamReader(System.in));
 String message=null;
 String response=null;
 do {
 System.out.print("Enter message: ");
 message = userEntry.readLine();
 if (!message.equals("***close**")) {
 outPacket = new DatagramPacket(
 message.getBytes(),
 message.length(),
 host,
 PORT); //Step 2.
 dgramSocket.send(outPacket); //Step 3.
 buffer = new byte[256]; //Step 4.
 inPacket = new DatagramPacket(
 buffer,
 buffer.length); //Step 5.
 dgramSocket.receive(inPacket); //Step 6.

 response = new String(inPacket.getData(),0, inPacket.getLength()); //Step 7.


     if (!response.equals("error")) {
          System.out.println("\nSERVER> " + response);
          System.out.println("\n* Closing connection... *");
          dgramSocket.close(); //Step 8.
        }
        else{
          System.out.println(" ");
          System.out.println("Please enter a valid input; DATE or TIME");

        }
 }
 else{

 }
}
while (!message.equals("***close***"));
 } catch(IOException e) {
 e.printStackTrace();
 System.out.println("\n* Closing connection... *");
 System.out.println("\n* Closing connection... *");
 } finally {
 dgramSocket.close(); //Step 8.
 }
}
}
