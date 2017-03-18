package helloworld.fly.snake.ttlmht;

/**
 * Created by LaptopF1 on 3/17/2017.
 */

public class Champions {
    private String namE;
    private String nickName;
    private String adresS;
    private byte[] imG;

    public Champions() {

    }

    public Champions(String namE, String nickName, String adresS, byte[] imG) {
        this.namE = namE;
        this.nickName = nickName;
        this.adresS = adresS;
        this.imG = imG;
    }

    public String getNamE() {
        return namE;
    }

    public void setNamE(String namE) {
        this.namE = namE;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAdresS() {
        return adresS;
    }

    public void setAdresS(String adresS) {
        this.adresS = adresS;
    }

    public byte[] getImG() {
        return imG;
    }

    public void setImG(byte[] imG) {
        this.imG = imG;
    }
}
