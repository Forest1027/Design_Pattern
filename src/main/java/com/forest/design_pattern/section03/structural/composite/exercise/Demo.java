package com.forest.design_pattern.section03.structural.composite.exercise;

import java.util.*;
import java.util.function.Consumer;

public class Demo {
    public static void main(String[] args) {
        SingleValue singleValue = new SingleValue(2);
        ManyValues manyValues = new ManyValues();
        manyValues.add(1);
        manyValues.add(1);
        manyValues.add(1);

        MyList list = new MyList(Arrays.asList(manyValues));
        System.out.println(list.sum());
    }
}


interface ValueContainer extends Iterable<Integer> {
}

class SingleValue implements ValueContainer {
    public int value;

    // please leave this constructor as-is
    public SingleValue(int value) {
        this.value = value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Collections.singleton(value).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        action.accept(value);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return Collections.singleton(value).spliterator();
    }
}

class ManyValues extends ArrayList<Integer> implements ValueContainer {
}


class MyList extends ArrayList<ValueContainer> {
    // please leave this constructor as-is
    public MyList(Collection<? extends ValueContainer> c) {
        super(c);
    }

    public int sum() {
        int sum = 0;
        for (ValueContainer container : this) {
            for (int val : container) {
                sum += val;
            }
        }
        return sum;
    }
}
