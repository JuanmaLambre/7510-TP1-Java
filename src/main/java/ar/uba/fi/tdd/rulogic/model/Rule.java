package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

import ar.uba.fi.tdd.rulogic.model.Fact;


public class Rule {

    Fact signature;
    List<Fact> requirements;

    public Rule(String rule) {
        String[] ruleParts = rule.replaceAll("\\s+", "").split(":-");
        signature = new Fact(ruleParts[0]);
        String[] reqs = ruleParts[1].replaceAll("\\),", ")!").split("!");
        requirements = Arrays.stream(reqs)
                .map(r -> new Fact(r))
                .collect(Collectors.toList());
    }

    public Fact getSignature() {
        return this.signature;
    }

    public List<Fact> getRequirements() {
        return this.requirements;
    }

    public List<Fact> replaceArgs(List<String> args) {
        HashMap<String, String> replacements = new HashMap();
        for (int i = 0; i < signature.getArgs().size(); ++i)
            replacements.put(signature.getArgs().get(i), args.get(i));
        return requirements.stream()
                .map(f -> f.replaceArgs(replacements))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String reqs = this.requirements.stream()
                .map(r -> r.toString())
                .collect(Collectors.joining(", "));        
        return signature.toString() + " :- " + reqs;
    }

}
