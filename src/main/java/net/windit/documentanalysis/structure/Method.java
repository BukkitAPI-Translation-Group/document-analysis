package net.windit.documentanalysis.structure;

/**
 * Created by yuank on 2017/12/9.
 */
public class Method {
    private String name;
    private String fullName;
    private String type;
    private String description;

<<<<<<< HEAD
    public Method(String name, String fullName, String type, String description) {
=======
    public Method(String name, String fullName,String type, String description) {
>>>>>>> 7632e50e45e374f32c28725da15acd6d52cc8d03
        this.name = name;
        this.fullName = fullName;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
