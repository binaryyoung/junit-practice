package scratch;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class AssertTest {

	// 요청한 자금을 처리할 수 없을 때 발생할 예외
	class InsufficientFundsException extends RuntimeException {
		public InsufficientFundsException(String message) {
			super(message);
		}
		
		private static final long serialVersionUID = 1L;
	}

	class Account {
		int balance;
		String name;
		
		Account(String name) {
			this.name = name;
		}
		
		// 돈이 제대로 넣어 졌는지 테스트
		void deposit(int dollars) {
			balance += dollars;
		}
		
		// 인출시 예외 일부러 발생 시키고 , 예외 메세지도 잘 받아오는 지 테스트
		void withdraw(int dollars) {
			if (balance < dollars) {
				throw new InsufficientFundsException("balance only 0");
			}
			balance -= dollars;
		}
		
		public String getName() {
			return name;
		}
		
		public int getBalance() {
			return balance;
		}
		
		public boolean hasPositiveBalance() {
			return balance > 0;
		}
	}
	
	class Customer {
		List<Account> accounts = new ArrayList<AssertTest.Account>();
		
		void add(Account account) {
			accounts.add(account);
		}
		
		Iterator<Account> getAccounts() {
			return accounts.iterator();
		}
	}
	
	private Account account;
	
	@Before
	public void createAccount() {
		account = new Account("an account name");
	}
	
	@Test
	public void hasPositiveBalance() {
		// Act
		account.deposit(50);
		
		// Assert
		assertTrue(account.hasPositiveBalance());
	}
	
	@Test
	public void depositIncreasesBalance() {
		// Arrange
		int initialBalance = account.getBalance();
		
		// Act
		account.deposit(100);
		
		// Assert
		// 구체적인 값을 검증해야 될 경우에 이런 단언은 지양하기...
		// assertTrue(account.getBalance() > initialBalance);
		
		// hamcrest의 assertThat을 사용하면 전체적으로 일관성있는 테스트 코드 작성이 가능함
		assertThat(account.getBalance(), equalTo(100));
	}
	
	@Ignore // 기대한 대로 해당 어노테이션이 달린 테스트를 테스트 수행 시 무시함
	@Test
	public void compareArraysFailing() {
		
		// 컬렉션이나 배열이 같은지 검증할 수도 있음.
		assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b"}));
	}

	@Test
	public void matchersFailure() {
		// startWith()은 prefix가 파라미터와 같은지 검증
		assertThat(account.getName(), startsWith("an"));
	}
	
	
}
