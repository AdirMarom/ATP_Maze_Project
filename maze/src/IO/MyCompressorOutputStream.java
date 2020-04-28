package IO;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;

public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;
    private byte[] compress_array;
    public MyCompressorOutputStream(OutputStream outputStream){
        this.out=outputStream;
    }

    public void write(int b){}

    public void write(byte[] bytes){

        byte[] param_byte=Arrays.copyOfRange(bytes,0,30);
        String Param=Arrays.toString(param_byte);
        Param=Param.substring(1,Param.length()-1);
        Param=Param.replace(",","");
        Param=Param.replace(" ","");

        byte[] binary_value=Arrays.copyOfRange(bytes,30,bytes.length);
        String value=Arrays.toString(binary_value);
        String value_String=value.replace(",","");
        value_String=value_String.replace(" ","");
        value_String=value_String.substring(1,value_String.length()-1);

        String[] str=new String[bytes.length/8+1];
        str=value_String.split("(?<=\\G.{8})");
        String compress_str=fromBinaryStringToBase64(str);
        byte[] byteStrings=compress_str.getBytes() ;
        this.compress_array=byteStrings;
        String s=new String(byteStrings);
        byte[] b=Base64.getDecoder().decode(s);
        s="";
        byte temp;
        int[] arr=new int[b.length];
        for(int i=0 ;i<b.length;i++){
          temp=b[i];
          arr[i]=Byte.toUnsignedInt(temp);

          while(Integer.toBinaryString(arr[i]).length()<8){

          }
          s+=" "+Integer.toBinaryString(arr[i]);
        }
    }

    private String fromBinaryStringToBase64(String[] split) {
        byte[] arrayBinary = new byte[split.length];
        for(int i=0;i<split.length;i++){
            arrayBinary[i] = (byte)Integer.parseInt(split[i],2);
        }
        return Base64.getEncoder().encodeToString(arrayBinary);
    }


}
