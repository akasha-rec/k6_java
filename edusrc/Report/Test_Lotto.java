package Report; // 과제4 2월 28일까지
//구현과제로 실습
//hashset을 arraylist로 변환하여 정렬하기 구현 hash함수를 사용해서 집합을 만듦 > 중복이 없어

/*
 * 로또 당첨 규칙:
 * 꽝: 번호 2개, 1개
 * 5등: 번호 3개 - 5000원
 * 4등: 번호 4개 - 5만원
 * 3등: 번호 5개 - 150만원 - 판매금액에 변동(판매금액의 12.5%)
 * 2등: 3등번호 + 보너스번호 - 3000만원 - 판매 금액에 변동, 당첨 확률: 1,300,000분의1
 * 1등: 6개 숫자 - 당첨 확률 8,000,000 분의 1, 10억 ~ 30억
 */
//hashset을 arraylist로 변환하여 정렬하기 구현
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
//class ListComparator implements Comparator<List<Integer>>{//List 2개 비교... > 보지 마라
//	@Override
//	public int compare(List<Integer> l1, List<Integer> l2) {
//		Iterator<Integer> ait = l1.iterator();
//		Iterator<Integer> bit = l2.iterator();
//		while (ait.hasNext()) {
//			int anum = ait.next();int bnum = bit.next();
//			if ( anum > bnum) return 1;
//			else if (anum < bnum) return -1;
//			
//		}
//		return 0;
//	}
//}
public class Test_Lotto {

	public static void main(String[] args) {
		lotto_generator(10);
	}

	public static void lotto_generator(int n) {
		Random rnd = new Random();
		List<HashSet<Integer>> lottoSet = new ArrayList<>();//복권 여러장 = 복권들의 집합
		HashSet<Integer> lotto = null;//HashSet<Integer> - 복권 1장, 복권들의 번호가 중복되도 상관X
//		List<List<Integer>> al = new ArrayList<>();
		/*
		 * [[35, 40, 27, 29, 14, 31, 15], [0, 1, 18, 38, 6, 24, 29], 중복 X
		 *  [16, 32, 0, 18, 34, 22, 14], [32, 34, 40, 9, 12, 28, 14],
		 *  [0, 19, 6, 9, 42, 29, 13], [2, 3, 37, 43, 44, 29, 14],
		 *  [33, 1, 17, 22, 6, 8, 12], [34, 21, 9, 10, 27, 44, 45], [18, 34, 23, 9, 28, 29, 15],
		 *  [32, 6, 23, 24, 10, 27, 43]]
		 */
		for (int i = 0; i < n; i++) {
			lotto = new HashSet<>(); //각 로또 번호 생성을 위해 HashSet 초기화
			//구현할 부분
			for (int j = 0; lotto.size() < 6; j++) {
				lotto.add(rnd.nextInt(45)+1); // 1~45 사이의 숫자 출력 < rnd.nextInt(n)은 0~n미만까지의 범위라서
			}
			lottoSet.add(lotto); //생성된 로또 번호를 리스트에 추가
		}
		
		System.out.println("\nlot hashset을 출력\n");
		for (HashSet<Integer> eachLotto : lottoSet) {
			System.out.println(eachLotto);
			/*
			 * [33  1 17 22  6  8]
			 *  [11  1 18 38  6 24]
			 */
			//구현할 부분
		}
		//System.out.println("복권 정렬전::lot = " + al);
		
		//al.sort(new ListComparator());
		//System.out.println("복권 정렬후::lot = " + al);
		//hashset의 리스트를 정렬하는 알고리즘 개발
		//hashset를 arrayList로 변환
		
		//당첨번호 추첨
		HashSet<Integer> win = new HashSet<>(); //중복되지 않는 당첨번호를 Hashset<Integer>win에 저장
		for (int j = 0; win.size() < 6; j++) {//6개 번호 win은 HashSet이라서 중복X
			win.add(rnd.nextInt(45)+1); //rnd.nextInt(n)은 0~n미만의 정수형이라서 1~45 사이를 출력하기 위해 표현
		}
		
		int bonus = rnd.nextInt(45)+1;
		System.out.print("당첨번호: " + win + "보너스 번호: " + bonus);//6개의 당첨번호와 보너스번호
		// 6개를 맞힌 복권을 찾는다 
		System.out.println();
		winnerLotto(win, bonus, lottoSet);//1등을 찾는다
		
	}
	static void winnerLotto(HashSet<Integer> w, int bonus, List<HashSet<Integer>> lottoSet) {
        for (int i = 0; i < lottoSet.size(); i++) {
            checkWinner(w, bonus, new ArrayList<>(lottoSet.get(i)));
        }
    }

    static void checkWinner(HashSet<Integer> w, int bonus, List<Integer> elem) {
        List<Integer> L = new ArrayList<>(w);
        int count = 0;

        for (int i = 0; i < L.size() - 1; i++) {
            if (elem.contains(L.get(i))) {
                count++;
            }
        }
        switch (count) {
            case 0:
            case 1:
            case 2:
                System.out.println("꽝");
                break;
            case 3:
                System.out.println("5등 복권 " + elem + " 당첨번호:" + w);
                break;
            case 4:
                System.out.println("4등 복권 " + elem + " 당첨번호:" + w);
                break;
            case 5:
		//if (L.get(6).equals(elem.get(6))) { //보너스 번호가 일치하는 지를 조사 
		if (elem.contains(bonus)) {
                    System.out.println("2등 복권 " + elem + " 당첨번호:" + w + " 보너스 번호 일치");
                } else {
                    System.out.println("3등 복권 " + elem + " 당첨번호:" + w);
                }
                break;
            case 6:
                System.out.println("1등 복권 " + elem + " 당첨번호:" + w);
                break;
		}


	}
}
