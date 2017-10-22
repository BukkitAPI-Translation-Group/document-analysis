package net.windit.documentanalysis;

import com.alibaba.fastjson.JSON;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.javadoc.Javadoc;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuank on 2017/10/21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("没有传递参数。用法：java -jar xxx.jar 源代码位置 包注释源代码位置");
            return;
        }
        String sourceDir = args[0];
        String packageDescriptionDir = args[1];
        long start = System.currentTimeMillis();
        List<File> files = Tools.listFiles(sourceDir);
        List<File> packages = Tools.listFiles(packageDescriptionDir);
        HashMap<String, String> packageDescriptions = new HashMap<>();
        for (File file : packages) {
            String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length());
            if (!fileType.equalsIgnoreCase("java")) continue;
            CompilationUnit cu = JavaParser.parse(file);
            NodeList<TypeDeclaration<?>> types = cu.getTypes();
            String packageName = cu.getPackageDeclaration().get().getNameAsString();
            //Pattern pattern = Pattern.compile("");
            String packageDescription = Tools.readFile(file); // TODO:暂无法用javaparser读取包注释
            packageDescriptions.put(packageName, packageDescription);
        }
        HashMap<String, Map<String, String>> map = new HashMap<>();
        for (File file : files) {
            CompilationUnit cu = JavaParser.parse(file);
            NodeList<TypeDeclaration<?>> types = cu.getTypes();
            String pn = cu.getPackageDeclaration().get().getNameAsString();
            //System.out.println(types.get(0).getName());
            for (TypeDeclaration<?> type : types) {
                ArrayList<String> list = new ArrayList<>();
                HashMap<String, String> membercomments = new HashMap<>();
                if (type.getJavadoc().isPresent()) {
                    //list.add(type.getJavadoc().get().getDescription().toText());
                    membercomments.put("ClassDescription", type.getJavadoc().get().getDescription().toText());
                }
                NodeList<BodyDeclaration<?>> members = type.getMembers();
                for (BodyDeclaration<?> member : members) {
                    if (member instanceof NodeWithJavadoc) {
                        NodeWithJavadoc javadoc = (NodeWithJavadoc) member;
                        if (javadoc.getJavadoc().isPresent()) {
                            // TODO: 暂时只分析方法注释
                            if (member instanceof MethodDeclaration) {
                                membercomments.put(((MethodDeclaration) member).getNameAsString(), ((Javadoc) javadoc.getJavadoc().get()).getDescription().toText());
                            }
                            //list.add(((Javadoc) javadoc.getJavadoc().get()).getDescription().toText());
                        }

                        //System.out.println(javadoc.getJavadoc());
                    }
                }
                map.put(type.getNameAsString(), membercomments);
            }
        }
        String json = JSON.toJSONString(map, true);
        Files.write(FileSystems.getDefault().getPath("H:\\", "result.json"), json.getBytes());
        //System.out.println(json);
        long end = System.currentTimeMillis();
        System.out.println("运行耗时： " + (end - start) + "ms");
    }
}
