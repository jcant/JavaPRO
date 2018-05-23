package compressor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LCodeConvert implements LetterCodeConverter {
    @Override
    public String convertCodesToLetters(String nameCoded) {
        Pattern p = Pattern.compile("&#\\d*;");
        Matcher m = p.matcher(nameCoded);
        while (m.find()) {
            String gr = m.group();
            char ch = (char) Integer.parseInt(gr.substring(2, gr.length() - 1));
            nameCoded = nameCoded.replaceFirst(gr, "" + ch);
        }

        return nameCoded;
    }
}
