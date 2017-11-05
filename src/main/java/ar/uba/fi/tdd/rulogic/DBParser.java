package ar.uba.fi.tdd.rulogic;

import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Fact;

public class DBParser {

    List<Rule> rules;
    List<Fact> facts;

    DBParser(String input) {
        Map<Boolean, List<String>> predicates = 
                Arrays.stream(input.split("\\."))
                .filter(p -> !p.replaceAll("\\s+", "").isEmpty())
                .collect(Collectors.partitioningBy(p -> p.contains(":-")));
        facts = predicates.get(false).stream()
                .filter(sentence -> !sentence.contains(":-"))
                .map(f -> new Fact(f))
                .collect(Collectors.toList());
        rules = predicates.get(true).stream()
                .filter(sentence -> sentence.contains(":-"))
                .map(r -> new Rule(r))
                .collect(Collectors.toList());
    }

    public List<Rule> getRules() {
        return rules;
    }

    public List<Fact> getFacts() {
        return facts;
    }

}