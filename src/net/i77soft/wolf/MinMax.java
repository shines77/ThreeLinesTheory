package net.i77soft.wolf;

public class MinMax {
    private int m = 0;
    private int n = 0;

    public MinMax(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int min() {
        if (this.m <= this.n)
            return this.m;
        else
            return this.n;
    }

    public int max() {
        if (this.m <= this.n)
            return this.n;
        else
            return this.m;
    }
}
