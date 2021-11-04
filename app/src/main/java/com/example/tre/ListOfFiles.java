package com.example.tre;

import android.util.Log;

import java.io.File;
import java.io.IOException;
public class ListOfFiles {
   public static void mainT(String path) throws IOException {
      //Creating a File object for directory
      File directoryPath = new File("/storage/emulated/0/Music");
      //List of all files and directories
      String contents[] = directoryPath.list();
      System.out.println("List of files and directories in the specified directory:");
      for(int i=0; i<contents.length; i++) {
         Log.i("read",contents[i]);
      }
   }
}