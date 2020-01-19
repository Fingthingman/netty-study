package com.netty.example.nettystudy.netty.decoer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

/**
 * @NAME: MyByteToLongDecode
 * @DATE: 2020/1/17
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MyLongToByteEncode extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        out.writeLong(msg);
    }
}
