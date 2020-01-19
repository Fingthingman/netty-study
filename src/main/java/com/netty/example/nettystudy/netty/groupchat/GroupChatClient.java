package com.netty.example.nettystudy.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @NAME: GroupChatClient
 * @DATE: 2020/1/15
 * @Author Mr.MaL
 * @Description TODO
 **/
public class GroupChatClient {

    private String address;
    private int port;

    public GroupChatClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void run()throws Exception{
        final NioEventLoopGroup workGroup = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            final ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new GroupChatClientHandler());
                            pipeline.addLast(new StringEncoder());
                        }
                    });
            final ChannelFuture channelFuture = bootstrap.connect(address, port).sync();
            final Channel channel = channelFuture.channel();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }

        }  finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        final GroupChatClient groupChatClient = new GroupChatClient("127.0.0.1", 8889);
        groupChatClient.run();
    }
}
