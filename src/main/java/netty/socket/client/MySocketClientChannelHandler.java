package netty.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author lyq on 2021-06-22 10:08 下午
 * @desc
 */
public class MySocketClientChannelHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收服务端数据
     * @param chc
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext chc, String msg) throws Exception {
        System.out.println(chc.channel().remoteAddress());
        System.out.println("收到服务端消息: " + msg);
        TimeUnit.SECONDS.sleep(1);
        chc.channel().writeAndFlush("from client: " + LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("已连接至服务器");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
