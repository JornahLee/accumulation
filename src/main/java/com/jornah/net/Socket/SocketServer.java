package com.jornah.net.Socket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SocketServer {
    public static DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH-mm");
    public static void main(String[] args) throws Exception {
        int port = 0;
        ServerSocket server = new ServerSocket(port);
        System.out.println("--licg---  server.getLocalPort() : " + server.getLocalPort() + "-----");
        System.out.println("waitting client request");
        Socket socket=null;
        File rFile=null;
        FileOutputStream fos=null;
        try{
            while(true){
                socket = server.accept();
                Date date=new Date(System.currentTimeMillis());
                rFile = new File("d:\\temp\\ManageAcctProd\\server\\receiveFile_"+dateFormat.format(date)+".txt");
                fos=new FileOutputStream(rFile);
                System.out.println("--licg---  acctpt ------");
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = inputStream.read(bytes)) != -1) {
                    fos.write(bytes,0,len);
                }
                fos.flush();
                fos.close();
                inputStream.close();
                socket.close();
            }
        }finally {
            server.close();
        }

    }
}