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

    public int read() throws IOException { return 0; }

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
            temp[i]=(int)s.charAt(i);
            b[i]=(byte)temp[i];
        }
        return b;
    }
    public int read(byte[] compress_byte){

        //compress parameter;
        byte[] param_array=Arrays.copyOfRange(compress_byte,0,30);
        int len_matrix=get_Dsize(param_array);

        //compress value
        byte[] array_value=Arrays.copyOfRange(compress_byte,30,compress_byte.length);
       // String base64_str=new String(array_value);
        // System.out.println(base64_str);
       // byte[] Dcompress=base64_str.getBytes();
       // System.out.println(Arrays.toString(Dcompress));
        // Base64.getDecoder().decode(base64_str);

        String value_string= Arrays.toString(array_value);
        String[] D_to_binary_compress=new String[len_matrix];

        StringBuilder temp_str=new StringBuilder();

        for (int j=0 ;j<array_value.length;j++){

            D_to_binary_compress[j]= Integer.toBinaryString(array_value[j]);

            temp_str.delete(0,temp_str.capacity());
            temp_str.append( D_to_binary_compress[j]);

            while (temp_str.length()<8 && j*8<len_matrix){
                temp_str.insert(0,0);
                if(temp_str.length()==8)
                    D_to_binary_compress[j]=temp_str.toString();
            }
        }
        String D_value_string= Arrays.toString(D_to_binary_compress);
        D_value_string=D_value_string.replace(",","");
        D_value_string=D_value_string.replace(" ","");
        D_value_string=D_value_string.substring(0,len_matrix-1);

        this.Dcomp_array=str_array_to_byte(D_value_string);

        System.out.println(Arrays.toString(Dcomp_array));

        return 0;
    }

}
