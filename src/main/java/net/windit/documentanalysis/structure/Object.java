package net.windit.documentanalysis.structure;

import java.util.List;

/**
 * Created by yuank on 2017/12/9.
 */
public class Object {
    private String name;
    private String fullName;
    private List<Field> fields;
    private List<Method> methods;
    private List<Constructor> constructors;
    private List<Field> fieldsExtended;
    private List<Method> methodsExtended;
    private String description;

    public Object(String name, String fullName, List<Field> fields, List<Method> methods, List<Constructor> constructors, List<Field> fieldsExtended, List<Method> methodsExtended, String description) {
        this.name = name;
        this.fullName = fullName;
        this.fields = fields;
        this.methods = methods;
        this.constructors = constructors;
        this.fieldsExtended = fieldsExtended;
        this.methodsExtended = methodsExtended;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Field> getFields() {
        return fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public List<Constructor> getConstructors() {
        return constructors;
    }

    public List<Field> getFieldsExtended() {
        return fieldsExtended;
    }

    public List<Method> getMethodsExtended() {
        return methodsExtended;
    }

    public String getDescription() {
        return description;
    }
}
