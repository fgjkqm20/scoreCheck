package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import view.ScoreCheckView;
import vo.Subject;

public class ScoreCheckController {
	// 필드
	private ScoreCheckView sv;			// 화면 입력, 출력 담당
	
	private String examName;			// 시험 이름
	File saveFile;						// 시험 결과 파일
	
	private ArrayList<Subject> exam;	// 과목 리스트
	private int subjectCount;			// 과목 개수

	// 생성자
	public ScoreCheckController() {
		sv = new ScoreCheckView();
		exam = new ArrayList<Subject>();
	}
	
	// 시작
	public void main() {
		examName = sv.inputName("시험");					// 시험 이름 입력 받기
		saveFile= new File(examName + ".txt");			// 시험 이름으로 파일 생성
		
		subjectCount = sv.inputNumber("과목 개수 : ");		// 과목 수 입력 받기
		
		for(int i=0; i<subjectCount; i++) {		// 과목 수만큼 반복
			exam.add(new Subject());		// 과목 리스트에 과목 추가
			// 각 과목 이름 입력 받기
			exam.get(i).setSubjectName(sv.inputName("\n" +(i+1) + "번째 과목"));
			// 각 과목 문제 수 입력 받기
			exam.get(i).setQuestionCount(sv.inputNumber("문제 개수 : "));
			// 각 문제의 답 입력하기
			exam.get(i).setAnswer(questionSolve("\n문제 풀기", exam.get(i).getAnswer(), exam.get(i).getQuestionCount()));
		}
		
		for(int i=0; i<subjectCount; i++) {
			// 과목 이름 출력
			sv.showSubjectName(exam.get(i).getSubjectName());
			// 각 문제의 정답 입력 하기
			exam.get(i).setCorrectAnswer(questionSolve("정답 입력", exam.get(i).getCorrectAnswer(), exam.get(i).getQuestionCount()));
		}
		
		for(int i=0; i<subjectCount; i++) {		// 과목 수만큼 반복
			sv.showWrongAnswer(exam.get(i));			// 틀린 문제 번호, 입력 한 답, 정답 출력
			sv.showResult(exam.get(i).getResult());		// 맞은 개수, 틀린 개수, 점수 출력
		}
		
		sv.showAverage(getAverage());		// 평균 출력
		save();								// 결과물 파일로 저장
		sv.end();							// Enter 입력 시 종료하기
	}
	
	// 답/정답 입력
	private ArrayList<Integer> questionSolve(String string, ArrayList<Integer> answer, int questionCount) {
		System.out.println(string);
		
		for(int i=0; i<questionCount; i++) {	// 문제 수만큼 반복
			int number = sv.inputNumber((i+1) + "번 : ");		// 답 또는 정답 입력 받기
			
			if(number == -1 && i != 0) {		// -1가 입력되었을 경우
				answer.remove(i-1);	// 이전에 입력 받은 번호 지우기
				i -= 2;
				continue;			// 이전 문제 다시 입력 받기
			}
			
			answer.add(number);		// 답 리스트에 입력 받으 답 추가
		}
		
		return answer;		// 변경된 답 리스트 반환
	}
	
	// 평균 점수 얻기
	private double getAverage() {
		double sum = 0;		// 점수 합계 저장 변수
		for(int i=0; i<subjectCount; i++) {		// 과목 수만큼 반복
			sum += exam.get(i).getScore();		// 과목 별 점수 sum 변수에 더하기
		}
		
		double acerage = sum / subjectCount;	// 평균 점수 저장 변수, 평균 점수 = 점수 합계 / 과목 수
		return Math.round(acerage*100)/100.0;	// 세 자리 수에서 반올리한 평균 점수 반환
	}
	
	// 결과를 파일 저장하기
	private void save() {
		try(Writer writer = new FileWriter(saveFile)) {
			for(int i=0; i<subjectCount; i++) {
				writer.write(exam.get(i).getSubjectName() + "\n");		// 파일에 과목 이름 쓰기
				writer.write("---------------------------------------------\n");
				writer.write("번호\t입력한 답\t\t정답\n");
				
				for(int j=0; j<exam.get(i).getQuestionCount(); j++) {
					// 입력한 답과 정답이 다른 경우 틀린 문제 번호, 입력한 답, 정답 파일에 쓰기
					if(exam.get(i).getAnswer().get(j) != exam.get(i).getCorrectAnswer().get(j)) {
						writer.write((j+1) + "번\t" + 
								exam.get(i).getAnswer().get(j) + "\t\t" + 
								exam.get(i).getCorrectAnswer().get(j) + "\n");
					}
				}
				
				writer.write("---------------------------------------------\n");
				writer.write(exam.get(i).getResult() + "\n\n");		// 파일에 맞은 개수, 틀린 개수, 점수 쓰기 
			}
			
			writer.write("평균 : " + getAverage());		// 파일에 평균 점수 쓰기
			writer.flush();		// 버퍼에 있는 것을 실제 파일에 쓰기
		} catch(IOException e) {	// 결과를 파일로 저장할 수 없을 경우
			sv.noSvae();	// 결과를 파일로 저장할 수 없다는 메시지 출력 
		}
	}
}
