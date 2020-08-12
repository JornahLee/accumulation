package com.jornah.xml;

import com.google.common.base.CaseFormat;
import com.graphbuilder.math.func.EFunction;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openxmlformats.schemas.drawingml.x2006.main.CTEffectStyleItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 使用DOM解析XML文件
 *
 * @author Administrator
 */
public class XMLDemo2 {
    // = >
    // EFunction
    // function
    public static void main(String[] args) throws IOException {
        getTextFromSqlTag();
    }

    private static void getTextFromSqlTag() throws IOException {
        String inputFilePath="/Users/licong/gitRepository/panda-api/api-boot/src/main/resources/com/qisike/panda/mapper";
        // String inputFilePath="/Users/licong/gitRepository/panda-jobs/src/main/resources/com/qisike/panda/mapper/";
        File file = new File(inputFilePath);

        String outputFileName="/Users/licong/apiAllSql-withSub.txt";

        File[] allMapper = null;
        Map<String, List<String>> allEntity = new HashMap<>();
        if (file.isDirectory()) {
            allMapper = file.listFiles();
            for (File mapper : allMapper) {
                System.out.println("mapper = " + mapper.getName());
                getSqlWithSubTag(mapper, allEntity);
            }
        }
        File output = new File(outputFileName);
        if (output.exists()) {
            output.delete();
        }
        output.createNewFile();
        FileWriter fw = new FileWriter(output, true);
        // allEntity.entrySet().stream().forEach();
        allEntity.entrySet().forEach(entry -> {
            // entity
            String entity = entry.getKey();
            try {
                fw.append(entity).append('\n');
                entry.getValue().forEach(sql -> {
                    try {
                        fw.append(sql).append('\n');
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                fw.append("\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fw.flush();
    }

    private static void getSqlFrom(File mapperFile, Map<String, List<String>> allTable) {
        // mapper下的 select insert update delete
        StringBuilder sb = new StringBuilder();
        List<String> entitySql = new ArrayList<>();
        try {
            Document document = XMLDemo1.getDocumentByFile(mapperFile);
            Element rootElement = document.getRootElement();// 获取根节点
            String mapperName = rootElement.attribute("namespace").getValue();
            for (Iterator<?> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                boolean isMatch = Objects.equals(element.getName(), "select") || Objects.equals(element.getName(), "insert") ||
                        Objects.equals(element.getName(), "update") || Objects.equals(element.getName(), "delete");
                if (isMatch) {
                    String text = element.getText();
                    String sql = text.replaceAll("\r|\n", "");
                    sb.insert(0, "explain ").append(sql);
                }
                if(StringUtils.isNotBlank(sb.toString())){
                    entitySql.add(sb.toString());
                }
                sb.setLength(0);
            }
            allTable.put(mapperName, entitySql);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
    private static void getSqlWithSubTag(File mapperFile, Map<String, List<String>> allTable) {
        // mapper下的 select insert update delete
        StringBuilder sb = new StringBuilder();
        List<String> entitySql = new ArrayList<>();
        try {
            Document document = XMLDemo1.getDocumentByFile(mapperFile);
            Element rootElement = document.getRootElement();// 获取根节点
            String mapperName = rootElement.attribute("namespace").getValue();
            for (Iterator<?> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                boolean isMatch = Objects.equals(element.getName(), "select") || Objects.equals(element.getName(), "insert") ||
                        Objects.equals(element.getName(), "update") || Objects.equals(element.getName(), "delete");
                if (isMatch) {
                    if (!element.isTextOnly()) {
                        String text = element.getText();
                        String sql = text.replaceAll("\r|\n", "").trim();
                        sb.append(sql);
                    }
                }
                if(StringUtils.isNotBlank(sb.toString())){
                    entitySql.add(sb.toString());
                }
                sb.setLength(0);
            }
            allTable.put(mapperName, entitySql);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }


    public static Document getDocumentByFile(File file, boolean validate) throws DocumentException {
        // 读取XML文件,获得document对象
        SAXReader saxReader = new SAXReader();
        saxReader.setValidation(validate);
        Document document1 = saxReader.read(file);
        return document1;
    }

    public static Document getDocumentByFile(File file) throws DocumentException {
        return getDocumentByFile(file, true);
    }

    public static Document getDocumentByStr(String xmlStr) throws DocumentException {
        // 解析XML形式的字符串,得到document对象.
        String xmlText = "<response><result>1</result><desc>保存成功</desc></response>";
        Document document2 = DocumentHelper.parseText(xmlStr);
        return document2;
    }

    public static Document createDocument() {
        //主动创建document对象
        Document document3 = DocumentHelper.createDocument(DocumentHelper.createElement("root").addAttribute("id", "1"));
        System.out.println(document3.getRootElement().attributeValue("id")); //打印结果：1
        return document3;

    }

}
