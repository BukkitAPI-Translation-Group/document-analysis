package net.windit.documentanalysis;

import com.alibaba.fastjson.JSON;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.javadoc.Javadoc;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by yuank on 2017/10/21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        List<File> files = Tools.listFiles("H:\\git\\Chinese_BukkitAPI\\BukkitApi");
        List<File> packages = Tools.listFiles("H:\\git\\Chinese_BukkitAPI\\javadoc");
        HashMap<String, String> packageDescriptions = new HashMap<>();
        for (File file : packages) {
            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
            if (!fileType.equalsIgnoreCase("java")) continue;
            CompilationUnit cu = JavaParser.parse(file);
            NodeList<TypeDeclaration<?>> types = cu.getTypes();
            String packageName = cu.getPackageDeclaration().get().getNameAsString();
            String code = cu.toString();
            Pattern pattern = Pattern.compile("");
            String packageDescription = null;
            System.out.println(packageDescription);
            packageDescriptions.put(packageName, packageDescription);
        }
        //System.out.println(packageDescriptions);
        HashMap<String, List<String>> map = new HashMap<>();
        for (File file : files) {
            CompilationUnit cu = JavaParser.parse(file);
            NodeList<TypeDeclaration<?>> types = cu.getTypes();
            String pn = cu.getPackageDeclaration().get().getNameAsString();
            //System.out.println(types.get(0).getName());
            for (TypeDeclaration<?> type : types) {
                ArrayList<String> list = new ArrayList<>();
                if (type.getJavadoc().isPresent()) {
                    list.add(type.getJavadoc().get().getDescription().toText());
                }
                NodeList<BodyDeclaration<?>> members = type.getMembers();
                for (BodyDeclaration<?> member : members) {
                    if (member instanceof NodeWithJavadoc) {
                        NodeWithJavadoc javadoc = (NodeWithJavadoc) member;
                        if (javadoc.getJavadoc().isPresent()) {
                            list.add(((Javadoc) javadoc.getJavadoc().get()).getDescription().toText());
                        }

                        //System.out.println(javadoc.getJavadoc());
                    }
                }
                map.put(type.getName().asString(), list);
            }
        }
        String json = JSON.toJSONString(map, true);
        Files.write(FileSystems.getDefault().getPath("H:\\", "result.json"), json.getBytes());
        //System.out.println(json);
        long end = System.currentTimeMillis();
        System.out.println("运行耗时： " + (end - start) + "ms");

        /*Path path = FileSystems.getDefault().getPath("H:\\Bukkit\\java\\org\\bukkit\\event\\player", "AsyncPlayerPreLoginEvent.java");
        CompilationUnit cu = JavaParser.parse(path);
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        System.out.println(types.size());
        for (TypeDeclaration<?> type : types) {

            /*NodeList<BodyDeclaration<?>> members = type.getMembers();
            for (BodyDeclaration<?> member : members) {
                if (member instanceof NodeWithJavadoc) {
                    NodeWithJavadoc javadoc = (NodeWithJavadoc) member;
                    System.out.println(member.toString());
                    System.out.println(javadoc.getJavadoc());
                }*/
                /*if (member instanceof MethodDeclaration) {
                    MethodDeclaration method = (MethodDeclaration) member;
                    System.out.println(method.getName() + "\n" + method.getJavadoc() + "\n");
                }*/
    }
}
