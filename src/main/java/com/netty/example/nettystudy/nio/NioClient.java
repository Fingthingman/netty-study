package com.netty.example.nettystudy.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @NAME: NioClient
 * @DATE: 2020/1/7
 * @Author Mr.MaL
 * @Description TODO
 **/
public class NioClient {

    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        if(!socketChannel.connect(new InetSocketAddress("127.0.0.1",6666))){
            while (!socketChannel.finishConnect()){
                System.out.println("正在连接");
            }
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap("Hello nio".getBytes());
        socketChannel.write(byteBuffer);
        socketChannel.close();
    }

}
