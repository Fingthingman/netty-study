package com.netty.example.nettystudy.grouptalk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @NAME: GroupTalkService
 * @DATE: 2020/1/9
 * @Author Mr.MaL
 * @Description TODO
 **/
public class GroupTalkService {


    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final int PORT = 8888;

    public GroupTalkService() {
        try {
            this.serverSocketChannel = ServerSocketChannel.open();
            this.serverSocketChannel.configureBlocking(false);
            this.serverSocketChannel.bind(new InetSocketAddress(PORT));
            this.selector = Selector.open();
            this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            System.out.println("群聊服务启动异常");
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        while (true) {
            int num = selector.select();
            if (num == 0) {
                continue;
            }
            final Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            if (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    final SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("上线："+socketChannel.getRemoteAddress().toString());
                }else if(selectionKey.isReadable()){
                    final SocketChannel channel = (SocketChannel) selectionKey.channel();
                    try {
                        final ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        final String msg = new String(byteBuffer.array());
                        System.out.println("转发msg:"+msg);
                        this.forwardMsg(msg,channel);
                    }catch (Exception e){
                        System.out.println(channel.getRemoteAddress()+"离线");
                        selectionKey.cancel();
                        channel.close();
                    }
                }
                iterator.remove();
            }

        }
    }

    private void forwardMsg(String msg,SocketChannel self) throws IOException {
        final Set<SelectionKey> keys = selector.keys();
        for (SelectionKey selectionKey:keys) {
            if(selectionKey.channel().equals(self) || !(selectionKey.channel() instanceof SocketChannel)){
                continue;
            }
            final SocketChannel orther = (SocketChannel) selectionKey.channel();
            orther.write(ByteBuffer.wrap(msg.getBytes()));
        }
    }

    public static void main(String[] args) throws IOException {
        GroupTalkService service = new GroupTalkService();
        service.listen();
    }
}
