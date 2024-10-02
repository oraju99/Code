package com.example;

import lombok.Data;

@Data
public class DataFile implements DirectoryComponent {
    private String name;
    private Integer size;
    private String fileExtension;
}
