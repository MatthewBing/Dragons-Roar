package cuaccessibility.dragons_roar;

/**
 * Created by Evan on 3/22/17.
 */

public enum Number {
    ZERO (0, "2060"),
    ONE (1, "05"),
    TWO(2, "30"),
    THREE(3, "3060"),
    FOUR(4, "106"),
    FIVE(5, "1010"),
    FIVE_PHON(5, "101"),
    SIX(6, "202"),
    SEVEN(7, "20105"),
    EIGHT(8, "0203"),
    EIGHT_PHON(8, "03"),
    NINE(9, "5050"),
    NINE_PHON(9, "505"),
    TEN(10, "305"),
    ELEVEN(11, "040105"),
    TWELVE(12, "30410"),
    THIRTEEN(13, "306305"),
    FOURTEEN(14, "106305"),
    FIFTEEN(15, "101305"),
    SIXTEEN(16, "202305"),
    SEVENTEEN(17, "20105305"),
    EIGHTEEN(18, "020305"),
    EIGHTEEN_PHON(18, "0305"),
    NINETEEN(19, "5050305"),
    NINETEEN_PHON(19, "505305"),
    TWENTY(20, "30530"),
    THIRTY(30, "30630"),
    FOURTY(40, "10630"),
    FIFTY(50, "10130"),
    SIXTY(60, "20230"),
    SEVENTY(70, "2010530"),
    EIGHTY(80, "02030"),
    EIGHTY_PHON(80, "030"),
    NINETY(90, "505030"),
    NINETY_PHON(90, "50530"),
    HUNDRED(100, "053603"),
    HUNDRED_and(100, "053603053"),
    HUNDRE(100, "05360");

    private final int value;
    private final String code;
    private static final String LOG = "NUMBERPARSE: ";

    Number(int v, String c) {
        this.value = v;
        this.code = c;
    }

    public int getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }

    public static int lookupValue(String c) throws Exception { //make better variable name
        c = c.trim();
        for (Number val : Number.values()){
            if (val.getCode().equals(c)){
                return val.getValue();
            }
        }
        throw new Exception(c); //do better
    }

    //This will not work as expect, do it over!
    public static String lookupCode(int v) throws Exception{

        for (Number strVal : Number.values()){
            if (strVal.getValue() == v){
                return strVal.getCode();
            }
        }

        throw new Exception(Integer.toString(v)); //do better
    }
}
