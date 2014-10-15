/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BioLinksDir;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Xinghua
 */
public class BioLinksDir {

    public BioLinksDir() {
    }
    
    public static void main(String[] args) throws IOException {
        BioLinksDir instance = new BioLinksDir();
        String inputFile = "C:\\Users\\Xinghua\\Desktop\\dna.json";
        String outputFolderDir = "C:\\Users\\Xinghua\\Desktop\\dna\\";
        if (args.length != 2) {
            System.out.println("two parameters are required!");
        }
        instance.SplitJsonFile(args[0],args[1]);
    }

    
    public void SplitJsonFile(String inputFile, String outputFolderDir){
        boolean no_next_node = false;      
        int startFileNameIndex;
        int endFileNameIndex;
        int hasNode;
        int endNode;
        int nextNode;
        String write2File;
        FileWriter writer;
        
        String inputContext = BioLinksDir.ReadFile(inputFile);
        //clean the outside Brace       
        inputContext = inputContext.substring(1, inputContext.length() - 1);
        //begin to find the first node
        while (true) {            
           //empty file
           hasNode = inputContext.indexOf("\"type\"");
           if(hasNode != -1){
               //get file name
               startFileNameIndex = inputContext.indexOf("\"") + 1;
               endFileNameIndex  =inputContext.indexOf("\"", startFileNameIndex);
               String fileName = inputContext.substring(startFileNameIndex, endFileNameIndex);
               //find end of the node           
               nextNode = inputContext.indexOf("\"type\"", hasNode + "\"type\"".length());
               if (nextNode == -1) {
                   no_next_node = true;
                   endNode = inputContext.length() - 1;
               } else {
                   endNode = inputContext.lastIndexOf(",", nextNode);
               }
               //write file
               String jsonFileName = outputFolderDir + fileName + ".json";
               write2File = "{" + inputContext.substring(0, endNode) + "}";
               inputContext = inputContext.substring(endNode + 1);
               try {
                   writer = new FileWriter(jsonFileName);
                   writer.write(write2File);
                   writer.close();
               }catch(IOException e){
                   System.out.println(e.toString());
               }
               //no more node
               if (no_next_node) {
                   break;
               }
           } else {
               break;
           }
        }
    
    }
    
    public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
            laststr += tempString;
        }
        reader.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return laststr;
    }

}
    
