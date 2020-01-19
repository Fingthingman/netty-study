package com.netty.example.nettystudy.netty.decoer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @NAME: MyByteToLongDecode
 * @DATE: 2020/1/17
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MyByteToLongDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println(123);
        if(in.readableBytes()>0){
            out.add(in.readLong());
        }
    }
}
