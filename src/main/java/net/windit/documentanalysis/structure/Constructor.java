package net.windit.documentanalysis.structure;

/**
 * Created by yuank on 2017/12/9.
 */
public class Constructor {
    private String name;
    private String fullName;
    private String description;

    public Constructor(String name, String fullName, String description) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getDescription() {
        return description;
    }
}