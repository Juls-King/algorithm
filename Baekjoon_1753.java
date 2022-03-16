// 최단경로
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
   static int[] info;	// 정점 별 최단가중치 정보 저장 배열

   public static class Node implements Comparable<Node> {	// 정점과 가중치를 함께 관리하기 위한 클래스
      public int vertex;	// 정점
      public int weight;	// 가중치

      public Node(int vertex, int weight) {
         this.vertex = vertex;
         this.weight = weight;
      }

      @Override
      public int compareTo(Node o) {	// 클래스의 정렬 기준을 weight로 설정
         return this.weight - o.weight;
      }
   }

   public static void main(String[] args) throws IOException {
      final int INF = Integer.MAX_VALUE;	// int형의 최대값으로 초기화
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

      String s = br.readLine();
      StringTokenizer st = new StringTokenizer(s);

      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(br.readLine());

      ArrayList<Node>[] map = new ArrayList[V + 1];	// 간선 정보를 저장할 리스트

      for (int i = 1; i <= V; i++) {
         map[i] = new ArrayList<Node>();	// 리스트의 구성요소에 다시 리스트를 선언
      }

      info = new int[V + 1];
      Arrays.fill(info, INF);

      for (int i = 0; i < E; i++) {
         s = br.readLine();

         st = new StringTokenizer(s);

         int start = Integer.parseInt(st.nextToken());
         int end = Integer.parseInt(st.nextToken());
         int weight = Integer.parseInt(st.nextToken());

         map[start].add(new Node(end, weight));	// 간선 정보 세팅
      }

      dijkstra(K, map);	// 함수 호출

      for (int i = 1; i <= V; i++) {	// 결과 출력
         if (info[i] == INF) {
            bw.write("INF" + "\n");
         } else {
            bw.write(info[i] + "\n");
         }
      }

      bw.flush();
      bw.close();
   }

   public static void dijkstra(int k, ArrayList<Node>[] map) throws IOException {
      PriorityQueue<Node> q = new PriorityQueue<Node>();	// 우선순위 큐 선언

      q.add(new Node(k, 0));	// 첫번째 정점 값은 그냥 큐에 삽입
      info[k] = 0;				// 첫번째 정점 비용 0으로 초기화

      while (!q.isEmpty()) {
         Node node = q.poll();	// 가중치가 가장 적은 node를 가져온다
         
//         System.out.println("v : " + node.vertex + " w : " + node.weight + " size : " + map[node.vertex].size());

         for (int i = 0; i < map[node.vertex].size(); i++) {	// map[node.vertex].size() : 해당 정점에서 갈 수 있는 정점 수
            int vertex = map[node.vertex].get(i).vertex;	// 현재 정점에서 갈 수 있는 정점
            int weight = map[node.vertex].get(i).weight;	// 현재 정점에서 갈 수 있는 정점으로의 가중치
            
//            System.out.println("vertex : " + vertex + " weight : " + weight);

            if (node.weight + weight < info[vertex]) {	// 현재까지의 정점 가중치 + 다음 정점으로의 가중치가 info의 값보다 작을 때

               info[vertex] = node.weight + weight;	// 값 갱신
               
//               System.out.println(Arrays.toString(info));

               q.offer(new Node(vertex, info[vertex]));	// 해당 노드정보 다시 큐에 삽입
            }
         }
      }
   }
}