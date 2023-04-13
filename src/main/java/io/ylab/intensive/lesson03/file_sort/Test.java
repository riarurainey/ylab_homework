package io.ylab.intensive.lesson03.file_sort;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        File dataFile = new Generator().generate("src/ylab_homework_03.file_sort/resources/data.txt", 375_000_000);
        System.out.println(new Validator(dataFile).isSorted()); // false
        File sortedFile = new Sorter().sortFile(dataFile);
        System.out.println(new Validator(sortedFile).isSorted()); // true
    }
}

