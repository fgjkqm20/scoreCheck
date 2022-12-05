package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import vo.Subject;

public class ScoreCheckView {
	// 필드
	private Scanner scanner;	// 콘솔 입력 받기

	// 셍성자
	public ScoreCheckView() {
		scanner = new Scanner(System.in);
	}

	// 이름 입력 받기
	public String inputName(String string) {		
		System.out.print(string + " 이름 : ");
		String name = scanner.nextLine();
		
		return name;
	}

	// 숫자 입력 받기
	public int inputNumber(String string) {
		int number = 0;		// 숫자 입력 받을 변수
		
		while(true) {	// 숫자를 입력 받을 때까지 무한 반복
			try {
				System.out.print(string);	// string에 입력된 문자열 출력
				number = scanner.nextInt();	// 숫자 입력 받기
				scanner.nextLine();			// 버퍼 비우기
			} catch(InputMismatchException e) {		// 문자를 입력했을 경우
				scanner.nextLine();	// 버퍼 비우기, 다시 입력 받기
			}
			if(number >= -1) {	// -1 이상 입력 했을 때만 무한 반복문 탈출
				break;
			}
		}
		
		return number;		// 입력 받은 숫자 반환하기
	}
	
	public void showSubjectName(String subjectName) {		// 과목 이름 출력
		System.out.println("\n" + subjectName);
	}

	// 틀린 문제 번호, 입력 한 답, 정답 출력
	public void showWrongAnswer(Subject subject) {
		System.out.println("\n" + subject.getSubjectName());
		System.out.println("---------------------------------------------");
		System.out.println("번호\t입력한 답\t정답");
		for(int i=0; i<subject.getQuestionCount(); i++) {
			// 입력한 답과 정답이 다른 경우 틀린 문제 번호, 입력한 답, 정답 출력
			if(subject.getAnswer().get(i) != subject.getCorrectAnswer().get(i)) {
				System.out.printf("%d번\t%d\t\t%d\n", i+1, 
						subject.getAnswer().get(i),
						subject.getCorrectAnswer().get(i));
				subject.addWrongAnswerNumber();		// 틀린 문제 수 증가
			}			
		}
		System.out.println("---------------------------------------------");
	}
	
	// 틀린 문제 번호, 입력 한 답, 정답 출력
	public void showResult(String result) {
		System.out.println(result);
	}
	
	// 평균 점수 출력
	public void showAverage(double average) {
		System.out.println("\n평균 : " + average);
	}
	
	// 결과를 파일로 저장할 수 없을 때 출력
	public void noSvae() {
		System.out.println("\n결과를 파일로 저장할 수 없습니다.");
	}
	
	// 종료 전 출력
	public void end() {
		System.out.print("\n종료 -> Enter");
		scanner.nextLine();				// 사용자가 Enter 누르면 종료
	}
}
