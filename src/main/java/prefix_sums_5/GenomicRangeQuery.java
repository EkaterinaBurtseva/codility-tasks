package prefix_sums_5;

//Find the minimal nucleotide from a range of sequence DNA.


public class GenomicRangeQuery {

    public static void main(String[] args) {
        int x[] = {2, 3};
        int y[] = {5, 4};
        solution("CGFGKLLL", x, y);
    }

    //Internet solution
    public static int[] solution(String S, int[] P, int[] Q) {
        int[][] genoms = new int[3][S.length() + 1];
        //if the char is found in the index i, then we set it to be 1 else they are 0
        //3 short values are needed for this reason
        short a, c, g;
        for (int i = 0; i < S.length(); i++) {
            a = 0;
            c = 0;
            g = 0;
            if ('A' == (S.charAt(i))) {
                a = 1;
            } else if ('C' == (S.charAt(i))) {
                c = 1;
            } else if ('G' == (S.charAt(i))) {
                g = 1;
            }
            //here we calculate prefix sums. To learn what's prefix sums look at here https://codility.com/media/train/3-PrefixSums.pdf
            genoms[0][i + 1] = genoms[0][i] + a;
            genoms[1][i + 1] = genoms[1][i] + c;
            genoms[2][i + 1] = genoms[2][i] + g;
        }

        int[] result = new int[P.length];
        //here we go through the provided P[] and Q[] arrays as intervals
        for (int i = 0; i < P.length; i++) {
            int fromIndex = P[i];
            //we need to add 1 to Q[i],
            //because our genoms[0][0], genoms[1][0] and genoms[2][0]
            //have 0 values by default, look above genoms[0][i+1] = genoms[0][i] + a;
            int toIndex = Q[i] + 1;
            if (genoms[0][toIndex] - genoms[0][fromIndex] > 0) {
                result[i] = 1;
            } else if (genoms[1][toIndex] - genoms[1][fromIndex] > 0) {
                result[i] = 2;
            } else if (genoms[2][toIndex] - genoms[2][fromIndex] > 0) {
                result[i] = 3;
            } else {
                result[i] = 4;
            }
        }

        return result;
    }
}
