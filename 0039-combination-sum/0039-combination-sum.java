class Solution {
    public void backtrack(int idx,int target,ArrayList<Integer> cur,List<List<Integer>> ans,int ar[]) {
        if(target==0) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        if(target<0) {
            return;
        }
        for(int i=idx;i<ar.length;i++) {
            cur.add(ar[i]);
            backtrack(i,target-ar[i],cur,ans,ar);
            cur.remove(cur.size()-1);
        }

    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0,target,new ArrayList<>(),ans,candidates);
        return ans;
    }
}