package main.java.implementation;

import main.java.interfaces.BubbleSort;

import java.io.BufferedReader;
import java.util.Arrays;

public class BubbleSortImpl implements BubbleSort {

    int[] values;

    public BubbleSortImpl(int[] values) {
        setValues(values);
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    @Override
    public void sort() {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i] < values[j]) {
                    int temp = values[i];
                    values[i] = values[j];
                    values[j] = temp;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            stringBuilder.append(values[i]).append("\n");
        }
        return stringBuilder.toString();
    }
}
