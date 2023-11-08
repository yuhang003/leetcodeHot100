package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * https://leetcode.cn/problems/lru-cache
 */
public class Problem_0146_LRUCache {

    static class LRUCache {
        private int size;
        private Map<Integer, Node<Integer, Integer>> keyNodeMap;
        private NodeDoubleLinkedList<Integer, Integer> nodeList;

        public LRUCache(int capacity) {
            size = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
        }

        public int get(int key) {
            if (keyNodeMap.containsKey(key)) {
                Node<Integer, Integer> node = keyNodeMap.get(key);
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return -1;
        }

        // set(Key, Value)
        // 新增  更新value的操作
        public void put(int key, int value) {
            // 更新
            if (keyNodeMap.containsKey(key)) {
                Node<Integer, Integer> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else { // 新增
                Node<Integer, Integer> newNode = new Node<>(key, value);
                nodeList.addNode(newNode);

                if (keyNodeMap.size() == size) {
                    Node<Integer, Integer> node = nodeList.removeHead();
                    keyNodeMap.remove(node.key);
                }

                keyNodeMap.put(key, newNode);
            }
        }

        static class Node<K, V> {
            public K key;
            public V value;
            public Node<K, V> last;
            public Node<K, V> next;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        // 双向链表
        static class NodeDoubleLinkedList<K, V> {
            public Node<K, V> head;
            public Node<K, V> tail;

            // 给一个newNode，将其挂到链表尾部
            public void addNode(Node<K, V> newNode) {
                if (newNode == null) return;

                if (head == null) {
                    head = newNode;
                    tail = newNode;
                } else {
                    tail.next = newNode;
                    newNode.last = tail;
                    tail = newNode;
                }
            }

            // 将Node节点移动到链表尾部
            // node 入参，一定保证！node在双向链表里！
            // node原始的位置，左右重新连好，然后把node分离出来
            // 挂到整个链表的尾巴上
            public void moveNodeToTail(Node<K, V> node) {
                if (tail == node) return;

                if (head == node) {
                    head = node.next;
                    head.last = null;
                } else {
                    node.last.next = node.next;
                    node.next.last = node.last;
                }

                node.last = tail;
                node.next = null;
                tail.next = node;
                tail = node;
            }

            // 移除头部节点，并返回被移除的节点
            public Node<K, V> removeHead() {
                if (head == null) return null;

                Node<K, V> res = head;
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    head = res.next;
                    res.next = null;
                    head.last = null;
                }

                return res;
            }
        }
    }
}
