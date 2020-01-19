package com.netty.example.nettystudy.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @NAME: MessageProtocolDecode
 * @DATE: 2020/1/19
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MessageProtocolDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        MessageProtocol mp = new MessageProtocol();
        mp.setLen(length);
        mp.setContent(content);
        out.add(mp);
    }
}
