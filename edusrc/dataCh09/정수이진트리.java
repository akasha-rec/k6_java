package dataCh09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

class TreeNode5 {
	TreeNode5 LeftChild;
	int data;
	TreeNode5 RightChild;

	public TreeNode5() {
		LeftChild = RightChild = null;
	}

	public TreeNode5(int x) {
		// TODO Auto-generated constructor stub
		data = x;
		LeftChild = RightChild = null;

	}
}

class ObjectStack5{
	//--- 실행시 예외: 스택이 비어있음 ---//
	// generic class는 Throwable을 상속받을 수 없다 - 지원하지 않는다
	public class EmptyGenericStackException extends Exception {
		private static final long serialVersionUID = 1L;
		public EmptyGenericStackException() {
			super();
		}
	}

	//--- 실행시 예외: 스택이 가득 참 ---//
	public class OverflowGenericStackException extends RuntimeException {
		public OverflowGenericStackException() {
		}
	}

    private List<TreeNode5> data;  // list를 사용: 배열은 크기를 2배로 늘리는 작업 필요 
	//private List<T> data;
	private int capacity; // 스택의 크기
	private int top; // 스택 포인터

//--- 생성자(constructor) ---//
	public ObjectStack5(int capacity) {
		top = 0;
		this.capacity = capacity;
		// this.data = new T[capacity]; // 스택 본체용 배열을 생성
		try {
		data = new ArrayList<>(capacity);
		} catch (OutOfMemoryError e) {
			capacity = 0;
		}
	}

//--- 스택에 x를 푸시 ---//
	public boolean push(TreeNode5 x) throws OverflowGenericStackException {
		System.out.println("top = " + top +"capacity = " + capacity);
		if (top >= capacity)
			throw new OverflowGenericStackException();
		top++;
		return data.add(x);

	}

//--- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
	public TreeNode5 pop() throws EmptyGenericStackException  {
		if (top < 0)
			throw new EmptyGenericStackException();
		return data.remove(--top);
	}

//--- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyGenericStackException  {
		if (top <= 0)
			throw new EmptyGenericStackException();
		return data.get(top - 1);
	}

//--- 스택을 비움 ---//
	public void clear() {
		top = 0;
	}

//--- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
			if (data.get(i).equals(x))
				return i; // 검색 성공
		return -1; // 검색 실패
	}

//--- 스택의 크기를 반환 ---//
	public int getCapacity() {
		return capacity;
	}

//--- 스택에 쌓여있는 데이터 갯수를 반환 ---//
	public int size() {
		return top;
	}

//--- 스택이 비어있는가? ---//
	public boolean isEmpty() {
		return top <= 0;
	}

//--- 스택이 가득 찼는가? ---//
	public boolean isFull() {
		return top >= capacity;
	}

//--- 스택 안의 모든 데이터를 바닥 → 꼭대기 순서로 출력 ---//
	public void dump() {
		if (top <= 0)
			System.out.println("stack이 비어있습니다.");
		else {
			for (int i = 0; i < top; i++)
				System.out.print(data.get(i)+ " ");
			System.out.println();
		}
	}
}

//정수를 저정하는 이진트리 만들기 실습
class ObjectQueue5 {
    private TreeNode5[] que;//큐는 배열로 구현
	//private List<Integer> que; // 수정본
	private int capacity; // 큐의 크기
	private int front; // 맨 처음 요소 커서
	private int rear; // 맨 끝 요소 커서
	private int num; // 현재 데이터 개수

//--- 실행시 예외: 큐가 비어있음 ---//
	public class EmptyQueueException extends RuntimeException {
		public EmptyQueueException(String msg) {
			super(msg);
		}
	}

//--- 실행시 예외: 큐가 가득 찼음 ---//
	public class OverflowQueueException extends RuntimeException {
		public OverflowQueueException(String msg) {
			super(msg);
		}
	}

//--- 생성자(constructor) ---//
public ObjectQueue5(int maxlen) {
   num = front = rear = 0;
   capacity = maxlen;
   try {
	   que = new TreeNode5[maxlen];
   } catch (OutOfMemoryError e) {        // 생성할 수 없음
       capacity = 0;
   }
}

//--- 큐에 데이터를 인큐 ---//
	public int enque(TreeNode5 x) throws OverflowQueueException {
		if (num >= capacity)
			throw new OverflowQueueException("queue full"); // 큐가 가득 찼음
		que[rear++] = x; 
		num++;

		return 1;
	}

//--- 큐에서 데이터를 디큐 ---//
	public TreeNode5 deque() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		TreeNode5 x = que[front++];
		num--;

		return x;
	}

//--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
	public TreeNode5 peek() throws EmptyQueueException {
		if (num <= 0)
			throw new EmptyQueueException("queue empty"); // 큐가 비어있음
		return que[front];
	}

//--- 큐를 비움 ---//
	public void clear() {
		num = front = rear = 0;
	}

//--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
	public int indexOf(TreeNode5 x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que[idx].equals(x)) // 검색 성공
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
		return num;
	}

//--- 큐가 비어있는가? ---//
	public boolean isEmpty() {
		return num <= 0;
	}

//--- 큐가 가득 찼는가? ---//
	public boolean isFull() {
		return num >= capacity;
	}

//--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
	public void dump() {
		if (num <= 0)
			System.out.println("큐가 비어있습니다.");
		else {
			for (int i = 0; i < num; i++)
				System.out.print(que[((i + front) % capacity)] + " ");
			System.out.println();
		}
	}
}
class Tree5 {
	TreeNode5 root;

	Tree5() {//new Tree5()에 의해 생성자 호출
		root = null;
	}

	TreeNode5 inorderSucc(TreeNode5 current) {//방법 2가지 : 1. 삭제할 노드의 오른쪽 서브트리에서 가장 작은 노드와 바꾼다. 2. 삭제할 노드의 왼쪽 서브트리에서 가장 큰 노드와 바꾼다.
		TreeNode5 temp = current.RightChild;//1. 삭제할 노드의 오른쪽 서브트리에서 가장 작은 노드를 찾아서 바꾸겠다.
		if (current.RightChild != null)//non-leaf의 경우
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		System.out.println("inordersucc:: temp.data = "+temp.data);
		return temp;
	}

	boolean isLeafNode(TreeNode5 current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}
	
	boolean isOneChild(TreeNode5 current) {
		if (current.LeftChild == null || current.RightChild == null)
			return true;
		else
			return false;
	}
	
	void inorder() {//두 메서드는 다르다. driver 함수?
		inorder(root);//workhorse > inorder(TreeNode5 current) 메서드
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode5 CurrentNode) {//recursive
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);//왼쪽을 모두 출력
			System.out.print(" " + CurrentNode.data);//
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode5 CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode5 CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	void NonrecInorder()//void Tree5::inorder(TreeNode5 *CurrentNode)와 비교
	//stack을 사용한 inorder 출력
	{
		ObjectStack5 s = new ObjectStack5(20);
		TreeNode5 CurrentNode = root;
		while (true) {
			while (CurrentNode != null) {
				s.push(CurrentNode);
				CurrentNode = CurrentNode.LeftChild;
			}
			if (!s.isEmpty()) {
				try {
					CurrentNode = s.pop();
				} catch (dataCh09.ObjectStack5.EmptyGenericStackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" " + CurrentNode.data);
				CurrentNode = CurrentNode.RightChild;
			}
			else break;  
		}
	}
	void levelOrder() //level 별로 출력한다. level이 증가하면 다음줄에 출력한다
	//난이도: 최상급 구현
	{
		ObjectQueue5 q = new ObjectQueue5(20);
		Queue<Integer> que = new LinkedList<>();
		int oldLevel = 0,  newLevel=0;
		que.add(oldLevel+1);
		TreeNode5 CurrentNode = root;
		newLevel = que.remove();
		
	}

	boolean insert(int x) {// binary search tree를 만드는 입력 : left subtree < 노드 x < right subtree
		//inorder traversal시에 정렬된 결과가 나와야 한다
		TreeNode5 p = root;
		TreeNode5 q = null;
		TreeNode5 temp = new TreeNode5(x);
		int branchMode = 0;
		
		if (root == null) {//root 생성해줘야 한다고 생각하고 시작
			root = temp;
			System.out.println("temp 데이터는 " + temp.data);
			return true;
		}

		while (p != null) {//root가 있어서 비교하면서 만들기 시작. root가 null이 될 일X > 무한 루프 >> p != null이 아닐 때까지 while문 돌려
			if(p.data > x) {//p가 삽입할 값보다 작으면 왼쪽 노드로 가야
				if(p.LeftChild == null) {
					branchMode = 1;
					break;
				}
//				q = p;
				p = p.LeftChild;
				branchMode = 1;
			} else {
				if (p.RightChild == null) {//
					branchMode = 2;
					break;
				}
//				q = p;
				p = p.RightChild;
				branchMode = 2;
			}
		}
		
		if (branchMode == 1) {
//			q.LeftChild = temp;
			p.LeftChild = temp;
		} else {
//			q.RightChild = temp;
			p.RightChild = temp;
		}
		return true;
	}

	boolean delete(int num) {//binary search tree에서 임의 값을 갖는 노드를 찾아 삭제한다.
		//삭제 대상이 leaf node인 경우, non-leaf node로 구분하여 구현한다. 자식이 있으면 non-leaf / 자식이 없으면 leaf
		TreeNode5 p = root, parent = null; //parent : non-leaf q = null 
		int branchMode = 0; // 1은 left, 2는 right
		if (root == null) {
			return false;
		}
		while (p != null) {
			if(num == p.data) {
				//찾았다 > p가 leaf-node / non-leaf 확인 > 1. leaf-node면 isLeafNode 호출, non-leaf면 child가 2. one or 3. two 확인하고
				if(isLeafNode(p)) {
					if (branchMode == 1) {
						parent.LeftChild = null;
						System.out.println("1");
						return true;
				 } else {
						parent.RightChild = null;
						System.out.println("2");
						return true;
					}
				}
				else if (isOneChild(p)) {
					if (branchMode == 1) {
						parent.LeftChild = p.LeftChild;
						System.out.println("3");
						return true;
					} else {
						parent.RightChild = p.RightChild;
						System.out.println("4");
						return true;
					}
				} else {
					TreeNode5 tmp = new TreeNode5();
					tmp = inorderSucc(p);//삭제할 p의 오른쪽 서브트리에서 가장 작은 값
					delete(tmp.data);//복사하고 기존 노드와의 연결 제거
					parent.RightChild = tmp;
					tmp.LeftChild = p.LeftChild; //기존 p의 왼쪽 자식이 tmp의 왼쪽 자식이 되고
					tmp.RightChild= p.RightChild;//기존 p의 오른쪽 자식이 tmp의 오른쪽 자식이 되고
					System.out.println("5");
					return true;
				}
					
//				p = null;
//				return true;
			} else if (num < p.data) {//parent 설정
				parent = p;
				p = p.LeftChild;
				branchMode = 1;
			} else {
				parent = p;
				p = p.RightChild;
				branchMode = 2;
			}
			
		}
		return false;

	}

	boolean search(int num) {//num 값을 binary search tree에서 검색
		TreeNode5 p = root;
		if(root == null) {
			return false;
		}
		while (p != null) {
			if(p.data == num) {
				return true;
			} else if (num < p.data) {
				p = p.LeftChild;
//				return true;
			} else {
				p = p.RightChild;
//				return true;
			}
		}
		return false;
	}
}

public class 정수이진트리 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬출력"), 
		LevelorderPrint("레벨별출력"), StackInorderPrint("스택정렬출력"), 
		PreorderPrint("prefix출력"), PostorderPrint("postfix출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree5 t = new Tree5();//1. heap에 공간 할당 2. 할당된 주소값을 참조변수에게 3. 생성자 함수 body 실행
		Menu menu; // 메뉴
		int count = 10;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 
//				int[] input = new int[count];
//				int r = stdIn.nextInt();
//				t.insert(r);
				t.insert(30);
				t.insert(20);
				t.insert(40);
				t.insert(10);
				t.insert(5);
				t.insert(35);
				t.insert(54);
				t.insert(55);
				t.insert(60);
//				for (int ix = 0; ix < count; ix++) {
//					input[ix] = rand.nextInt(50);
//				}
//				for (int n: input)
//					System.out.print(n + " ");
//				for (int i = 0; i < count; i++) {
//					if (!t.insert(input[i]))
//						System.out.println("Insert Duplicated data");
//				}
				break;

			case Delete: //임의 정수 삭제
				System.out.println("삭제할 데이터:: ");
				num = stdIn.nextInt();
				if (t.delete(num))
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println("삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터:: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case LevelorderPrint: // 
				t.levelOrder();
				System.out.println();
				//t.NonrecInorder();
				break;
			case StackInorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.NonrecInorder();
				break;
			case PreorderPrint://prefix 출력
				t.preorder();
				System.out.println();
				break;
			case PostorderPrint://postfix로 출력
				t.postorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
