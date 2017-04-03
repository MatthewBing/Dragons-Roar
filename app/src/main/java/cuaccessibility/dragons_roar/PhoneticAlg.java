package cuaccessibility.dragons_roar;

import android.text.TextUtils;

import java.util.AbstractMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Phonetic algorithm class to deal with parsing commands and dice rolls from a voice signal. This
 * deals both with applying the phonetic algorithm and selecting the intended command and finding
 * arguments.
 *
 * Created by Evan on 3/15/17.
 */


public class PhoneticAlg {

    private final String LOG = "PHONALG: ";

    private String generateCode(String in){
        StringBuilder codeString = new StringBuilder();
        in = in.toLowerCase().trim();

        for (int i=0; i < in.length(); i++){
            switch(in.charAt(i)){
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                case 'w':
                case 'h':
                    codeString.append('0');
                    break;
                case 'b':
                case 'f':
                case 'p':
                case 'v':
                    codeString.append('1');
                    break;
                case 'c':
                case 'g':
                case 'j':
                case 'k':
                case 'q':
                case 's':
                case 'x':
                case 'z':
                    codeString.append('2');
                    break;
                case 'd':
                case 't':
                    codeString.append('3');
                    break;
                case 'l':
                    codeString.append('4');
                    break;
                case 'm':
                case 'n':
                    codeString.append('5');
                    break;
                case 'r':
                    codeString.append('6');
                    break;
            }

        }
        for (int i = 1; i < codeString.length(); i++){
            if (codeString.charAt(i-1) == codeString.charAt(i)){
                codeString.replace(i, i+1, "");
            }
        }
        return codeString.toString();
    }

    private String findD(String code) throws Exception {
        for (int i = code.length() - 1; i > 0; i--) {
            if (code.substring(i-1, i+1).equals("30")) {
                //then d is found, return
                return code.substring(0, i-1);
            }
        }

        throw new Exception();
    }

    private int findNum(String code) throws Exception {
        //Note: see if this can be done more efficiently and compactly
        Pattern numberRegexPattern = Pattern.compile("(05)|(30)|(3060)|(106)|(1010)|(101)|(202)|" +
                "(20105)|(0203)|(030)|(505)|(305)|(040105)|(30410)|(306305)|(106305)|(101305)|" +
                "(202305)|(20105305)|(020305)|(0305)|(5050305)|505305|(30630)|(30530)|(10630)|" +
                "(10130)|(20230)|(2010530)|(02030)|(030)|(505030)|(50530)|(053603)|(05360)");
        Matcher numberRegexMatcher;
        String bestMatch = "";
        int numValue = 0;
        int priorLength = code.length();

        for (int i = code.length() - 1; i > code.length()-9 && i >= 0; i--){
            numberRegexMatcher = numberRegexPattern.matcher(code.substring(i, code.length()));
            if (numberRegexMatcher.matches()) {
                bestMatch = code.substring(i, code.length());
                priorLength = i;
            }
        }

        numValue += Number.lookupValue(bestMatch);

        if (numValue < 10){
            try{
                numberRegexPattern = Pattern.compile("(30630)|(30530)|(10630)|(10130)|(20230)|" +
                        "(2010530)|(02030)|(030)|(505030)|(50530)|(053603)|(05360)");
                bestMatch = "";
                code = code.substring(0, priorLength);
                for (int i = code.length() - 1; i > code.length()-8 && i >= 0 ; i--){
                    numberRegexMatcher = numberRegexPattern.matcher(code.substring(i, code.length()));
                    if (numberRegexMatcher.matches()){
                        bestMatch = code.substring(i, code.length());
                        priorLength = i;
                    }
                    numValue += Number.lookupValue(bestMatch);
                }
            }catch (Exception e){
                return numValue;
            }
        }

        if (numValue < 100){
            try{
                numberRegexPattern = Pattern.compile("(053603)|(05360)");
                bestMatch = "";
                code = code.substring(0, priorLength);
                for (int i = code.length() - 1; i > code.length()-7 && i >= 0 ; i--){
                    numberRegexMatcher = numberRegexPattern.matcher(code.substring(i, code.length()));
                    if (numberRegexMatcher.matches()){
                        bestMatch = code.substring(i, code.length());
                        priorLength = i;
                    }
                    numValue += Number.lookupValue(bestMatch);
                }
            }catch (Exception e){
                return numValue;
            }
        }
        return numValue;
    }

    //Still has some flaws - will work out later
    public AbstractMap.SimpleEntry parseBothValues(String transcription) throws Exception {

        int dice, size;
        String transcriptionCode = generateCode(transcription);
        try{
            size = findNum(transcriptionCode);
            transcriptionCode = transcriptionCode.substring(0, transcriptionCode.length() - Number.lookupCode(size).length());
            dice = parseDiceValue(transcriptionCode);
            return new AbstractMap.SimpleEntry<Integer, Integer>(dice, size);
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }

    public int parseDiceValue(String transcription) throws Exception {
        int dice;
        String transcriptionCode;

        if (!TextUtils.isDigitsOnly(transcription)){
            transcriptionCode = generateCode(transcription);
        }else{
            transcriptionCode = transcription;
        }

        transcriptionCode = findD(transcriptionCode);
        dice = findNum(transcriptionCode);

        return dice;
    }

}
