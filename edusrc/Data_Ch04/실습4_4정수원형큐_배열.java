package Data_Ch04; //6번째 선행학습
/*
 * 큐에서는 예외 클래스를 만드는 연습
 */
import java.util.Random;
/*
 * 큐 1번 실습 코드 - 정수들의 큐
 */
import java.util.Scanner;

//int형 고정 길이 큐

class IntQueue3 {
	private int[] que; // 큐용 배열
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private boolean isEmptyTag;
//	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyIntQueue3Exception extends RuntimeException {
		public EmptyIntQueue3Exception(String message) {
			super(message);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowIntQueue3Exception extends RuntimeException {
		public OverflowIntQueue3Exception(String message) {
			super(message);
		}
	}
//--- 생성자(constructor) ---//
	public IntQueue3(int maxlen) {
		capacity = maxlen;
		front=rear=0;
		isEmptyTag = true;
		try {
			que = new int[maxlen];
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}
//--- 큐에 데이터를 인큐 ---//
	public int enque(int x) throws OverflowIntQueue3Exception {
		if (isFull()) 
			throw new OverflowIntQueue3Exception("stack is full");
		if (rear > capacity) {
			rear = 0;
		}
		if (rear==front) 
			isEmptyTag = false;
		
		que[rear++] = x;
		return x;
	}
//--- 큐에서 데이터를 디큐 ---//
	public int deque() throws EmptyIntQueue3Exception {
		if (isEmpty())
			throw new EmptyIntQueue3Exception("stack is empty");
		
		int x = que[front++];
		if (front > capacity) {//텅~
			front = 0;			
		}
		if (rear==front) 
			isEmptyTag = true;
		return x;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public int peek() throws EmptyIntQueue3Exception {
		if (isEmpty())
			throw new EmptyIntQueue3Exception("stack is empty");
		return que[front];
	}

//--- 큐를 비움 ---//
		public void clear() throws EmptyIntQueue3Exception {
			if (isEmpty())
				throw new EmptyIntQueue3Exception("stack is empty");
			front=rear=0;
			isEmptyTag = true;
		}
	
//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
		public int indexOf(int x) {
			for (int i = 0; i < size(); i++) {
				int idx = (i + front) % capacity;
				if (que[idx] == x) // 검색 성공
					return idx;
			}
			return -1; // 검색 실패
		}

//--- 큐의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
	public int size() {
		if(isEmpty())
			return 0;
		
		if (front < rear)
			return rear - front;
		else
			return capacity - (front - rear);
	}

//--- 원형 큐가 비어있는가? --- 수정 필요//
	public boolean isEmpty() {
		if (front == rear && isEmptyTag)
			return true;
		else
			return false;
	}
//--- 원형 큐가 가득 찼는가? --- 수정 필요//
	public boolean isFull() {
		if (front == rear && !isEmptyTag) {
            return true;
        } else {
            return false;
        }
    }
//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() throws EmptyIntQueue3Exception {
		if (isEmpty())
			throw new EmptyIntQueue3Exception("stack is empty");
		for (int i = 0; i < size(); i++)
			System.out.print(que[(i+front)%capacity]+" ");
		System.out.println();
	}
}
public class 실습4_4정수원형큐_배열 {
	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		IntQueue3 oq = new IntQueue3(4); // 최대 64개를 인큐할 수 있는 큐
		Random random = new Random();
		int rndx = 0, p = 0;
		while (true) {
			System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", oq.size(), oq.getCapacity());
			System.out.print("(1)인큐　(2)디큐　(3)피크　(4)덤프　(5)clear  (0)종료: ");
			int menu = stdIn.nextInt();
			if (menu == 0)
				break;
			switch (menu) {
			case 1: // 인큐
				rndx = random.nextInt(20);
				System.out.print("입력데이터: (" + rndx +")");
				try {
					oq.enque(rndx);
				} catch(IntQueue3.OverflowIntQueue3Exception e) {
					System.out.println("stack이 가득 차있습니다." + e.getMessage());
				}
				break;

			case 2: // 디큐
				try {
					p = oq.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다." + e.getMessage());
				}
				break;

			case 3: // 피크
				try {
				p = oq.peek();
				System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다."+e.getMessage());
				}
				break;

			case 4: // 덤프
				try {
					oq.dump();
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("queue이 비어있습니다."+ e.getMessage());
					e.printStackTrace();
				}
				break;
			case 5: //clear
				try {
					oq.clear();
				} catch (IntQueue3.EmptyIntQueue3Exception e) {
					System.out.println("큐가 비어 있습니다."+ e.getMessage());
				}
				break;
			default:
				break;
			}
		}
	}

}