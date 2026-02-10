package src.day02_collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsDemo {
    public static void main(String[] args) {
        List<String> todoList = new ArrayList<>();

        todoList.add("学习 Java");
        todoList.add("健身");
        todoList.add("学习 Java");

        System.out.println("=== List 演示 ===");
        System.out.println("待办事项总数: " + todoList.size());
        System.out.println("第一个事项: " + todoList.get(0));

        for (String task : todoList) {
            System.out.println("todo: " + task);
        }

        Set<String> uniqueTags = new HashSet<>();
        uniqueTags.add("Java");
        uniqueTags.add("编程");
        uniqueTags.add("Java");
        System.out.println("\n=== Set 演示 ===");
        System.out.println("实际标签数量: " + uniqueTags.size());
        System.out.println("包含: " + uniqueTags);

        Map<String, Integer> salaryMap = new HashMap<>();
        salaryMap.put("Alice", 5000);
        salaryMap.put("Bob", 8000);
        salaryMap.put("Alice", 6000);
        System.out.println("\n=== Map 演示 ===");
        System.out.println("Alice 的工资: " + salaryMap.get("Alice"));

        if (salaryMap.containsKey("Bob")) {
            System.out.println("Bob 在工资表里");
        }
    }
}
