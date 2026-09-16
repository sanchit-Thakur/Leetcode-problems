class Solution {
    public String toLowerCase(String s) {
       StringBuilder sb = new StringBuilder();
       int n = s.length();
       for(int i =0; i<n; i++){
        int temp = s.charAt(i);
            if(temp >= 'A' && temp <= 'Z'){
                temp = temp + 32;
            }
            sb.append((char)temp);
        }
        return sb.toString();        
       }
} 
