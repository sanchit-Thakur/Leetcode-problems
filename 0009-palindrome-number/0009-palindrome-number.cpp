class Solution {
public:
    bool isPalindrome(int x) {
        if (x < 0) return false;  

        long  rev = 0, num = x;
        while(num) {
            rev = rev*10+num%10;  
            num /= 10;
        }
        return rev == x;  
    }
};