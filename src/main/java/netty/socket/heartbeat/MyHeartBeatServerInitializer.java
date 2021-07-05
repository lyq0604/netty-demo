package netty.socket.heartbeat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author lyq on 2021-06-23 10:52 下午
 * @desc
 */
public class MyHeartBeatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 空闲状态检测handler
        pipeline.addLast(new IdleStateHandler(5, 6, 7, TimeUnit.SECONDS));
        pipeline.addLast(new MyHeartBeatServerHandler());
    }

}
