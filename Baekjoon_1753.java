package algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Baekjoon_1753 {
   static int[] info;

   public static class Node implements Comparable<Node> {
      public int vertex;
      public int weight;

      public Node(int vertex, int weight) {
         this.vertex = vertex;
         this.weight = weight;
      }

      @Override
      public int compareTo(Node o) {
         return this.weight - o.weight;
      }
   }

   public static void main(String[] args) throws IOException {
      final int INF = Integer.MAX_VALUE;
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String s = br.readLine();
      StringTokenizer st = new StringTokenizer(s);

      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(br.readLine());

      ArrayList<Node>[] map = new ArrayList[V + 1];

      for (int i = 1; i <= V; i++) {
         map[i] = new ArrayList<Node>();
      }

      info = new int[V + 1];
      Arrays.fill(info, INF);

      for (int i = 0; i < E; i++) {
         s = br.readLine();

         st = new StringTokenizer(s);

         int start = Integer.parseInt(st.nextToken());
         int end = Integer.parseInt(st.nextToken());
         int weight = Integer.parseInt(st.nextToken());

         map[start].add(new Node(end, weight));
      }

      bfs(K, map);

      for (int i = 1; i <= V; i++) {
         if (info[i] == INF) {
            bw.write("INF" + "\n");
         } else {
            bw.write(info[i] + "\n");
         }
      }

      bw.flush();
      bw.close();
   }

   public static void bfs(int k, ArrayList<Node>[] map) throws IOException {
      PriorityQueue<Node> q = new PriorityQueue<Node>();

      q.add(new Node(k, 0));
      info[k] = 0;

      while (!q.isEmpty()) {
         Node temp = q.poll();
//         System.out.println("v : " + temp.vertex + " w : " + temp.weight + " size : " + map[temp.vertex].size());

         for (int i = 0; i < map[temp.vertex].size(); i++) {
            int vertex = map[temp.vertex].get(i).vertex;
            int weight = map[temp.vertex].get(i).weight;
//            System.out.println("vertex : " + vertex + " weight : " + weight);

            if (temp.weight + weight < info[vertex]) {

               info[vertex] = temp.weight + weight;
//               System.out.println(Arrays.toString(info));

               q.offer(new Node(vertex, info[vertex]));
            }
         }
      }
   }
}