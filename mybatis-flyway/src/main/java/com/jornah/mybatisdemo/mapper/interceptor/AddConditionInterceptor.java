package com.jornah.mybatisdemo.mapper.interceptor;

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
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.TableFunction;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;

// mybatis 拦截器，只要交给spring管理，就会自动被注册，所以在配置类里配置时候只能使用spring容器里的，
// 如果重新new的话，则会重复注册拦截器，导致重复拦截
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class AddConditionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        resetSql(invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private void resetSql(Invocation invocation) throws JSQLParserException, NoSuchFieldException, IllegalAccessException {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();

        if (StringUtils.isNotEmpty(boundSql.getSql())) {
            Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, resetSql(boundSql.getSql()));
        }
    }

    /*自定义SQL*/
    private String resetSql(String sql) throws JSQLParserException {
        Statement parse = CCJSqlParserUtil.parse(sql);
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
                    expressionList.getExpressions().add(new LongValue(2));
                }

                @Override
                public void visit(NamedExpressionList namedExpressionList) {
                }

                @Override
                public void visit(MultiExpressionList multiExprList) {
                }
            });
        }
        if (parse instanceof Select) {
            SelectBody selectBody = ((Select) parse).getSelectBody();
            PlainSelect select = (PlainSelect) selectBody;
            select.getFromItem().accept(new FromItemVisitor() {
                @Override
                public void visit(Table tableName) {
                    tableName.setName("gaiguo_de_biao");
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
            });
            Expression where = select.getWhere();
            String newCond = where + "and product_id= " + 2;
            Expression newWhere = CCJSqlParserUtil.parseCondExpression(newCond);
            select.setWhere(newWhere);

        }
        System.out.println("--licg---     parse : " + parse + "    -----");
        return parse.toString();
    }

}
