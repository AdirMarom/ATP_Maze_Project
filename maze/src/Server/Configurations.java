package Server;
import algorithms.search.BestFirstSearch;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.DepthFirstSearch;
import algorithms.search.ISearchingAlgorithm;

import java.io.*;
import java.util.Properties;

public class Configurations {

    public static String numOfThreads(){ return String.valueOf(Runtime.getRuntime().availableProcessors());}

    public static ISearchingAlgorithm getISearchingAlgorithm(){
        String algorithm="";
        try (InputStream input = new FileInputStream("C:\\Users\\adirm\\OneDrive\\Desktop\\github\\ATP_maze\\maze\\resources\\config.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            algorithm=prop.getProperty("Searchable");
        } catch (IOException ex) { ex.printStackTrace(); }
            // get the property value and return

            if(algorithm.contains("BFS"))
                return new BreadthFirstSearch();
            else if(algorithm.contains("DFS"))
                return new DepthFirstSearch();
            else
                return new BestFirstSearch();
    }

    public static int GetThreadNumber(){

        int num=2;
        try (InputStream input = new FileInputStream("C:\\Users\\adirm\\OneDrive\\Desktop\\github\\ATP_maze\\maze\\resources\\config.properties")) {

            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            num=Integer.parseInt(prop.getProperty("numOfThread"));



        } catch (IOException ex) { ex.printStackTrace(); }
        return num;
    }


/*
    public static void main(){

        try (OutputStream output = new FileOutputStream("C:\\Users\\adirm\\OneDrive\\Desktop\\github\\ATP_maze\\maze\\resources\\config.properties")) {

            Properties prop=new Properties();
            prop.getProperty("Searchable","BFS");
            prop.getProperty("numOfThread",numOfThreads());
            prop.store(output,null);

        } catch (IOException e) { e.printStackTrace();}

        }
*/



    }


