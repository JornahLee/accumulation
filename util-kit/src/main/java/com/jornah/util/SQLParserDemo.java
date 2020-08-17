package com.jornah.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;

import java.util.List;

/**
 * SQL字符串 解析为 对象
 * @author  licong
 * @date  2020/7/6 下午4:45
 */
public class SQLParserDemo {
    public static void main(String[] args) throws JSQLParserException {

        String insertSql = "insert into users(id,name,pwd) values(1,2,3)";
        // String selectSql = "select d,ff,ff from users join devices where id in (1,2) group by id Order by id limit 1,100";

        Statement parse = CCJSqlParserUtil.parse(insertSql);
        if (parse instanceof Insert) {
            Insert insert = (Insert) parse;
            String tableName = insert.getTable().getName();
            List<Column> columns = insert.getColumns();
            columns.add(new Column("product_id"));
            ItemsList itemsList = insert.getItemsList();
            itemsList.accept(new ItemsListVisitor() {
                @Override
                public void visit(SubSelect subSelect) {
                }

                @Override
                public void visit(ExpressionList expressionList) {
                    expressionList.getExpressions().add(new LongValue(1));
                }

                @Override
                public void visit(NamedExpressionList namedExpressionList) {
                }

                @Override
                public void visit(MultiExpressionList multiExprList) {
                }
            });
            System.out.println("表名:"+tableName+" 列名:"+columns+" 属性:"+itemsList);
        }
        if (parse instanceof Select) {
            SelectBody selectBody = ((Select) parse).getSelectBody();
            PlainSelect select = (PlainSelect) selectBody;
            Expression where = select.getWhere();
            String newCond=where+"and product_id= "+ 1;
            // select.setWhere();
            Expression newWhere = CCJSqlParserUtil.parseCondExpression(newCond);
            // where.accept();
            select.setWhere(newWhere);
            System.out.println("--licg---     select : " + select + "    -----");

        }
        // 注意不用id 进行delete或者update的情况

    }

}
