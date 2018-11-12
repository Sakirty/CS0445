
/**
 * A class that has a methond to recursively replaces characters in a String.
 * 
 * @author Charles Hoot
 * @version 4.0
 */
public class RecursiveStringReplace
{

    /**
     * replace - Replace all instances of one character by another.
     * 
     * @param  s   The string to do the replacement on.
     * @param  from   The character to be replaced.
     * @param  to   the character to change to.
     * @return     A new string with the appropriate characters replaced.
     */
    public String replace(String s, char from, char to)
    {
        if(s.length()<1)
            return  s;
        if(s.charAt(0) == from)
            s = to + s.substring(1);
        System.out.println(s);

        return s.charAt(0) + replace(s.substring(1,s.length()),from,to);
    }


}
