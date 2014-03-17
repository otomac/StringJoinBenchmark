package jp.co.acroquest.benchmark;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;

import java.util.StringJoiner;

public class StringJoinStudy {

    private static final int TIMES = 10000;
    private static final String[] strArray = new String[TIMES];

    private static final String DELIMITER = ",";

    @GenerateMicroBenchmark
    public void plusJoin() {
        String text = "abcde";
        String temp = "";
        for (int i = 0; i < TIMES; i++) {
            if (temp.length() > 0) {
                temp += DELIMITER;
            }
            temp += text;
        }

        String joined = temp;
        //System.out.println("joined=" + joined);
    }

    @GenerateMicroBenchmark
    public void bufferJoin() {
        String text = "abcde";
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < TIMES; i++) {
            if (temp.length() > 0) {
                temp.append(DELIMITER);
            }
            temp.append(text);
        }

        String joined = temp.toString();
        //System.out.println("joined=" + joined);
    }

    @GenerateMicroBenchmark
    public void builderJoin() {
        String text = "abcde";
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < TIMES; i++) {
            if (temp.length() > 0) {
                temp.append(DELIMITER);
            }
            temp.append(text);
        }

        String joined = temp.toString();
        //System.out.println("joined=" + joined);
    }

    @GenerateMicroBenchmark
    public void joinJoin() {
        String joined = String.join(DELIMITER, strArray);
        //System.out.println("joined=" + joined);
    }

    @GenerateMicroBenchmark
    public void joinerJoin() {
        StringJoiner joiner = new StringJoiner(DELIMITER);
        for (String str : strArray) {
            joiner.add(str);
        }

        String joined = joiner.toString();
        //System.out.println("joined=" + joined);
    }

    public static void createArray() {
        for (int i = 0; i < TIMES; i++) {
            strArray[i] = "abcde";
        }
    }

    public static void main(String[] args) throws Exception {
        createArray();
        Main.main("-wi 5 -i 10 -f 10".split(" "));
    }
}
