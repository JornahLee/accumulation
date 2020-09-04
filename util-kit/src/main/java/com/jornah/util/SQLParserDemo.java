package com.jornah.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.expression.operators.relational.ItemsListVisitor;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.expression.operators.relational.NamedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.FromItemVisitor;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.ParenthesisFromItem;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
import net.sf.jsqlparser.statement.values.ValuesStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL字符串 解析为 对象
 *
 * @author licong
 * @date 2020/7/6 下午4:45
 */
public class SQLParserDemo {
    public static void main(String[] args) throws JSQLParserException {

        // String insertSql = "insert into users(id,name,pwd) values(1,2,3)";
        // String selectSql = "select ff from users f ORDER BY d.id desc";
        // String insertMultiple = "INSERT INTO orders (`order_number`, `subscription_id`, `package_id`, `device_type`, `price`, `quantity`, `amount`, `currency`, `package_detail`, `payment_detail`, `payment_number`, `order_status`, `user_id`, `purchase_type`, `created_at`, `updated_at`, `payment_method_id`, `source`, product_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // CCJSqlParserUtil.parse("TRUNCATE TABLE `payment_methods`;");
        //order是保留关键字，导致解析出错。
        // String complexSql ="select a from users f ORDER BY d.`order` desc" ;
        // String complexSql ="select * FROM topup_packages WHERE `subscription_plan` ->> '$.itunes-product-id'=1" ;
        // String complexSql ="select * FROM topup_packages WHERE `subscription_plan`  ->>  '$.\"itunes-product-id\"' = '1d' " ;
        // String complexSql ="select * FROM topup_packages WHERE JSON_EXTRACT(`subscription_plan` ,'$.itunes-product-id')= 1" ;
        // String testSql="INSERT INTO user_numbers(id) values(LAST_INSERT_ID()+1);" ;
        String testSql="UPDATE users  SET expire_at=expire_at + ?,updated_at=now()  WHERE id in (?,?)" ;
        Statement parse = CCJSqlParserUtil.parse(testSql);
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
                    multiExprList.getExprList()
                            .forEach(expr -> expr.getExpressions()
                                    .add(new LongValue(1)));

                }
            });
            System.out.println("表名:" + tableName + " 列名:" + columns + " 属性:" + itemsList);
        }
        if (parse instanceof Select) {
            SelectBody selectBody = ((Select) parse).getSelectBody();
            PlainSelect select = (PlainSelect) selectBody;
            select.accept(new SelectVisitor() {
                @Override
                public void visit(PlainSelect plainSelect) {
                    List<Table> tables = new ArrayList<>();
                    // 因为 from 后面可能会有子查询，所以此处是get fromItem 而不是 get table
                    plainSelect.getFromItem().accept(new MyFromItemVisitor(tables));
                    tables.forEach(t -> {
                        System.out.println(t.getName());
                        System.out.println(t.getAlias());
                    });

                }

                @Override
                public void visit(SetOperationList setOpList) {

                }

                @Override
                public void visit(WithItem withItem) {

                }

                @Override
                public void visit(ValuesStatement aThis) {

                }
            });
            Expression where = select.getWhere();
            Column df = new Column("df");
            df.setTable(new Table("df"));
            String newCond = where + "and product_id= " + 1;
            // select.setWhere();
            Expression newWhere = CCJSqlParserUtil.parseCondExpression(newCond);
            // where.accept();
            select.setWhere(newWhere);
            System.out.println("--licg---     select : " + select + "    -----");

        }
        // 注意不用id 进行delete或者update的情况

    }

    public static class MyFromItemVisitor implements FromItemVisitor {
        List<Table> tables;

        public MyFromItemVisitor(List<Table> tables) {
            this.tables = tables;
        }

        @Override
        public void visit(Table table) {
            tables.add(table);
        }

        @Override
        public void visit(SubSelect subSelect) {

        }

        @Override
        public void visit(SubJoin subjoin) {

        }

        @Override
        public void visit(LateralSubSelect lateralSubSelect) {

        }

        @Override
        public void visit(ValuesList valuesList) {

        }

        @Override
        public void visit(TableFunction tableFunction) {

        }

        @Override
        public void visit(ParenthesisFromItem aThis) {

        }
    }


}
