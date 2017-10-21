package net.windit.documentanalysis;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.comments.JavadocComment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by yuank on 2017/10/21.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        File file = new File("H:\\git\\Chinese_BukkitAPI\\javadoc\\org\\bukkit\\package-info.java");
        CompilationUnit cu = JavaParser.parse("/**\n* Hello world\n*/\npackage helloworld;");
        System.out.println(cu.getAllContainedComments());
    }
}
