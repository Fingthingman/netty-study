package com.netty.example.nettystudy.netty.protocol;

import com.netty.example.nettystudy.netty.sample.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @NAME: NettyClient
 * @DATE: 2020/1/11
 * @Author Mr.MaL
 * @Description TODO
 **/
public class NettyProtocolClient {

    public static void main(String[] args) throws InterruptedException {
        final NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventExecutors)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MessageProtocolEncode());
                        ch.pipeline().addLast(new MessageProtocolDecode());
                        ch.pipeline().addLast(new WriteMessageHandler());
                    }
                });
        final ChannelFuture sync = bootstrap.connect("127.0.0.1", 8888).sync();
        sync.channel().closeFuture().sync();
    }
}
