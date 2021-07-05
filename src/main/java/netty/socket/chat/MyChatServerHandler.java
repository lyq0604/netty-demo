package netty.socket.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author lyq on 2021-06-23 9:02 下午
 * @desc
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 用于保存多个客户端与服务端建立的连接(Channel)
     */
    private static final ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        group.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush(ch.remoteAddress() + "发送的消息是" + msg +"\n");
            } else {
                ch.writeAndFlush("【自己】：" + msg +"\n");
            }
        });
    }

    /**
     * 客户端与服务端建立连接时回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.writeAndFlush(String.format("服务器 - %s 加入\n", channel.remoteAddress()));
        group.add(channel);
    }

    /**
     * 客户端与服务端连接断开时回调
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(String.format("服务器 - %s 离开\n", channel.remoteAddress()));
    }

    /**
     * 连接处于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(String.format("%s上线\n", channel.remoteAddress()));
    }

    /**
     * 连接处于非活跃状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(String.format("%s下线\n", channel.remoteAddress()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
