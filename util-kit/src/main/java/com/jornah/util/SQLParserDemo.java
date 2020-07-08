package com.jornah.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;

import java.util.List;

/**
 * SQL字符串 解析为 对象
 * @author  licong
 * @date  2020/7/6 下午4:45
 */
public class SQLParserDemo {
    public static void main(String[] args) throws JSQLParserException {

        String insertSql = "insert into user (id,name,age) value(1001,'范闲',20)";
        Statement parse = CCJSqlParserUtil.parse(insertSql);
        Insert insert = (Insert) parse;

        String tableName = insert.getTable().getName();
        List<Column> columns = insert.getColumns();
        ItemsList itemsList = insert.getItemsList();
        System.out.println("表名:"+tableName+" 列名:"+columns+" 属性:"+itemsList);
    }

}
