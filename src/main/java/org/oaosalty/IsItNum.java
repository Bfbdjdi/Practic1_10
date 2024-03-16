package org.oaosalty;

public class IsItNum {
    public static boolean Check (String strNum)
    {
        try
        {
            int intNum = Integer.parseInt(strNum);
            return true;
        }
        catch (NumberFormatException err)
        {
            return false;
        }
    }
}
