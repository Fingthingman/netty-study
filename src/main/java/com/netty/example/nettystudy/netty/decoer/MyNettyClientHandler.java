package com.netty.example.nettystudy.netty.decoer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @NAME: NettyClientHandler
 * @DATE: 2020/1/11
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MyNettyClientHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush("78965412L");
    }
}
