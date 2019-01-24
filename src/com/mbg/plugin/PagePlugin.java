package com.mbg.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

public abstract class PagePlugin extends PluginAdapter {

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        addField("limit",topLevelClass,introspectedTable);
        addField("offset",topLevelClass,introspectedTable);
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        setXmlCode(element,introspectedTable);
        return true;
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    public abstract void setXmlCode(XmlElement element, IntrospectedTable introspectedTable);

    private void addField(String name, TopLevelClass topLevelClass, IntrospectedTable introspectedTable){
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
//		field.setType(FullyQualifiedJavaType.getIntInstance());
        field.setType(PrimitiveTypeWrapper.getIntegerInstance());
        field.setName(name);
//		field.setInitializationString("-1");
        topLevelClass.addField(field);
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);
        Method setMethod = new Method();
        setMethod.setVisibility(JavaVisibility.PUBLIC);
        setMethod.setName("set" + camel);
        setMethod.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));
        setMethod.addBodyLine("this." + name + "=" + name + ";");
        topLevelClass.addMethod(setMethod);
        Method getMethod = new Method();
        getMethod.setVisibility(JavaVisibility.PUBLIC);
        getMethod.setReturnType(PrimitiveTypeWrapper.getIntegerInstance());
        getMethod.setName("get" + camel);
        getMethod.addBodyLine("return " + name + ";");
        topLevelClass.addMethod(getMethod);
    }
}
