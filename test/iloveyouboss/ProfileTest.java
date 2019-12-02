package iloveyouboss;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import iloveyouboss.Profile;

public class ProfileTest {
	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;

	@Before
	public void create() {
		profile = new Profile("Bull Hockey, Inc. ");
		question = new BooleanQuestion(1, "Got Bonus?");
		criteria = new Criteria();
	}
	
	/**
	 * 가중치가 MustMatch인 질문의 답변이 서로 다른 경우
	 */
	@Test
	public void matchAnswerFalseWhenMustMatchCriteriaNotMet() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE),
				Weight.MustMatch));
		
		boolean matches = profile.matches(criteria);
			
		assertFalse(matches);
	}

	/**
	 * 가중치가 DontCare일 때 True를 반환하는지 테스트
	 */
	@Test
	public void matchAnswerTrueForAnyDontCareCriteria() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE),
				Weight.DontCare));
		
		boolean matches = profile.matches(criteria);
		
		assertTrue(matches);
	}

}
