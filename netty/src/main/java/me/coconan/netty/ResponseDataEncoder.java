package me.coconan.netty;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseDataEncoder extends MessageToByteEncoder<RequestData> {
    
    private final Charset charset = Charset.forName("UTF-8");

    protected void encode(ChannelHandlerContext ctx, RequestData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
        out.writeInt(msg.getStringValue().length());
        out.writeCharSequence(msg.getStringValue(), charset);
    }
}
