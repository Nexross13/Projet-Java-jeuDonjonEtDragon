package test;

public class testCalcul {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 196; i++) {
            String text = "";
            if (i>0 && i<13) {
                text = "Nord";
            }

            if (i>182 && i<196) {
                text = "Sud";
            }

            if (i%14 == 0 || i == 0) {
                text = "Ouest";
            }

            if (j == 13 ) {
                text = "Est";
                j = 0;
            }

            j++;
            System.out.println(text);
        }
    }
}
