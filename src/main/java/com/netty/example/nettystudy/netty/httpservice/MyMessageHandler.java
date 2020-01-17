package com.netty.example.nettystudy.netty.httpservice;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @NAME: MyMessageHandler
 * @DATE: 2020/1/14
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MyMessageHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            final URI uri = new URI(request.uri());
            System.out.println(uri.getPath());
            final ByteBuf byteBuf = Unpooled.copiedBuffer("hello,Service!", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            System.out.println(byteBuf.readableBytes());
            ctx.writeAndFlush(response);
        }
    }
}
