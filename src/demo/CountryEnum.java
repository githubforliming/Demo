package demo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author LM
 * @create 2019-09-08 12:18
 */
public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer retCode;
    private String retMessage;

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArray  = CountryEnum.values();
        for (CountryEnum c : myArray) {
             if (c.retCode == index) {
                return c;
            }
        }
        return null;
    }
    // 构造
    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    // get set
    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }
}
