package com.netty.example.nettystudy.netty.httpservice;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @NAME: MyChannelInitializer
 * @DATE: 2020/1/14
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        ch.pipeline().addLast("MyHttpServerCodec",new HttpServerCodec());
        ch.pipeline().addLast("MyMessageHandler",new MyMessageHandler());
    }
}
