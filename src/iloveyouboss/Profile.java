package iloveyouboss;

import java.util.HashMap;
import java.util.Map;

public class Profile {
	private Map<String, Answer> answers = new HashMap<String, Answer>();
	private int score;
	private String name;
	
	public Profile(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	// 질문 , answer객체
	public void add(Answer answer) {
		answers.put(answer.getQuestionText(), answer);
	}
	
	public boolean matches(Criteria criteria) {
		score = 0;
		
		boolean kill = false;
		boolean anyMatches =false;
		for (Criterion criterion: criteria) {
			Answer answer = answers.get(
					criterion.getAnswer().getQuestionText());
			boolean match =
					criterion.getWeight() == Weight.DontCare ||
					answer.match(criterion.getAnswer());
			
			// 중요도가 가장 높은 질문이 서로 일치하지 않으면 kill
			if (!match && criterion.getWeight() == Weight.MustMatch) {
				kill = true;
			}
			
			// 동일한 질문에 대해 중요도가 낮거나 대답이 일치하면 점수를 가산한다.
			if (match) {
				score += criterion.getWeight().getValue();
			}
			// | 는 or 연산
			// anyMatches = anyMatches | match
			anyMatches |= match;
		}
		if (kill)
			return false;
		return anyMatches;
	}
	
	public int score() {
		return score;
	}
}
 