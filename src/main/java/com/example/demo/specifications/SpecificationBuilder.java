package com.example.demo.specifications;

import com.example.demo.dto.filter.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class SpecificationBuilder {
    private List<SearchCriteria> params;

    public SpecificationBuilder() {
        params = new ArrayList<>();
    }

    public SpecificationBuilder add(SearchCriteria searchCriteria) {
        params.add(searchCriteria);
        return this;
    }

    public Specification build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(BaseSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i - 1)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }

    public Specification createSpecification(String search) {
        SpecificationBuilder builder = new SpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)(,|;)", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));
        }

        Specification spec = builder.build();
        return spec;
    }
}
