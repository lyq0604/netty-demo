package bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lyq on 2021-04-18 下午3:14
 * @desc
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket clientSocket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        String msg = null;
        while ((msg = reader.readLine()) != null) {
            System.out.println(String.format("接收到客户端消息：%s", msg));
        }
    }

}
