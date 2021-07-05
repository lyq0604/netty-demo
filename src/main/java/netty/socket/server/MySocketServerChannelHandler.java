package netty.socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author lyq on 2021-06-22 9:49 下午
 * @desc
 */
public class MySocketServerChannelHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext chc, String msg) throws Exception {
        SocketAddress address = chc.channel().remoteAddress();
        System.out.println( "收到来自客户端的消息: " + msg);
        TimeUnit.SECONDS.sleep(1);
        chc.channel().writeAndFlush("from server: " + LocalDateTime.now());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
