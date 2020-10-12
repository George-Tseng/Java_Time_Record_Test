package javaTest;

import java.util.Scanner;
import java.util.Timer;

public class Java_Time_Record_Test {
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String timeNow;
		Timer tr0 = new Timer();

		while(true) {
			/*初始狀態-設定檔及暫存檔均不存在*/
			if (!File_Conf.checkConfFile() && !File_Conf.checkTmpFile()) {
				System.out.println("這似乎是本程式第一次被啟動，將開始執行初始化作業");

				/*紀錄啟動時的時間*/
				timeNow = Get_Date.getDateNow();

				/*嘗試建立設定檔*/
				if(!File_Conf.createConfFile().equals("")){
					System.out.println("設定檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法*/
				else{
					File_Conf.writeConfFile(timeNow);
				}

				/*嘗試建立暫存檔*/
				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法，立即執行第一次，之後每過一秒執行一次*/
				else{
					tr0.schedule(new File_Timer(), 0, 1000);
				}

				/*設定JVM關閉後自動刪除暫存檔*/
				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*上次使用時按正常流程關閉*/
			else if (File_Conf.checkConfFile() && !File_Conf.checkTmpFile()) {

				/*讀取設定檔中的所有紀錄*/
				String allLastTime = File_Conf.readConfFile();
				/*用換行拆分資料*/
				String [] recordSpace = allLastTime.split("\n");
				/*最後一筆為上次啟動時間*/
				String lastTime = recordSpace[recordSpace.length - 1];

				System.out.println("上次啟動時間為："+lastTime);

				/*紀錄啟動時的時間*/
				timeNow = Get_Date.getDateNow();
				/*在原有紀錄中加入新的一筆*/
				String allTime = allLastTime + "\n" + timeNow;

				/*將所有紀錄重新寫入設定檔*/
				File_Conf.writeConfFile(allTime);

				/*嘗試建立暫存檔*/
				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法，立即執行第一次，之後每過一秒執行一次*/
				else{
					tr0.schedule(new File_Timer(), 0, 1000);
				}

				/*設定JVM關閉後自動刪除暫存檔*/
				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*上次使用時未依正常流程關閉*/
			else if (File_Conf.checkConfFile() && File_Conf.checkTmpFile()) {
				System.out.println("程式似乎並未正常地被關閉...");

				/*讀取設定檔中的所有紀錄*/
				String allLastTime = File_Conf.readConfFile();
				/*用換行拆分資料*/
				String [] recordSpace = allLastTime.split("\n");
				/*最後一筆為上次啟動時間*/
				String lastTime = recordSpace[recordSpace.length - 1];

				System.out.println("上次啟動時間為："+lastTime);

				/*紀錄啟動時的時間*/
				timeNow = Get_Date.getDateNow();
				/*在原有紀錄中加入新的一筆*/
				String allTime = allLastTime + "\n" + timeNow;

				/*將所有紀錄重新寫入設定檔*/
				File_Conf.writeConfFile(allTime);

				/*嘗試立即刪除暫存檔*/
				if(!File_Conf.deleteTmpFileNow().equals("")){
					System.out.println("上次執行時的暫存檔清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				/*嘗試建立暫存檔*/
				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法，立即執行第一次，之後每過一秒執行一次*/
				else{
					tr0.schedule(new File_Timer(), 0, 1000);
				}

				/*設定JVM關閉後自動刪除暫存檔*/
				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}
			/*異常*/
			else {
				System.out.println("程式檔案有部分損毀或遺失...");

				/*讀取暫存檔的紀錄作為上次啟動時間*/
				String lastTime = File_Conf.readTmpFile();
				System.out.println("最後紀錄時間為："+lastTime);

				/*紀錄啟動時的時間*/
				timeNow = Get_Date.getDateNow();
				/*在原有紀錄中加入新的一筆*/
				String allTime = lastTime + "\n" + timeNow;

				/*嘗試建立設定檔*/
				if(!File_Conf.createConfFile().equals("")){
					System.out.println("設定檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法*/
				else{
					File_Conf.writeConfFile(allTime);
				}

				/*嘗試立刻刪除暫存檔*/
				if(!File_Conf.deleteTmpFileNow().equals("")){
					System.out.println("上次執行時的暫存檔清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				/*嘗試建立暫存檔*/
				if(!File_Conf.createTmpFile().equals("")){
					System.out.println("暫存檔建立失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}
				/*成功才執行寫入方法，立即執行第一次，之後每過一秒執行一次*/
				else{
					tr0.schedule(new File_Timer(), 0, 1000);
				}

				/*設定JVM關閉後自動刪除暫存檔*/
				if(!File_Conf.deleteTmpFile().equals("")){
					System.out.println("暫存檔排定清除失敗...\n錯誤資訊為：");
					System.out.println(File_Conf.createConfFile());
				}

				System.out.println("目前時間為："+timeNow);
			}

			System.out.print("是否要離開本程式了？輸入「exit」即可離開：");
			String exitKey = scan.nextLine();
			if(exitKey.toLowerCase().equals("exit")){
				break;
			}
		}

		System.out.println("本程式已終止");
		/*關閉Scanner*/
		scan.close();
		/*中止Timer*/
		tr0.cancel();
	}
}
