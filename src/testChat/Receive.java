package testChat;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Receive {
    public static void main(String[] args)  {
        //创建套接字
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(9998);

            while (true){

                //接受数据包
                byte[] b = new byte[1024];
                DatagramPacket dp = new DatagramPacket(b, b.length);

                //接受数据,将数据放入数据包
                ds.receive(dp);

                String str = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Student said: "+str);
                if("byebye".equals(str)){
                    System.out.println("聊天结束");
                    break;
                }

                //向对方说话
                Scanner sc = new Scanner(System.in);
                System.out.print("Teacher: ");
                String str2 = sc.next();
                byte[] by= str2.getBytes();
                DatagramPacket dp2 = new DatagramPacket(by, by.length,
                        InetAddress.getByName("localhost"), 8889);

                //发送数据包
                ds.send(dp2);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {


            ds.close();
        }


    }
}
