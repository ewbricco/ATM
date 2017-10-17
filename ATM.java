import java.util.stream.IntStream;

/**
 * Created by ebricco on 10/3/17.
 */
public class ATM {
    public static void main(String[] args) {
        int[] denominations = new int[]{100, 50, 20, 10, 5, 1};
        int[] supply = new int[]{100, 100, 100, 0, 0, 100};

        getChange(denominations, supply, 80);
    }

    public static void getChange(int[] denominations, int[] supply, int amount) {
        int[] change = getChange(denominations, supply, 0, amount, new int[denominations.length]);

        System.out.println("change tendered:");

        if(change == null) {
            System.out.println("unable to make change");
        }
        else {
            for (int i = 0; i < change.length; i++) {
                System.out.println(denominations[i] + ": " + change[i]);
            }

        }
    }


    public static int[] getChange(int[] denominations, int[] supply, int i, int amount, int[] change) {

        denominations = denominations.clone();
        supply = supply.clone();
        change = change.clone();

        //check base cases
        if(i > denominations.length-1) {
            return null;
        } else if(amount == 0) {
            return change;
        } else if(supply[i] == 0 || amount - denominations[i] < 0) {
            return getChange(denominations, supply, i+1, amount, change);
        }

        //else return min of either including current denom or not

        int[] without = getChange(denominations, supply, i+1, amount, change);

        supply[i]--;
        change[i]++;
        int[] with = getChange(denominations, supply, i, amount - denominations[i], change);

        if(with == null && without == null) {
            return null;
        } else if(with == null) {
            return without;
        } else if(without == null) {
            return with;
        } else if (IntStream.of(with).sum() <= IntStream.of(without).sum()) {
            return with;
        } else {
            return without;
        }
    }
}

