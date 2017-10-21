package net.windit.documentanalysis;

import com.github.javaparser.JavaParser;
import com.github.javaparser.javadoc.description.JavadocDescription;
import com.github.javaparser.javadoc.description.JavadocDescriptionElement;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by yuank on 2017/10/21.
 */
public class Test2 {
    public static void main(String[] args) throws Exception {
        String code = "/**\n" +
                "* a\n" +
                "*/\n" +
                "package helloworld;";
        JavadocDescription jd = JavaParser.parseJavadoc(code).getDescription();
        Class clazz = jd.getClass();
        Field f = clazz.getDeclaredField("elements");
        f.setAccessible(true);
        List<JavadocDescriptionElement> list = (List<JavadocDescriptionElement>) f.get(jd);
        System.out.println(list.get(0).toText());
    }
}
