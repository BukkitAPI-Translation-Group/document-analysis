package net.windit.documentanalysis;

import com.alibaba.fastjson.JSON;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.nodeTypes.NodeWithJavadoc;
import com.github.javaparser.javadoc.Javadoc;
import net.windit.documentanalysis.structure.Field;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuank on 2017/12/9.
 */
public class Builder {
    private String sourcePath;
    private String packageInfoSourcePath;
    private String saveTo;

<<<<<<< HEAD
    public Builder(String sourcePath, String packageInfoSourcePath, String saveTo) throws NotDirectoryException, IOException {
=======
    public Builder(String sourcePath, String packageInfoSourcePath, String saveTo) throws NotDirectoryException,IOException {
>>>>>>> 7632e50e45e374f32c28725da15acd6d52cc8d03
        File sp = new File(sourcePath);
        File pisp = new File(packageInfoSourcePath);
        File st = new File(saveTo);
        if (!sp.isDirectory() || !pisp.isDirectory()) {
            throw new NotDirectoryException("指定位置不是文件夹");
        }
        if (!st.exists()) {
            Files.createDirectory(FileSystems.getDefault().getPath(st.getName()));
        }
        this.sourcePath = sourcePath;
        this.packageInfoSourcePath = packageInfoSourcePath;
        this.saveTo = saveTo;
    }

    public void buildAll() throws Exception {
        buildPackages();
    }

    public void buildPackages() throws Exception {
        String sourceDir = sourcePath;
        String packageDescriptionDir = packageInfoSourcePath;
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
        StringBuilder fields = new StringBuilder();
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
                                MethodDeclaration method = ((MethodDeclaration) member);
                                membercomments.put(((MethodDeclaration) member).getNameAsString(), ((Javadoc) javadoc.getJavadoc().get()).getDescription().toText());
                            }
                            if (member.isFieldDeclaration()) {
                                FieldDeclaration field = ((FieldDeclaration) member);
                                Field f = new Field(field.getVariables().get(0).getNameAsString(), ((Javadoc) javadoc.getJavadoc().get()).getDescription().toText(), null, field.getElementType().asString());
                                fields.append(f.toString() + "\n");
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
<<<<<<< HEAD
        Files.write(FileSystems.getDefault().getPath(saveTo, "result.json"), json.getBytes());
        Files.write(FileSystems.getDefault().getPath(saveTo, "fields.json"), fields.toString().getBytes());
        Files.write(FileSystems.getDefault().getPath(saveTo, "packages.json"), JSON.toJSONString(packageDescriptions, true).getBytes());
=======
        Files.write(FileSystems.getDefault().getPath("H:\\", "result.json"), json.getBytes());
        Files.write(FileSystems.getDefault().getPath("H:\\", "fields.json"), fields.toString().getBytes());
        Files.write(FileSystems.getDefault().getPath("H:\\", "packages.json"),JSON.toJSONString(packageDescriptions, true).getBytes());
>>>>>>> 7632e50e45e374f32c28725da15acd6d52cc8d03
        //System.out.println(json);
    }

    public void buildObjectIndex() {
    }

    public void buildFieldIndex() {
    }

    public void buildMethodIndex() {
    }

    public void buildConstructorIndex() {
    }
}
