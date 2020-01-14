package com.netty.example.nettystudy.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @NAME: BioTest
 * @DATE: 2019/12/19
 * @Author Mr.MaL
 * @Description TODO
 **/
public class BioTest {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            executorService.submit(()->handle(socket));
        }

    }

    private static void handle(Socket socket) {

        try  {
            while (true){
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                inputStream.read(bytes);
                System.out.println(new String(bytes));
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write("你好呀".getBytes());
                outputStream.flush();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
