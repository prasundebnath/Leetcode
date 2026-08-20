class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        helper(s, 0, result, new ArrayList<>());
        return result;
    }
    
    private void helper(String s, int index, List<String> result, List<String> list) {
        if (index >= s.length()) {
            if (list.size() == 4) 
                result.add(String.join(".", list));
                
            return;
        }
        
        if (list.size() >= 4)
            return;   
        
        else {
            for (int i = 1; i <= 3 && index + i <= s.length(); i++) {
                String str = s.substring(index, index + i);
                int ip = Integer.valueOf(str);

                if (ip >= 0 && ip <= 255) {
                    list.add(str);
                    helper(s, index + i, result, list);
                    list.remove(list.size() - 1);
                }
                
                if (ip == 0)
                    break;
            }
        }
    }
}
