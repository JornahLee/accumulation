package com.jornah.elasticsearch.service;

import com.google.gson.Gson;
import com.jornah.elasticsearch.dao.ArticleRepository;
import com.jornah.elasticsearch.entity.Article;
import com.jornah.elasticsearch.util.RandomUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestService {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ArticleRepository articleRepository;

    public Article newArticle(String title,String text) {
        //构建并保存Article
        Article article = new Article();
        article.setId(RandomUtil.randomInt(10000, 99999));
        article.setTitle(title);
        article.setText(text);
        article.setCreatetime(new Date());
        article.setAuthor("zhengkai.blog.csdn.net");
        return  article;
    }

    public Object save(String title,String text) {
        //构建并保存Article
        Article article = new Article();
        article.setId(RandomUtil.randomInt(10000, 99999));
        article.setTitle(title);
        article.setText(text);
        article.setCreatetime(new Date());
        article.setAuthor("zhengkai.blog.csdn.net");
        articleRepository.save(article);
        return article;
    }

    public String search(String title, Pageable pageable) {

        //以下查询等同于封装了{"query":{"bool":{"must":[{"wildcard":{"title.keyword":{"wildcard":"*SpringBoot*","boost":1}}}],"disable_coord":false,"adjust_pure_negative":true,"boost":1}}}
        //按标题进行模糊查询
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title.keyword", title);
        //按照顺序构建builder,bool->must->wildcard ,有了上文的JSON,顺序就很好理解了
        BoolQueryBuilder must = QueryBuilders.boolQuery().must(queryBuilder);
        //封装pageable分页
        Page<Article> queryResult = articleRepository.search(must, pageable);
        //返回
        Gson gson=new Gson();
        return  gson.toJson(queryResult.getContent());
    }

    public Object originSearch(String title) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("title.keyword", "*SpringBoot*");
        BoolQueryBuilder must = boolQuery.must(queryBuilder);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        NativeSearchQuery build = nativeSearchQueryBuilder.withQuery(must).build();
        List<Article> queryForList = elasticsearchTemplate.queryForList(build, Article.class);
        return queryForList;
    }
//返回
}
