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
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SubSelect;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Properties;

@Component
@Intercepts({@org.apache.ibatis.plugin.Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
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
        final Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
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
                    expressionList.getExpressions().add(new LongValue(1));
                }

                @Override
                public void visit(NamedExpressionList namedExpressionList) {
                }

                @Override
                public void visit(MultiExpressionList multiExprList) {
                }
            });
            System.out.println("表名:" + tableName + " 列名:" + columns + " 属性:" + itemsList);
        }
        if (parse instanceof Select) {
            SelectBody selectBody = ((Select) parse).getSelectBody();
            PlainSelect select = (PlainSelect) selectBody;
            Expression where = select.getWhere();
            String newCond = where + "and product_id= " + 1;
            Expression newWhere = CCJSqlParserUtil.parseCondExpression(newCond);
            select.setWhere(newWhere);
            System.out.println("--licg---     select : " + select + "    -----");

        }
        return null;
    }

}
