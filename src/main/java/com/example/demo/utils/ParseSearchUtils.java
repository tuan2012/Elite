package com.example.demo.utils;

import com.example.demo.dto.filter.SearchCriteria;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParseSearchUtils {
    public List<SearchCriteria> parseSearch(String search) {
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(,|;)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            searchCriteriaList.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
        }
        return searchCriteriaList;
    }
}
