package part2;

public class BinaryTreeDriver {
    public static void main(String[] args) {
        System.out.println("2.3. Declarative tree creation (tree from diagram, root 50)");
        BinaryTree<Integer> sampleTree = new BinaryTree<>(
                50,
                new BinaryTree<>(
                        17,
                        new BinaryTree<>(
                                12,
                                new BinaryTree<>(9),
                                new BinaryTree<>(14)
                        ),
                        new BinaryTree<>(
                                23,
                                new BinaryTree<>(19),
                                null
                        )
                ),
                new BinaryTree<>(
                        72,
                        new BinaryTree<>(
                                54,
                                null,
                                new BinaryTree<>(67)
                        ),
                        new BinaryTree<>(76)
                )
        );

        System.out.println("Size = " + sampleTree.getSize());
        System.out.println();

        System.out.println("2.4. Traversals of sample tree");
        System.out.print("Inorder:   ");
        sampleTree.inorderTraverse();
        System.out.print("Preorder:  ");
        sampleTree.preorderTraverse();
        System.out.print("Postorder: ");
        sampleTree.postorderTraverse();
        System.out.println();

        System.out.println("Small test tree: 1 with left 0 and right 2");
        BinaryTree<Integer> small = new BinaryTree<>(
                1,
                new BinaryTree<>(0),
                new BinaryTree<>(2)
        );
        System.out.print("Inorder:   ");
        small.inorderTraverse();
        System.out.print("Preorder:  ");
        small.preorderTraverse();
        System.out.print("Postorder: ");
        small.postorderTraverse();
        System.out.println("Size = " + small.getSize());
        System.out.println();

        System.out.println("Tree from Part 1 diagram (root 7)");
        BinaryTree<Integer> part1Tree = new BinaryTree<>(
                7,
                new BinaryTree<>(
                        8,
                        new BinaryTree<>(5),
                        new BinaryTree<>(
                                6,
                                null,
                                new BinaryTree<>(
                                        2,
                                        new BinaryTree<>(9),
                                        null
                                )
                        )
                ),
                new BinaryTree<>(
                        3,
                        new BinaryTree<>(1),
                        new BinaryTree<>(4)
                )
        );
        System.out.print("Inorder:   ");
        part1Tree.inorderTraverse();
        System.out.print("Preorder:  ");
        part1Tree.preorderTraverse();
        System.out.print("Postorder: ");
        part1Tree.postorderTraverse();
        System.out.println("Size = " + part1Tree.getSize());
        System.out.println();

        System.out.println("2.5. Inorder iterator (enhanced for loop)");
        for (Integer a : sampleTree) {
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
