package netty.socket.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lyq on 2021-06-23 10:08 下午
 * @desc
 */
public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(reader.readLine());
        }
    }

}
