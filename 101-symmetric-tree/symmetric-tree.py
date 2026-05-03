class Solution:
    def isSymmetric(self, root: Optional[TreeNode]) -> bool:
        
        def isMirror(left, right):
            # both null
            if not left and not right:
                return True
            
            # one null or values not equal
            if not left or not right or left.val != right.val:
                return False
            
            # check mirror condition
            return (isMirror(left.left, right.right) and 
                    isMirror(left.right, right.left))
        
        return isMirror(root.left, root.right)