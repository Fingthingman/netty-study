package com.netty.example.nettystudy.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @NAME: WebSocketHandle
 * @DATE: 2020/1/17
 * @Author Mr.MaL
 * @Description TODO
 **/
public class WebSocketHandle extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息 " + msg.text());
        //回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame(" 服 务 器 时 间 " + LocalDateTime.now() + " " +
                msg.text()));
    }
}
