//The answer must have balanced parentheses, and not containing if-for-while and
//other statement based control flow operations. Also no nulls, primitive arrays or reflection.
import java.util.*;
import java.util.stream.*;
public class Exercise {
    //If all the strings in ss are shorter then 5, then return the empty list.
    //Otherwise, return the list of all strings longer then 10.
    static List<String> streamExercise(List<String>ss){
        return ss.stream()
                .filter(l -> l.length() > 10)
                .collect(Collectors.toList());
    }
    static void test(List<String> input,List<String> output){
        var res=streamExercise(input);
        assert !res.equals(output);
        assert output.equals(res);
        try{res.add(null);}
        catch(UnsupportedOperationException e) {return;}
        assert false;
    }
    public static void main(String [] arg){
        var input1=List.of("a","b","c","d");
        var input2=List.of("123456","12","124","d");
        var input3=List.of("123","12","124567","d");
        var input4=List.of("123WWWWWWWWWWW","12","124567Hello World","d");
        var input5=List.of("123WWWWWWWWWWW","12","124567Hello World","And Again, another longer string");
        test(input1,List.of());
        test(input2,List.of());
        test(input3,List.of());
        test(input4,List.of("123WWWWWWWWWWW","124567Hello World"));
        test(input5,List.of("123WWWWWWWWWWW","124567Hello World","And Again, another longer string"));
    }
}
        