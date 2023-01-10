/*
* 백준 1991번. 트리 순회
* https://www.acmicpc.net/problem/1991
*/

private fun main() {

    val tree = initVariable()
    printResult(tree)
}

private fun initVariable(): BinaryTree {

    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()

    val tree = BinaryTree()

    repeat(n) {

        val (root, left, right) = br.readLine().split(' ')
        tree.insert(root, left, right)
    }

    return tree
}

private fun printResult(tree: BinaryTree) {

    tree.printPreorder()
    tree.printInorder()
    tree.printPostorder()
}

class BinaryTree {

    private var root: Node? = null

    fun insert(value: String, left: String, right: String) {

        if (root == null) {
            if (value != ".") root = Node(value)
            if (left != ".") root!!.left = Node(left)
            if (right != ".") root!!.right = Node(right)
        }

        else {
            insert(root!!, value, left, right)
        }
    }

    private fun insert(root: Node, value: String, left: String, right: String) {

        if (root.value == value) {
            if (left != ".") root.left = Node(left)
            if (right != ".") root.right = Node(right)
        }

        else {
            if (root.left != null) insert(root.left!!, value, left, right)
            if (root.right != null) insert(root.right!!, value, left, right)
        }

    }

    fun printPreorder() {

        val sb = StringBuilder()
        printPreorder(root, sb)
        println(sb.toString())
    }

    private fun printPreorder(node: Node?, sb: StringBuilder) {

        if (node == null) return

        sb.append(node.value)
        printPreorder(node.left, sb)
        printPreorder(node.right, sb)
    }

    fun printInorder() {

        val sb = StringBuilder()
        printInorder(root, sb)
        println(sb.toString())
    }

    private fun printInorder(node: Node?, sb: StringBuilder) {

        if (node == null) return

        printInorder(node.left, sb)
        sb.append(node.value)
        printInorder(node.right, sb)
    }

    fun printPostorder() {

        val sb = StringBuilder()
        printPostorder(root, sb)
        println(sb.toString())
    }

    private fun printPostorder(node: Node?, sb: StringBuilder) {

        if (node == null) return

        printPostorder(node.left, sb)
        printPostorder(node.right, sb)
        sb.append(node.value)
    }
}

data class Node(val value: String, var left: Node? = null, var right: Node? = null)
