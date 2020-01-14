package com.netty.example.nettystudy.grouptalk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @NAME: GroupTalkClient
 * @DATE: 2020/1/9
 * @Author Mr.MaL
 * @Description TODO
 **/
public class GroupTalkClient {

    private SocketChannel socketChannel;
    private Selector selector;
    private  String username ;

    public GroupTalkClient() {
        try {
            this.socketChannel = SocketChannel.open();
            this.socketChannel.configureBlocking(false);
            this.socketChannel.connect(new InetSocketAddress("127.0.0.1",8888));
            this.selector = Selector.open();
            this.socketChannel.register(selector, SelectionKey.OP_READ);
            if(this.socketChannel.finishConnect()){
                this.username = socketChannel.getLocalAddress().toString();
            }

        } catch (Exception e) {
            System.out.println("群聊客户端启动异常");
            e.printStackTrace();
        }

    }

    public void readMsg() throws IOException {
        while (true){
            int num = selector.select();
            if (num==0) {
                continue;
            }
            final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                final SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()){
                    final SocketChannel channel = (SocketChannel) selectionKey.channel();
                    final ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    System.out.println("收到消息:"+new String(buffer.array()));
                }
                iterator.remove();
            }
        }
    }

    public void sendMsg(String msg)  {
        try {
            msg = username+":"+msg;
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e) {
            System.out.println("发送消息失败");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        GroupTalkClient client = new GroupTalkClient();
        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                client.sendMsg(scanner.nextLine());
            }
        }).start();
        client.readMsg();


    }
}
