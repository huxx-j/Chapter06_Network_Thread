package com.javaex.networkex01;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();

        System.out.println("====<클라이언트 시작>====");
        System.out.println("=========================");
        System.out.println("[서버에 연결을 요청합니다.]");

        socket.connect(new InetSocketAddress("192.168.1.48", 1001));
        // 메세지 보내기
        System.out.println("[서버에 연결 되었습니다.]");
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        BufferedWriter bw = new BufferedWriter(osw);

        // 메세지 받기
        InputStream is = socket.getInputStream();
        Reader isr = new InputStreamReader(is, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        Scanner sc = new Scanner(System.in);

        String str = "";

        while (true) {

            if ("/q".equals(str)) {
                System.out.println("접속종료되었습니다.");
                break;
            }
            str = sc.nextLine();

            bw.write(str);
            bw.newLine();
            bw.flush();

            String reMsg = br.readLine();
            System.out.println("server : [" + reMsg + "]");
        }

        // String str = "테스트입니다.";

        /*
         * bw.write(str); bw.newLine(); bw.flush();
         *
         * String reMsg = br.readLine(); System.out.println("server : [" + reMsg
         * + "]");
         */

        br.close();
        bw.close();

        System.out.println("=========================");
        System.out.println("====<클라이언트 종료>====");

        socket.close();
    }
}
