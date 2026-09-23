class Solution {
    public int searchInsert(int[] a, int t) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) / 2;
            if (a[m] < t) l = m + 1;
            else r = m;
        }
        return l;
    }
}