package com.jornah.markdown;

import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.DataKey;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.util.Arrays;

// https://github.com/vsch/flexmark-java/tree/master/flexmark-java-samples/src/com/vladsch/flexmark/java/samples
public class TocSubContextSample2 {
    final private static DataHolder OPTIONS = new MutableDataSet().set(Parser.EXTENSIONS, Arrays.asList(
            TocExtension.create()
    ));

    static final Parser PARSER = Parser.builder(OPTIONS).build();
    static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).indentSize(2).build();
    final public static DataKey<String> TOC_HTML = new DataKey<>("TOC_HTML", "");



    // use the PARSER to parse and RENDERER to render with pegdown compatibility
    public static void main(String[] args) {
        // You can re-use parser and renderer instances
        Document document = PARSER.parse("" +
                "[TOC] \n" +
                "\n" +
                "# Heading **some bold** 1\n" +
                "## Heading 1.1 _some italic_\n" +
                "### Heading 1.1.1\n" +
                "### Heading 1.1.2  **_some bold italic_**\n" +
                "");
        String html = RENDERER.render(document);
        String toc = TOC_HTML.get(document);

        System.out.println("<div class=\"toc\">");
        System.out.print(toc);
        System.out.println("</div>");

        System.out.println("<div class=\"body\">");
        System.out.print(html);
        System.out.println("</div>");


    }
}