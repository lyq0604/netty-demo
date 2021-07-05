package netty.socket.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author lyq on 2021-06-23 9:49 下午
 * @desc
 */
public class MyChatClient2 {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new MyChatClientHandlerInitializer());
            Channel channel = bootstrap.connect("localhost", 8888).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                channel.writeAndFlush(br.readLine() + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

}
