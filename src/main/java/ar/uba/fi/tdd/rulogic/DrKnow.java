package ar.uba.fi.tdd.rulogic;

import java.util.List;
import java.util.Optional;

import ar.uba.fi.tdd.rulogic.model.Rule;
import ar.uba.fi.tdd.rulogic.model.Fact;

public class DrKnow {

    List<Rule> rules;
    List<Fact> facts;


    DrKnow(List<Rule> rules, List<Fact> facts) {
        this.rules = rules;
        this.facts = facts;
    }

    public Boolean ask(String query) {
        Fact qFact = new Fact(query);
        return facts.contains(qFact) || appliesRules(qFact);
    }

    private Boolean appliesRules(Fact query) {
        Optional<Rule> ruleContainer = rules.stream()
                .filter(r -> r.getSignature().getName().equals(query.getName()))
                .findFirst();
        if (!ruleContainer.isPresent()) {
            return false;
        } else {
            List<Fact> queryFacts = buildFacts(ruleContainer.get(), query);
            for (Fact f : queryFacts) {
                if (!this.facts.contains(f)) return false;
            }
            return true;
        }
    }

    private List<Fact> buildFacts(Rule rule, Fact query) {
        List<String> queryArgs = query.getArgs();
        return rule.replaceArgs(queryArgs);
    }
}