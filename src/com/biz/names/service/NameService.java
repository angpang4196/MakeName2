package com.biz.names.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.biz.names.vo.FullNameVO;

// 파일 2개를 읽어서 조합을 해야 하므로
// 기본적으로 2개의 파일을 읽어 값들을 보관할
// List 2개를 선언 및 초기화 하자.
public class NameService {

	// 새로운 이름을 만들어서 fullNameList를 선언 및 초기화
	List<FullNameVO> fNameList;

	// 파일읽어서 저장할 List 2개
	List<String> firstList; // 성씨 저장
	List<String> sndList; // 이름 저장

	// 파일 2개를 읽기위해서
	// service에서 직접 파일 이름을 지정해도 되지만
	// main() 에서 파일 이름을 매개변수로 전달하도록
	// 디자인하자.

	String fstFile;
	String sndFile;

	public NameService(String fstFile, String sndFile) {
		fNameList = new ArrayList();

		firstList = new ArrayList();
		sndList = new ArrayList();

		this.fstFile = fstFile;
		this.sndFile = sndFile;
	}

	// 한국인성씨.txt 파일에서 성씨리스트를 읽어서 firstList에 저장할 method
	public void readFirstFile() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(fstFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;
				String[] names = reader.split(":");

				String firstName = names[1]; // 한자 포함
				
				// 일부 특수문자는 단독으로 split이 안되는 것들이 있다.
				// 대표적으로 (), ! 문자들은 split이 안 되는데 
				// 이런 문자 앞에 슬래시(\)를 2개 포함하면 => \\
				// split 분해가 가능하다.
				String[] hanNs = firstName.split("\\(");

				firstList.add(hanNs[0]);

			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 이름리스트.txt 파일에서 이름리스트를 읽어서 sndList에 저장할 method
	public void readSndFile() {
		FileReader fr;
		BufferedReader buffer;

		try {
			fr = new FileReader(sndFile);
			buffer = new BufferedReader(fr);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null)
					break;

				sndList.add(reader);

			}
			buffer.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 100개의 새로운 이름을 만드는 데, service에서 개수를 정하지 않고 main()에서 몇개를 만들지 정해주도록 디자인
	public void madeFullName(int nameSize) {

		// fNameList를 비우고 이름을 생성
		fNameList.clear();
		
		int fSize = firstList.size();
		int sSize = sndList.size();

		for (int i = 0; i < nameSize; i++) {
			int fstPos = (int) (Math.random() * (fSize - 1));
			int sndPos = (int) (Math.random() * (sSize - 1));

			String fName = firstList.get(fstPos);
			String sName = sndList.get(sndPos);

			FullNameVO vo = new FullNameVO();

			vo.setStr1stName(fName);
			vo.setStr2ndName(sName);

			String fullName = fName + sName;

			fNameList.add(vo);

		}
	}

	public void saveFile(String saveFile) {
		PrintWriter pw;

		try {
			pw = new PrintWriter(saveFile);

			for (FullNameVO vo : fNameList) {
				String s = vo.getStr1stName() + vo.getStr2ndName();
				pw.println(s);
			}
			pw.close();
			System.out.println("Save Complete");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void viewName(int nameCount) {
		System.out.println("=================================================");
		System.out.println("한국인이름들");
		System.out.println("=================================================");
		System.out.println("이름 개수 : " + nameCount + "개");
		for (int i = 0; i < nameCount; i++) {
			System.out.println(fNameList.get(i).getStr1stName() + fNameList.get(i).getStr2ndName());
		}
	}
}
