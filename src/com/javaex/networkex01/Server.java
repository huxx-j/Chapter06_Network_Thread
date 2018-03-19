package com.javaex.networkex01;

import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("192.168.1.48", 1001));

        System.out.println("=======<서버시작>=======");
        System.out.println("======================");
        System.out.println("[ 연결을 기다리고 있습니다. ]");

        Socket socket = serverSocket.accept();
        System.out.println("[클라이언트가 연결 되었습니다.]");

        // 메세지 받기

        InputStream is = socket.getInputStream();
        Reader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);

        // 메세지 보내기
        OutputStream os = socket.getOutputStream();
        Writer osw = new OutputStreamWriter(os, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);
        String msg;

        while (true) {
            msg = br.readLine();
            if (msg == null) {
                System.out.println("클라이언트 접속 종료");
                break;
            }
            System.out.println("받은 메세지 : " + msg);

            bw.write(msg);
            bw.newLine();
            bw.flush(); // 버퍼가 안차도 일단 보냄
        }

        br.close();

        System.out.println("======================");
        System.out.println("=======<서버종료>=======");

        serverSocket.close();
    }
}
