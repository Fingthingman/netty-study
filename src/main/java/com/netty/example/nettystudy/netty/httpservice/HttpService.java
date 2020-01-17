package com.netty.example.nettystudy.netty.httpservice;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @NAME: HttpService
 * @DATE: 2020/1/14
 * @Author Mr.MaL
 * @Description TODO
 **/
public class HttpService {

    public static void main(String[] args) throws InterruptedException {
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();
        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new MyChannelInitializer());

        final ChannelFuture channelFuture = serverBootstrap.bind(8888).sync();
        channelFuture.channel().closeFuture().sync();
    }
}
