package com.jornah.pojo;

import java.util.Set;
import java.util.TreeSet;

public class DbTable {
    private String dbName;
    private String tableName;
    private Set<String> columns;

    public DbTable(String dbName, String tableName, Set<String> columns) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<String> getColumns() {
        return columns;
    }

    public void setColumns(Set<String> columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "DbTable{" +
                "dbName='" + dbName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columns=" + columns +
                '}';
    }
}
