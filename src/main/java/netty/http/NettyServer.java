package netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author lyq on 2021-06-19 7:27 上午
 * @desc
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {
        // 接收连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 处理请求
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            // 服务端启动器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new CustomHttpServerInitializer());
            // 绑定端口
            ChannelFuture channelFuture = bootstrap.bind(8888).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
