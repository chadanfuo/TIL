package com.programming.ch06.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

/**
 * @apiNote ch06. page383 문6
 * @Company : KAONI
 * @Author  : 이동재
 * @Date    : 2020. 5. 21.
 * @Version :
 */
public class ElapsedTimeGame {
	
	private BufferedReader br;
	private int intPlayerOne;
	private int intPlayerTwo;
	
	// 경과시간을 맞추는 게임입니다.
	// Enter을 누르면 현재시간을 보여주며, 10초에 가까운 사람이 이기는 게임입니다.(2인)
	// ex) player1 시작 <Enter키>
	// 현재 초 시간 = 42
	// 10초 예상 <Enter키>
	// 현재 초 시간 = 50
	
	public ElapsedTimeGame() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static void main(String[] args) {
		new ElapsedTimeGame().start();
	}
	
	public void start() {
		System.out.println("*** ElapsedTimeGame Start ***");
		System.out.println("10초에 가까운 사람이 이기는 게임입니다.");
		playGame();
		System.out.println("*** 게임을 종료합니다. ***");
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void playGame() {
		System.out.println("player1 <Enter>를 누르세요");
		this.intPlayerOne = pressDownEnter();
		System.out.println("player1 10초를 예상하고 <Enter>를 누르세요");
		this.intPlayerOne = resultSecondsGap(intPlayerOne, pressDownEnter());
		System.out.println("player2 <Enter>를 누르세요");
		this.intPlayerTwo = pressDownEnter();
		System.out.println("player2 10초를 예상하고 <Enter>를 누르세요");
		this.intPlayerTwo = resultSecondsGap(intPlayerTwo, pressDownEnter());
		showWinner(Math.abs(intPlayerOne-10), Math.abs(intPlayerTwo-10));
	}
	
	/**
	 * Desc : Enter를 대기합니다. 누를 시 현재 초를 반환해 주는 메서드입니다.
	 * @Method Name : pressDownEnter
	 * @param 
	 * @return int
	 * @throws
	 */
	public int pressDownEnter() {
		try {
			br.readLine();
		} catch (IOException e) {
		}
		int currentSeconds = new Date().getSeconds();
		System.out.println("현재 초 : " + currentSeconds);
		return currentSeconds;
	}
	
	/**
	 * Desc : Game의 결과를 비교해주는 메서드 입니다.
	 * @Method Name : showWinner
	 * @param intPlayerOne, intPlayerTwo
	 * @return int
	 * @throws
	 */
	public void showWinner(int intPlayerOne, int intPlayerTwo) {
		System.out.print("player1의 결과 : " + this.intPlayerOne + "초, ");
		System.out.println("player2의 결과 : " + this.intPlayerTwo + "초");
		if (intPlayerOne < intPlayerTwo) {
			System.out.println("*** player1 Winner!!! ***");
		} else if (intPlayerOne == intPlayerTwo) {
			System.out.println("*** Draw ***");
		} else {
			System.out.println("*** player2 Winner!!! ***");
		}
	}
	
	/**
	 * Desc : 첫번째 시간과 두번째 시간의 갭차이를 계산하여 주는 메서드입니다.
	 * @Method Name : resultSecondsGap
	 * @param firstSeconds, lastSeconds
	 * @return int
	 * @throws
	 */
	public int resultSecondsGap(int firstSeconds, int lastSeconds) {
		if (firstSeconds <= lastSeconds) {
			return lastSeconds - firstSeconds;
		} else {
			return (60-firstSeconds + lastSeconds);
		}
	}
	

}
