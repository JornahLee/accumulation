package com.jornah.i18n;

import com.jornah.interfaces.Translator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;

public class I18nMessageGenerator {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/licong/gitRepository/panda-api/api-boot/src/main/resources");
        String[] targetLan = {"ar", "es", "fr", "in", "ja", "ko", "ru", "th", "tr"};
        String key = "";
        String value = "无效邀请码";
        I18nMessageGenerator i18nMessageGenerator = new I18nMessageGenerator();
        i18nMessageGenerator.generateMessage(file, targetLan,
                key, value,
                new MyTranslator(), i18nMessageGenerator.append());
    }

    private void generateMessage(File targetDir, String[] targetLan,
                                 String i18key, String value,
                                 Translator translator, BiConsumer<File, String> outType) {
        File[] files = getTargetFiles(targetDir);
        for (String lan : targetLan) {
            Arrays.stream(files).forEach(file -> {
                if (file.getName().contains("_" + lan + ".")) {
                    String i18Result = translator.translate(value, lan);
                    String output = String.format("\n%s=%s\n", i18key, i18Result);
                    // 指定输出方式，自定义输出位置
                    outType.accept(file, output);
                    System.out.println(lan + ": " + output + "\n");
                }
            });
        }
        // Process exec = Runtime.getRuntime().exec(shell);
    }

    private BiConsumer<File, String> append() {
        return (file, output) -> {
            try (FileWriter fw = new FileWriter(file, true)) {
                fw.append(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private BiConsumer<File, String> insert() throws IOException {
        // FileReader fr = new FileReader("");
        // File file;
        // byte[] buff = new byte[1024];
        // BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        // ByteOutputStream bos = new ByteOutputStream();
        // while ((bis.read(buff)) != -1) {
        //     bos.write(buff);
        // }
        // String allText = bos.toString();

        return (file, output) -> {
            try (FileWriter fw = new FileWriter(file, true)) {
                fw.append(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    private File[] getTargetFiles(File targetDir) {
        File[] files;
        if (targetDir.isDirectory()) {
            files = targetDir.listFiles();
            if (Objects.isNull(files)) {
                throw new RuntimeException(targetDir.getAbsolutePath() + "is empty");
            }
        } else {
            files = new File[]{targetDir};
        }
        return files;
    }

    /**
     * Translator暂时还未有实现类
     */
    public static class MyTranslator implements Translator {

        @Override
        public String translate(String word, String srcLan, String tarLan) {
            return null;
        }

        @Override
        public String translate(String word, String tarLan) {
            return null;
        }
    }
}
