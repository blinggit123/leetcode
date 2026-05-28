class TrieNode:
    def __init__(self):
        self.children = {}
        self.idx = -1


class Solution:
    def stringIndices(self, wordsContainer, wordsQuery):
        root = TrieNode()

        def better(i, j):
            if j == -1:
                return i
            if len(wordsContainer[i]) < len(wordsContainer[j]):
                return i
            if len(wordsContainer[i]) > len(wordsContainer[j]):
                return j
            return i if i < j else j

        for i, word in enumerate(wordsContainer):
            node = root
            node.idx = better(i, node.idx)

            for ch in reversed(word):
                if ch not in node.children:
                    node.children[ch] = TrieNode()

                node = node.children[ch]
                node.idx = better(i, node.idx)

        ans = []

        for word in wordsQuery:
            node = root

            for ch in reversed(word):
                if ch not in node.children:
                    break
                node = node.children[ch]

            ans.append(node.idx)

        return ans