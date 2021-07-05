package netty.socket.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @author lyq on 2021-06-23 9:51 下午
 * @desc
 */
public class MyChatClientHandlerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pip = socketChannel.pipeline();
        pip.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        pip.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pip.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pip.addLast(new MyChatClientHandler());
    }

}
