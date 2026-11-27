class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int a = 0;
        int b = arr.length - 1;
        while(a < b){
            int mid = a + (b - a) / 2;
            if(arr[mid] > arr[mid + 1]){

                b = mid;
            } else{
                a = mid + 1;
            }
        }
        return a;
    }
}