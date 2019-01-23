package com.mbg.domain;

public class MybatisGeneratorConfig extends DatabaseConfig {

    private String mapperPackage;

    private String modelPackage;

    private String xmlPackage;


    private String table;

    private String schema;

    private String domainObjectName;

    private String mapperName;


    private String databaseType;

    private String sourcesRootPath;

    private String resourcesRootPath;

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getModelPackage() {
        return modelPackage;
    }

    public void setModelPackage(String modelPackage) {
        this.modelPackage = modelPackage;
    }

    public String getXmlPackage() {
        return xmlPackage;
    }

    public void setXmlPackage(String xmlPackage) {
        this.xmlPackage = xmlPackage;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getDomainObjectName() {
        return domainObjectName;
    }

    public void setDomainObjectName(String domainObjectName) {
        this.domainObjectName = domainObjectName;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getResourcesRootPath() {
        return resourcesRootPath;
    }

    public void setResourcesRootPath(String resourcesRootPath) {
        this.resourcesRootPath = resourcesRootPath;
    }

    public String getSourcesRootPath() {
        return sourcesRootPath;
    }

    public void setSourcesRootPath(String sourcesRootPath) {
        this.sourcesRootPath = sourcesRootPath;
    }
}
