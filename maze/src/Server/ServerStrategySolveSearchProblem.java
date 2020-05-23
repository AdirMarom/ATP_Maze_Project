package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerStrategySolveSearchProblem implements IServerStrategy{
    private Maze myMaze;
    private Solution mySol;
    private ISearchingAlgorithm SearchAlgorithm;

    public ServerStrategySolveSearchProblem(){
        super();
        this.myMaze=new Maze();
        this.mySol=null;
        this.SearchAlgorithm=Configurations.getISearchingAlgorithm();

    }

    private Solution found_the_solution(File soles,String num_of_sol){
        Solution sol=null;
        File[] file_list=soles.listFiles();
        ArrayList<File> my_list=new ArrayList<File>() ;
        for(int i=0;i<file_list.length;i++){
            my_list.add(file_list[i]);
        }
        try {
            for(int i=0;i<my_list.size();i++) {
                String name_sol=my_list.get(i).getName().substring(3);
                if(num_of_sol.equals(name_sol)){
                    ObjectInputStream buf = new ObjectInputStream(new FileInputStream(my_list.get(i).getPath()));
                    sol=(Solution) buf.readObject();
                    break;
                }
            }
        }
        catch (Exception e){}
        return sol;


    }

    private String found_match_maze(File mazes){
        String result=null;
        File[] file_list=mazes.listFiles();
        ArrayList<File> my_list=new ArrayList<File>() ;
        for(int i=0;i<file_list.length;i++){
            my_list.add(file_list[i]);
        }
        for(int i=0;i<my_list.size();i++) {
            try {
                ObjectInputStream buf = new ObjectInputStream(new FileInputStream(my_list.get(i).getPath()));
                Maze maze = (Maze) buf.readObject();
                if (maze.equals(this.myMaze)) {
                    String name = my_list.get(i).getName();
                    result = name.substring(4);
                    break;
                }
            }
            catch (Exception e){}

        }
        return result;

    }

    private void createUserDir(final String dirName) throws IOException {
            final File homeDir = new File(System.getProperty("java.io.tmpdir"));
            final File dir = new File(homeDir, dirName);
            if (!dir.exists() && !dir.mkdirs()) {
                throw new IOException("Unable to create " + dir.getAbsolutePath());
            }
        }

    public void WriteToTmp(String name,String Directory_name,Object o){
        if(o==null)
            return;
        String Directory="java.io.tmpdir";
        String tempDir = System.getProperty(Directory);
        tempDir+=Directory_name+"\\"+name+".tmp";
        File Dir=new File(tempDir);
        File file_Name= null;
        try {

            File myobj=new File(tempDir);
            file_Name=myobj;
            FileWriter Write=new FileWriter(file_Name.getName(),true);
            ObjectOutputStream buf = new ObjectOutputStream(new FileOutputStream(file_Name.getPath()));
            buf.writeObject(o);
            buf.flush();
            buf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void serverStrategy(InputStream inFromClient, OutputStream outToClient){

        try {
            ObjectOutputStream toClient = new ObjectOutputStream(outToClient);
            ObjectInputStream fromClient = new ObjectInputStream(inFromClient);
            this.myMaze=(Maze)fromClient.readObject();

            createUserDir("savedMazed");
            createUserDir("savedSol");

            final File mazes = new File(System.getProperty("java.io.tmpdir")+"/savedMazed");
            final File soles = new File(System.getProperty("java.io.tmpdir")+"/savedSol");

            int count_mazes=mazes.listFiles().length;
            int count_soles=soles.listFiles().length;

            String found=found_match_maze(mazes);

            if(found!=null){

                this.mySol=found_the_solution(soles,found);
            }

            else{

                ISearchable Smaze=new SearchableMaze(this.myMaze) ;
                this.mySol=SearchAlgorithm.solve(Smaze);
                if(this.mySol==null)
                    System.out.println("this solution is null ");

                String maze_name="maze"+count_mazes;
                String sol_name="sol"+count_soles;

                WriteToTmp(maze_name,"savedMazed",this.myMaze);
                WriteToTmp(sol_name,"savedSol",this.mySol);
            }
            toClient.writeObject(this.mySol);
            toClient.flush();
            toClient.close();

        }   catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
        }

    }
}
