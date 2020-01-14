package com.netty.example.nettystudy.nio;

import org.springframework.http.converter.json.GsonBuilderUtils;

import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @NAME: ChannelTest
 * @DATE: 2020/1/2
 * @Author Mr.MaL
 * @Description TODO
 **/
public class ChannelTest {

    public static void main(String[] args) throws Exception {
        mutis();
    }


    public static void writeFile() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("D://my.txt");
        FileChannel fileChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("Hello World!".getBytes());
        buffer.flip();
        fileChannel.write(buffer);
        outputStream.close();
    }

    public static void readFile() throws IOException {
        File file = new File("D://my.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel fileChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
    }

    public static void copyFile() throws IOException {
        File sourceFile = new File("D://my.txt");
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileChannel sourceChannel = inputStream.getChannel();
        File targetFile = new File("D://my2.txt");
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        FileChannel targetChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(10);
        while (-1 != sourceChannel.read(buffer)) {
            buffer.flip();
            targetChannel.write(buffer);
            buffer.clear();
        }
        sourceChannel.close();
        targetChannel.close();
        inputStream.close();
        outputStream.close();
    }

    public static void copyFile2() throws IOException {
        File sourceFile = new File("D://my.txt");
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileChannel sourceChannel = inputStream.getChannel();
        File targetFile = new File("D://my3.txt");
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        FileChannel targetChannel = outputStream.getChannel();
        sourceChannel.transferTo(0, sourceFile.length(), targetChannel);
    }

    public static void copyFile3() throws IOException {
        File sourceFile = new File("D://my.txt");
        FileInputStream inputStream = new FileInputStream(sourceFile);
        FileChannel sourceChannel = inputStream.getChannel();
        File targetFile = new File("D://my4.txt");
        FileOutputStream outputStream = new FileOutputStream(targetFile);
        FileChannel targetChannel = outputStream.getChannel();
        targetChannel.transferFrom(sourceChannel, 0, sourceFile.length());
    }


    public static void mappedByteBuffer() throws Exception {
        RandomAccessFile file = new RandomAccessFile("D://my.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        fileChannel.read(byteBuffer);
//        byte[] bytes = mappedByteBuffer.array();
        System.out.println(new String(byteBuffer.array()));
        fileChannel.close();
    }

    public static void mutis() throws Exception {
        ByteBuffer[] list = new ByteBuffer[3];
        list[0] =ByteBuffer.allocate(5).put("88888".getBytes());
        list[1] = ByteBuffer.allocate(2).put("11".getBytes());
        list[2] = ByteBuffer.allocate(1).put("2".getBytes());
        for (ByteBuffer bb:list) {
            bb.flip();
        }
        RandomAccessFile file = new RandomAccessFile("D://my.txt", "rw");
        FileChannel fileChannel = file.getChannel();
        fileChannel.write(list);
        fileChannel.close();
        file.close();
    }

}
