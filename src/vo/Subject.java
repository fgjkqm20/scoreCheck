package vo;

import java.util.ArrayList;

public class Subject {
	// 필드
	private String subjectName;					// 과목 이름
	private int questionCount;					// 문제 수
	private ArrayList<Integer> answer;			// 답 
	private ArrayList<Integer> correctAnswer;	// 정답
	private int wrongAnswerNumber;				// 틀린 문제 수
	
	// 생성자
	public Subject() {
		answer = new ArrayList<Integer>();
		correctAnswer = new ArrayList<Integer>();
	}
	
	// getter, setter
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(int questionCount) {
		this.questionCount = questionCount;
	}
	public ArrayList<Integer> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Integer> answer) {
		this.answer = answer;
	}
	public ArrayList<Integer> getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(ArrayList<Integer> correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	// 틀린 문제 수 증가
	public void addWrongAnswerNumber() {
		wrongAnswerNumber++;
	}
	// 점수 얻기
	public double getScore() {
		// 점수 = 100 - (한 문제당 점수) * 틀린 개수
		// 한 문제당 점수 = 100 / 시험 문제 수
		double score = 100 - (100.0/questionCount) * wrongAnswerNumber;
		// 3자리 수에서 반올림 한 점수 값 반환
		return Math.round(score*100)/100.0;
	}
	// 결과 얻기
	public String getResult() {
		String result = "맞은 개수 : " + (questionCount-wrongAnswerNumber) + ", ";
		result += "틀린 개수 : " + wrongAnswerNumber + ", ";
		result += "점수 : " + getScore();
		
		return result;
	}
}
