class Solution {
    public char repeatedCharacter(String s) {
        HashSet<Character> h = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            if(h.contains(s.charAt(i)))
                return s.charAt(i);
            h.add(s.charAt(i));
        }
        return ' ';
    }
}