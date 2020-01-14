package com.netty.example.nettystudy.httpservice;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObject;
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
        ch.pipeline().addLast(new HttpServerCodec());
        ch.pipeline().addLast(null);
    }
}
