package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 399. 除法求值
 * https://leetcode.cn/problems/evaluate-division
 */
public class Problem_0399_CalcEquation {
    public static void main(String[] args) {
        List<List<String>> equations = Arrays.stream(new String[][]{{"a", "b"}, {"b", "c"}})
                .map(l -> Arrays.stream(l).collect(Collectors.toList()))
                .collect(Collectors.toList());

        double[] values = new double[]{2.0, 3.0};

        List<List<String>> queries = Arrays.stream(new String[][]{{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}})
                .map(l -> Arrays.stream(l).collect(Collectors.toList())).collect(Collectors.toList());

        Problem_0399_CalcEquation test = new Problem_0399_CalcEquation();
        double[] ans = test.calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(ans));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Node> nodes = new HashMap<>();

        // 将所有变量看做一个节点，初始化并查集
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double val = values[i];
            if (!nodes.containsKey(a)) {
                nodes.put(a, new Node(a));
            }
            if (!nodes.containsKey(b)) {
                nodes.put(b, new Node(b));
            }
            union(nodes, a, b, val);
        }

        // 处理询问
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                res[i] = -1.0;
            } else {
                Node pa = find(nodes, a), pb = find(nodes, b);
                if (pa != pb) {
                    res[i] = -1.0;
                } else {
                    res[i] = nodes.get(a).weight / nodes.get(b).weight; // map 存储的是每个节点到根节点路径上所有边权的乘积
                }
            }
        }
        return res;
    }

    // 带权并查集中节点的数据结构
    class Node {
        String name;
        Node parent;
        double weight;
        Node(String name) {
            this.name = name;
            this.parent = this;
            this.weight = 1.0;
        }
    }

    // 找到某个节点所在的祖先节点，并更新节点路径上的权值
    private Node find(Map<String, Node> nodes, String name) {
        Node node = nodes.get(name);
        if (node.parent != node) {
            Node root = find(nodes, node.parent.name);
            node.weight *= node.parent.weight;
            node.parent = root;
        }
        return node.parent;
    }

    // 在带权并查集中合并两个节点所在的集合
    private void union(Map<String, Node> nodes, String a, String b, double val) {
        Node pa = find(nodes, a), pb = find(nodes, b);
        if (pa != pb) {
            pa.parent = pb;
            pa.weight = val * nodes.get(b).weight / nodes.get(a).weight; // 更新节点到根节点路径上所有边权的乘积
        }
    }
}
