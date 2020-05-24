package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Base64;

/**
 * compress byte array to base 64
 */

public class MyCompressorOutputStream extends OutputStream {
    private OutputStream out;

    /**
     *
     * @param outputStream
     */
    public MyCompressorOutputStream(OutputStream outputStream){
        this.out=outputStream ;
    }

    /**
     ** override function
     * @param b
     */
    public void write(int b){
        try {
            out.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * compress byte value (index 24-end) to base 64
     * @param bytes uncompress byte[] array
     */
    public void write(byte[] bytes){

        if(bytes==null)
            return;

        //compress parameter;
        byte[] param_array=Arrays.copyOfRange(bytes,0,24);

        //compress value
        byte[] value_array=Arrays.copyOfRange(bytes,24,bytes.length);
        String value_string= Arrays.toString(value_array);
        value_string=value_string.replace(",","");
        value_string=value_string.replace(" ","");
        value_string=value_string.substring(1,value_string.length()-1);

        String[] binary_chunks=value_string.split("(?<=\\G.{8})");
        byte[] comp =fromBinaryStringToBase64(binary_chunks);
        byte[] result=new byte[param_array.length+comp.length];

        for(int i=0 ;i<result.length ;i++){
            if(i<24)
                result[i]=param_array[i];
            else
                result[i]=comp[i-24];
        }
        try {

            if(this.out instanceof ObjectOutputStream){
                ObjectOutputStream c=(ObjectOutputStream)out;
                c.writeObject(result);
            }
            else{
                this.out.write(result);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param split chunks of 8 binary chunk convert to 1 base64
     * @return conpress string to string base64
     */
    private byte[] fromBinaryStringToBase64(String[] split) {
        byte[] arrayBinary = new byte[split.length];
        for(int i=0;i<split.length;i++){
            arrayBinary[i] = (byte)Integer.parseInt(split[i],2);
        }
        return arrayBinary;

    }

}