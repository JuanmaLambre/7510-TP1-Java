package ar.uba.fi.tdd.rulogic.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.HashMap;


public class Fact {

    String name;
    List<String> args;

    public Fact(String fact) {
        String[] factParts = fact.replaceAll("\\s+", "").split("[(),]");
        this.name = factParts[0];
        this.args = Arrays.asList(Arrays.copyOfRange(factParts, 1, factParts.length));
    }

    private Fact(String name, List<String> args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public List<String> getArgs() {
        return args;
    }

    public Fact replaceArgs(HashMap<String,String> replacements) {
        List<String> newArgs = this.args.stream()
                .map(arg -> replacements.get(arg))
                .collect(Collectors.toList());
        return new Fact(this.name, newArgs);
    }

    @Override
    public String toString() {
        String arguments = args.stream().collect(Collectors.joining(", "));
        return String.format("%s(%s)", name, arguments);
    }

    @Override
    public boolean equals(Object fact) {
        Fact that = (Fact) fact;
        
        if (this.args.size() != that.args.size()) 
            return false;

        for (int i = 0; i < this.args.size(); ++i) {
            if (!this.args.get(i).equals(that.args.get(i)))
                return false;
        }
        return this.name.equals(that.name);
    }

}
