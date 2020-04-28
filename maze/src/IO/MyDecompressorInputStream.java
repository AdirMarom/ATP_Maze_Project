package IO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private byte[] Dcomp_array;


    public MyDecompressorInputStream(InputStream inputStream ){
        this.in=inputStream;
    }

    public int read() throws IOException {
        return 0;
    }

    public int read(byte[] compress_byte){
        String s1= Arrays.toString(compress_byte);
        String[] str=s1.split(",");
        this.Dcomp_array=Base64.getDecoder().decode(s1);

        /*
        String s2=s1.replace(",","");
        s2=s2.replace(" ","");
        s2=s2.substring(1);
        s2=s2.substring(0,s2.length()-2);
        System.out.println(s2);
        str=s2.split("(?<=\\G.{8})");
*/
        return 0;
    }

    private String fromBase64ToBinaryString(String[] split) {
        byte[] temp;
        String result="";
        byte[] arrayBinary = new byte[split.length];
        for(int i=0;i<split.length;i++){
            temp=Base64.getDecoder().decode(split[i]);
            result+=Arrays.toString(temp)+",";
        }
        return result;
    }


}
