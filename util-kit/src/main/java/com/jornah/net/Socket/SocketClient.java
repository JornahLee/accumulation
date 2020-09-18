package com.jornah.net.Socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {
    public static void main(String args[]){
        // 要连接的服务端IP地址和端口
        String host = "10.148.81.197";
        int port = 8089;
        // 与服务端建立连接
        Socket socket = null;
        OutputStream outputStream = null;
        int len=0;
        try{
            socket = new Socket(host, port);
            // 建立连接后获得输出流
            outputStream = socket.getOutputStream();
            InputStream in =new FileInputStream("d:\\socketDemo\\client\\file1.txt");
            byte[] buff=new byte[1024];
            while((len=in.read(buff))!=-1){
                outputStream.write(buff,0,len);
            }
            outputStream.flush();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket!=null){
                    socket.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}