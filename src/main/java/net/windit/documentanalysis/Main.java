package net.windit.documentanalysis;

/**
 * Created by yuank on 2017/10/21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("没有传递参数。用法：java -jar xxx.jar 源代码位置 包注释源代码位置 保存位置(文件夹)");
            return;
        }
        long start = System.currentTimeMillis();
        new Builder(args[0], args[1], args[2]).buildAll();
        long end = System.currentTimeMillis();
        System.out.println("运行耗时： " + (end - start) + "ms");
    }
}
