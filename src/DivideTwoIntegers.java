/**
 * Created by jianiyang on 16/7/13.
 */

/* 1,被除数一直减去除数直到为零, 这种方法的迭代次数是结果的大小, 比如结果为n, 算法复杂度为O(n)zuoyi。
   2,使用位运算。先让除数左移直至大于被除数之前得到一个最大的基,接下来尝试每次减去这个基, 如果可以则结果增加2^k,然后基继续右移迭代, 直到基为
     0为止。这个方法的迭代次数是按2的幂直到超过结果, 时间复杂度是O(logn)。
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor){
        //判断各种情况, corner cases;
        if(divisor == 0)
            return Integer.MAX_VALUE;

        boolean isNeg = (dividend ^ divisor) >>> 31 == 1;

        int res = 0;
        if(dividend == Integer.MIN_VALUE){
            dividend += Math.abs(divisor);
            if(divisor == -1){
                return Integer.MAX_VALUE;
            }
            res++;
        }
        if(divisor == Integer.MIN_VALUE)
            return res;

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int digit = 0;
        while (divisor <= (dividend >> 1)){ //除数左移直至大于被除数前得到的一个最大的基
            divisor <<= 1;
            digit ++;  //用来标记是第几位的基
        }
        while (digit >= 0){
            if (dividend >= divisor){
                res += 1 << digit; //还原在result上
                dividend -= divisor;
            }
            divisor >>= 1;
            digit --;
        }
        return isNeg? -res : res;
    }

    public static void main(String[] args){
        int dividend = 4, divisor = -2;
        DivideTwoIntegers res = new DivideTwoIntegers();
        int output = res.divide(dividend, divisor);
        System.out.println(output);
    }

}
