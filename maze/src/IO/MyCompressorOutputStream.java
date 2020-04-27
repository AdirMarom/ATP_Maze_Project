package IO;

import java.io.OutputStream;
import java.util.Arrays;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream outputStream;
    public MyCompressorOutputStream(){
    }

    public void write(int b){

    }

    public void write(byte[] bytes){

        String s1= Arrays.toString(bytes);
        String s2=s1.replace(",","");
        s2=s2.replace(" ","");
        s2=s2.substring(1);
        s2=s2.substring(0,s2.length()-2);
        System.out.println(s2);
        String[] str=new String[bytes.length/8+1];
        str=s2.split("(?<=\\G.{8})");
        byte[] byteStrings = str




    }
}
