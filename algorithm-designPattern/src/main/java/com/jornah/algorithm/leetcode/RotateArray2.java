package com.jornah.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author licong
 * @date 2021/10/11 00:37
 */
public class RotateArray2 {
    public static void main(String[] args) {

//        int[] arr = {477181, 661861, 837383, 492412, 935349, 84343, 723642, 678340, 123214, 484282, 473991, 823195, 247179, 206529, 724472, 412154, 590105, 808986, 800645, 235405, 952307, 620444, 491813, 406073, 973100, 847153, 143198, 395544, 616975, 219674, 735709, 19659, 240163, 642316, 121616, 845895, 919656, 535031, 535159, 897657, 48036, 585753, 566416, 667007, 970911, 310419, 258013, 887399, 567257, 961026, 111997, 265452, 462298, 572804, 201605, 560897, 770141, 866103, 162344, 359167, 614160, 658059, 326275, 805308, 794579, 719789, 583649, 485891, 989631, 549951, 517823, 955342, 246748, 973149, 438154, 762754, 75742, 671191, 750264, 866855, 556036, 358442, 815399, 580831, 803092, 130761, 868664, 701546, 344771, 840137, 861171, 914529, 738805, 595480, 896469, 237253, 634451, 738557, 667220, 492495, 22486, 209170, 552646, 260546, 999905, 267414, 152955, 153968, 468870, 98812, 492105, 601338, 457869, 963092, 616337, 789339, 775355, 378087, 573144, 675684, 415724, 817653, 476612, 784651, 605854, 678208, 100151, 151761, 73885, 799996, 356011, 142916, 579028, 567643, 911773, 225420, 158913, 383726, 566779, 665976, 100573, 550746, 296682, 70839};
        int[] arr = new int[]{1, 2, 3, 4};
        rotate(arr, 2);
//        Arrays.stream(arr).forEach(System.out::println);

        int i = 0;
        while (i < 1000) {
            System.out.println(arr[i % arr.length]);
            i++;
        }
        for (int j = 0; j < arr.length; j++) {
            arr[(j+10)%arr.length]=arr[i];
        }

    }

    public static void rotate(int[] nums, int k) {
        int length = nums.length;
        int[] temp = new int[length];
        //把原数组值放到一个临时数组中，
        for (int i = 0; i < length; i++) {
            temp[i] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[(i + k) % nums.length] = temp[i];
        }

    }


}
