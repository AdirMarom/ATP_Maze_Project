package IO;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private int len_matrix;
    private int De_len_count=0;


    public MyDecompressorInputStream(InputStream inputStream ){
        this.in=inputStream;
    }

    public int read() throws IOException {return this.in.read();}

    public int get_Dsize(byte[] param_array){
        int col=0;
        int row=0;
        for(int i=0;i<10;i++) {
            if (i <= 4)
                row += Byte.toUnsignedInt(param_array[i]);
            else if (i > 4 && i <= 9)
                col += Byte.toUnsignedInt(param_array[i]);
        }
        int len_matrix=row*col;
        return len_matrix;
    }

    public byte[] str_array_to_byte(String s){
        byte[] b=new byte[s.length()];
        int[] temp=new int[s.length()];
        for(int i=0 ;i<s.length() ;i++){
            temp[i]=Integer.parseInt(String.valueOf(s.charAt(i)));
            b[i]=(byte)temp[i];
        }
        return b;
    }

    public int read(byte[] D_Compress_result){

        if(D_Compress_result==null)
            return 0;

        byte[] compress_byte = new byte[0];
        try {
            compress_byte=new byte[this.in.available()];
            this.in.read(compress_byte);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //compress parameter;
        byte[] param_array=Arrays.copyOfRange(compress_byte,0,30);
        this.len_matrix=get_Dsize(param_array);

        //compress value
        byte[] array_value=Arrays.copyOfRange(compress_byte,30,compress_byte.length);
        int[] Unsigh_value=convert_ByteSigh_to_UnSigh(array_value);

        String[] D_to_binary_compress=from_int_to_binary_chunk(Unsigh_value);

        String D_value_string= from_Binary_array_To_string(D_to_binary_compress,1,len_matrix+1);

        byte[] temp_arr=str_array_to_byte(D_value_string);
        concatenate(param_array,temp_arr,D_Compress_result);


        return 1;
    }

    private int[] convert_ByteSigh_to_UnSigh(byte[] arr){
        int[] res=new int[arr.length];
        for(int i=0 ;i<arr.length;i++){
            res[i]=Byte.toUnsignedInt(arr[i]);
        }
        return res;
    }

    private byte[] concatenate(byte[] a, byte[] b,byte[] res) {
        int aLen = a.length;
        int bLen = b.length;

        byte[] c = (byte[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        System.arraycopy(c,0,res,0,res.length);

        return c;
    }

    private String from_Binary_array_To_string(String[] arr,int start,int end){
        String result= Arrays.toString(arr);
        result=result.replace(",","");
        result=result.replace(" ","");
        result=result.substring(start,end);
        return result;
    }

    private String[] from_int_to_binary_chunk(int[] number_arr){

        String[] D_to_binary_compress=new String[this.len_matrix];
        for (int j=0 ;j<number_arr.length;j++){
            D_to_binary_compress[j]= Integer.toBinaryString(number_arr[j]);
            De_len_count+= D_to_binary_compress[j].length();
            if(j==number_arr.length-1)
                D_to_binary_compress[j]=eight_binary_number(D_to_binary_compress[j],true);
            else
                D_to_binary_compress[j]=eight_binary_number(D_to_binary_compress[j],false);
        }
        return D_to_binary_compress;
    }

    private String eight_binary_number(String Binary_num,boolean last){

        StringBuilder temp_str=new StringBuilder();
        temp_str.append(Binary_num);
        while (temp_str.length()<8 && De_len_count<this.len_matrix){
            if(last){
                temp_str.insert(0, 0);
                De_len_count++;
                if (De_len_count==this.len_matrix){
                    Binary_num=temp_str.toString();
                }
            }
            else{
                temp_str.insert(0, 0);
                De_len_count++;
                if (temp_str.length() == 8)
                    Binary_num=temp_str.toString();
            }
        }
        return Binary_num;
    }
}