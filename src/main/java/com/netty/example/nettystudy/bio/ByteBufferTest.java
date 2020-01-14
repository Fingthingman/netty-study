package com.netty.example.nettystudy.bio;

import java.nio.ByteBuffer;

/**
 * @NAME: ByteBufferTest
 * @DATE: 2019/12/23
 * @Author Mr.MaL
 * @Description TODO
 **/
public class ByteBufferTest {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("马德里".getBytes());
        System.out.println(String.format("pos:%s,limit:%s,cap:%s",byteBuffer.position(),byteBuffer.limit(),byteBuffer.capacity()));
        byte[] bytes = new byte[9];
        byteBuffer.flip();
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        byteBuffer.rewind();
        bytes = new byte[6];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println(String.format("pos:%s,limit:%s,cap:%s",byteBuffer.position(),byteBuffer.limit(),byteBuffer.capacity()));

    }
}
