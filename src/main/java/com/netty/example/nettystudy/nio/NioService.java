package com.netty.example.nettystudy.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @NAME: NioService
 * @DATE: 2020/1/7
 * @Author Mr.MaL
 * @Description TODO
 **/
public class NioService {

    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(6666));
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            if(selector.select(1000) == 0){
                System.out.println("1秒内暂无事件");
                continue;
            }
             Set<SelectionKey> selectionKeys = selector.selectedKeys();
             Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    System.out.println("有一个连接");
                    SocketChannel channel = serverSocketChannel.accept();
                    channel.configureBlocking(false);
                    channel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if(selectionKey.isReadable()){
                    final SocketChannel selectableChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                    selectableChannel.read(byteBuffer);
                    System.out.println("数据"+new String(byteBuffer.array()));
                }
                iterator.remove();
            }
        }
    }
}
