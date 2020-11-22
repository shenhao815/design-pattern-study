package com.it.adapter.b;

import javafx.scene.web.WebView;

import java.util.Arrays;

class Waveform {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Waveform[" + "id=" + id + ']';
    }
}

class Filter {
    public String name() {
        return getClass().getSimpleName();
    }

    public Waveform process(Waveform input) {
        return input;
    }
}

class LowPass extends Filter {
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public Waveform process(Waveform input) {
        return input; // Dummy processing
    }
}

class HighPass extends Filter {
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    public Waveform process(Waveform input) {
        return input;
    }
}

class BandPass extends Filter {
    double lowCutoff,highCutoff;

    public BandPass(double lowCutoff, double highCutoff) {
        this.lowCutoff = lowCutoff;
        this.highCutoff = highCutoff;
    }
    public Waveform process(Waveform input) {
        return input;
    }
}

// =============================================

interface Processor {
    String name();

    Object process(Object input);
}

abstract class StringProcessor implements Processor{
    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}


class Upcase extends StringProcessor {
    public Object process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class Downcase extends StringProcessor {
    @Override
    public Object process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter extends StringProcessor {
    public Object process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}

class Apply {
    public static void process(Processor p, Object s) {
        System.out.println("Using Processor: " + p.name());
        System.out.println(p.process(s));
    }
}

class FilterAdapter implements Processor {
    private Filter filter;
    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String name() {
        return filter.name();
    }

    @Override
    public Waveform process(Object input) {
        return filter.process((Waveform) input);
    }
}

public class AppTest {

    public static void main(String[] args) {
        String str = "how are you";
        Processor p = new Splitter();
        Apply.process(p, str);
        Waveform wf = new Waveform();
        Apply.process(new FilterAdapter(new HighPass(1)),wf);
        Waveform wf2 = new Waveform();
        Apply.process(new FilterAdapter(new BandPass(8,2)),wf2);
    }
}
