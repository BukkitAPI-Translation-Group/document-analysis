package net.windit.documentanalysis.structure;

import java.util.List;

/**
 * Created by yuank on 2017/12/9.
 */
public class Field {
    private String name;
    private String description;
    private List<String> modifiers;
    private String type;

    public Field(String name, String description, List<String> modifiers, String type) {
        this.name = name;
        this.description = description;
        this.modifiers = modifiers;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getModifiers() {
        return modifiers;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "name:" + name + ",description:" + description + ",type:" + type;
    }
}
