package javaTest;

import java.util.Scanner;

public class Java_Time_Record_Test {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String timeNow;

		while(true) {
			/*初始狀態-設定檔及暫存檔均不存在*/
			if (!File_Conf.checkConfFile() && !File_Conf.checkTmpFile()) {
				System.out.println("這似乎是本程式第一次被啟動，將開始執行初始化作業");

				timeNow = Get_Date.getDateNow();

				if(!File_Conf.createConfFile().equals("")){
					System.out.println("設定檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeConfFile(timeNow);
				}

				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeTmpFile(timeNow);
				}

				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*上次使用時按正常流程關閉*/
			else if (File_Conf.checkConfFile() && !File_Conf.checkTmpFile()) {

				String lastTime = File_Conf.readConfFile();
				System.out.println("上次啟動時間為："+lastTime);

				timeNow = Get_Date.getDateNow();

				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeTmpFile(timeNow);
				}

				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*上次使用時未依正常流程關閉*/
			else if (File_Conf.checkConfFile() && File_Conf.checkTmpFile()) {
				System.out.println("程式似乎並未正常地被關閉...");

				String lastTime = File_Conf.readConfFile();
				System.out.println("上次啟動時間為："+lastTime);

				timeNow = Get_Date.getDateNow();

				if(!File_Conf.deleteTmpFileNow().equals("")){
					System.out.println("上次執行時的暫存檔清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeTmpFile(timeNow);
				}

				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*異常*/
			else {
				System.out.println("程式檔案有部分損毀或遺失...");

				String lastTime = File_Conf.readTmpFile();
				System.out.println("上次啟動時間為："+lastTime);

				timeNow = Get_Date.getDateNow();

				if(!File_Conf.createConfFile().equals("")){
					System.out.println("設定檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeConfFile(timeNow);
				}

				if(!File_Conf.deleteTmpFileNow().equals("")){
					System.out.println("上次執行時的暫存檔清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				else{
					File_Conf.writeTmpFile(timeNow);
				}

				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}

			System.out.println("是否要離開本程式了？輸入「exit」即可離開");
			String exitKey = scan.nextLine();
			if(exitKey.toLowerCase().equals("exit")){
				break;
			}
		}

		System.out.println("本程式已終止");
		scan.close();
	}
}
