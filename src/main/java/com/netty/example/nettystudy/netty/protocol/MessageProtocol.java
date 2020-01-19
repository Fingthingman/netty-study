package com.netty.example.nettystudy.netty.protocol;

/**
 * @NAME: MessageProtocol
 * @DATE: 2020/1/19
 * @Author Mr.MaL
 * @Description TODO
 **/
public class MessageProtocol {

    private int len;
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
