package com.dis.bot.tool;

public class StringTool {

    public static String padTo(Integer maxLength, String value){
        var trimToIndex = maxLength>value.length()?value.length():maxLength;
        StringBuilder sb = new StringBuilder(value.substring(0, trimToIndex));
        for(int i = 0; i < maxLength; i++) {
            if(i >= value.length()){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}
