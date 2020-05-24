package IO;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;


/**
 * decoding compress byte array.
 */
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;
    private int len_matrix;
    private int De_len_count=0;
    private int Decompress_actual_size;

    /**
     *set size of decompress array
     * @param size
     */
    private void set_actual_size(int size){this.Decompress_actual_size=size;}

    /**
     *constructor
     * @param inputStream object type
     */
    public MyDecompressorInputStream(InputStream inputStream ){this.in=inputStream;}

    /**
     *
     * @return override function;
     * @throws IOException
     */
    public int read() throws IOException {return this.in.read();}

    /**
     *get compress row and column
     * @param param_array
     * @return matrix dimension
     */
    public int get_dimension(byte[] param_array){
        int col=0;
        int row=0;
        for(int i=0;i<8;i++) {
            if (i <= 3)
                row += Byte.toUnsignedInt(param_array[i]);
            else if (i > 3 && i <= 7)
                col += Byte.toUnsignedInt(param_array[i]);
        }
        int len_matrix=row*col;
        return len_matrix;
    }

    /**
     *take binary number in string convert every number (0 / 1) to byte  cell
     * @param s-
     * @return
     */
    public byte[] str_array_to_byte(String s){

        byte[] b=new byte[s.length()];
        int[] temp=new int[s.length()];
        for(int i=0 ;i<s.length() ;i++){
            temp[i]=Integer.parseInt(String.valueOf(s.charAt(i)));
            b[i]=(byte)temp[i];
        }
        return b;
    }

    /**
     * read compress byte fill empty array in decompress value
     * @param D_Compress_result empty byte array
     * @return
     */
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
        byte[] param_array=Arrays.copyOfRange(compress_byte,0,24);
        this.len_matrix= get_dimension(param_array);

        //compress value
        byte[] array_value=Arrays.copyOfRange(compress_byte,24,compress_byte.length);
        int[] Unsigh_value=convert_ByteSigh_to_UnSigh(array_value);

        String[] D_to_binary_compress=from_int_to_binary_chunk(Unsigh_value);

        String D_value_string= from_Binary_array_To_string(D_to_binary_compress,1,len_matrix+1);

        byte[] temp_arr=str_array_to_byte(D_value_string);
        set_actual_size(24+this.len_matrix);
        concatenate(param_array,temp_arr,D_Compress_result);
        return 1;
    }

    /**
     *
     * @param arr byte sigh array
     * @return unsigh int<256
     */
    private int[] convert_ByteSigh_to_UnSigh(byte[] arr){
        int[] res=new int[arr.length];
        for(int i=0 ;i<arr.length;i++){
            res[i]=Byte.toUnsignedInt(arr[i]);
        }
        return res;
    }

    /**
     *concatenate a and b array to res array
     * @param a
     * @param b
     * @param res
     * @return res array= a + b
     */
    private byte[] concatenate(byte[] a, byte[] b,byte[] res) {
        int aLen = a.length;
        int bLen = b.length;

        byte[] c = (byte[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        System.arraycopy(c,0,res,0,this.Decompress_actual_size);

        return c;
    }

    /**
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private String from_Binary_array_To_string(String[] arr,int start,int end){
        String result= Arrays.toString(arr);
        result=result.replace(",","");
        result=result.replace(" ","");
        result=result.substring(start,end);
        return result;
    }

    /**
     *
     * @param number_arr
     * @return string of 8 number in binary base in every cell
     */
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

    /**
     *
     * @param Binary_num
     * @param last
     * @return binary number 8 digit
     */
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