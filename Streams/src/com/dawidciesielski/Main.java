package com.dawidciesielski;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> someBingoNumbers = Arrays.asList(
                "N40", "N21",
                "B24", "B34",
                "J65", "J65", "J54", "J36",
                "I68", "I17", "I29", "I71", "i23",
                "O71");

//        List<String> iNumbers = new ArrayList<>();
//
//        someBingoNumbers.forEach(number -> {
//            if (number.startsWith("I")) {
//                iNumbers.add(number);
//            }
//        });
//
//        iNumbers.sort((String s1, String s2) -> s1.compareTo(s2));
//        iNumbers.forEach((String s) -> System.out.println(s));

        someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("I"))
                .sorted()
                .forEach(System.out::println);

        Stream<String> ioNumberStream = Stream.of("I68", "I17", "I29", "W71");
        Stream<String> inNumberStream = Stream.of("N40", "M23", "R26", "K82");
        Stream<String> concatStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.println(concatStream
                .distinct()
                .peek(System.out::println)
                .count());

        Employee danny = new Employee("Danny Wanny", 24);
        Employee billy = new Employee("Billy Kid", 33);
        Employee johnny = new Employee("Johnny Bravo", 41);
        Employee garry = new Employee("Garry Steele", 20);

        Department hr = new Department("Human Resources");
        hr.addEmployee(danny);
        hr.addEmployee(billy);
        hr.addEmployee(garry);
        Department accounting = new Department("Accounting");
        accounting.addEmployee(johnny);

        List<Department> departments = new ArrayList<>();
        departments.add(hr);
        departments.add(accounting);

        departments.stream()
                .flatMap(department -> department.getEmployees()
                        .stream())
                        .forEach(System.out::println);

//        List<String> sortedINumbers = someBingoNumbers
//                .stream()
//                .map(String::toUpperCase)
//                .filter(s -> s.startsWith("I"))
//                .sorted()
//                .collect(Collectors.toList());

        List<String> sortedINumbers = someBingoNumbers
                .stream()
                .map(String::toUpperCase)
                .filter(s -> s.startsWith("I"))
                .sorted()
                .collect(ArrayList::new,ArrayList::add,ArrayList::addAll);

        for (String s : sortedINumbers) {
            System.out.println(s);
        }

        Map<Integer, List<Employee>> groupByAge = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .collect(Collectors.groupingBy(employee -> employee.getAge()));


        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((e1,e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent(System.out::println);

        Stream.of("ABC", "AC", "BAA", "CCCC", "XY", "ST")
                .filter(s -> {System.out.println(s);
                return s.length() == 3;
                })
                .count();

        String myString = "Let's split this up into an array";
        Arrays.stream(myString.split(" ")).forEach(System.out::println);


        Function<String,String> lambdaFunction = s -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(s.charAt(i));
                }
            }
            return returnVal.toString();
        };
        System.out.println(lambdaFunction.apply("1234567890"));
        String result = everySecondCharacter(lambdaFunction, "1234567890");
        System.out.println(result);


        Supplier<String> iLoveJava = () -> "I love Java";
        String supplierResult = iLoveJava.get();
        System.out.println(supplierResult);


    }

    public static String everySecondCharacter (Function<String, String> func, String source) {

        return func.apply(source);
    }

}
