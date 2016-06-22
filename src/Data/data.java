package Data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import DBConn.MySqlconn;

public class data {
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			Map<String, List<String>> map = connection.getHeaderFields();
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("鍙戦�丟ET璇锋眰鍑虹幇寮傚父锛�" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(result);
		result = result.replace(" ", "_").replace("{", "").replace("}", "").replace(",", " ").replace("'", "");

		return result;
	}

	public static void main(String[] strs) {
		//hello
		while (true) {
			String s = sendGet("http://www.myqx.gov.cn/api/", "mod=Weather&action=GetLastSk");
			System.out.println(s);
			String[] rec = s.split(" ");
			singleRec sig = new singleRec();
			sig.setWd(Double.valueOf(rec[0].substring(rec[0].indexOf(':') + 1)));
			sig.setShidu(rec[1].substring(rec[1].indexOf(':') + 1));
			sig.setY1(Double.valueOf(rec[2].substring(rec[2].indexOf(':') + 1)));
			sig.setFx(Double.valueOf(rec[3].substring(rec[3].indexOf(':') + 1)));
			sig.setFxname(rec[4].substring(rec[4].indexOf(':') + 1));
			sig.setFxcode(rec[5].substring(rec[5].indexOf(':') + 1));
			sig.setFsname(rec[6].substring(rec[6].indexOf(':') + 1));
			sig.setFs(Integer.valueOf(rec[7].substring(rec[7].indexOf(':') + 1)));
			sig.setSj(rec[8].substring(rec[8].indexOf(':') + 1));
			MySqlconn mcn = new MySqlconn();
			try {
				mcn.insert(sig);
				Thread.sleep(3500000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
