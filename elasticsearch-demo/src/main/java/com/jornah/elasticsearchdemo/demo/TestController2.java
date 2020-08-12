package com.jornah.elasticsearchdemo.demo;

import com.jornah.elasticsearchdemo.pojo.Person;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;

@RestController
@RequestMapping("/v2")
public class TestController2 {

    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public TestController2(ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @PostMapping("/person")
    public String save(@RequestBody Person person) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchRestTemplate.index(indexQuery);
        return documentId;
    }

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id") Long id) {
        Person person = elasticsearchRestTemplate
                .queryForObject(GetQuery.getById(id.toString()), Person.class);
        return person;
    }

    @GetMapping("/person/findByKeyword")
    public List<Person> findByKeyword(String keyword) {
        PageRequest pageRequest = PageRequest.of(0, 200, Sort.by("lastname").descending());
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchQuery("firstname", keyword))
                .withPageable(pageRequest)
                .build();
        List<Person> people = elasticsearchRestTemplate.queryForList(searchQuery, Person.class);
        people.forEach(System.out::println);
        return people;
    }

    @GetMapping("/person/find/all")
    public List<Person> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 200);
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .withPageable(pageRequest)
                .build();
        return elasticsearchRestTemplate.queryForList(searchQuery, Person.class);
    }

    // filter 比query 速度更快
    // query的时候i，会先比较查询条件，然后计算分值，最后返回文档结果；
    // 而filter则是先判断是否满足查询条件，如果不满足，会缓存查询过程（记录该文档不满足结果）；
    // 满足的话，就直接缓存结果
    // 综上所述，filter快在两个方面：
    //         1.对结果进行缓存
    // 2.避免计算分值
    public Object findByFilter() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(
                        QueryBuilders.boolQuery()
                                .must(matchQuery("price", 80))  //结构类似.bool -> must ->match
                                // 对结果进行过滤
                                .filter(rangeQuery("price").gt(6))
                )

                .build();
        return elasticsearchRestTemplate.queryForList(searchQuery, Person.class);
    }
        //短语搜索
    public Object findByPhrase() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchPhraseQuery("name","heiren")
                )
                .build();

        return elasticsearchRestTemplate.queryForList(searchQuery, Person.class);
    }
    public Object findWithHighlight() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchPhraseQuery("name", "heiren")
                )
                .withHighlightFields(new HighlightBuilder.Field("name"))
                .build();
        return elasticsearchRestTemplate.queryForList(searchQuery, Person.class);
    }

    // https://blog.csdn.net/mj86534210/article/details/79910909 参考文献
}