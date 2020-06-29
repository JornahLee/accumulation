package com.jornah.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 使用DOM解析XML文件
 *
 * @author Administrator
 */
public class XMLDemo1 {
    public static void main(String[] args) throws IOException {

        File file = new File("/Users/licong/gitRepository/panda-api/api-boot/src/main/resources/com/qisike/panda/mapper");
        File[] allMapper = null;
        Map<String, List<String>> allTable = new HashMap<>();
        if (file.isDirectory()) {
            allMapper = file.listFiles();
            for (File mapper : allMapper) {
                System.out.println("mapper = " + mapper.getName());
                getColumnsByResultMap(mapper, allTable);
            }
        }
        File output = new File("/Users/licong/allColumn.txt");
        if (!output.exists()) {
            output.createNewFile();
        }
        FileWriter fw = new FileWriter(output, true);
        allTable.entrySet().stream()
                .forEach(entry -> {
                    try {
                        String key = entry.getKey();
                        int startIndex = key.lastIndexOf(".") + 1;
                        if (startIndex > 0) {
                            key = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, key.substring(startIndex).replaceAll("Mapper", ""));
                        }
                        fw.append(key).append('\n');

                        fw.append("<sql id=\"").append(key).append("Columns\">");
                        StringBuilder sb = new StringBuilder();
                        entry.getValue().stream().forEach(i -> sb.append(i).append(','));
                        if (sb.length() - 1 > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        fw.append(sb.toString());
                        fw.append("</sql>").append("\n");

                        fw.append("<include refid=\"").append(key)
                                .append("Columns").append("\"/>")
                                .append("\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        fw.flush();

    }

    private static void getColumnsByResultMap(File file, Map<String, List<String>> allTable) {
        StringBuilder sb = new StringBuilder();
        List<String> tableColumn = new ArrayList<>();
        try {
            Document document = XMLDemo1.getDocumentByFile(file);
            Element rootElement = document.getRootElement();// 获取根节点
            String mapper = rootElement.attribute("namespace").getValue();
            for (Iterator<?> iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                if (Objects.equals(element.getName(), "resultMap")) {
                    Iterator itr2 = element.elementIterator();
                    while (itr2.hasNext()) {
                        Element ele2 = (Element) itr2.next();
                        sb.setLength(0);

                        String column = Optional.ofNullable(ele2.attribute("column"))
                                .map(Attribute::getValue).orElse("");
                        sb.append("`").append(column).append("`");
                        tableColumn.add(sb.toString());
                    }
                }
            }
            allTable.put(mapper, tableColumn);
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
