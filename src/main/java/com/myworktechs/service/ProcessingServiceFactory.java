package com.myworktechs.service;

import com.myworktechs.model.rule.Rule;
import com.myworktechs.service.filter.CollectiveFilter;
import com.myworktechs.service.filter.Filter;
import com.myworktechs.service.filter.SimpleFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessingServiceFactory {

    public static ProcessingService createProcessingService(List<Rule> rulesList) {
        List<Filter> filterList = new ArrayList<>();

        rulesList.forEach(rule -> {
            boolean collective = rule.isCollective();
            if (collective) {
                filterList.add(new CollectiveFilter(rule));
            } else {
                filterList.add(new SimpleFilter(rule));
            }

        });
        return new ProcessingService(Collections.unmodifiableList(filterList));
    }

}
