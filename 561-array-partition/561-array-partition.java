class Solution {
  public int arrayPairSum(int[] nums) {
    int temp = 0;

    Arrays.sort(nums);

    for (int i = 0; i < nums.length; i += 2)
      temp += nums[i];

    return temp;
  }
}