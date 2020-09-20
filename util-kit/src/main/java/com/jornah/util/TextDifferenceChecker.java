package com.jornah.util;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.*;

import java.io.*;

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
        DiffFormatter diffFormatter = new DiffFormatter(System.out);
        diffFormatter.format(diff, before, after);

        System.out.println("---------------------");
        diff.forEach(edit -> System.out.println(edit.toString()));
        System.out.println("diff.size(): " + diff.size());


        // InMemoryRepository repo = new InMemoryRepository.Builder().build();
        // Git git = new Git(repo);
        //
        // AddCommand add = git.add();
        // add.addFilepattern("someDirectory").call();
        // CommitCommand commit = git.commit();
        // commit.setMessage("initial commit").call();
        // git.diff().call();
    }

}
