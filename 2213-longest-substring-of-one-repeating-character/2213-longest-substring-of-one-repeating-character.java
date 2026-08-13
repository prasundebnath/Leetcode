class Solution {

    class Node {
        int len;
        int pref;
        int suff;
        int best;
        char leftChar;
        char rightChar;
    }

    Node[] tree;
    char[] arr;

    private Node merge(Node a, Node b) {

        if (a == null) return b;
        if (b == null) return a;

        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.pref = a.pref;
        if (a.pref == a.len && a.rightChar == b.leftChar) {
            res.pref += b.pref;
        }

        res.suff = b.suff;
        if (b.suff == b.len && a.rightChar == b.leftChar) {
            res.suff += a.suff;
        }

        res.best = Math.max(a.best, b.best);

        if (a.rightChar == b.leftChar) {
            res.best = Math.max(
                res.best,
                a.suff + b.pref
            );
        }

        return res;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = new Node();

            tree[node].len = 1;
            tree[node].pref = 1;
            tree[node].suff = 1;
            tree[node].best = 1;
            tree[node].leftChar = arr[l];
            tree[node].rightChar = arr[l];

            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    private void update(int node, int l, int r,
                        int idx, char ch) {

        if (l == r) {

            arr[idx] = ch;

            tree[node].leftChar = ch;
            tree[node].rightChar = ch;
            tree[node].pref = 1;
            tree[node].suff = 1;
            tree[node].best = 1;

            return;
        }

        int mid = (l + r) / 2;

        if (idx <= mid) {
            update(node * 2, l, mid, idx, ch);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, ch);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    public int[] longestRepeating(
            String s,
            String queryCharacters,
            int[] queryIndices) {

        int n = s.length();

        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int q = queryIndices.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {

            update(
                1,
                0,
                n - 1,
                queryIndices[i],
                queryCharacters.charAt(i)
            );

            ans[i] = tree[1].best;
        }

        return ans;
    }
}