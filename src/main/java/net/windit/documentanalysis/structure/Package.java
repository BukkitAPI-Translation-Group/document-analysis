package net.windit.documentanalysis.structure;

import java.util.List;

/**
 * Created by yuank on 2017/12/9.
 */
public class Package {
    private String name;
    private String description;
    private List<Object> objects;

    public Package(String name, String description, List<Object> objects) {
        this.name = name;
        this.description = description;
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public String getDescription() {
        return description;
    }
}
