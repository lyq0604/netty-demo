package netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lyq on 2021-06-19 7:54 上午
 * @desc Channel注册后的初始话操作
 */
public class CustomHttpServerInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 请求与响应编解码的处理器
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 自定义处理器
        pipeline.addLast("customServerHandler", new CustomHttpServerHandler());
    }
}
