package common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatUtils {
    /**
     * Calculates the chi-square value for N positive integers less than r
     * Source: "Algorithms in C" - Robert Sedgewick - pp. 517
     * NB: Sedgewick recommends: "...to be sure, the test should be tried a few times,
     * since it could be wrong in about one out of ten times."
     * @param  randomNums  a uniformly-randomly-generated array of integers
     * @param  r           upper bound for the random range
     * @return             whether it is likely to be uniformly randomly generated
     * @throws Exception 
     */
    public static boolean isRandom(List<Integer> randomNums, int r) throws Exception
    {
            //According to Sedgewick: "This is valid if N is greater than about 10r"
            if (randomNums.size() <= 10 * r){
					throw new Exception("Sample too small");
				}

            //PART A: Get frequency of randoms
            Map<Integer,Integer> ht = getFrequencies(randomNums);

            //PART B: Calculate chi-square - this approach is in Sedgewick
            double n_r = (double)randomNums.size() / r;
            double chiSquare = 0;

            for (int v : ht.values())
            {
                    double f = v - n_r;
                    chiSquare += f * f;
            }
            chiSquare /= n_r;

            //PART C: According to Swdgewick: "The statistic should be within 2(r)^1/2 of r
            //This is valid if N is greater than about 10r"
            return Math.abs(chiSquare - r) <= 2 * Math.sqrt(r);
    }

    /**
     * @param  nums  an array of integers
     * @return       a Map, key being the number and value its frequency
     */
    private static Map<Integer,Integer> getFrequencies(List<Integer> nums)
    {
            Map<Integer,Integer> freqs = new HashMap<Integer,Integer>();

            for (int x : nums)
            {
                    if (freqs.containsKey(x))
                            freqs.put(x, freqs.get(x) + 1);
                    else
                            freqs.put(x, 1);
            }

            return freqs;
    }
}
