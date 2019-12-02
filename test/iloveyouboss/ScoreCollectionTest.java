package iloveyouboss;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import iloveyouboss.ScoreCollection;

public class ScoreCollectionTest {

	/**
	 * 두 개의 숫자를 받아 arthmeticMean이 의도한 값을 받아오는지 테스트
	 * 
	 * test는 준비-실행-단언 의 과정으로 진행된다.
	 */
	@Test
	public void answerArthmeticMeanOfTwoNumbers() {
		// 준비 : 실행에 필요한 작업
		ScoreCollection scoreCollection = new ScoreCollection();
		scoreCollection.add(()->5);
		scoreCollection.add(()->7);
		
		// 실행
		int actualResult = scoreCollection.arthmeticMean();
		
		// 단언 : 의도한 결과가 나오는 지 확인
		assertThat(actualResult, equalTo(6));
	}
	
	@Test(expected = ArithmeticException.class)
	public void answerArtmeticMean() {
		ScoreCollection scoreCollection = new ScoreCollection();
		
		int result = scoreCollection.arthmeticMean();
	}

}
