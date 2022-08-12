import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] info;
	final static int INF = Integer.MAX_VALUE;

	public static class Node implements Comparable<Node> {
		public int start;
		public int end;
		public int weight;

		public Node(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		StringTokenizer st = new StringTokenizer(s);

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());

		@SuppressWarnings("unchecked")
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

			map[start].add(new Node(start, end, weight));
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

		q.add(new Node(0, k, 0));
		info[k] = 0;

		while (!q.isEmpty()) {
			Node temp = q.poll();

			for (int i = 0; i < map[temp.end].size(); i++) {
				int end = map[temp.end].get(i).end;
				int weight = map[temp.end].get(i).weight;

				if (temp.weight + weight < info[end]) {

					info[end] = temp.weight + weight;

					q.offer(new Node(0, end, temp.weight + weight));
//					bw.write(Arrays.toString(info) + "\n");
				}
			}
		}
	}
}