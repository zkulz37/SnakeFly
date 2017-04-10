package helloworld.fly.snake.giupbehoctoan;

/**
 * Created by LaptopF1 on 4/9/2017.
 */

public class KetQua {
    private int sO1;
    private int sO2;
    private int tOng;

    public KetQua(int sO1, int sO2) {
        this.sO1 = sO1;
        this.sO2 = sO2;
    }

    public int getsO1() {
        return sO1;
    }

    public void setsO1(int sO1) {
        this.sO1 = sO1;
    }

    public int getsO2() {
        return sO2;
    }

    public void setsO2(int sO2) {
        this.sO2 = sO2;
    }

    public int gettOng() {
        return sO1+sO2;
    }

    public void settOng(int tOng) {
        this.tOng = tOng;
    }
}
