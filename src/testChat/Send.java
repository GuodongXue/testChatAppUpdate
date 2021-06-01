package testChat;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Send {
    public static void main(String[] args)   {
        //创建套接字
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(8889);

            while(true){
                //创捷数据包

                Scanner sc = new Scanner(System.in);
                System.out.print("Student: ");
                String str = sc.next();

                byte[] b = str.getBytes();
                DatagramPacket dp = new DatagramPacket(b, b.length,
                        InetAddress.getByName("localhost"), 9998);

                //发送数据包
                ds.send(dp);
                if("byebye".equals(str)){
                    System.out.println("聊天结束");
                    break;
                }

                //进行接收
                byte[] by = new byte[1024];
                DatagramPacket dp2 = new DatagramPacket(by, by.length);

                //接受数据,将数据放入数据包
                ds.receive(dp2);

                String s = new String(dp2.getData(), 0, dp2.getLength());
                System.out.println("Teacher said: "+s);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            //关闭资源
            ds.close();
        }

    }
}

