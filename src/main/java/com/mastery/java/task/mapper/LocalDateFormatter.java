package com.mastery.java.task.mapper;//package com.mastery.java.task.mapper;
//
//import lombok.NonNull;
//import org.springframework.core.convert.converter.Converter;
//
//import java.time.DateTimeException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class LocalDateFormatter implements Converter<String, LocalDate> {
//    private static final List<String> listSupportedFormat = Arrays.asList("yyyy-MM-dd");
//    private static final List<DateTimeFormatter> listOfPatterns = listSupportedFormat
//            .stream()
//            .map(DateTimeFormatter::ofPattern)
//            .collect(Collectors.toList());
//
//    @Override
//    public LocalDate convert(@NonNull String s) {
//        for (DateTimeFormatter dateTimeFormatter : listOfPatterns) {
//            try {
//                return LocalDate.parse(s, dateTimeFormatter);
//            } catch (DateTimeParseException ex) {
//            }
//        }
//        throw new DateTimeException(String
//                .format("impossible parse the following date yyyy-MM-dd. Supported formats: yyyy-MM-dd",
//                        s, String.join(", ", listSupportedFormat)));
//    }
//}
