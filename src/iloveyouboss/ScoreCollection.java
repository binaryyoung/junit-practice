package iloveyouboss;

import java.util.ArrayList;
import java.util.List;

public class ScoreCollection {
	private List<Scoreable>	scores = new ArrayList<Scoreable>();
	
	public void add(Scoreable scoreable) {
		scores.add(scoreable);
	}
	
	public int arthmeticMean() {
		// mapToInt : IntStream으로 변환
		int total = scores.stream().mapToInt(Scoreable::getScore).sum();
		return total / scores.size();
	}
}
