package com.example;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JavaCode {
    public static void main(String[] args) {

        String filePath = "";
        String searchParameter = "";
        String searchValue = "";



        SpringApplication.run(JavaCode.class, args);
    }

    private List<String> searchResult(String filePath, String searchParameter, String searchValue) {
        File file=new File(filePath);

        List<String> searchResult = new ArrayList<>();

        // checking search Parameter

        File[] directoryComponents = file.listFiles();
        for( int i=0;i< directoryComponents.length;i++ ) {
            File currentFile = directoryComponents[i];
            String currentFileName = currentFile.getName();

            // search on the basis of name or extension
            // FILENAME.FILE-EXTENSION

            if(searchParameter.equalsIgnoreCase("NAME")) {
                // searching matching

                if (currentFileName.length() > 0)
                {
                    int endIndex = currentFileName.lastIndexOf(".");
                    if (endIndex != -1)
                    {
                        String currentFileNameWithoutExtension = currentFileName.substring(0, endIndex);
                        if (currentFileNameWithoutExtension.equalsIgnoreCase(searchValue)) {
                            searchResult.add(currentFileName);
                        }
                    }
                }
            }

            if(searchParameter.equalsIgnoreCase("NAME")) {
                // searching matching

                if (currentFileName.length() > 0)
                {
                    int endIndex = currentFileName.lastIndexOf(".");
                    if (endIndex != -1)
                    {
                        String currentFileNameWithoutExtension = currentFileName.substring(0, endIndex);
                        if (currentFileNameWithoutExtension.equalsIgnoreCase(searchValue)) {
                            searchResult.add(currentFileName);
                        }
                    }
                }
            }

        }



    }
}
