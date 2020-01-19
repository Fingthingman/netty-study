package com.netty.example.nettystudy.netty.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @NAME: WriteMessageHandler
 * @DATE: 2020/1/19
 * @Author Mr.MaL
 * @Description TODO
 **/
public class WriteMessageHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final MessageProtocol messageProtocol = new MessageProtocol();
        byte[] msg = "测试一下啦".getBytes();
        messageProtocol.setLen(msg.length);
        messageProtocol.setContent(msg);
        ctx.writeAndFlush(messageProtocol);
    }
}
