/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import java.util.Locale;
import java.util.StringTokenizer;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author huanlu
 */
@ManagedBean
@ApplicationScoped
public class StringUtil {
        /** 
     * 使用StringTokenizer类将字符串按分隔符转换成字符数组 
     * @param string 字符串  
     * @param divisionChar 分隔符 
     * @return 字符串数组 
     * @see [类、类#方法、类#成员] 
     */  
    public String[] stringAnalytical(String string, String divisionChar)  
    {  
        int i = 0;  
        StringTokenizer tokenizer = new StringTokenizer(string, divisionChar);  
          
        String[] str = new String[tokenizer.countTokens()];  
          
        while (tokenizer.hasMoreTokens())  
        {  
            str[i] = new String();  
            str[i] = tokenizer.nextToken();  
            i++;  
        }  
          
        return str;  
    }  
      
    /** 
     * 字符串解析，不使用StringTokenizer类和java.lang.String的split()方法 
     * 将字符串根据分割符转换成字符串数组 
     * @param string 字符串 
     * @param c 分隔符 
     * @return 解析后的字符串数组 
     */  
    public String[] stringAnalytical(String string, char c)  
    {  
        //字符串中分隔符的个数  
        int count = 0;  
          
        //如果不含分割符则返回字符本身  
        if (string.indexOf(c) == -1)  
        {  
            return new String[]{string};  
        }  
          
        char[] cs = string.toCharArray();  
          
        //过滤掉第一个和最后一个是分隔符的情况  
        for (int i = 1; i < cs.length -1; i++)  
        {  
            if (cs[i] == c)  
            {  
                count++; //得到分隔符的个数  
            }  
        }  
          
        String[] strArray = new String[count + 1];  
        int k = 0, j = 0;  
        String str = string;  
          
        //去掉第一个字符是分隔符的情况  
        if ((k = str.indexOf(c)) == 0)  
        {  
            str = string.substring(k + 1);  
        }  
          
        //检测是否包含分割符，如果不含则返回字符串  
        if (str.indexOf(c) == -1)  
        {  
            return new String[]{str};  
        }  
          
        while ((k = str.indexOf(c)) != -1)  
        {  
            strArray[j++] = str.substring(0, k);  
            str = str.substring(k + 1);  
            if ((k = str.indexOf(c)) == -1 && str.length() > 0)  
            {  
                strArray[j++] = str.substring(0);  
            }  
        }  
          
        return strArray;  
    }
    
    /**
   * Returns length of a string. If the string is null, return 0.
   *
   * @param data input string
   * @return 0 if the data is null or the length of the data string.
   */
  public int strlen(String data) {
    return (data == null) ? 0 : data.length();
  }

  /**
   * Returns a string with all alphabetic characters converted to lowercase.
   *
   * @param data input string
   * @return a string in lowercse.
   */
  public String strToLower(String data) {
    return (null == data) ? "" : data.toLowerCase(Locale.getDefault());
  }

  /**
   * Returns a string with all alphabetic characters converted to uppercse.
   *
   * @param data input string
   * @return a string in uppercase.
   */
  public String strToUpper(String data) {
    return (null == data) ? "" : data.toUpperCase(Locale.getDefault());
  }

  /**
   * Checks if a string starts with lower case char.
   *
   * @param str the string to check
   * @return true if the string starts with lower case char.
   */
  public boolean startsWithLowerCaseChar(String str) {
    int c0 = (int) str.charAt(0);
    return c0 >= 97 && c0 <= 122;
  }

  /**
   * 删除字符串中间的指定字符.
   *
   * @TODO 无法删除回车、tab等？
   *
   * @param data 需要处理的字符串
   * @return string
   */
  public String stripSpaces(String data,String del) {
    if (data == null) {
      return data;
    }

    return replace(data.trim(), del, "");
  }
  
  public String stripSpaces(String data,char del) {
    if (data == null) {
      return data;
    }

    return replace(data.trim(), del, "");
  }

  /**
   * Replaces old symbols with an empty string.
   *
   * <pre>
   * Examples:
   *      replace("123te123ch123", "123");    would return "tech"
   * </pre>
   *
   * @param data the original string
   * @param oldSymbol the substring to be replaced
   * @return a replaced string
   */
  public String replace(String data, String oldSymbol) {
    return replace(data, oldSymbol, "");
  }
  
  public String replace(String data, char oldSymbol) {
    return replace(data, oldSymbol, "");
  }

  /**
   * Replaces old symbols with new symbols in a string.
   *
   * <pre>
   * Examples:
   *      replace("123te123ch123", "123", "X"); would return "XteXchX"
   * </pre>
   *
   * @param str the original string
   * @param oldSymbol the substring to be replaced
   * @param newSymbol the replacement substring
   * @return a replaced string
   */
  public String replace(String str, String oldSymbol, String newSymbol) {
    // In a string replace one substring with another
    if (str == null || !str.contains(oldSymbol)) {
      return str;
    }

    int oldLength = oldSymbol.length();
    StringBuilder result = new StringBuilder();
    int idx = str.indexOf(oldSymbol, 0);
    int lastIndex = 0;
    while (idx != -1) {
      result.append(str.substring(lastIndex, idx)).append(newSymbol);
      lastIndex = idx + oldLength;
      idx = str.indexOf(oldSymbol, lastIndex);
    }
    result.append(str.substring(lastIndex));

    return result.toString();
  }
  
  public String replace(String str, char oldSymbol, String newSymbol) {
    // In a string replace one substring with another
    if (str == null || !str.contains(String.valueOf(oldSymbol))) {
      return str;
    }

    StringBuilder result = new StringBuilder();
    int idx = str.indexOf(oldSymbol, 0);
    int lastIndex = 0;
    while (idx != -1) {
      result.append(str.substring(lastIndex, idx)).append(newSymbol);
      lastIndex = idx + 1;
      idx = str.indexOf(oldSymbol, lastIndex);
    }
    result.append(str.substring(lastIndex));

    return result.toString();
  }

  /**
   * Replaces the last occurance of an old symbol with a new symbol.
   *
   * @param str the original string
   * @param oldSymbol the substring to be replaced
   * @param newSymbol the replacement substring
   * @return a replaced string
   */
  public String replaceLast(String str, String oldSymbol, String newSymbol) {
    if (str == null || !str.contains(oldSymbol)) {
      return str;
    }

    int lastIndex = str.lastIndexOf(oldSymbol);
    int oldLength = oldSymbol.length();
    String result = str.substring(0, lastIndex) + newSymbol
            + str.substring(lastIndex + oldLength);

    return result;
  }
}
