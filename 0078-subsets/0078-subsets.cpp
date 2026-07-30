class Solution {
public:
    vector<vector<int>> ans;

    void backtrack(vector<int>& nums, int index, vector<int>& subset) {

        if (index == nums.size()) {
            ans.push_back(subset);
            return;
        }

        
        subset.push_back(nums[index]);
        backtrack(nums, index + 1, subset);

       
        subset.pop_back();
        backtrack(nums, index + 1, subset);
    }

    vector<vector<int>> subsets(vector<int>& nums) {

        vector<int> subset;
        backtrack(nums, 0, subset);

        return ans;
    }
};