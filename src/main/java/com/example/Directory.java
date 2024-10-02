package com.example;

import lombok.Data;

import java.util.List;

@Data
public class Directory implements DirectoryComponent {
    private String name;
    private Integer size;
    private List<DirectoryComponent> directoryComponentsList;

//    public List<String> getAllFiles(String searchParameter, String searchValue) {
//        for (DirectoryComponent directoryComponent:directoryComponentsList) {
//
//        }
//    }
}
