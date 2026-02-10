package src.day04_modern;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tom", "Alice", "Bob");
        names.sort((a, b) -> a.length() - b.length());
        System.out.println(names);


        List<String> words = Arrays.asList("apple", "banana", "avocado", "pear", "apricot");

        List<String> result = words.stream()
                .filter(w -> w.startsWith("a"))     // 留下以 a 开头的
                .map(String::toUpperCase)           // 转成大写
                .collect(Collectors.toList());      // 收集成 List

        result.forEach(System.out::println);        // 打印每个元素


        String raw = null;
        String value = Optional.ofNullable(raw)
                .map(String::trim)          // 如果不为空才会执行
                .filter(s -> !s.isEmpty())  // 过滤空字符串
                .orElse("DEFAULT");         // 为空就给默认值
        System.out.println(value);

        List<String> names2 = Arrays.asList("Tom", "Bob", "Alice", "Amy");

        String firstA = names2.stream()
                .filter(n -> n.startsWith("A"))
                .findFirst()                       // 返回 Optional<String>
                .orElse("NO_MATCH");

        System.out.println(firstA);

        List<Integer> nums = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        List<Integer> evenNums = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 10)
                .collect(Collectors.toList());
        evenNums.forEach(System.out::println); 
        
        
        List<String> names3 = Arrays.asList(" tom ", "  ", null, "Alice", " bob");

        List<String> trimmedNames = names3.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> s.substring(0, 1).toUpperCase(Locale.ROOT) + s.substring(1).toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
        trimmedNames.forEach(System.out::println);


        List<String> words2 = Arrays.asList("apple","apricot","banana","blueberry","avocado");
        
        Map<Character, List<String>> grouped = words2.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0)));
        System.out.println(grouped);

        Map<Character, List<String>> grouped2 = words2.stream()
                .collect(Collectors.groupingBy(w -> w.charAt(0),
                TreeMap::new,
                Collectors.toList()));
        System.out.println(grouped2);  
        
        
        Map<Character, List<String>> grouped3 = words2.stream()
        .collect(Collectors.groupingBy(
                s -> s.charAt(0),
                TreeMap::new,
                Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream().sorted().collect(Collectors.toList())
                )
        ));
        System.out.println(grouped3);        


    }
}
