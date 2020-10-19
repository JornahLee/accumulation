package com.jornah.util;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.*;

import java.io.*;

import static com.jornah.util.TextDifferenceChecker.ChangeType.DELETE;
import static com.jornah.util.TextDifferenceChecker.ChangeType.INSERT;

public class TextDifferenceChecker {


    // 如何读懂 git diff 的结果 http://www.ruanyifeng.com/blog/2012/08/how_to_read_diff.html
    public static void main(String[] args) throws GitAPIException, IOException {
        File diffResult = new File("diff.txt");
        if (!diffResult.exists()) {
            diffResult.createNewFile();
        }
        // ChronoUnit.MILLIS.between()
        String text1 = "123\n" +
                "asd\n" +
                "123\n" +
                "\n" +
                "123\n";
        String text2 = "123\n" +
                "asd\n" +
                "223\n" +
                "\n" +
                "223\n";
        RawText before = new RawText(text1.getBytes());
        RawText after = new RawText(text2.getBytes());
        EditList diff = DiffAlgorithm.getAlgorithm(DiffAlgorithm.SupportedAlgorithm.MYERS).diff(RawTextComparator.DEFAULT, before, after);

        System.out.println("---------------------");
        FileOutputStream diffOriginOut = new FileOutputStream(diffResult);
        DiffFormatter diffFormatter = new DiffFormatter(diffOriginOut);
        diffFormatter.format(diff, before, after);
        diffOriginOut.flush();
        System.out.println("---------------------");
        diff.forEach(edit -> System.out.println(edit.toString()));
        System.out.println("diff.size(): " + diff.size());

        InputStreamReader isr = new InputStreamReader(new FileInputStream(diffResult));
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            ChangeType type = gitChangeType(line);
            sb.insert(0,"<p>");
            sb.append("</p>");
            if (type.equals(DELETE)) {
                sb.insert(2, " style='background-color: red'");
            } else if (type.equals(INSERT)) {
                sb.insert(2, " style='background-color: green'");
            }
            System.out.println(sb);
            sb.setLength(0);
        }

        // do {
        //     line = br.readLine();
        //     if (line.startsWith("-")) {
        //         sb.insert(0,"<p style='background-color: red'>");
        //         sb.append("</p>");
        //     } else if (line.startsWith("+")) {
        //         sb.insert(0,"<p style='background-color: green'>");
        //         sb.append("</p>");
        //     }
        //     sb.setLength(0);
        // } while (line != null);

        // InMemoryRepository repo = new InMemoryRepository.Builder().build();
        // Git git = new Git(repo);
        //
        // AddCommand add = git.add();
        // add.addFilepattern("someDirectory").call();
        // CommitCommand commit = git.commit();
        // commit.setMessage("initial commit").call();
        // git.diff().call();
    }

    private static ChangeType gitChangeType(String source) {
        if (source.startsWith("+")) {
            return ChangeType.INSERT;
        }
        if (source.startsWith("-")) {
            return DELETE;
        }
        return ChangeType.NONE;


    }

    public enum ChangeType {
        INSERT,DELETE,NONE
    }

}
