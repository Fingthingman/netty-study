package com.netty.example.nettystudy.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @NAME: ReadMessageHandler
 * @DATE: 2020/1/19
 * @Author Mr.MaL
 * @Description TODO
 **/
public class ReadMessageHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        System.out.println(new String(msg.getContent()));
    }
}
