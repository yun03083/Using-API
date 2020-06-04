package android.cs.pusan.ac.apipractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)findViewById(R.id.result);

        System.out.println("나오나?");
        boolean initem = false;
        boolean DataTime = false, Pm10Value = false, Pm10Value24 = false, Pm25Value = false;
        boolean Pm25Value24 = false, Pm10Grade = false, Pm25Grade = false, Pm10Grade1h = false;
        boolean Pm25Grade1h = false;

        String dataTime = null, pm10Value = null, pm10Value24 = null, pm25Value = null;
        String pm25Value24 = null, pm10Grade = null, pm25Grade = null, pm10Grade1h = null;
        String pm25Grade1h = null;

        try {
            URL url = new URL("http://openapi.airkorea.or.kr/openapi/services/rest/" +
                        "+ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?" +
                        "+stationName=%ED%95%99%EC%9E%A5%EB%8F%99&dataTerm=month&pageNo=1&numOfRows=10&ServiceKey=" +
                        "+W%2FIZgDCgC%2FSr9cWTAbtUkoV3A%2Bv1Xg2430j86PDLrqR4if7HEIYcQVGix0dJmMaSGTeuqUxNCeZntb%2Bmeq%2FnMw%3D%3D&ver=1.3");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);
            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("dataTime")) { //태그 있으면 내용을 받을수 있게 하자
                            DataTime = true;
                        }
                        if (parser.getName().equals("pm10Value")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm10Value = true;
                        }
                        if (parser.getName().equals("pm10Value24")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm10Value24 = true;
                        }
                        if (parser.getName().equals("pm25Value")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm25Value = true;
                        }
                        if (parser.getName().equals("pm25Value24")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm25Value24 = true;
                        }
                        if (parser.getName().equals("pm10Grade")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm10Grade = true;
                        }
                        if (parser.getName().equals("pm25Grade")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm25Grade = true;
                        }
                        if (parser.getName().equals("pm10Grade1h")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm10Grade1h = true;
                        }
                        if (parser.getName().equals("lat")) { //태그 있으면 내용을 받을수 있게 하자
                            Pm25Grade1h = true;
                        }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (DataTime) { //true일 때 태그의 내용을 저장.
                            dataTime = parser.getText();
                            DataTime = false;
                        }
                        if (Pm10Value) { //true일 때 태그의 내용을 저장.
                            pm10Value = parser.getText();
                            Pm10Value = false;
                        }
                        if (Pm10Value24) { //true일 때 태그의 내용을 저장.
                            pm10Value24 = parser.getText();
                            Pm10Value24 = false;
                        }
                        if (Pm25Value) { //true일 때 태그의 내용을 저장.
                            pm25Value = parser.getText();
                            Pm25Value = false;
                        }
                        if (Pm25Value24) { //true일 때 태그의 내용을 저장.
                            pm25Value24 = parser.getText();
                            Pm25Value24 = false;
                        }
                        if (Pm10Grade) { //true일 때 태그의 내용을 저장.
                            pm10Grade = parser.getText();
                            Pm10Grade = false;
                        }
                        if (Pm25Grade) { //true일 때 태그의 내용을 저장.
                            pm25Grade = parser.getText();
                            Pm25Grade = false;
                        }
                        if (Pm10Grade1h) { //true일 때 태그의 내용을 저장.
                            pm10Grade1h = parser.getText();
                            Pm10Grade1h = false;
                        }
                        if (Pm25Grade1h) { //isMapy이 true일 때 태그의 내용을 저장.
                            pm25Grade1h = parser.getText();
                            Pm25Grade1h = false;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            status1.setText(status1.getText() + "측정시간 : " + dataTime + " \n"
                                    + "현재 미세먼지(10pm)값 : " + pm10Value + "\n"
                                    + "현재 미세먼지(2.5pm)값 : " + pm25Value + "\n"
                                    + "24시간 미세먼지(10pm)값 : " + pm10Value24 + "\n"
                                    + "24시간 미세먼지(2.5pm)값 : " + pm25Value24 + "\n"
                                    + "현재 미세먼지(10pm) 등급 : " + pm10Grade1h + "\n"
                                    + "현재 미세먼지(2.5pm) 등급 : " + pm25Grade1h + "\n"
                                    + "24시간 미세먼지(10pm) 등급 : " + pm10Grade + "\n"
                                    + "24시간 미세먼지(2.5pm) 등급 : " + pm25Grade + "\n");

                            initem = false;
                        }
                        break;

                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            status1.setText("에러 발생");
        }
    }
}
