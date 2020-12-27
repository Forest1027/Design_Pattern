package com.forest.design_pattern.section02.creational.builder.exercise;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        CodeBuilder codeBuilder = new CodeBuilder("Person").addField("name", "String").addField("age", "int");
        System.out.println(codeBuilder);
    }
}

class Field {
    private String name;
    private String type;

    public Field(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

class Code {
    String className;
    List<Field> fields = new ArrayList<>();

    public Code(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        String result = "public class "+className+"\n{\n";
        for (Field field: fields
             ) {
            result += "  public "+field.getType()+" "+ field.getName()+";\n";
        }
        result += "}";

        return result;
    }
}

class CodeBuilder
{
    protected Code code;

    public CodeBuilder(String className)
    {
        code = new Code(className);
    }

    public CodeBuilder addField(String name, String type)
    {
        code.fields.add(new Field(name,type));
        return this;
    }

    @Override
    public String toString()
    {
        return  code.toString();
    }
}
