class WordDictionary {

    class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node curr = root;

        for(int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if(curr.children[index] == null) {
                curr.children[index] = new Node();
            }

            curr = curr.children[index];
        }

        curr.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int i, Node node) {
        if(node == null)
            return false;

        if(i == word.length())
            return node.isEnd;

        char ch = word.charAt(i);

        if(ch == '.') {
            for(int k = 0; k < 26; k++) {
                if(node.children[k] != null) {
                    if(dfs(word, i + 1, node.children[k]))
                        return true;
                }
            }
            return false;
        } else {
            int index = ch - 'a';
            return dfs(word, i + 1, node.children[index]);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */