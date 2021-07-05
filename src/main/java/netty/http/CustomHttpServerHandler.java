package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author lyq on 2021-06-19 7:58 上午
 * @desc 自定义handler
 */
public class CustomHttpServerHandler extends SimpleChannelInboundHandler<HttpMessage> {

    protected void channelRead0(ChannelHandlerContext chc, HttpMessage httpMessage) throws Exception {
        SocketAddress address = chc.channel().remoteAddress();
        System.out.println(String.format("远程地址: %s", address));
        if (httpMessage instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) httpMessage;
            String methodName = request.method().name();
            System.out.println(String.format("请求方式: %s", methodName));
            // 响应
            ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            chc.writeAndFlush(response);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Active");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Inactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Registered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel Unregistered");
        super.channelUnregistered(ctx);
    }

}
