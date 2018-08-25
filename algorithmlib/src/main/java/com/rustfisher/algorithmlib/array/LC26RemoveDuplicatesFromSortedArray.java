package com.rustfisher.algorithmlib.array;

import com.rustfisher.algorithmlib.AlgoStepSlice;
import com.rustfisher.algorithmlib.BaseAlgo;
import com.rustfisher.algorithmlib.ContentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 26
 */
public class LC26RemoveDuplicatesFromSortedArray extends BaseAlgo {

    public static List<AlgoStepSlice> removeDuplicates(int[] nums) {
        List<AlgoStepSlice> stepList = new ArrayList<>();
        stepList.add(new AlgoStepSlice(nums));

        if (nums.length == 0) {
            stepList.add(AlgoStepSlice.genIntAnsSlice(nums, 0));
            return stepList;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            markDots(nums, stepList, i, j);
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
                markDots(nums, stepList, i, j);
            }
        }
        stepList.add(AlgoStepSlice.genIntAnsSlice(nums, i + 1));
        markDots(nums, stepList, i + 1);
        return stepList;
    }

    @Override
    public Map<ContentInfo, ContentInfo> getDescMap() {
        baseDescMap = new HashMap<>();
        baseDescMap.put(new ContentInfo("Description"), new ContentInfo("Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.\n" +
                "\n" +
                "Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.\n" +
                "\n" +
                "Example 1:\n" +
                "\n" +
                "Given nums = [1,1,2],\n" +
                "\n" +
                "Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.\n" +
                "\n" +
                "It doesn't matter what you leave beyond the returned length.\n" +
                "Example 2:\n" +
                "\n" +
                "Given nums = [0,0,1,1,1,2,2,3,3,4],\n" +
                "\n" +
                "Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.\n" +
                "\n" +
                "It doesn't matter what values are set beyond the returned length.\n" +
                "Clarification:\n" +
                "\n" +
                "Confused why the returned value is an integer but your answer is an array?\n" +
                "\n" +
                "Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.\n" +
                "\n" +
                "Internally you can think of this:\n" +
                "\n" +
                "// nums is passed in by reference. (i.e., without making a copy)\n" +
                "int len = removeDuplicates(nums);\n" +
                "\n" +
                "// any modification to nums in your function would be known by the caller.\n" +
                "// using the length returned by your function, it prints the first len elements.\n" +
                "for (int i = 0; i < len; i++) {\n" +
                "    print(nums[i]);\n" +
                "}"));

        baseDescMap.put(new ContentInfo("Java 1"), new ContentInfo(
                "    public int removeDuplicates(int[] nums) {\n" +
                        "    if (nums.length == 0) return 0;\n" +
                        "    int i = 0;\n" +
                        "    for (int j = 1; j < nums.length; j++) {\n" +
                        "        if (nums[j] != nums[i]) {\n" +
                        "            i++;\n" +
                        "            nums[i] = nums[j];\n" +
                        "        }\n" +
                        "    }\n" +
                        "    return i + 1;\n" +
                        "}\n"));
        return baseDescMap;
    }
}
