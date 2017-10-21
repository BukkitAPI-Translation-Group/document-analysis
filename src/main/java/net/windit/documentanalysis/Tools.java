package net.windit.documentanalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yuank on 2017/10/21.
 */
public class Tools {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        File f = new File("H:\\Bukkit\\java\\org\\bukkit");
        List<File> list = listFiles(f);
        System.out.println(list.size());
        System.out.println(list);
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");

    }

    public static List<File> listFiles(File file) {
        return listFiles(file.getAbsolutePath());
    }

    public static List<File> listFiles(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return Collections.emptyList();
        }
        LinkedList<File> filelist = new LinkedList<File>();
        if (file.isFile()) {
            return Collections.EMPTY_LIST;
        }
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                filelist.addAll(listFiles(f));
            } else {
                filelist.add(f);
            }
        }
        return filelist;
    }

    public static String readFile(File file) {
        StringBuffer content = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String string;
            while ((string = bufferedReader.readLine()) != null) {
                content.append(string);
            }
            bufferedReader.close();
        } catch (IOException e) {
            return "";
        }
        return content.toString();
    }
}
