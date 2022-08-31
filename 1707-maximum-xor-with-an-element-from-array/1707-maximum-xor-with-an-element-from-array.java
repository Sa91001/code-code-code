class Solution {
    static class Trie {
        Trie[] ch;
        
        Trie() {
            ch = new Trie[2];
        }
    }
    
    void add(int num, int ind, Trie node) {
        if(ind == -1) return;
        if(((1 << ind) & num) != 0) {
            if(node.ch[1] == null) {
                node.ch[1] = new Trie();
            }
            add(num, ind - 1, node.ch[1]);
        }
        else {
            if(node.ch[0] == null) {
                node.ch[0] = new Trie();
            }
            add(num, ind - 1, node.ch[0]);
        }
    }
    
    int res = -1;
    
    void query(int x, int m, Trie node, int ind, boolean isBounded) {
        if(ind == -1) {
            res = res ^ x;
            return;
        }
        if(isBounded && ((1 << ind) & m) == 0) {
                if(node.ch[0] == null) {
                    res = -1;
                    return;
                }
                else {
                    if(res == -1) {
                        res = 0;
                    }
                    query(x, m, node.ch[0], ind - 1, isBounded);
                }
        }
        else {
            if(((1 << ind) & x) == 0) {
                    if(node.ch[1] != null) {
                        if(res == -1) {
                            res = 0;
                        }
                        int tmp = res;
                        res |= (1 << ind);
                        query(x, m, node.ch[1], ind - 1, isBounded);
                        if(res != -1) return;
                        if(node.ch[0] != null) {
                            res = tmp;
                            query(x, m, node.ch[0], ind - 1, false);
                        }
                    }
                    else if(node.ch[0] != null) {
                        if(res == -1) {
                            res = 0;
                        }
                        query(x, m, node.ch[0], ind - 1, false);
                    }
                    else {
                        res = -1;
                        return;
                    }
                }
                else {
                    if(node.ch[0] != null) {
                        if(res == -1) {
                            res = 0;
                        }
                        query(x, m, node.ch[0], ind - 1, false);
                    }
                    else if(node.ch[1] != null) {
                        if(res == -1) {
                            res = 0;
                        }
                        res |= (1 << ind);
                        query(x, m, node.ch[1], ind - 1, isBounded);
                    }
                    else {
                        res = -1;
                        return;
                    }
                }
        }
    }
    
    public int[] maximizeXor(int[] nums, int[][] queries) {
        int len = queries.length;
        Trie root = new Trie();
        int[] ans = new int[len];
        for(int num: nums) {
            add(num, 30, root);
        }
        for(int i = 0; i < len; i++) {
            query(queries[i][0], queries[i][1], root, 30, true);
            ans[i] = res;
            res = -1;
        }
        return ans;
    }
}