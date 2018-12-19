package com.biz.names.exec;

import java.util.Scanner;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.biz.names.service.NameService;

public class NameExec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fstFile = "src/com/biz/names/한국인성씨.txt";
		String sndFile = "src/com/biz/names/이름리스트.txt";
		String saveFile = "src/com/biz/names/exec/fullNameList.txt";

		NameService ns = new NameService(fstFile, sndFile);

		Scanner scan = new Scanner(System.in);

		ns.readFirstFile();
		ns.readSndFile();

		int intNameCount = 100; // 보고싶은 이름 개수

		while (true) {
			System.out.println("=================================================");
			System.out.println("( 0 : 종료 , 1 : 화면 출력, 2: 파일 저장)");
			System.out.println("=================================================");
			System.out.println("입력란 : ");
			String strIn = scan.nextLine();

			int intIn = Integer.valueOf(strIn);

			ns.madeFullName(intNameCount);

			if (intIn == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			if (intIn == 1) {
				ns.viewName(intNameCount);

			}
			if (intIn == 2) {
				ns.saveFile(saveFile);

			}
		}
	}
}
