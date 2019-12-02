package iloveyouboss;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import iloveyouboss.Profile;
import iloveyouboss.Question;

public class ProfileTest {

	/**
	 * 가중치가 MustMatch인 질문의 답변이 서로 다른 경우
	 */
	@Test
	public void matchAnswerFalseWhenMustMatchCriteriaNotMet() {
		Profile profile = new Profile("Bull Hockey, Inc.");
		Question question = new BooleanQuestion(1, "Got Bonuses?");
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		criteria.add(criterion);
		
		boolean matches = profile.matches(criteria);
			
		assertFalse(matches);
	}

	/**
	 * 가중치가 DontCare일 때 True를 반환하는지 테스트
	 */
	@Test
	public void matchAnswerTrueForAnyDontCareCriteria() {
		Profile profile = new Profile("Bull Hockey, Inc.");
		Question question = new BooleanQuestion(1, "Got Milk?");
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.DontCare);
		criteria.add(criterion);
		
		boolean matches = profile.matches(criteria);
		
		assertTrue(matches);
	}

}
