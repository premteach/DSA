class Trie {

    class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
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
        Node node = find(word);
        return node != null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private Node find(String s) {
        Node curr = root;

        for(int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            if(curr.children[index] == null)
                return null;

            curr = curr.children[index];
        }

        return curr;
    }
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */