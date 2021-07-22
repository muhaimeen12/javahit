package message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BulkSmsUsingHttp {

	public static void main(String[] args) throws Exception {

		// Enter the Details Accordingly (From mobile App)
		String message = "";
		String username = "muhaimeen";
		String password = "123456";
		String address = "http://192.168.43.36"; // Get it from the Mobile App
		String port = "8090";
		String phone = "";
		int phoneNumberCount = 0;
		
		

		Set<String> phoneNumbersSet = new HashSet<String>();
		Scanner input = new Scanner(System.in);

		// Get the No.Of Phone Number Count
		System.out.print("Enter No.of Phone Numbers (Count): ");
		phoneNumberCount = input.nextInt();
		input.nextLine();

		// Get the Phone Numbers
		System.out.println("Enter " + phoneNumberCount + " Phone Numbers:");
		if (phoneNumberCount == 1) {
			phone = input.nextLine();
		} else {
			for (int i = 0; i < phoneNumberCount; i++) {
				phoneNumbersSet.add(input.nextLine());
			}

			for (String str : phoneNumbersSet) {
				phone = phone + "," + str;
			}
		}

		// Get the Message to Send in SMS
		System.out.println("Enter the Message To Send: ");
		message = input.nextLine();
		input.close();

		try {
			URL url = new URL(address + ":" + port + "/SendSMS?username=" + username + "&password=" + password + "&phone=" + phone + "&message=" + URLEncoder.encode(message, "UTF-8"));
			URLConnection connection = url.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = bufferedReader.readLine()) != null) {
				System.out.println(inputLine);
			}
			bufferedReader.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
