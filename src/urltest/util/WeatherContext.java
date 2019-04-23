package urltest.util;


import java.io.*;
import java.util.HashMap;

public class WeatherContext {

    public static HashMap<String, String> NAME_CODE_MAP = new HashMap<>(1024);

    public static void makeContext() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./res/cityCode.txt")), "UTF-8"));
        String x = "";
        while ((x = br.readLine()) != null) {
            String[] sArray = x.split("=");
            NAME_CODE_MAP.put(sArray[1].trim(), sArray[0].trim());
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        //可以尝试nio读取
//       FileChannel fileChannel = new FileInputStream("res/cityCode.txt").getChannel();
//        CharBuffer buffer = ByteBuffer.allocate(1024).asCharBuffer();
//        MappedByteBuffer in = new RandomAccessFile("res/cityCode.txt","rw").getChannel()
//                .map(FileChannel.MapMode.READ_WRITE,0,50*1024);
//        for(int i = 0; i < 50*1024; i++){
//            System.out.print(new String(in.get(i),"utf-8"));
//        }

    }
}
