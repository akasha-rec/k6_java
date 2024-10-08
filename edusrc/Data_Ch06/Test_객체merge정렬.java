package Data_Ch06;
/*
 * 6장 구현 실습과제1 
 */
class PhyscData implements Comparable<PhyscData>{
    public PhyscData(String name, int height, double vision) {
		// TODO Auto-generated constructor stub
    	this.name = name;
    	this.height = height;
    	this.vision = vision;
	}
	String name;              // 이름
    int    height;            // 키
    double vision;            // 시력
}
public class Test_객체merge정렬 {
	// --- 배열 요소 a[idx1]와 a[idx2]의 값을 교환 ---//
	static void merge(PhyscData[] a,  int lefta, int righta, int leftb, int rightb ) {
		PhyscData temp[] = new PhyscData[30];
		int ix = 0;
		int p = lefta, q = leftb;
		while (p <= righta && q <= rightb) {
			if (a[p].compareTo(a[q])) temp[ix++] = a[p++];
			else if (a[p] > a[q]) temp[ix++] = a[q++];
			else {
				temp[ix++] = a[p++];temp[ix++] = a[q++];
			}
		}
		while (p > righta && q <= rightb) temp[ix++] = a[q++];
		while (q > rightb && p <= righta) temp[ix++] = a[p++];
		System.out.println();
		for (int j = 0; j < ix; j++) {
			a[lefta+ j] = temp[j];
			System.out.print(" "+ a[lefta+j]);
		}
			System.out.println();
	}

	// --- 퀵 정렬(비재귀 버전)---//
	static void MergeSort(PhyscData[] a, int left, int right) {
		int mid = (left+right)/2;
		if (left == right) return;
		MergeSort(a, left, mid);
		MergeSort(a, mid+1, right);
		merge(a, left, mid, mid+1, right);
		return;
	}

	public static void main(String[] args) {
		PhyscData[] x = {
		         new PhyscData("강민하", 162, 0.3),
		         new PhyscData("김찬우", 173, 0.7),
		         new PhyscData("박준서", 171, 2.0),
		         new PhyscData("유서범", 171, 1.5),
		         new PhyscData("이수연", 168, 0.4),
		         new PhyscData("장경오", 171, 1.2),
		         new PhyscData("황지안", 169, 0.8),
		     };
		int nx = x.length;

		   MergeSort(x, 0, nx - 1); // 배열 x를 퀵정렬
			System.out.println("오름차순으로 정렬했습니다.");
		     System.out.println("■ 신체검사 리스트 ■");
		     System.out.println(" 이름     키  시력");
		     System.out.println("------------------");
		     for (int i = 0; i < x.length; i++)
		         System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}
